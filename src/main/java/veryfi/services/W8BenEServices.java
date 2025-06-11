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
 * API services for W-8BEN-E
 */
class W8BenEServices extends NetworkClient {

    /**
     * Creates an instance of {@link W8BenEServices}.
     *
     * @param credentials  the {@link Credentials} provided by Veryfi.
     * @param apiVersion   the {@link int} api version to use Veryfi.
     */
    protected W8BenEServices(Credentials credentials, int apiVersion) {
        super(credentials, apiVersion);
    }

    /**
     * Creates an instance of {@link W8BenEServices}.
     *
     * @param credentials  the {@link Credentials} provided by Veryfi.
     * @param apiVersion   the {@link int} api version to use Veryfi.
     * @param httpClient   {@link HttpClient} for the Veryfi API
     */
    protected W8BenEServices(Credentials credentials, int apiVersion, HttpClient httpClient) {
        super(credentials, apiVersion, httpClient);
    }

    /**
     * Returns a json string {@link String} with the list of W-8BEN-E. https://docs.veryfi.com/api/w-8ben-e/get-w-8-ben-es/
     *
     * @param page   The page number. The response is capped to maximum of 50 results per page.
     * @param pageSize The number of Documents per page.
     * @param boundingBoxes A field used to determine whether or not to return bounding_box and bounding_region for extracted fields in the Document response.
     * @param confidenceDetails A field used to determine whether or not to return the score and ocr_score fields in the Document response.
     * @param parameters Additional request parameters.
     * @return the url {@link String}
     */
    protected String getW8BenEs(int page, int pageSize, boolean boundingBoxes, boolean confidenceDetails, JSONObject parameters) {
        if (parameters == null)
            parameters = new JSONObject();
        parameters.put("page", page);
        parameters.put("page_size", pageSize);
        parameters.put("bounding_boxes", boundingBoxes);
        parameters.put("confidence_details", confidenceDetails);
        return request(HttpMethod.GET, Endpoint.w8BenE.path, parameters);
    }

    /**
     * Returns a json string {@link CompletableFuture<String>} list of W-8BEN-E. https://docs.veryfi.com/api/w-8ben-e/get-w-8-ben-es/
     *
     * @param page   The page number. The response is capped to maximum of 50 results per page.
     * @param pageSize The number of Documents per page.
     * @param boundingBoxes A field used to determine whether or not to return bounding_box and bounding_region for extracted fields in the Document response.
     * @param confidenceDetails A field used to determine whether or not to return the score and ocr_score fields in the Document response.
     * @param parameters Additional request parameters.
     * @return the list of previously processed W-8BEN-E {@link String}
     */
    protected CompletableFuture<String> getW8BenEsAsync(int page, int pageSize, boolean boundingBoxes, boolean confidenceDetails, JSONObject parameters) {
        if (parameters == null)
            parameters = new JSONObject();
        parameters.put("page", page);
        parameters.put("page_size", pageSize);
        parameters.put("bounding_boxes", boundingBoxes);
        parameters.put("confidence_details", confidenceDetails);
        return requestAsync(HttpMethod.GET, Endpoint.w8BenE.path, parameters);
    }

    /**
     * Returns a json string {@link String} W-8BEN-E information. https://docs.veryfi.com/api/w-8ben-e/get-a-w-8-ben-e/
     *
     * @param documentId ID of the W-8BEN-E you'd like to retrieve.
     * @return the data extracted from the W-8BEN-E {@link String}
     */
    protected String getW8BenE(String documentId) {
        String endpointName = Endpoint.w8BenE.path + documentId + "/";
        JSONObject parameters = new JSONObject();
        parameters.put("id", documentId);
        return request(HttpMethod.GET, endpointName, parameters);
    }

    /**
     * Returns a json string {@link CompletableFuture<String>} W-8BEN-E information. https://docs.veryfi.com/api/w-8ben-e/get-a-w-8-ben-e/
     *
     * @param documentId ID of the W-8BEN-E you'd like to retrieve.
     * @return the data extracted from the W-8BEN-E {@link String}
     */
    protected CompletableFuture<String> getW8BenEAsync(String documentId) {
        String endpointName = Endpoint.w8BenE.path + documentId + "/";
        JSONObject parameters = new JSONObject();
        parameters.put("id", documentId);
        return requestAsync(HttpMethod.GET, endpointName, parameters);
    }

    /**
     * Process a W-8BEN-E and extract all the fields from it. https://docs.veryfi.com/api/w-8ben-e/process-a-w-8-ben-e/
     *
     * @param filePath      Path on disk to a file to submit for data extraction.
     * @param parameters    Additional request parameters.
     * @return the data extracted from the W-8BEN-E {@link String}
     */
    protected String processW8BenE(String filePath, JSONObject parameters) {
        parameters = addFileToParameters(filePath, parameters);
        return request(HttpMethod.POST, Endpoint.w8BenE.path, parameters);
    }

    /**
     * Process a W-8BEN-E and extract all the fields from it. https://docs.veryfi.com/api/w-8ben-e/process-a-w-8-ben-e/
     *
     * @param filePath      Path on disk to a file to submit for data extraction.
     * @param parameters    Additional request parameters.
     * @return the data extracted from the W-8BEN-E {@link CompletableFuture<String>}
     */
    protected CompletableFuture<String> processW8BenEAsync(String filePath, JSONObject parameters) {
        parameters = addFileToParameters(filePath, parameters);
        return requestAsync(HttpMethod.POST, Endpoint.w8BenE.path, parameters);
    }

    /**
     * Process a W-8BEN-E and extract all the fields from it. https://docs.veryfi.com/api/w-8ben-e/process-a-w-8-ben-e/
     *
     * @param fileName      Name of the file to submit for data extraction.
     * @param fileData      Base64 encoded file data.
     * @param parameters    Additional request parameters.
     * @return the data extracted from the W-8BEN-E {@link String}
     */
    protected String processW8BenE(String fileName, String fileData, JSONObject parameters) {
        parameters = addFileToParameters(fileName, fileData, parameters);
        return request(HttpMethod.POST, Endpoint.w8BenE.path, parameters);
    }

    /**
     * Process a W-8BEN-E and extract all the fields from it. https://docs.veryfi.com/api/w-8ben-e/process-a-w-8-ben-e/
     *
     * @param fileName      Name of the file to submit for data extraction.
     * @param fileData      Base64 encoded file data.
     * @param parameters    Additional request parameters.
     * @return the data extracted from the W-8BEN-E {@link CompletableFuture<String>}
     */
    protected CompletableFuture<String> processW8BenEAsync(String fileName, String fileData, JSONObject parameters) {
        parameters = addFileToParameters(fileName, fileData, parameters);
        return requestAsync(HttpMethod.POST, Endpoint.w8BenE.path, parameters);
    }

    /**
     * Process W-8BEN-E from url and extract all the fields from it. https://docs.veryfi.com/api/w-8ben-e/process-a-w-8-ben-e/
     *
     * @param fileUrl       Required if file_urls isn't specified. Publicly accessible URL to a file, e.g. "https://cdn.example.com/receipt.jpg".
     * @param fileUrls      Required if file_url isn't specifies. List of publicly accessible URLs to multiple files, e.g. ["https://cdn.example.com/receipt1.jpg", "https://cdn.example.com/receipt2.jpg"]
     * @param parameters    Additional request parameters
     * @return the data extracted from the W-8BEN-E {@link String}
     */
    protected String processW8BenEUrl(String fileUrl, List<String> fileUrls, JSONObject parameters) {
        parameters = addUrlToParameters(fileUrl, fileUrls, parameters);
        return request(HttpMethod.POST, Endpoint.w8BenE.path, parameters);
    }

    /**
     * Process W-8BEN-E from url and extract all the fields from it. https://docs.veryfi.com/api/w-8ben-e/process-a-w-8-ben-e/
     *
     * @param fileUrl       Required if file_urls isn't specified. Publicly accessible URL to a file, e.g. "https://cdn.example.com/receipt.jpg".
     * @param fileUrls      Required if file_url isn't specifies. List of publicly accessible URLs to multiple files, e.g. ["https://cdn.example.com/receipt1.jpg", "https://cdn.example.com/receipt2.jpg"]
     * @param parameters    Additional request parameters
     * @return the data extracted from the W-8BEN-E {@link CompletableFuture<String>}
     */
    protected CompletableFuture<String> processW8BenEUrlAsync(String fileUrl, List<String> fileUrls, JSONObject parameters) {
        parameters = addUrlToParameters(fileUrl, fileUrls, parameters);
        return requestAsync(HttpMethod.POST, Endpoint.w8BenE.path, parameters);
    }

    /**
     * Delete W-8BEN-E from Veryfi. https://docs.veryfi.com/api/w-8ben-e/delete-a-w-8-ben-e/
     *
     * @param documentId ID of the W-8BEN-E you'd like to delete.
     * @return the response data. {@link String}
     */
    protected String deleteW8BenE(String documentId) {
        String endpointName = Endpoint.w8BenE.path + documentId + "/";
        JSONObject parameters = new JSONObject();
        parameters.put("id", documentId);
        return request(HttpMethod.DELETE, endpointName, parameters);
    }

    /**
     * Delete W-8BEN-E from Veryfi. https://docs.veryfi.com/api/w-8ben-e/delete-a-w-8-ben-e/
     *
     * @param documentId ID of the W-8BEN-E you'd like to delete.
     * @return the response data. {@link CompletableFuture<String>}
     */
    protected CompletableFuture<String> deleteW8BenEAsync(String documentId) {
        String endpointName = Endpoint.w8BenE.path + documentId + "/";
        JSONObject parameters = new JSONObject();
        parameters.put("id", documentId);
        return requestAsync(HttpMethod.DELETE, endpointName, parameters);
    }

}
