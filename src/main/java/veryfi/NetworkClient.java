package veryfi;

import org.json.JSONObject;
import veryfi.enums.HttpMethod;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

import static veryfi.Constants.*;
import static veryfi.Constants.SHA256;

abstract public class NetworkClient {
    private Credentials credentials;
    private HttpClient httpClient;
    private int timeOut = 120;
    private String baseUrl = "https://api.veryfi.com/api/";
    private int apiVersion = 8;
    protected final Logger logger = Logger.getLogger("ClientImpl");

    /**
     * Creates an instance of {@link NetworkClient}.
     *
     * @param credentials  the {@link Credentials} provided by Veryfi.
     * @param apiVersion   the {@link int} api version to use Veryfi.
     */
    public NetworkClient(Credentials credentials, int apiVersion) {
        this.credentials = credentials;
        this.apiVersion = apiVersion;
        this.httpClient = HttpClient.newHttpClient();
    }

    /**
     * Creates an instance of {@link NetworkClient}.
     *
     * @param credentials  the {@link Credentials} provided by Veryfi.
     * @param apiVersion   the {@link int} api version to use Veryfi.
     * @param httpClient   {@link HttpClient} for the Veryfi API
     */
    public NetworkClient(Credentials credentials, int apiVersion, HttpClient httpClient) {
        this.credentials = credentials;
        this.apiVersion = apiVersion;
        this.httpClient = httpClient;
    }

    /**
     * Returns a {@link String} ApiKey for the headers of the request.
     * @return the ApiKey {@link String}
     */
    private String getApiKey() {
        return "apikey " + credentials.username + ":" + credentials.apiKey;
    }

    /**
     * Returns a {@link String} API Base URL with API Version.
     * @return the url {@link String}
     */
    private String getUrl() {
        return baseUrl + "v" + apiVersion;
    }

    /**
     * Submit the HTTP request.
     *
     * @param httpVerb         HTTP Method
     * @param endpointName     Endpoint name such as 'documents', 'users', etc.
     * @param requestArguments JSON payload to send to Veryfi
     * @return A JSON of the response data.
     */
    protected String request(HttpMethod httpVerb, String endpointName, JSONObject requestArguments) {
        HttpRequest request = getHttpRequest(httpVerb, endpointName, requestArguments);
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.headers() != null) {
                Optional<String> traceId = response.headers().firstValue("x-veryfi-trace-id");
                traceId.ifPresent(s -> logger.info("x-veryfi-trace-id: " + s));
            }
            return response.body();
        } catch (Exception e) {
            logger.severe("request: " + e.getMessage());
            return "";
        }
    }

    /**
     * Submit the HTTP request.
     *
     * @param httpVerb         HTTP Method
     * @param endpointName     Endpoint name such as 'documents', 'users', etc.
     * @param requestArguments JSON payload to send to Veryfi
     * @return A JSON of the response data.
     */
    protected CompletableFuture<String> requestAsync(HttpMethod httpVerb, String endpointName,
                                                   JSONObject requestArguments) {
        HttpRequest request = getHttpRequest(httpVerb, endpointName, requestArguments);
        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body);
    }

    /**
     * Creates the HTTP request Object.
     *
     * @param httpVerb         HTTP Method
     * @param endpointName     Endpoint name such as 'documents', 'users', etc.
     * @param requestArguments JSON payload to send to Veryfi
     * @return request Object for the HttpClient {@link HttpRequest}
     */
    private HttpRequest getHttpRequest(HttpMethod httpVerb, String endpointName, JSONObject requestArguments) {
        HttpRequest request;
        String apiUrl = getUrl() + endpointName;
        List<String> headers = getHeaders(requestArguments);

        switch (httpVerb) {
            case DELETE -> request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .timeout(Duration.ofSeconds(timeOut))
                    .headers(headers.toArray(new String[0]))
                    .DELETE()
                    .build();
            case POST -> request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .timeout(Duration.ofSeconds(timeOut))
                    .headers(headers.toArray(new String[0]))
                    .POST(HttpRequest.BodyPublishers.ofString(requestArguments.toString()))
                    .build();
            case PUT -> request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .timeout(Duration.ofSeconds(timeOut))
                    .headers(headers.toArray(new String[0]))
                    .PUT(HttpRequest.BodyPublishers.ofString(requestArguments.toString()))
                    .build();
            default -> request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .timeout(Duration.ofSeconds(timeOut))
                    .headers(headers.toArray(new String[0]))
                    .GET()
                    .build();
        }
        return request;
    }

    /**
     * Prepares the headers needed for a request.
     *
     * @param requestArguments JSON payload to send to Veryfi {@link JSONObject}
     * @return List of the headers {@link List<String>}
     */
    private List<String> getHeaders(JSONObject requestArguments) {
        Date date = new Date();
        long timeStamp = date.getTime();
        List<String> headers = new ArrayList<>();
        JSONObject jsonHeaders = new JSONObject();
        jsonHeaders.put(USER_AGENT, USER_AGENT_JAVA);
        jsonHeaders.put(ACCEPT, APPLICATION_JSON);
        jsonHeaders.put(CONTENT_TYPE, APPLICATION_JSON);
        jsonHeaders.put(CLIENT_ID, credentials.clientId);
        jsonHeaders.put(AUTHORIZATION, getApiKey());
        jsonHeaders.put(X_VERYFI_REQUEST_TIMESTAMP, String.valueOf(timeStamp));
        jsonHeaders.put(X_VERYFI_REQUEST_SIGNATURE, generateSignature(timeStamp, requestArguments));
        for (String key : JSONObject.getNames(jsonHeaders)) {
            headers.add(key);
            headers.add(jsonHeaders.getString(key));
        }
        return headers;
    }

    /**
     * Generate unique signature for payload params.
     *
     * @param timeStamp     Unix Long timestamp
     * @param payloadParams JSON params to be sent to API request
     * @return Unique signature generated using the client_secret and the payload
     */
    private String generateSignature(Long timeStamp, JSONObject payloadParams) {
        JSONObject jsonPayload = new JSONObject(payloadParams.toString());
        jsonPayload.put(TIMESTAMP, Long.toString(timeStamp));
        String payload = jsonPayload.toString();
        byte[] secretBytes = credentials.clientSecret.getBytes(StandardCharsets.UTF_8);
        byte[] payloadBytes = payload.getBytes(StandardCharsets.UTF_8);
        SecretKeySpec keySpec = new SecretKeySpec(secretBytes, SHA256);

        Mac mac;
        try {
            mac = Mac.getInstance(SHA256);
        } catch (NoSuchAlgorithmException e) {
            return e.getMessage();
        }
        try {
            mac.init(keySpec);
        } catch (InvalidKeyException e) {
            return e.getMessage();
        }
        byte[] macBytes = mac.doFinal(payloadBytes);
        String base64SignatureEncoded;
        base64SignatureEncoded = Base64.getEncoder().encodeToString(macBytes);
        return base64SignatureEncoded;
    }

    /**
     * Define new time out for the requests in seconds
     * @param timeOut of the http requests in seconds
     */
    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    /**
     * By default, the base URL is https://api.veryfi.com/api/;
     * @param baseUrl for the Veryfi API
     */
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    /**
     * By default is https://api.veryfi.com/api/;
     * @param httpClient {@link HttpClient} for the Veryfi API
     */
    public void setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * Creates the JSON Object for the parameters of the request
     *
     * @param fileName              Name of the file to upload to the Veryfi API
     * @param fileData              Base64 encoded file data
     * @param parameters            Additional request parameters
     * @return the JSON object of the parameters of the request
     */
    protected JSONObject addFileToParameters(String fileName, String fileData, JSONObject parameters) {
        if (parameters == null)
            parameters = new JSONObject();
        parameters.put(FILE_NAME, fileName);
        parameters.put(FILE_DATA, fileData);
        return parameters;
    }

    /**
     * Creates the JSON Object for the parameters of the request
     *
     * @param filePath              Path on disk to a file to submit for data extraction
     * @param parameters            Additional request parameters
     * @return the JSON object of the parameters of the request
     */
    protected JSONObject addFileToParameters(String filePath, JSONObject parameters) {
        String fileName = filePath.replaceAll("^.*[/\\\\]", "");
        File file = new File(filePath);
        String fileData = "";
        try {
            byte[] fileContent = Files.readAllBytes(file.toPath());
            fileData = Base64.getEncoder().encodeToString(fileContent);
        } catch (Exception e) {
            logger.severe("addFileToParameters: " + e.getMessage());
        }
        return addFileToParameters(fileName, fileData, parameters);
    }

    /**
     * Creates the JSON object of the parameters of the request
     *
     * @param fileUrl               Required if file_urls isn't specified. Publicly accessible URL to a file, e.g. "https://cdn.example.com/receipt.jpg".
     * @param fileUrls              Required if file_url isn't specifies. List of publicly accessible URLs to multiple files, e.g. ["https://cdn.example.com/receipt1.jpg", "https://cdn.example.com/receipt2.jpg"]
     * @param parameters            Additional request parameters
     * @return JSON object of the request arguments
     */
    protected JSONObject addUrlToParameters(String fileUrl, List<String> fileUrls, JSONObject parameters) {
        if (parameters == null)
            parameters = new JSONObject();
        parameters.put(FILE_URL, fileUrl);
        if (fileUrls != null) {
            parameters.put(FILE_URLS, fileUrls);
        }
        return parameters;
    }

}