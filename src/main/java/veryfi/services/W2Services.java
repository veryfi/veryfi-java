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
 * API services for W2s
 */
class W2Services extends NetworkClient {

    /**
     * Creates an instance of {@link W2Services}.
     *
     * @param credentials  the {@link Credentials} provided by Veryfi.
     * @param apiVersion   the {@link int} api version to use Veryfi.
     */
    protected W2Services(Credentials credentials, int apiVersion) {
        super(credentials, apiVersion);
    }

    /**
     * Creates an instance of {@link W2Services}.
     *
     * @param credentials  the {@link Credentials} provided by Veryfi.
     * @param apiVersion   the {@link int} api version to use Veryfi.
     * @param httpClient   {@link HttpClient} for the Veryfi API
     */
    protected W2Services(Credentials credentials, int apiVersion, HttpClient httpClient) {
        super(credentials, apiVersion, httpClient);
    }

    /**
     * Returns a json string {@link String} with the list of W2s. https://docs.veryfi.com/api/w2s/get-w-2-s/
     *
     * @param page   The page number. The response is capped to maximum of 50 results per page.
     * @param pageSize The number of Documents per page.
     * @param boundingBoxes A field used to determine whether or not to return bounding_box and bounding_region for extracted fields in the Document response.
     * @param confidenceDetails A field used to determine whether or not to return the score and ocr_score fields in the Document response.
     * @param parameters Additional request parameters.
     * @return the url {@link String}
     */
    protected String getW2s(int page, int pageSize, boolean boundingBoxes, boolean confidenceDetails, JSONObject parameters) {
        if (parameters == null)
            parameters = new JSONObject();
        parameters.put("page", page);
        parameters.put("page_size", pageSize);
        parameters.put("bounding_boxes", boundingBoxes);
        parameters.put("confidence_details", confidenceDetails);
        return request(HttpMethod.GET, Endpoint.w2s.path, parameters);
    }

    /**
     * Returns a json string {@link CompletableFuture<String>} list of W2s. https://docs.veryfi.com/api/w2s/get-w-2-s/
     *
     * @param page   The page number. The response is capped to maximum of 50 results per page.
     * @param pageSize The number of Documents per page.
     * @param boundingBoxes A field used to determine whether or not to return bounding_box and bounding_region for extracted fields in the Document response.
     * @param confidenceDetails A field used to determine whether or not to return the score and ocr_score fields in the Document response.
     * @param parameters Additional request parameters.
     * @return the list of previously processed W2s {@link String}
     */
    protected CompletableFuture<String> getW2sAsync(int page, int pageSize, boolean boundingBoxes, boolean confidenceDetails, JSONObject parameters) {
        if (parameters == null)
            parameters = new JSONObject();
        parameters.put("page", page);
        parameters.put("page_size", pageSize);
        parameters.put("bounding_boxes", boundingBoxes);
        parameters.put("confidence_details", confidenceDetails);
        return requestAsync(HttpMethod.GET, Endpoint.w2s.path, parameters);
    }

    /**
     * Returns a json string {@link String} W2 information. https://docs.veryfi.com/api/w2s/get-a-w-2/
     *
     * @param documentId ID of the W2 you'd like to retrieve.
     * @return the data extracted from the W2 {@link String}
     */
    protected String getW2(String documentId) {
        String endpointName = Endpoint.w2s.path + documentId + "/";
        JSONObject parameters = new JSONObject();
        parameters.put("id", documentId);
        return request(HttpMethod.GET, endpointName, parameters);
    }

    /**
     * Returns a json string {@link CompletableFuture<String>} W2 information. https://docs.veryfi.com/api/w2s/get-a-w-2/
     *
     * @param documentId ID of the W2 you'd like to retrieve.
     * @return the data extracted from the W2 {@link String}
     */
    protected CompletableFuture<String> getW2Async(String documentId) {
        String endpointName = Endpoint.w2s.path + documentId + "/";
        JSONObject parameters = new JSONObject();
        parameters.put("id", documentId);
        return requestAsync(HttpMethod.GET, endpointName, parameters);
    }

    /**
     * Process a W2 and extract all the fields from it. https://docs.veryfi.com/api/w2s/process-a-w-2/
     *
     * @param filePath      Path on disk to a file to submit for data extraction.
      * @param parameters    Additional request parameters.
     * @return the data extracted from the W2 {@link String}
     */
    protected String processW2(String filePath, JSONObject parameters) {
        parameters = addFileToParameters(filePath, parameters);
        return request(HttpMethod.POST, Endpoint.w2s.path, parameters);
    }

    /**
     * Process a W2 and extract all the fields from it. https://docs.veryfi.com/api/w2s/process-a-w-2/
     *
     * @param filePath      Path on disk to a file to submit for data extraction.
     * @param parameters    Additional request parameters.
     * @return the data extracted from the W2 {@link CompletableFuture<String>}
     */
    protected CompletableFuture<String> processW2Async(String filePath, JSONObject parameters) {
        parameters = addFileToParameters(filePath, parameters);
        return requestAsync(HttpMethod.POST, Endpoint.w2s.path, parameters);
    }

    /**
     * Process W2 from url and extract all the fields from it. https://docs.veryfi.com/api/w2s/process-a-w-2/
     *
     * @param fileUrl       Required if file_urls isn't specified. Publicly accessible URL to a file, e.g. "https://cdn.example.com/receipt.jpg".
     * @param fileUrls      Required if file_url isn't specifies. List of publicly accessible URLs to multiple files, e.g. ["https://cdn.example.com/receipt1.jpg", "https://cdn.example.com/receipt2.jpg"]
     * @param parameters    Additional request parameters
     * @return the data extracted from the W2 {@link String}
     */
    protected String processW2Url(String fileUrl, List<String> fileUrls, JSONObject parameters) {
        parameters = addUrlToParameters(fileUrl, fileUrls, parameters);
        return request(HttpMethod.POST, Endpoint.w2s.path, parameters);
    }

    /**
     * Process W2 from url and extract all the fields from it. https://docs.veryfi.com/api/w2s/process-a-w-2/
     *
     * @param fileUrl       Required if file_urls isn't specified. Publicly accessible URL to a file, e.g. "https://cdn.example.com/receipt.jpg".
     * @param fileUrls      Required if file_url isn't specifies. List of publicly accessible URLs to multiple files, e.g. ["https://cdn.example.com/receipt1.jpg", "https://cdn.example.com/receipt2.jpg"]
     * @param parameters    Additional request parameters
     * @return the data extracted from the W2 {@link CompletableFuture<String>}
     */
    protected CompletableFuture<String> processW2UrlAsync(String fileUrl, List<String> fileUrls, JSONObject parameters) {
        parameters = addUrlToParameters(fileUrl, fileUrls, parameters);
        return requestAsync(HttpMethod.POST, Endpoint.w2s.path, parameters);
    }

    /**
     * Delete W2 from Veryfi. https://docs.veryfi.com/api/w2s/delete-a-w-2/
     *
     * @param documentId ID of the W2 you'd like to delete.
     * @return the response data. {@link String}
     */
    protected String deleteW2(String documentId) {
        String endpointName = Endpoint.w2s.path + documentId + "/";
        JSONObject parameters = new JSONObject();
        parameters.put("id", documentId);
        return request(HttpMethod.DELETE, endpointName, parameters);
    }

    /**
     * Delete W2 from Veryfi. https://docs.veryfi.com/api/w2s/delete-a-w-2/
     *
     * @param documentId ID of the W2 you'd like to delete.
     * @return the response data. {@link CompletableFuture<String>}
     */
    protected CompletableFuture<String> deleteW2Async(String documentId) {
        String endpointName = Endpoint.w2s.path + documentId + "/";
        JSONObject parameters = new JSONObject();
        parameters.put("id", documentId);
        return requestAsync(HttpMethod.DELETE, endpointName, parameters);
    }

}
