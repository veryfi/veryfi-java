package veryfi.services;

import org.json.JSONObject;
import veryfi.Credentials;
import veryfi.NetworkClient;
import veryfi.enums.Endpoint;
import veryfi.enums.HttpMethod;

import java.net.http.HttpClient;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * API services for W9s
 */
class W9Services extends NetworkClient {

    /**
     * Creates an instance of {@link W9Services}.
     *
     * @param credentials  the {@link Credentials} provided by Veryfi.
     * @param apiVersion   the {@link int} api version to use Veryfi.
     */
    protected W9Services(Credentials credentials, int apiVersion) {
        super(credentials, apiVersion);
    }

    /**
     * Creates an instance of {@link W9Services}.
     *
     * @param credentials  the {@link Credentials} provided by Veryfi.
     * @param apiVersion   the {@link int} api version to use Veryfi.
     * @param httpClient   {@link HttpClient} for the Veryfi API
     */
    protected W9Services(Credentials credentials, int apiVersion, HttpClient httpClient) {
        super(credentials, apiVersion, httpClient);
    }

    /**
     * Returns a json string {@link String} with the list of W9s. https://docs.veryfi.com/api/w9s/get-w-9-s/
     *
     * @param page   The page number. The response is capped to maximum of 50 results per page.
     * @param pageSize The number of Documents per page.
     * @param boundingBoxes A field used to determine whether or not to return bounding_box and bounding_region for extracted fields in the Document response.
     * @param confidenceDetails A field used to determine whether or not to return the score and ocr_score fields in the Document response.
     * @param parameters Additional request parameters.
     * @return the url {@link String}
     */
    protected String getW9s(int page, int pageSize, boolean boundingBoxes, boolean confidenceDetails, JSONObject parameters) {
        if (parameters == null)
            parameters = new JSONObject();
        parameters.put("page", page);
        parameters.put("page_size", pageSize);
        parameters.put("bounding_boxes", boundingBoxes);
        parameters.put("confidence_details", confidenceDetails);
        return request(HttpMethod.GET, Endpoint.w9s.path, parameters);
    }

    /**
     * Returns a json string {@link CompletableFuture}{@code <String>} list of W9s. https://docs.veryfi.com/api/w9s/get-w-9-s/
     *
     * @param page   The page number. The response is capped to maximum of 50 results per page.
     * @param pageSize The number of Documents per page.
     * @param boundingBoxes A field used to determine whether or not to return bounding_box and bounding_region for extracted fields in the Document response.
     * @param confidenceDetails A field used to determine whether or not to return the score and ocr_score fields in the Document response.
     * @param parameters Additional request parameters.
     * @return the list of previously processed W9s {@link String}
     */
    protected CompletableFuture<String> getW9sAsync(int page, int pageSize, boolean boundingBoxes, boolean confidenceDetails, JSONObject parameters) {
        if (parameters == null)
            parameters = new JSONObject();
        parameters.put("page", page);
        parameters.put("page_size", pageSize);
        parameters.put("bounding_boxes", boundingBoxes);
        parameters.put("confidence_details", confidenceDetails);
        return requestAsync(HttpMethod.GET, Endpoint.w9s.path, parameters);
    }

    /**
     * Returns a json string {@link String} W9 information. https://docs.veryfi.com/api/w9s/get-a-w-9/
     *
     * @param documentId ID of the W9 you'd like to retrieve.
     * @return the data extracted from the W9 {@link String}
     */
    protected String getW9(String documentId) {
        String endpointName = Endpoint.w9s.path + documentId + "/";
        JSONObject parameters = new JSONObject();
        parameters.put("id", documentId);
        return request(HttpMethod.GET, endpointName, parameters);
    }

    /**
     * Returns a json string {@link CompletableFuture}{@code <String>} W9 information. https://docs.veryfi.com/api/w9s/get-a-w-9/
     *
     * @param documentId ID of the W9 you'd like to retrieve.
     * @return the data extracted from the W9 {@link String}
     */
    protected CompletableFuture<String> getW9Async(String documentId) {
        String endpointName = Endpoint.w9s.path + documentId + "/";
        JSONObject parameters = new JSONObject();
        parameters.put("id", documentId);
        return requestAsync(HttpMethod.GET, endpointName, parameters);
    }

    /**
     * Process a W9 and extract all the fields from it. https://docs.veryfi.com/api/w9s/process-a-w-9/
     *
     * @param filePath      Path on disk to a file to submit for data extraction.
      * @param parameters    Additional request parameters.
     * @return the data extracted from the W9 {@link String}
     */
    protected String processW9(String filePath, JSONObject parameters) {
        parameters = addFileToParameters(filePath, parameters);
        return request(HttpMethod.POST, Endpoint.w9s.path, parameters);
    }

    /**
     * Process a W9 and extract all the fields from it. https://docs.veryfi.com/api/w9s/process-a-w-9/
     *
     * @param filePath      Path on disk to a file to submit for data extraction.
     * @param parameters    Additional request parameters.
     * @return the data extracted from the W9 {@link CompletableFuture}{@code <String>}
     */
    protected CompletableFuture<String> processW9Async(String filePath, JSONObject parameters) {
        parameters = addFileToParameters(filePath, parameters);
        return requestAsync(HttpMethod.POST, Endpoint.w9s.path, parameters);
    }

    /**
     * Process a W9 and extract all the fields from it. https://docs.veryfi.com/api/w9s/process-a-w-9/
     *
     * @param fileName      Name of the file to submit for data extraction.
     * @param fileData      Base64 encoded file data.
     * @param parameters    Additional request parameters.
     * @return the data extracted from the W9 {@link String}
     */
    protected String processW9(String fileName, String fileData, JSONObject parameters) {
        parameters = addFileToParameters(fileName, fileData, parameters);
        return request(HttpMethod.POST, Endpoint.w9s.path, parameters);
    }

    /**
     * Process a W9 and extract all the fields from it. https://docs.veryfi.com/api/w9s/process-a-w-9/
     *
     * @param fileName      Name of the file to submit for data extraction.
     * @param fileData      Base64 encoded file data.
     * @param parameters    Additional request parameters.
     * @return the data extracted from the W9 {@link CompletableFuture}{@code <String>}
     */
    protected CompletableFuture<String> processW9Async(String fileName, String fileData, JSONObject parameters) {
        parameters = addFileToParameters(fileName, fileData, parameters);
        return requestAsync(HttpMethod.POST, Endpoint.w9s.path, parameters);
    }

    /**
     * Process W9 from url and extract all the fields from it. https://docs.veryfi.com/api/w9s/process-a-w-9/
     *
     * @param fileUrl       Required if file_urls isn't specified. Publicly accessible URL to a file, e.g. "https://cdn.example.com/receipt.jpg".
     * @param fileUrls      Required if file_url isn't specifies. List of publicly accessible URLs to multiple files, e.g. ["https://cdn.example.com/receipt1.jpg", "https://cdn.example.com/receipt2.jpg"]
     * @param parameters    Additional request parameters
     * @return the data extracted from the W9 {@link String}
     */
    protected String processW9Url(String fileUrl, List<String> fileUrls, JSONObject parameters) {
        parameters = addUrlToParameters(fileUrl, fileUrls, parameters);
        return request(HttpMethod.POST, Endpoint.w9s.path, parameters);
    }

    /**
     * Process W9 from url and extract all the fields from it. https://docs.veryfi.com/api/w9s/process-a-w-9/
     *
     * @param fileUrl       Required if file_urls isn't specified. Publicly accessible URL to a file, e.g. "https://cdn.example.com/receipt.jpg".
     * @param fileUrls      Required if file_url isn't specifies. List of publicly accessible URLs to multiple files, e.g. ["https://cdn.example.com/receipt1.jpg", "https://cdn.example.com/receipt2.jpg"]
     * @param parameters    Additional request parameters
     * @return the data extracted from the W9 {@link CompletableFuture}{@code <String>}
     */
    protected CompletableFuture<String> processW9UrlAsync(String fileUrl, List<String> fileUrls, JSONObject parameters) {
        parameters = addUrlToParameters(fileUrl, fileUrls, parameters);
        return requestAsync(HttpMethod.POST, Endpoint.w9s.path, parameters);
    }

    /**
     * Delete W9 from Veryfi. https://docs.veryfi.com/api/w9s/delete-a-w-9/
     *
     * @param documentId ID of the W9 you'd like to delete.
     * @return the response data. {@link String}
     */
    protected String deleteW9(String documentId) {
        String endpointName = Endpoint.w9s.path + documentId + "/";
        JSONObject parameters = new JSONObject();
        parameters.put("id", documentId);
        return request(HttpMethod.DELETE, endpointName, parameters);
    }

    /**
     * Delete W9 from Veryfi. https://docs.veryfi.com/api/w9s/delete-a-w-9/
     *
     * @param documentId ID of the W9 you'd like to delete.
     * @return the response data. {@link CompletableFuture}{@code <String>}
     */
    protected CompletableFuture<String> deleteW9Async(String documentId) {
        String endpointName = Endpoint.w9s.path + documentId + "/";
        JSONObject parameters = new JSONObject();
        parameters.put("id", documentId);
        return requestAsync(HttpMethod.DELETE, endpointName, parameters);
    }

}
