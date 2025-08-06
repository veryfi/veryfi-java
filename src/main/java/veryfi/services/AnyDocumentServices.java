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
 * API services for Any Document
 */
class AnyDocumentServices extends NetworkClient {

    /**
     * Creates an instance of {@link AnyDocumentServices}.
     *
     * @param credentials  the {@link Credentials} provided by Veryfi.
     * @param apiVersion   the {@link int} api version to use Veryfi.
     */
    protected AnyDocumentServices(Credentials credentials, int apiVersion) {
        super(credentials, apiVersion);
    }

    /**
     * Creates an instance of {@link AnyDocumentServices}.
     *
     * @param credentials  the {@link Credentials} provided by Veryfi.
     * @param apiVersion   the {@link int} api version to use Veryfi.
     * @param httpClient   {@link HttpClient} for the Veryfi API
     */
    protected AnyDocumentServices(Credentials credentials, int apiVersion, HttpClient httpClient) {
        super(credentials, apiVersion, httpClient);
    }

    /**
     * Returns a json string {@link String} with the list of Any Documents. https://docs.veryfi.com/api/anydocs/get-A-docs/
     *
     * @param page   The page number. The response is capped to maximum of 50 results per page.
     * @param pageSize The number of Documents per page.
     * @param boundingBoxes A field used to determine whether or not to return bounding_box and bounding_region for extracted fields in the Document response.
     * @param confidenceDetails A field used to determine whether or not to return the score and ocr_score fields in the Document response.
     * @param parameters Additional request parameters.
     * @return the url {@link String}
     */
    protected String getAnyDocuments(int page, int pageSize, boolean boundingBoxes, boolean confidenceDetails, JSONObject parameters) {
        if (parameters == null)
            parameters = new JSONObject();
        parameters.put("page", page);
        parameters.put("page_size", pageSize);
        parameters.put("bounding_boxes", boundingBoxes);
        parameters.put("confidence_details", confidenceDetails);
        return request(HttpMethod.GET, Endpoint.anyDocuments.path, parameters);
    }

    /**
     * Returns a json string {@link CompletableFuture}{@code <String>} list of Any Documents. https://docs.veryfi.com/api/anydocs/get-A-docs/
     *
     * @param page   The page number. The response is capped to maximum of 50 results per page.
     * @param pageSize The number of Documents per page.
     * @param boundingBoxes A field used to determine whether or not to return bounding_box and bounding_region for extracted fields in the Document response.
     * @param confidenceDetails A field used to determine whether or not to return the score and ocr_score fields in the Document response.
     * @param parameters Additional request parameters.
     * @return the list of previously processed Any Documents {@link String}
     */
    protected CompletableFuture<String> getAnyDocumentsAsync(int page, int pageSize, boolean boundingBoxes, boolean confidenceDetails, JSONObject parameters) {
        if (parameters == null)
            parameters = new JSONObject();
        parameters.put("page", page);
        parameters.put("page_size", pageSize);
        parameters.put("bounding_boxes", boundingBoxes);
        parameters.put("confidence_details", confidenceDetails);
        return requestAsync(HttpMethod.GET, Endpoint.anyDocuments.path, parameters);
    }

    /**
     * Returns a json string {@link String} Any Document information. https://docs.veryfi.com/api/anydocs/get-a-A-doc/
     *
     * @param documentId ID of the Any Document you'd like to retrieve.
     * @return the data extracted from the Any Document {@link String}
     */
    protected String getAnyDocument(String documentId) {
        String endpointName = Endpoint.anyDocuments.path + documentId + "/";
        JSONObject parameters = new JSONObject();
        parameters.put("id", documentId);
        return request(HttpMethod.GET, endpointName, parameters);
    }

    /**
     * Returns a json string {@link CompletableFuture}{@code <String>} Any Document information. https://docs.veryfi.com/api/anydocs/get-a-A-doc/
     *
     * @param documentId ID of the Any Document you'd like to retrieve.
     * @return the data extracted from the Any Document {@link String}
     */
    protected CompletableFuture<String> getAnyDocumentAsync(String documentId) {
        String endpointName = Endpoint.anyDocuments.path + documentId + "/";
        JSONObject parameters = new JSONObject();
        parameters.put("id", documentId);
        return requestAsync(HttpMethod.GET, endpointName, parameters);
    }

    /**
     * Process a Any Document and extract all the fields from it. https://docs.veryfi.com/api/anydocs/process-A-doc/
     *
     * @param filePath      Path on disk to a file to submit for data extraction.
     * @param blueprintName The name of the extraction blueprints.
     * @param parameters    Additional request parameters.
     * @return the data extracted from the Any Document {@link String}
     */
    protected String processAnyDocument(String filePath, String blueprintName, JSONObject parameters) {
        parameters = addFileToParameters(filePath, parameters);
        parameters.put("blueprint_name", blueprintName);
        return request(HttpMethod.POST, Endpoint.anyDocuments.path, parameters);
    }

    /**
     * Process a Any Document and extract all the fields from it. https://docs.veryfi.com/api/anydocs/process-A-doc/
     *
     * @param filePath      Path on disk to a file to submit for data extraction.
     * @param blueprintName The name of the extraction blueprints.
     * @param parameters    Additional request parameters.
     * @return the data extracted from the Any Document {@link CompletableFuture}{@code <String>}
     */
    protected CompletableFuture<String> processAnyDocumentAsync(String filePath, String blueprintName, JSONObject parameters) {
        parameters = addFileToParameters(filePath, parameters);
        parameters.put("blueprint_name", blueprintName);
        return requestAsync(HttpMethod.POST, Endpoint.anyDocuments.path, parameters);
    }

    /**
     * Process a Any Document and extract all the fields from it. https://docs.veryfi.com/api/anydocs/process-A-doc/
     *
     * @param fileName      Name of the file to upload to the Veryfi API
     * @param fileData      Base64 encoded file data
     * @param blueprintName The name of the extraction blueprints.
     * @param parameters    Additional request parameters.
     * @return the data extracted from the Any Document {@link String}
     */
    protected String processAnyDocument(String fileName, String fileData, String blueprintName, JSONObject parameters) {
        parameters = addFileToParameters(fileName, fileData, parameters);
        parameters.put("blueprint_name", blueprintName);
        return request(HttpMethod.POST, Endpoint.anyDocuments.path, parameters);
    }

    /**
     * Process a Any Document and extract all the fields from it. https://docs.veryfi.com/api/anydocs/process-A-doc/
     *
     * @param fileName      Name of the file to upload to the Veryfi API
     * @param fileData      Base64 encoded file data
     * @param blueprintName The name of the extraction blueprints.
     * @param parameters    Additional request parameters.
     * @return the data extracted from the Any Document {@link CompletableFuture}{@code <String>}
     */
    protected CompletableFuture<String> processAnyDocumentAsync(String fileName, String fileData, String blueprintName, JSONObject parameters) {
        parameters = addFileToParameters(fileName, fileData, parameters);
        parameters.put("blueprint_name", blueprintName);
        return requestAsync(HttpMethod.POST, Endpoint.anyDocuments.path, parameters);
    }

    /**
     * Process Any Document from url and extract all the fields from it. https://docs.veryfi.com/api/anydocs/process-A-doc/
     *
     * @param fileUrl       Required if file_urls isn't specified. Publicly accessible URL to a file, e.g. "https://cdn.example.com/receipt.jpg".
     * @param fileUrls      Required if file_url isn't specifies. List of publicly accessible URLs to multiple files, e.g. ["https://cdn.example.com/receipt1.jpg", "https://cdn.example.com/receipt2.jpg"]
     * @param blueprintName The name of the extraction blueprints.
     * @param parameters    Additional request parameters
     * @return the data extracted from the Any Document {@link String}
     */
    protected String processAnyDocumentUrl(String fileUrl, List<String> fileUrls, String blueprintName, JSONObject parameters) {
        parameters = addUrlToParameters(fileUrl, fileUrls, parameters);
        parameters.put("blueprint_name", blueprintName);
        return request(HttpMethod.POST, Endpoint.anyDocuments.path, parameters);
    }

    /**
     * Process Any Document from url and extract all the fields from it. https://docs.veryfi.com/api/anydocs/process-A-doc/
     *
     * @param fileUrl       Required if file_urls isn't specified. Publicly accessible URL to a file, e.g. "https://cdn.example.com/receipt.jpg".
     * @param fileUrls      Required if file_url isn't specifies. List of publicly accessible URLs to multiple files, e.g. ["https://cdn.example.com/receipt1.jpg", "https://cdn.example.com/receipt2.jpg"]
     * @param blueprintName The name of the extraction blueprints.
     * @param parameters    Additional request parameters
     * @return the data extracted from the Any Document {@link CompletableFuture}{@code <String>}
     */
    protected CompletableFuture<String> processAnyDocumentUrlAsync(String fileUrl, List<String> fileUrls, String blueprintName, JSONObject parameters) {
        parameters = addUrlToParameters(fileUrl, fileUrls, parameters);
        parameters.put("blueprint_name", blueprintName);
        return requestAsync(HttpMethod.POST, Endpoint.anyDocuments.path, parameters);
    }

    /**
     * Delete Any Document from Veryfi. https://docs.veryfi.com/api/anydocs/delete-a-A-doc/
     *
     * @param documentId ID of the Any Document you'd like to delete.
     * @return the response data. {@link String}
     */
    protected String deleteAnyDocument(String documentId) {
        String endpointName = Endpoint.anyDocuments.path + documentId + "/";
        JSONObject parameters = new JSONObject();
        parameters.put("id", documentId);
        return request(HttpMethod.DELETE, endpointName, parameters);
    }

    /**
     * Delete Any Document from Veryfi. https://docs.veryfi.com/api/anydocs/delete-a-A-doc/
     *
     * @param documentId ID of the Any Document you'd like to delete.
     * @return the response data. {@link CompletableFuture}{@code <String>}
     */
    protected CompletableFuture<String> deleteAnyDocumentAsync(String documentId) {
        String endpointName = Endpoint.anyDocuments.path + documentId + "/";
        JSONObject parameters = new JSONObject();
        parameters.put("id", documentId);
        return requestAsync(HttpMethod.DELETE, endpointName, parameters);
    }

}
