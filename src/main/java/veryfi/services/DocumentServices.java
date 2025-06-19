package veryfi.services;

import org.json.JSONObject;
import veryfi.Base64Helper;
import veryfi.Credentials;
import veryfi.NetworkClient;
import veryfi.enums.Endpoint;
import veryfi.enums.HttpMethod;

import java.io.File;
import java.net.http.HttpClient;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static veryfi.Constants.*;

/**
 * API services for documents
 */
class DocumentServices extends NetworkClient {

    /**
     * Creates an instance of {@link DocumentServices}.
     *
     * @param credentials  the {@link Credentials} provided by Veryfi.
     * @param apiVersion   the {@link int} api version to use Veryfi.
     */
    protected DocumentServices(Credentials credentials, int apiVersion) {
        super(credentials, apiVersion);
    }

    /**
     * Creates an instance of {@link DocumentServices}.
     *
     * @param credentials  the {@link Credentials} provided by Veryfi.
     * @param apiVersion   the {@link int} api version to use Veryfi.
     * @param httpClient   {@link HttpClient} for the Veryfi API
     */
    protected DocumentServices(Credentials credentials, int apiVersion, HttpClient httpClient) {
        super(credentials, apiVersion, httpClient);
    }

    /**
     * Returns a json string {@link String} with the list of documents. https://docs.veryfi.com/api/receipts-invoices/search-documents/
     *
     * @param page   The page number. The response is capped to maximum of 50 results per page.
     * @param pageSize The number of Documents per page.
     * @param boundingBoxes A field used to determine whether or not to return bounding_box and bounding_region for extracted fields in the Document response.
     * @param confidenceDetails A field used to determine whether or not to return the score and ocr_score fields in the Document response.
     * @param parameters Additional request parameters.
     * @return the url {@link String}
     */
    protected String getDocuments(int page, int pageSize, boolean boundingBoxes, boolean confidenceDetails, JSONObject parameters) {
        if (parameters == null)
            parameters = new JSONObject();
        parameters.put("page", page);
        parameters.put("page_size", pageSize);
        parameters.put("bounding_boxes", boundingBoxes);
        parameters.put("confidence_details", confidenceDetails);
        return request(HttpMethod.GET, Endpoint.documents.path, parameters);
    }

    /**
     * Returns a json string {@link CompletableFuture<String>} list of documents. https://docs.veryfi.com/api/receipts-invoices/search-documents/
     *
     * @param page   The page number. The response is capped to maximum of 50 results per page.
     * @param pageSize The number of Documents per page.
     * @param boundingBoxes A field used to determine whether or not to return bounding_box and bounding_region for extracted fields in the Document response.
     * @param confidenceDetails A field used to determine whether or not to return the score and ocr_score fields in the Document response.
     * @param parameters Additional request parameters.
     * @return the list of previously processed documents {@link String}
     */
    protected CompletableFuture<String> getDocumentsAsync(int page, int pageSize, boolean boundingBoxes, boolean confidenceDetails, JSONObject parameters) {
        if (parameters == null)
            parameters = new JSONObject();
        parameters.put("page", page);
        parameters.put("page_size", pageSize);
        parameters.put("bounding_boxes", boundingBoxes);
        parameters.put("confidence_details", confidenceDetails);
        return requestAsync(HttpMethod.GET, Endpoint.documents.path, parameters);
    }

    /**
     * Returns a json string {@link String} document information. https://docs.veryfi.com/api/receipts-invoices/get-a-document/
     *
     * @param documentId ID of the document you'd like to retrieve.
     * @return the data extracted from the Document {@link String}
     */
    protected String getDocument(String documentId) {
        String endpointName = Endpoint.documents.path + documentId + "/";
        JSONObject requestArguments = new JSONObject();
        requestArguments.put("id", documentId);
        return request(HttpMethod.GET, endpointName, requestArguments);
    }

    /**
     * Returns a json string {@link CompletableFuture<String>} document information. https://docs.veryfi.com/api/receipts-invoices/get-a-document/
     *
     * @param documentId ID of the document you'd like to retrieve.
     * @return the data extracted from the Document {@link String}
     */
    protected CompletableFuture<String> getDocumentAsync(String documentId) {
        String endpointName = Endpoint.documents.path + documentId + "/";
        JSONObject requestArguments = new JSONObject();
        requestArguments.put("id", documentId);
        return requestAsync(HttpMethod.GET, endpointName, requestArguments);
    }

    /**
     * Process a document and extract all the fields from it. https://docs.veryfi.com/api/receipts-invoices/process-a-document/
     *
     * @param filePath              Path on disk to a file to submit for data extraction
     * @param categories            List of categories Veryfi can use to categorize the document
     * @param deleteAfterProcessing Delete this document from Veryfi after data has been extracted
     * @param parameters            Additional request parameters
     * @return the data extracted from the Document {@link String}
     */
    protected String processDocument(String filePath, List<String> categories, boolean deleteAfterProcessing,
                                  JSONObject parameters) {
        JSONObject requestArguments = getProcessDocumentArguments(filePath, categories, deleteAfterProcessing, parameters);
        return request(HttpMethod.POST, Endpoint.documents.path, requestArguments);
    }

    /**
     * Process a document and extract all the fields from it. https://docs.veryfi.com/api/receipts-invoices/process-a-document/
     *
     * @param filePath              Path on disk to a file to submit for data extraction
     * @param categories            List of categories Veryfi can use to categorize the document
     * @param deleteAfterProcessing Delete this document from Veryfi after data has been extracted
     * @param parameters            Additional request parameters
     * @return the data extracted from the Document {@link CompletableFuture<String>}
     */
    protected CompletableFuture<String> processDocumentAsync(String filePath, List<String> categories,
                                                          boolean deleteAfterProcessing, JSONObject parameters) {
        JSONObject requestArguments = getProcessDocumentArguments(filePath, categories, deleteAfterProcessing, parameters);
        return requestAsync(HttpMethod.POST, Endpoint.documents.path, requestArguments);
    }

    /**
     * Process a document and extract all the fields from it. https://docs.veryfi.com/api/receipts-invoices/process-a-document/
     *
     * @param fileName              Name of the file to upload to the Veryfi API
     * @param fileData              Base64 encoded file data
     * @param categories            List of categories Veryfi can use to categorize the document
     * @param deleteAfterProcessing Delete this document from Veryfi after data has been extracted
     * @param parameters            Additional request parameters
     * @return the data extracted from the Document {@link String}
     */
    protected String processDocument(String fileName, String fileData, List<String> categories,
                                     boolean deleteAfterProcessing, JSONObject parameters) {
        JSONObject requestArguments = getProcessDocumentArguments(fileName, fileData, categories, deleteAfterProcessing, parameters);
        return request(HttpMethod.POST, Endpoint.documents.path, requestArguments);
    }

    /**
     * Process a document and extract all the fields from it. https://docs.veryfi.com/api/receipts-invoices/process-a-document/
     *
     * @param fileName              Name of the file to upload to the Veryfi API
     * @param fileData              Base64 encoded file data
     * @param categories            List of categories Veryfi can use to categorize the document
     * @param deleteAfterProcessing Delete this document from Veryfi after data has been extracted
     * @param parameters            Additional request parameters
     * @return the data extracted from the Document {@link CompletableFuture<String>}
     */
    protected CompletableFuture<String> processDocumentAsync(String fileName, String fileData, List<String> categories,
                                                             boolean deleteAfterProcessing, JSONObject parameters) {
        JSONObject requestArguments = getProcessDocumentArguments(fileName, fileData, categories, deleteAfterProcessing, parameters);
        return requestAsync(HttpMethod.POST, Endpoint.documents.path, requestArguments);
    }

    /**
     * Process Document from url and extract all the fields from it. https://docs.veryfi.com/api/receipts-invoices/process-a-document/
     *
     * @param fileUrl               Required if file_urls isn't specified. Publicly accessible URL to a file, e.g. "https://cdn.example.com/receipt.jpg".
     * @param fileUrls              Required if file_url isn't specifies. List of publicly accessible URLs to multiple files, e.g. ["https://cdn.example.com/receipt1.jpg", "https://cdn.example.com/receipt2.jpg"]
     * @param categories            List of categories to use when categorizing the document
     * @param deleteAfterProcessing Delete this document from Veryfi after data has been extracted
     * @param maxPagesToProcess     When sending a long document to Veryfi for processing, this parameter controls how many pages of the document will be read and processed, starting from page 1.
     * @param boostMode             Flag that tells Veryfi whether boost mode should be enabled. When set to 1, Veryfi will skip data enrichment steps, but will process the document faster. Default value for this flag is 0
     * @param externalId            Optional custom document identifier. Use this if you would like to assign your own ID to documents
     * @param parameters            Additional request parameters
     * @return the data extracted from the Document {@link String}
     */
    protected String processDocumentUrl(String fileUrl, List<String> fileUrls, List<String> categories,
                                     boolean deleteAfterProcessing, int maxPagesToProcess,
                                     boolean boostMode, String externalId, JSONObject parameters) {
        JSONObject requestArguments = getProcessDocumentUrlArguments(fileUrl, fileUrls, categories, deleteAfterProcessing, maxPagesToProcess, boostMode, externalId, parameters);
        return request(HttpMethod.POST, Endpoint.documents.path, requestArguments);
    }

    /**
     * Process Document from url and extract all the fields from it. https://docs.veryfi.com/api/receipts-invoices/process-a-document/
     *
     * @param fileUrl               Required if file_urls isn't specified. Publicly accessible URL to a file, e.g. "https://cdn.example.com/receipt.jpg".
     * @param fileUrls              Required if file_url isn't specifies. List of publicly accessible URLs to multiple files, e.g. ["https://cdn.example.com/receipt1.jpg", "https://cdn.example.com/receipt2.jpg"]
     * @param categories            List of categories to use when categorizing the document
     * @param deleteAfterProcessing Delete this document from Veryfi after data has been extracted
     * @param maxPagesToProcess     When sending a long document to Veryfi for processing, this parameter controls how many pages of the document will be read and processed, starting from page 1.
     * @param boostMode             Flag that tells Veryfi whether boost mode should be enabled. When set to 1, Veryfi will skip data enrichment steps, but will process the document faster. Default value for this flag is 0
     * @param externalId            Optional custom document identifier. Use this if you would like to assign your own ID to documents
     * @param parameters            Additional request parameters
     * @return the data extracted from the Document {@link CompletableFuture<String>}
     */
    protected CompletableFuture<String> processDocumentUrlAsync(String fileUrl, List<String> fileUrls,
                                                             List<String> categories, boolean deleteAfterProcessing,
                                                             int maxPagesToProcess, boolean boostMode,
                                                             String externalId, JSONObject parameters) {
        JSONObject requestArguments = getProcessDocumentUrlArguments(fileUrl, fileUrls, categories, deleteAfterProcessing, maxPagesToProcess, boostMode, externalId, parameters);
        return requestAsync(HttpMethod.POST, Endpoint.documents.path, requestArguments);
    }

    /**
     * Delete Document from Veryfi. https://docs.veryfi.com/api/receipts-invoices/delete-a-document/
     *
     * @param documentId ID of the document you'd like to delete.
     * @return the response data. {@link String}
     */
    protected String deleteDocument(String documentId) {
        String endpointName = Endpoint.documents.path + documentId + "/";
        JSONObject requestArguments = new JSONObject();
        requestArguments.put("id", documentId);
        return request(HttpMethod.DELETE, endpointName, requestArguments);
    }

    /**
     * Delete Document from Veryfi. https://docs.veryfi.com/api/receipts-invoices/delete-a-document/
     *
     * @param documentId ID of the document you'd like to delete.
     * @return the response data. {@link CompletableFuture<String>}
     */
    protected CompletableFuture<String> deleteDocumentAsync(String documentId) {
        String endpointName = Endpoint.documents.path + documentId + "/";
        JSONObject requestArguments = new JSONObject();
        requestArguments.put("id", documentId);
        return requestAsync(HttpMethod.DELETE, endpointName, requestArguments);
    }

    /**
     * Update data for a previously processed document, including almost any field like `vendor`, `date`, `notes` etc.
     * https://docs.veryfi.com/api/receipts-invoices/update-a-document/
     * @param documentId ID of the document you'd like to update.
     * @param parameters Additional request parameters
     * @return A document json with updated fields, if fields are writable. Otherwise, a document with unchanged fields. {@link String}
     */
    protected String updateDocument(String documentId, JSONObject parameters) {
        String endpointName = Endpoint.documents.path + documentId + "/";
        return request(HttpMethod.PUT, endpointName, parameters);
    }

    /**
     * Update data for a previously processed document, including almost any field like `vendor`, `date`, `notes` etc.
     * https://docs.veryfi.com/api/receipts-invoices/update-a-document/
     * @param documentId ID of the document you'd like to update.
     * @param parameters Additional request parameters
     * @return A document json with updated fields, if fields are writable. Otherwise, a document with unchanged fields. {@link CompletableFuture<String>}
     */
    protected CompletableFuture<String> updateDocumentAsync(String documentId, JSONObject parameters) {
        String endpointName = Endpoint.documents.path + documentId + "/";
        return requestAsync(HttpMethod.PUT, endpointName, parameters);
    }

    /**
     * Creates the JSON Object for the parameters of the request
     *
     * @param fileName              Name of the file to upload to the Veryfi API
     * @param fileData              Base64 encoded file data
     * @param categories            List of categories Veryfi can use to categorize the document
     * @param deleteAfterProcessing Delete this document from Veryfi after data has been extracted
     * @param parameters            Additional request parameters
     * @return the JSON object of the parameters of the request
     */
    private JSONObject getProcessDocumentArguments(String fileName, String fileData, List<String> categories,
                                                   boolean deleteAfterProcessing, JSONObject parameters) {
        if (categories == null || categories.isEmpty()) {
            categories = LIST_CATEGORIES;
        }
        JSONObject requestArguments = new JSONObject();
        requestArguments.put(FILE_NAME, fileName);
        requestArguments.put(FILE_DATA, fileData);
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
     * Creates the JSON Object for the parameters of the request
     *
     * @param filePath              Path on disk to a file to submit for data extraction
     * @param categories            List of categories Veryfi can use to categorize the document
     * @param deleteAfterProcessing Delete this document from Veryfi after data has been extracted
     * @param parameters            Additional request parameters
     * @return the JSON object of the parameters of the request
     */
    private JSONObject getProcessDocumentArguments(String filePath, List<String> categories,
                                                  boolean deleteAfterProcessing, JSONObject parameters) {
        String fileName = filePath.replaceAll("^.*[/\\\\]", "");
        File file = new File(filePath);
        String fileData = "";
        try {
            fileData = Base64Helper.getBase64FileContent(file);
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
        return getProcessDocumentArguments(fileName, fileData, categories, deleteAfterProcessing, parameters);
    }

    /**
     * Creates the JSON object of the parameters of the request
     *
     * @param fileUrl               Required if file_urls isn't specified. Publicly accessible URL to a file, e.g. "https://cdn.example.com/receipt.jpg".
     * @param fileUrls              Required if file_url isn't specifies. List of publicly accessible URLs to multiple files, e.g. ["https://cdn.example.com/receipt1.jpg", "https://cdn.example.com/receipt2.jpg"]
     * @param categories            List of categories to use when categorizing the document
     * @param deleteAfterProcessing Delete this document from Veryfi after data has been extracted
     * @param maxPagesToProcess     When sending a long document to Veryfi for processing, this parameter controls how many pages of the document will be read and processed, starting from page 1.
     * @param boostMode             Flag that tells Veryfi whether boost mode should be enabled. When set to 1, Veryfi will skip data enrichment steps, but will process the document faster. Default value for this flag is 0
     * @param externalId            Optional custom document identifier. Use this if you would like to assign your own ID to documents
     * @param parameters            Additional request parameters
     * @return JSON object of the request arguments
     */
    private JSONObject getProcessDocumentUrlArguments(String fileUrl, List<String> fileUrls, List<String> categories,
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
}
