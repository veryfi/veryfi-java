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
 * API services for Checks
 */
class CheckServices extends NetworkClient {

    /**
     * Creates an instance of {@link CheckServices}.
     *
     * @param credentials  the {@link Credentials} provided by Veryfi.
     * @param apiVersion   the {@link int} api version to use Veryfi.
     */
    protected CheckServices(Credentials credentials, int apiVersion) {
        super(credentials, apiVersion);
    }

    /**
     * Creates an instance of {@link CheckServices}.
     *
     * @param credentials  the {@link Credentials} provided by Veryfi.
     * @param apiVersion   the {@link int} api version to use Veryfi.
     * @param httpClient   {@link HttpClient} for the Veryfi API
     */
    protected CheckServices(Credentials credentials, int apiVersion, HttpClient httpClient) {
        super(credentials, apiVersion, httpClient);
    }

    /**
     * Returns a json string {@link String} with the list of Checks.
     *
     * @param page   The page number. The response is capped to maximum of 50 results per page.
     * @param pageSize The number of Documents per page.
     * @param boundingBoxes A field used to determine whether or not to return bounding_box and bounding_region for extracted fields in the Document response.
     * @param confidenceDetails A field used to determine whether or not to return the score and ocr_score fields in the Document response.
     * @param parameters Additional request parameters.
     * @return the url {@link String}
     */
    protected String getChecks(int page, int pageSize, boolean boundingBoxes, boolean confidenceDetails, JSONObject parameters) {
        if (parameters == null)
            parameters = new JSONObject();
        parameters.put("page", page);
        parameters.put("page_size", pageSize);
        parameters.put("bounding_boxes", boundingBoxes);
        parameters.put("confidence_details", confidenceDetails);
        return request(HttpMethod.GET, Endpoint.checks.path, parameters);
    }

    /**
     * Returns a json string {@link CompletableFuture<String>} list of Checks.
     *
     * @param page   The page number. The response is capped to maximum of 50 results per page.
     * @param pageSize The number of Documents per page.
     * @param boundingBoxes A field used to determine whether or not to return bounding_box and bounding_region for extracted fields in the Document response.
     * @param confidenceDetails A field used to determine whether or not to return the score and ocr_score fields in the Document response.
     * @param parameters Additional request parameters.
     * @return the list of previously processed checks {@link String}
     */
    protected CompletableFuture<String> getChecksAsync(int page, int pageSize, boolean boundingBoxes, boolean confidenceDetails, JSONObject parameters) {
        if (parameters == null)
            parameters = new JSONObject();
        parameters.put("page", page);
        parameters.put("page_size", pageSize);
        parameters.put("bounding_boxes", boundingBoxes);
        parameters.put("confidence_details", confidenceDetails);
        return requestAsync(HttpMethod.GET, Endpoint.checks.path, parameters);
    }

    /**
     * Returns a json string {@link String} Check information
     *
     * @param documentId ID of the Check you'd like to retrieve.
     * @return the data extracted from the Check {@link String}
     */
    protected String getCheck(String documentId) {
        String endpointName = Endpoint.checks.path + documentId + "/";
        JSONObject parameters = new JSONObject();
        parameters.put("id", documentId);
        return request(HttpMethod.GET, endpointName, parameters);
    }

    /**
     * Returns a json string {@link CompletableFuture<String>} Check information.
     *
     * @param documentId ID of the Check you'd like to retrieve.
     * @return the data extracted from the Check {@link String}
     */
    protected CompletableFuture<String> getCheckAsync(String documentId) {
        String endpointName = Endpoint.checks.path + documentId + "/";
        JSONObject parameters = new JSONObject();
        parameters.put("id", documentId);
        return requestAsync(HttpMethod.GET, endpointName, parameters);
    }

    /**
     * Process a Check and extract all the fields from it
     *
     * @param filePath      Path on disk to a file to submit for data extraction.
      * @param parameters    Additional request parameters.
     * @return the data extracted from the Check {@link String}
     */
    protected String processCheck(String filePath, JSONObject parameters) {
        parameters = addFileToParameters(filePath, parameters);
        return request(HttpMethod.POST, Endpoint.checks.path, parameters);
    }

    /**
     * Process a Check and extract all the fields from it
     *
     * @param filePath      Path on disk to a file to submit for data extraction.
     * @param parameters    Additional request parameters.
     * @return the data extracted from the Check {@link CompletableFuture<String>}
     */
    protected CompletableFuture<String> processCheckAsync(String filePath, JSONObject parameters) {
        parameters = addFileToParameters(filePath, parameters);
        return requestAsync(HttpMethod.POST, Endpoint.checks.path, parameters);
    }

    /**
     * Process Check from url and extract all the fields from it.
     *
     * @param fileUrl       Required if file_urls isn't specified. Publicly accessible URL to a file, e.g. "https://cdn.example.com/receipt.jpg".
     * @param fileUrls      Required if file_url isn't specifies. List of publicly accessible URLs to multiple files, e.g. ["https://cdn.example.com/receipt1.jpg", "https://cdn.example.com/receipt2.jpg"]
     * @param parameters    Additional request parameters
     * @return the data extracted from the Check {@link String}
     */
    protected String processCheckUrl(String fileUrl, List<String> fileUrls, JSONObject parameters) {
        parameters = addUrlToParameters(fileUrl, fileUrls, parameters);
        return request(HttpMethod.POST, Endpoint.checks.path, parameters);
    }

    /**
     * Process Check from url and extract all the fields from it.
     *
     * @param fileUrl       Required if file_urls isn't specified. Publicly accessible URL to a file, e.g. "https://cdn.example.com/receipt.jpg".
     * @param fileUrls      Required if file_url isn't specifies. List of publicly accessible URLs to multiple files, e.g. ["https://cdn.example.com/receipt1.jpg", "https://cdn.example.com/receipt2.jpg"]
     * @param parameters    Additional request parameters
     * @return the data extracted from the Check {@link CompletableFuture<String>}
     */
    protected CompletableFuture<String> processCheckUrlAsync(String fileUrl, List<String> fileUrls, JSONObject parameters) {
        parameters = addUrlToParameters(fileUrl, fileUrls, parameters);
        return requestAsync(HttpMethod.POST, Endpoint.checks.path, parameters);
    }

    /**
     * Delete Check from Veryfi
     *
     * @param documentId ID of the Check you'd like to delete.
     * @return the response data. {@link String}
     */
    protected String deleteCheck(String documentId) {
        String endpointName = Endpoint.checks.path + documentId + "/";
        JSONObject parameters = new JSONObject();
        parameters.put("id", documentId);
        return request(HttpMethod.DELETE, endpointName, parameters);
    }

    /**
     * Delete Check from Veryfi
     *
     * @param documentId ID of the Check you'd like to delete.
     * @return the response data. {@link CompletableFuture<String>}
     */
    protected CompletableFuture<String> deleteCheckAsync(String documentId) {
        String endpointName = Endpoint.checks.path + documentId + "/";
        JSONObject parameters = new JSONObject();
        parameters.put("id", documentId);
        return requestAsync(HttpMethod.DELETE, endpointName, parameters);
    }

}
