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
 * API services for PDF Split Processing
 */
public class SplitServices extends NetworkClient {

    /**
     * Creates an instance of {@link SplitServices}.
     *
     * @param credentials  the {@link Credentials} provided by Veryfi.
     * @param apiVersion   the {@link int} api version to use Veryfi.
     */
    protected SplitServices(Credentials credentials, int apiVersion) {
        super(credentials, apiVersion);
    }

    /**
     * Creates an instance of {@link SplitServices}.
     *
     * @param credentials  the {@link Credentials} provided by Veryfi.
     * @param apiVersion   the {@link int} api version to use Veryfi.
     * @param httpClient   {@link HttpClient} for the Veryfi API
     */
    protected SplitServices(Credentials credentials, int apiVersion, HttpClient httpClient) {
        super(credentials, apiVersion, httpClient);
    }

    /**
     * Split document PDF from url and extract all the fields from it. https://docs.veryfi.com/api/receipts-invoices/split-and-process-a-pdf/
     *
     * @param filePath      Path on disk to a file to submit for data extraction.
     * @param parameters    Additional request parameters.
     * @return the data extracted from the document {@link String}
     */
    protected String splitDocument(String filePath, JSONObject parameters) {
        parameters = addFileToParameters(filePath, parameters);
        return request(HttpMethod.POST, Endpoint.split.path, parameters);
    }

    /**
     * Split document PDF from url and extract all the fields from it. https://docs.veryfi.com/api/receipts-invoices/split-and-process-a-pdf/
     *
     * @param filePath      Path on disk to a file to submit for data extraction.
     * @param parameters    Additional request parameters.
     * @return the data extracted from the document {@link CompletableFuture <String>}
     */
    protected CompletableFuture<String> splitDocumentAsync(String filePath, JSONObject parameters) {
        parameters = addFileToParameters(filePath, parameters);
        return requestAsync(HttpMethod.POST, Endpoint.split.path, parameters);
    }

    /**
     * Split document PDF from url and extract all the fields from it. https://docs.veryfi.com/api/receipts-invoices/split-and-process-a-pdf/
     *
     * @param fileName      Name of the file to upload to the Veryfi API
     * @param fileData      Base64 encoded file data
     * @param parameters    Additional request parameters.
     * @return the data extracted from the document {@link String}
     */
    protected String splitDocument(String fileName, String fileData, JSONObject parameters) {
        parameters = addFileToParameters(fileName, fileData, parameters);
        return request(HttpMethod.POST, Endpoint.split.path, parameters);
    }

    /**
     * Split document PDF from url and extract all the fields from it. https://docs.veryfi.com/api/receipts-invoices/split-and-process-a-pdf/
     *
     * @param fileName      Name of the file to upload to the Veryfi API
     * @param fileData      Base64 encoded file data
     * @param parameters    Additional request parameters.
     * @return the data extracted from the document {@link CompletableFuture<String>}
     */
    protected CompletableFuture<String> splitDocumentAsync(String fileName, String fileData, JSONObject parameters) {
        parameters = addFileToParameters(fileName, fileData, parameters);
        return requestAsync(HttpMethod.POST, Endpoint.split.path, parameters);
    }

    /**
     * Split document PDF from url and extract all the fields from it. https://docs.veryfi.com/api/receipts-invoices/split-and-process-a-pdf/
     *
     * @param fileUrl       Required if file_urls isn't specified. Publicly accessible URL to a file, e.g. "https://cdn.example.com/receipt.jpg".
     * @param fileUrls      Required if file_url isn't specifies. List of publicly accessible URLs to multiple files, e.g. ["https://cdn.example.com/receipt1.jpg", "https://cdn.example.com/receipt2.jpg"]
     * @param parameters    Additional request parameters.
     * @return the data extracted from the document {@link String}
     */
    protected String splitDocumentUrl(String fileUrl, List<String> fileUrls, JSONObject parameters) {
        parameters = addUrlToParameters(fileUrl, fileUrls, parameters);
        return request(HttpMethod.POST, Endpoint.split.path, parameters);
    }

    /**
     * Split document PDF from url and extract all the fields from it. https://docs.veryfi.com/api/receipts-invoices/split-and-process-a-pdf/
     *
     * @param fileUrl       Required if file_urls isn't specified. Publicly accessible URL to a file, e.g. "https://cdn.example.com/receipt.jpg".
     * @param fileUrls      Required if file_url isn't specifies. List of publicly accessible URLs to multiple files, e.g. ["https://cdn.example.com/receipt1.jpg", "https://cdn.example.com/receipt2.jpg"]
     * @param parameters    Additional request parameters.
     * @return the data extracted from the document {@link CompletableFuture<String>}
     */
    protected CompletableFuture<String> splitDocumentUrlAsync(String fileUrl, List<String> fileUrls, JSONObject parameters) {
        parameters = addUrlToParameters(fileUrl, fileUrls, parameters);
        return requestAsync(HttpMethod.POST, Endpoint.split.path, parameters);
    }

    /**
     * Veryfi's Get a Submitted PDF endpoint allows you to retrieve a collection of previously processed documents. https://docs.veryfi.com/api/receipts-invoices/get-submitted-pdf/
     *
     * @param page   The page number. The response is capped to maximum of 50 results per page.
     * @param pageSize The number of Documents per page.
     * @param boundingBoxes A field used to determine whether or not to return bounding_box and bounding_region for extracted fields in the Document response.
     * @param confidenceDetails A field used to determine whether or not to return the score and ocr_score fields in the Document response.
     * @param parameters Additional request parameters.
     * @return JSON object of previously processed documents {@link String}
     */
    protected String getSplitDocuments(int page, int pageSize, boolean boundingBoxes, boolean confidenceDetails, JSONObject parameters) {
        if (parameters == null)
            parameters = new JSONObject();
        parameters.put("page", page);
        parameters.put("page_size", pageSize);
        parameters.put("bounding_boxes", boundingBoxes);
        parameters.put("confidence_details", confidenceDetails);
        return request(HttpMethod.GET, Endpoint.split.path, parameters);
    }

    /**
     * Veryfi's Get a Submitted PDF endpoint allows you to retrieve a collection of previously processed documents. https://docs.veryfi.com/api/receipts-invoices/get-submitted-pdf/
     *
     * @param page   The page number. The response is capped to maximum of 50 results per page.
     * @param pageSize The number of Documents per page.
     * @param boundingBoxes A field used to determine whether or not to return bounding_box and bounding_region for extracted fields in the Document response.
     * @param confidenceDetails A field used to determine whether or not to return the score and ocr_score fields in the Document response.
     * @param parameters Additional request parameters.
     * @return JSON object of previously processed documents {@link String}
     */
    protected CompletableFuture<String> getSplitDocumentsAsync(int page, int pageSize, boolean boundingBoxes, boolean confidenceDetails, JSONObject parameters) {
        if (parameters == null)
            parameters = new JSONObject();
        parameters.put("page", page);
        parameters.put("page_size", pageSize);
        parameters.put("bounding_boxes", boundingBoxes);
        parameters.put("confidence_details", confidenceDetails);
        return requestAsync(HttpMethod.GET, Endpoint.split.path, parameters);
    }

    /**
     * Veryfi's Get a Documents from PDF endpoint allows you to retrieve a collection of previously processed documents. https://docs.veryfi.com/api/receipts-invoices/get-documents-from-pdf/
     *
     * @param documentId ID of the document you'd like to retrieve.
     * @return the data extracted from the document {@link String}
     */
    protected String getSplitDocument(String documentId) {
        String endpointName = Endpoint.split.path + documentId + "/";
        JSONObject parameters = new JSONObject();
        parameters.put("id", documentId);
        return request(HttpMethod.GET, endpointName, parameters);
    }

    /**
     * Veryfi's Get a Documents from PDF endpoint allows you to retrieve a collection of previously processed documents. https://docs.veryfi.com/api/receipts-invoices/get-documents-from-pdf/
     *
     * @param documentId ID of the document you'd like to retrieve.
     * @return the data extracted from the document {@link CompletableFuture<String>}
     */
    protected CompletableFuture<String> getSplitDocumentAsync(String documentId) {
        String endpointName = Endpoint.split.path + documentId + "/";
        JSONObject parameters = new JSONObject();
        parameters.put("id", documentId);
        return requestAsync(HttpMethod.GET, endpointName, parameters);
    }

}
