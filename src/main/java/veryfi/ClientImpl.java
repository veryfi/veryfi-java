package veryfi;

import org.json.JSONObject;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

import static veryfi.Constants.*;

/**
 * Veryfi API client for Java.
 */
public class ClientImpl implements Client {

    private final Logger logger = Logger.getLogger("ClientImpl");

    private final String clientId;
    private final String clientSecret;
    private final String username;
    private final String apiKey;
    private final int apiVersion;
    private String baseUrl = "https://api.veryfi.com/api/";
    private int timeOut = 120;
    private HttpClient httpClient;

    /**
     * Creates an instance of {@link ClientImpl}.
     * @param clientId the {@link String} provided by Veryfi.
     * @param clientSecret the {@link String} provided by Veryfi.
     * @param username the {@link String} provided by Veryfi.
     * @param apiKey the {@link String} provided by Veryfi.
     * @param apiVersion the {@link int} api version to use Veryfi.
     */
    public ClientImpl(String clientId, String clientSecret, String username, String apiKey, int apiVersion) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.username = username;
        this.apiKey = apiKey;
        this.apiVersion = apiVersion;
        this.httpClient = HttpClient.newHttpClient();
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
     * @param httpVerb HTTP Method
     * @param endpointName Endpoint name such as 'documents', 'users', etc.
     * @param requestArguments JSON payload to send to Veryfi
     * @return A JSON of the response data.
     */
    private String request(HttpMethod httpVerb, String endpointName, JSONObject requestArguments) {
        HttpRequest request = getHttpRequest(httpVerb, endpointName, requestArguments);
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            logger.severe(e.getMessage());
            return "";
        }
    }

    /**
     * Submit the HTTP request.
     * @param httpVerb HTTP Method
     * @param endpointName Endpoint name such as 'documents', 'users', etc.
     * @param requestArguments JSON payload to send to Veryfi
     * @return A JSON of the response data.
     */
    private CompletableFuture<String> requestAsync(HttpMethod httpVerb, String endpointName,
                                                   JSONObject requestArguments) {
        HttpRequest request = getHttpRequest(httpVerb, endpointName, requestArguments);
        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body);
    }

    /**
     * Creates the HTTP request Object.
     * @param httpVerb HTTP Method
     * @param endpointName Endpoint name such as 'documents', 'users', etc.
     * @param requestArguments JSON payload to send to Veryfi
     * @return request Object for the HttpClient {@link HttpRequest}
     */
    private HttpRequest getHttpRequest(HttpMethod httpVerb, String endpointName, JSONObject requestArguments) {
        HttpRequest request;
        String apiUrl = getUrl() + "/partner" + endpointName;
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
     * Returns a {@link String} ApiKey for the headers of the request.
     * @return the ApiKey {@link String}
     */
    private String getApiKey() {
        return "apikey " + this.username + ":" + this.apiKey;
    }


    /**
     * Prepares the headers needed for a request.
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
        jsonHeaders.put(CLIENT_ID, this.clientId);
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
     * @param timeStamp Unix Long timestamp
     * @param payloadParams JSON params to be sent to API request
     * @return Unique signature generated using the client_secret and the payload
     */
    private String generateSignature(Long timeStamp, JSONObject payloadParams) {
        JSONObject jsonPayload = new JSONObject(payloadParams.toString());
        jsonPayload.put(TIMESTAMP, Long.toString(timeStamp));
        String payload = jsonPayload.toString();
        byte[] secretBytes = clientSecret.getBytes(StandardCharsets.UTF_8);
        byte[] payloadBytes = payload.getBytes(StandardCharsets.UTF_8);
        SecretKeySpec keySpec = new SecretKeySpec(secretBytes, SHA256);

        Mac mac;
        try {
            mac = Mac.getInstance(SHA256);
        } catch (NoSuchAlgorithmException e) {
            return  e.getMessage();
        }
        try {
            mac.init(keySpec);
        } catch (InvalidKeyException e) {
            return  e.getMessage();
        }
        byte[] macBytes = mac.doFinal(payloadBytes);
        String base64SignatureEncoded;
        base64SignatureEncoded = Base64.getEncoder().encodeToString(macBytes);
        return base64SignatureEncoded;
    }

    /**
     * Returns a json string {@link String} with the list of documents.
     * @return the url {@link String}
     */
    @Override
    public String getDocuments() {
        String endpointName = "/documents/";
        JSONObject requestArguments = new JSONObject();
        return request(HttpMethod.GET, endpointName, requestArguments);
    }

    /**
     * Returns a json string {@link CompletableFuture<String>} list of documents.
     * @return the list of previously processed documents {@link String}
     */
    @Override
    public CompletableFuture<String> getDocumentsAsync() {
        String endpointName = "/documents/";
        JSONObject requestArguments = new JSONObject();
        return requestAsync(HttpMethod.GET, endpointName, requestArguments);
    }

    /**
     * Returns a json string {@link String} document information
     * @param documentId ID of the document you'd like to retrieve.
     * @return the data extracted from the Document {@link String}
     */
    @Override
    public String getDocument(String documentId) {
        String endpointName = "/documents/" + documentId + "/";
        JSONObject requestArguments = new JSONObject();
        requestArguments.put("id", documentId);
        return request(HttpMethod.GET, endpointName, requestArguments);
    }

    /**
     * Returns a json string {@link CompletableFuture<String>} document information.
     * @param documentId ID of the document you'd like to retrieve.
     * @return the data extracted from the Document {@link String}
     */
    @Override
    public CompletableFuture<String> getDocumentAsync(String documentId) {
        String endpointName = "/documents/" + documentId + "/";
        JSONObject requestArguments = new JSONObject();
        requestArguments.put("id", documentId);
        return requestAsync(HttpMethod.GET, endpointName, requestArguments);
    }

    /**
     * Creates the JSON Object for the parameters of the request
     * @param filePath Path on disk to a file to submit for data extraction
     * @param categories List of categories Veryfi can use to categorize the document
     * @param deleteAfterProcessing Delete this document from Veryfi after data has been extracted
     * @param parameters Additional request parameters
     * @return the JSON object of the parameters of the request
     */
    public JSONObject getProcessDocumentArguments(String filePath, List<String> categories,
                                                  boolean deleteAfterProcessing, JSONObject parameters) {
        if (categories == null || categories.isEmpty()) {
            categories = LIST_CATEGORIES;
        }
        String fileName = Paths.get(filePath).getFileName().toString();
        InputStream fileStream = ClassLoader.getSystemResourceAsStream(filePath);
        String base64EncodedString = "";
        try {
            if (fileStream != null) {
                base64EncodedString = Base64.getEncoder().encodeToString(fileStream.readAllBytes());
            }
        } catch (IOException e) {
            logger.severe(e.getMessage());
        }
        JSONObject requestArguments = new JSONObject();
        requestArguments.put(FILE_NAME, fileName);
        requestArguments.put(FILE_DATA, base64EncodedString);
        requestArguments.put(CATEGORIES, categories);
        requestArguments.put(AUTO_DELETE, deleteAfterProcessing);

        if (parameters != null && !parameters.isEmpty()) {
            for (String key : JSONObject.getNames(parameters)) {
                requestArguments.put(key, parameters.get(key));
            }
        }
        return requestArguments;
    }

    /**
     * Process a document and extract all the fields from it
     * @param filePath Path on disk to a file to submit for data extraction
     * @param categories List of categories Veryfi can use to categorize the document
     * @param deleteAfterProcessing Delete this document from Veryfi after data has been extracted
     * @param parameters Additional request parameters
     * @return the data extracted from the Document {@link String}
     */
    @Override
    public String processDocument(String filePath, List<String> categories, boolean deleteAfterProcessing,
                                  JSONObject parameters) {
        String endpointName = "/documents/";
        JSONObject requestArguments = getProcessDocumentArguments(filePath, categories, deleteAfterProcessing, parameters);
        return request(HttpMethod.POST, endpointName, requestArguments);
    }

    /**
     * Process a document and extract all the fields from it
     * @param filePath Path on disk to a file to submit for data extraction
     * @param categories List of categories Veryfi can use to categorize the document
     * @param deleteAfterProcessing Delete this document from Veryfi after data has been extracted
     * @param parameters Additional request parameters
     * @return the data extracted from the Document {@link CompletableFuture<String>}
     */
    @Override
    public CompletableFuture<String> processDocumentAsync(String filePath, List<String> categories,
                                                          boolean deleteAfterProcessing, JSONObject parameters) {
        String endpointName = "/documents/";
        JSONObject requestArguments = getProcessDocumentArguments(filePath, categories,deleteAfterProcessing, parameters);
        return requestAsync(HttpMethod.POST, endpointName, requestArguments);
    }

    /**
     * Creates the JSON object of the parameters of the request
     * @param fileUrl Required if file_urls isn't specified. Publicly accessible URL to a file, e.g. "https://cdn.example.com/receipt.jpg".
     * @param fileUrls Required if file_url isn't specifies. List of publicly accessible URLs to multiple files, e.g. ["https://cdn.example.com/receipt1.jpg", "https://cdn.example.com/receipt2.jpg"]
     * @param categories List of categories to use when categorizing the document
     * @param deleteAfterProcessing Delete this document from Veryfi after data has been extracted
     * @param maxPagesToProcess When sending a long document to Veryfi for processing, this parameter controls how many pages of the document will be read and processed, starting from page 1.
     * @param boostMode Flag that tells Veryfi whether boost mode should be enabled. When set to 1, Veryfi will skip data enrichment steps, but will process the document faster. Default value for this flag is 0
     * @param externalId Optional custom document identifier. Use this if you would like to assign your own ID to documents
     * @param parameters Additional request parameters
     * @return JSON object of the request arguments
     */
    public JSONObject getProcessDocumentUrlArguments(String fileUrl, List<String> fileUrls, List<String> categories,
                                                     boolean deleteAfterProcessing, int maxPagesToProcess,
                                                     boolean boostMode, String externalId, JSONObject parameters) {
        if (categories == null || categories.isEmpty()) {
            categories = LIST_CATEGORIES;
        }
        JSONObject requestArguments = new JSONObject();
        requestArguments.put(AUTO_DELETE, deleteAfterProcessing);
        requestArguments.put(BOOST_MODE, boostMode);
        requestArguments.put(CATEGORIES, categories);
        requestArguments.put(EXTERNAL_ID, externalId);
        requestArguments.put(FILE_URL, fileUrl);
        if (fileUrls != null) {
            requestArguments.put(FILE_URLS, fileUrls);
        }
        requestArguments.put(MAX_PAGES_TO_PROCESS, maxPagesToProcess);

        if (parameters != null && !parameters.isEmpty()) {
            for (String key : JSONObject.getNames(parameters)) {
                requestArguments.put(key, parameters.get(key));
            }
        }
        return requestArguments;
    }


    /**
     * Process Document from url and extract all the fields from it.
     * @param fileUrl Required if file_urls isn't specified. Publicly accessible URL to a file, e.g. "https://cdn.example.com/receipt.jpg".
     * @param fileUrls Required if file_url isn't specifies. List of publicly accessible URLs to multiple files, e.g. ["https://cdn.example.com/receipt1.jpg", "https://cdn.example.com/receipt2.jpg"]
     * @param categories List of categories to use when categorizing the document
     * @param deleteAfterProcessing Delete this document from Veryfi after data has been extracted
     * @param maxPagesToProcess When sending a long document to Veryfi for processing, this parameter controls how many pages of the document will be read and processed, starting from page 1.
     * @param boostMode Flag that tells Veryfi whether boost mode should be enabled. When set to 1, Veryfi will skip data enrichment steps, but will process the document faster. Default value for this flag is 0
     * @param externalId Optional custom document identifier. Use this if you would like to assign your own ID to documents
     * @param parameters Additional request parameters
     * @return the data extracted from the Document {@link String}
     */
    @Override
    public String processDocumentUrl(String fileUrl, List<String> fileUrls, List<String> categories,
                                     boolean deleteAfterProcessing, int maxPagesToProcess,
                                     boolean boostMode, String externalId, JSONObject parameters) {

        String endpointName = "/documents/";
        JSONObject requestArguments = getProcessDocumentUrlArguments(fileUrl, fileUrls, categories, deleteAfterProcessing, maxPagesToProcess, boostMode, externalId, parameters);
        return request(HttpMethod.POST, endpointName, requestArguments);
    }

    /**
     * Process Document from url and extract all the fields from it.
     * @param fileUrl Required if file_urls isn't specified. Publicly accessible URL to a file, e.g. "https://cdn.example.com/receipt.jpg".
     * @param fileUrls Required if file_url isn't specifies. List of publicly accessible URLs to multiple files, e.g. ["https://cdn.example.com/receipt1.jpg", "https://cdn.example.com/receipt2.jpg"]
     * @param categories List of categories to use when categorizing the document
     * @param deleteAfterProcessing Delete this document from Veryfi after data has been extracted
     * @param maxPagesToProcess When sending a long document to Veryfi for processing, this parameter controls how many pages of the document will be read and processed, starting from page 1.
     * @param boostMode Flag that tells Veryfi whether boost mode should be enabled. When set to 1, Veryfi will skip data enrichment steps, but will process the document faster. Default value for this flag is 0
     * @param externalId Optional custom document identifier. Use this if you would like to assign your own ID to documents
     * @param parameters Additional request parameters
     * @return the data extracted from the Document {@link CompletableFuture<String>}
     */
    @Override
    public CompletableFuture<String> processDocumentUrlAsync(String fileUrl, List<String> fileUrls,
                                                             List<String> categories, boolean deleteAfterProcessing,
                                                             int maxPagesToProcess, boolean boostMode,
                                                             String externalId, JSONObject parameters) {
        String endpointName = "/documents/";
        JSONObject requestArguments = getProcessDocumentUrlArguments(fileUrl, fileUrls, categories, deleteAfterProcessing, maxPagesToProcess, boostMode, externalId, parameters);
        return requestAsync(HttpMethod.POST, endpointName, requestArguments);
    }

    /**
     * Delete Document from Veryfi
     * @param documentId ID of the document you'd like to delete.
     * @return the response data. {@link String}
     */
    @Override
    public String deleteDocument(String documentId) {
        String endpointName = "/documents/" + documentId + "/";
        JSONObject requestArguments = new JSONObject();
        requestArguments.put("id", documentId);
        return request(HttpMethod.DELETE, endpointName, requestArguments);
    }

    /**
     * Delete Document from Veryfi
     * @param documentId ID of the document you'd like to delete.
     * @return the response data. {@link CompletableFuture<String>}
     */
    @Override
    public CompletableFuture<String> deleteDocumentAsync(String documentId) {
        String endpointName = "/documents/" + documentId + "/";
        JSONObject requestArguments = new JSONObject();
        requestArguments.put("id", documentId);
        return requestAsync(HttpMethod.DELETE, endpointName, requestArguments);
    }

    /**
     * Update data for a previously processed document, including almost any field like `vendor`, `date`, `notes` etc.
     * @param documentId ID of the document you'd like to update.
     * @param parameters Additional request parameters
     * @return A document json with updated fields, if fields are writable. Otherwise, a document with unchanged fields. {@link String}
     */
    @Override
    public String updateDocument(String documentId, JSONObject parameters) {
        String endpointName = "/documents/" + documentId + "/";
        return request(HttpMethod.PUT, endpointName, parameters);
    }

    /**
     * Update data for a previously processed document, including almost any field like `vendor`, `date`, `notes` etc.
     * @param documentId ID of the document you'd like to update.
     * @param parameters Additional request parameters
     * @return A document json with updated fields, if fields are writable. Otherwise, a document with unchanged fields. {@link CompletableFuture<String>}
     */
    @Override
    public CompletableFuture<String> updateDocumentAsync(String documentId, JSONObject parameters) {
        String endpointName = "/documents/" + documentId + "/";
        return requestAsync(HttpMethod.PUT, endpointName, parameters);
    }

    /**
     * Retrieve all line items for a document.
     * @param documentId ID of the document you'd like to retrieve.
     * @return List of line items extracted from the document. {@link String}
     */
    @Override
    public String getLineItems(String documentId) {
        String endpointName = "/documents" + documentId + "/line-items/";
        JSONObject requestArguments = new JSONObject();
        return request(HttpMethod.GET, endpointName, requestArguments);
    }

    /**
     * Retrieve all line items for a document.
     * @param documentId ID of the document you'd like to retrieve.
     * @return List of line items extracted from the document. {@link CompletableFuture<String>}
     */
    @Override
    public CompletableFuture<String> getLineItemsAsync(String documentId) {
        String endpointName = "/documents" + documentId + "/line-items/";
        JSONObject requestArguments = new JSONObject();
        return requestAsync(HttpMethod.GET, endpointName, requestArguments);
    }

    /**
     * Retrieve a line item for existing document by ID.
     * @param documentId ID of the document you'd like to retrieve.
     * @param lineItemId ID of the line item you'd like to retrieve.
     * @return Line item extracted from the document. {@link String}
     */
    @Override
    public String getLineItem(String documentId, String lineItemId) {
        String endpointName = "/documents" + documentId + "/line-items/" + lineItemId;
        JSONObject requestArguments = new JSONObject();
        return request(HttpMethod.GET, endpointName, requestArguments);
    }

    /**
     * Retrieve a line item for existing document by ID.
     * @param documentId ID of the document you'd like to retrieve.
     * @param lineItemId ID of the line item you'd like to retrieve.
     * @return Line item extracted from the document. {@link CompletableFuture<String>}
     */
    @Override
    public CompletableFuture<String> getLineItemAsync(String documentId, String lineItemId) {
        String endpointName = "/documents" + documentId + "/line-items/" + lineItemId;
        JSONObject requestArguments = new JSONObject();
        return requestAsync(HttpMethod.GET, endpointName, requestArguments);
    }

    /**
     * Add a new line item on an existing document.
     * @param documentId ID of the document you'd like to update.
     * @param payload line item object to add.
     * @return Added line item data. {@link String}
     */
    @Override
    public String addLineItem(String documentId, JSONObject payload) {
        String endpointName = "/documents" + documentId + "/line-items/";
        //TODO Check JSON Object
        return request(HttpMethod.POST, endpointName, payload);
    }

    /**
     * Add a new line item on an existing document.
     * @param documentId ID of the document you'd like to update.
     * @param payload line item object to add.
     * @return Added line item data. {@link CompletableFuture<String>}
     */
    @Override
    public CompletableFuture<String> addLineItemAsync(String documentId, JSONObject payload) {
        String endpointName = "/documents" + documentId + "/line-items/";
        //TODO Check JSON Object
        return requestAsync(HttpMethod.POST, endpointName, payload);
    }

    /**
     * Update an existing line item on an existing document.
     * @param documentId ID of the document you'd like to update.
     * @param lineItemId ID of the line item you'd like to update.
     * @param payload line item object to update.
     * @return Line item data with updated fields, if fields are writable. Otherwise line item data with unchanged fields. {@link String}
     */
    @Override
    public String updateLineItem(String documentId, String lineItemId, JSONObject payload) {
        String endpointName = "/documents" + documentId + "/line-items/" + lineItemId;
        // TODO Check JSON Object
        return request(HttpMethod.PUT, endpointName, payload);
    }

    /**
     * Update an existing line item on an existing document.
     * @param documentId ID of the document you'd like to update.
     * @param lineItemId ID of the line item you'd like to update.
     * @param payload line item object to update.
     * @return Line item data with updated fields, if fields are writable. Otherwise line item data with unchanged fields. {@link CompletableFuture<String>}
     */
    @Override
    public CompletableFuture<String> updateLineItemAsync(String documentId, String lineItemId, JSONObject payload) {
        String endpointName = "/documents" + documentId + "/line-items/" + lineItemId;
        // TODO Check JSON Object
        return requestAsync(HttpMethod.PUT, endpointName, payload);
    }

    /**
     * Delete all line items on an existing document.
     * @param documentId ID of the document you'd like to delete.
     * @return the response data. {@link String}
     */
    @Override
    public String deleteLineItems(String documentId) {
        String endpointName = "/documents" + documentId + "/line-items/";
        JSONObject requestArguments = new JSONObject();
        return request(HttpMethod.DELETE, endpointName, requestArguments);
    }

    /**
     * Delete all line items on an existing document.
     * @param documentId ID of the document you'd like to delete.
     * @return @return the response data. {@link CompletableFuture<String>}
     */
    @Override
    public CompletableFuture<String> deleteLineItemsAsync(String documentId) {
        String endpointName = "/documents" + documentId + "/line-items/";
        JSONObject requestArguments = new JSONObject();
        return requestAsync(HttpMethod.DELETE, endpointName, requestArguments);
    }

    /**
     * Delete an existing line item on an existing document.
     * @param documentId  ID of the document you'd like to delete.
     * @param lineItemId  ID of the line item you'd like to delete.
     * @return the response data. {@link String}
     */
    @Override
    public String deleteLineItem(String documentId, String lineItemId) {
        String endpointName = "/documents" + documentId + "/line-items/" + lineItemId;
        JSONObject requestArguments = new JSONObject();
        return request(HttpMethod.DELETE, endpointName, requestArguments);
    }

    /**
     * Delete an existing line item on an existing document.
     * @param documentId  ID of the document you'd like to delete.
     * @param lineItemId  ID of the line item you'd like to delete.
     * @return the response data. {@link CompletableFuture<String>}
     */
    @Override
    public CompletableFuture<String> deleteLineItemAsync(String documentId, String lineItemId) {
        String endpointName = "/documents" + documentId + "/line-items/" + lineItemId;
        JSONObject requestArguments = new JSONObject();
        return requestAsync(HttpMethod.DELETE, endpointName, requestArguments);
    }

    /**
     * Define new time out for the requests in seconds
     * @param timeOut of the http requests in seconds
     */
    @Override
    public void setTimeOut(int timeOut) {
       this.timeOut = timeOut;
    }

    /**
     * By default, the base URL is https://api.veryfi.com/api/;
     * @param baseUrl for the Veryfi API
     */
    @Override
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public void setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }
}
