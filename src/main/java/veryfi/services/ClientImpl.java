package veryfi.services;

import org.json.JSONObject;
import veryfi.Client;
import veryfi.Credentials;
import veryfi.models.AddLineItem;
import veryfi.models.NotValidModelException;
import veryfi.models.UpdateLineItem;

import java.net.http.HttpClient;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Veryfi API client for Java.
 */
public class ClientImpl implements Client {

    private final DocumentServices documentServices;
    private final LineItemServices lineItemServices;
    private final TagServices tagServices;
    private final AnyDocumentServices anyDocumentServices;
    private final BankStatementServices bankStatementServices;
    private final BusinessCardsServices businessCardsServices;
    private final CheckServices checkServices;
    private final W2Services w2Services;
    private final W9Services w9Services;
    private final W8BenEServices w8BenEServices;
    private final ContractServices contractServices;

    /**
     * Creates an instance of {@link ClientImpl}.
     *
     * @param clientId     the {@link String} provided by Veryfi.
     * @param clientSecret the {@link String} provided by Veryfi.
     * @param username     the {@link String} provided by Veryfi.
     * @param apiKey       the {@link String} provided by Veryfi.
     * @param apiVersion   the {@link int} api version to use Veryfi.
     */
    public ClientImpl(String clientId, String clientSecret, String username, String apiKey, int apiVersion) {
        Credentials credentials = new Credentials(clientId, clientSecret, username, apiKey);
        documentServices = new DocumentServices(credentials, apiVersion);
        lineItemServices = new LineItemServices(credentials, apiVersion);
        tagServices = new TagServices(credentials, apiVersion);
        anyDocumentServices = new AnyDocumentServices(credentials, apiVersion);
        bankStatementServices = new BankStatementServices(credentials, apiVersion);
        businessCardsServices = new BusinessCardsServices(credentials, apiVersion);
        checkServices = new CheckServices(credentials, apiVersion);
        w2Services = new W2Services(credentials, apiVersion);
        w9Services = new W9Services(credentials, apiVersion);
        w8BenEServices = new W8BenEServices(credentials, apiVersion);
        contractServices = new ContractServices(credentials, apiVersion);
    }

    /**
     * Creates an instance of {@link ClientImpl}.
     *
     * @param clientId     the {@link String} provided by Veryfi.
     * @param clientSecret the {@link String} provided by Veryfi.
     * @param username     the {@link String} provided by Veryfi.
     * @param apiKey       the {@link String} provided by Veryfi.
     * @param apiVersion   the {@link int} api version to use Veryfi.
     * @param httpClient   {@link HttpClient} for the Veryfi API
     */
    public ClientImpl(String clientId, String clientSecret, String username, String apiKey, int apiVersion, HttpClient httpClient) {
        Credentials credentials = new Credentials(clientId, clientSecret, username, apiKey);
        documentServices = new DocumentServices(credentials, apiVersion, httpClient);
        lineItemServices = new LineItemServices(credentials, apiVersion, httpClient);
        tagServices = new TagServices(credentials, apiVersion, httpClient);
        anyDocumentServices = new AnyDocumentServices(credentials, apiVersion, httpClient);
        bankStatementServices = new BankStatementServices(credentials, apiVersion, httpClient);
        businessCardsServices = new BusinessCardsServices(credentials, apiVersion, httpClient);
        checkServices = new CheckServices(credentials, apiVersion, httpClient);
        w2Services = new W2Services(credentials, apiVersion, httpClient);
        w9Services = new W9Services(credentials, apiVersion, httpClient);
        w8BenEServices = new W8BenEServices(credentials, apiVersion, httpClient);
        contractServices = new ContractServices(credentials, apiVersion, httpClient);
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
    @Override
    public String getDocuments(int page, int pageSize, boolean boundingBoxes, boolean confidenceDetails, JSONObject parameters) {
        return documentServices.getDocuments(page, pageSize, boundingBoxes, confidenceDetails, parameters);
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
    @Override
    public CompletableFuture<String> getDocumentsAsync(int page, int pageSize, boolean boundingBoxes, boolean confidenceDetails, JSONObject parameters) {
        return documentServices.getDocumentsAsync(page, pageSize, boundingBoxes, confidenceDetails, parameters);
    }

    /**
     * Returns a json string {@link String} document information. https://docs.veryfi.com/api/receipts-invoices/get-a-document/
     *
     * @param documentId ID of the document you'd like to retrieve.
     * @return the data extracted from the Document {@link String}
     */
    @Override
    public String getDocument(String documentId) {
        return documentServices.getDocument(documentId);
    }

    /**
     * Returns a json string {@link CompletableFuture<String>} document information. https://docs.veryfi.com/api/receipts-invoices/get-a-document/
     *
     * @param documentId ID of the document you'd like to retrieve.
     * @return the data extracted from the Document {@link String}
     */
    @Override
    public CompletableFuture<String> getDocumentAsync(String documentId) {
        return documentServices.getDocumentAsync(documentId);
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
    @Override
    public String processDocument(String filePath, List<String> categories, boolean deleteAfterProcessing,
                                  JSONObject parameters) {
        return documentServices.processDocument(filePath, categories, deleteAfterProcessing, parameters);
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
    @Override
    public CompletableFuture<String> processDocumentAsync(String filePath, List<String> categories,
                                                          boolean deleteAfterProcessing, JSONObject parameters) {
        return documentServices.processDocumentAsync(filePath, categories, deleteAfterProcessing, parameters);
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
    @Override
    public String processDocument(String fileName, String fileData, List<String> categories,
                                  boolean deleteAfterProcessing, JSONObject parameters) {
        return documentServices.processDocument(fileName, fileData, categories, deleteAfterProcessing, parameters);
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
    @Override
    public CompletableFuture<String> processDocumentAsync(String fileName, String fileData, List<String> categories,
                                                          boolean deleteAfterProcessing, JSONObject parameters) {
        return documentServices.processDocumentAsync(fileName, fileData, categories, deleteAfterProcessing, parameters);
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
    @Override
    public String processDocumentUrl(String fileUrl, List<String> fileUrls, List<String> categories,
                                     boolean deleteAfterProcessing, int maxPagesToProcess,
                                     boolean boostMode, String externalId, JSONObject parameters) {

        return documentServices.processDocumentUrl(fileUrl, fileUrls, categories, deleteAfterProcessing,
                maxPagesToProcess, boostMode, externalId, parameters);
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
    @Override
    public CompletableFuture<String> processDocumentUrlAsync(String fileUrl, List<String> fileUrls,
                                                             List<String> categories, boolean deleteAfterProcessing,
                                                             int maxPagesToProcess, boolean boostMode,
                                                             String externalId, JSONObject parameters) {
        return documentServices.processDocumentUrlAsync(fileUrl, fileUrls, categories, deleteAfterProcessing,
                maxPagesToProcess, boostMode, externalId, parameters);
    }

    /**
     * Delete Document from Veryfi. https://docs.veryfi.com/api/receipts-invoices/delete-a-document/
     *
     * @param documentId ID of the document you'd like to delete.
     * @return the response data. {@link String}
     */
    @Override
    public String deleteDocument(String documentId) {
        return documentServices.deleteDocument(documentId);
    }

    /**
     * Delete Document from Veryfi. https://docs.veryfi.com/api/receipts-invoices/delete-a-document/
     *
     * @param documentId ID of the document you'd like to delete.
     * @return the response data. {@link CompletableFuture<String>}
     */
    @Override
    public CompletableFuture<String> deleteDocumentAsync(String documentId) {
        return documentServices.deleteDocumentAsync(documentId);
    }

    /**
     * Update data for a previously processed document, including almost any field like `vendor`, `date`, `notes` etc.
     * https://docs.veryfi.com/api/receipts-invoices/update-a-document/
     * @param documentId ID of the document you'd like to update.
     * @param parameters Additional request parameters
     * @return A document json with updated fields, if fields are writable. Otherwise, a document with unchanged fields. {@link String}
     */
    @Override
    public String updateDocument(String documentId, JSONObject parameters) {
        return documentServices.updateDocument(documentId, parameters);
    }

    /**
     * Update data for a previously processed document, including almost any field like `vendor`, `date`, `notes` etc.
     * https://docs.veryfi.com/api/receipts-invoices/update-a-document/
     * @param documentId ID of the document you'd like to update.
     * @param parameters Additional request parameters
     * @return A document json with updated fields, if fields are writable. Otherwise, a document with unchanged fields. {@link CompletableFuture<String>}
     */
    @Override
    public CompletableFuture<String> updateDocumentAsync(String documentId, JSONObject parameters) {
        return documentServices.updateDocumentAsync(documentId, parameters);
    }

    /**
     * Retrieve all line items for a document. https://docs.veryfi.com/api/receipts-invoices/get-document-line-items/
     *
     * @param documentId ID of the document you'd like to retrieve.
     * @return List of line items extracted from the document. {@link String}
     */
    @Override
    public String getLineItems(String documentId) {
        return lineItemServices.getLineItems(documentId);
    }

    /**
     * Retrieve all line items for a document. https://docs.veryfi.com/api/receipts-invoices/get-document-line-items/
     *
     * @param documentId ID of the document you'd like to retrieve.
     * @return List of line items extracted from the document. {@link CompletableFuture<String>}
     */
    @Override
    public CompletableFuture<String> getLineItemsAsync(String documentId) {
        return lineItemServices.getLineItemsAsync(documentId);
    }

    /**
     * Retrieve a line item for existing document by ID. https://docs.veryfi.com/api/receipts-invoices/get-a-line-item/
     *
     * @param documentId ID of the document you'd like to retrieve.
     * @param lineItemId ID of the line item you'd like to retrieve.
     * @return Line item extracted from the document. {@link String}
     */
    @Override
    public String getLineItem(String documentId, String lineItemId) {
        return lineItemServices.getLineItem(documentId, lineItemId);
    }

    /**
     * Retrieve a line item for existing document by ID. https://docs.veryfi.com/api/receipts-invoices/get-a-line-item/
     *
     * @param documentId ID of the document you'd like to retrieve.
     * @param lineItemId ID of the line item you'd like to retrieve.
     * @return Line item extracted from the document. {@link CompletableFuture<String>}
     */
    @Override
    public CompletableFuture<String> getLineItemAsync(String documentId, String lineItemId) {
        return lineItemServices.getLineItemAsync(documentId, lineItemId);
    }

    /**
     * Add a new line item on an existing document. https://docs.veryfi.com/api/receipts-invoices/create-a-line-item/
     *
     * @param documentId ID of the document you'd like to update.
     * @param payload    line item object to add.
     * @return Added line item data. {@link String}
     * @throws NotValidModelException when the model is not valid it throws this exception.
     */
    @Override
    public String addLineItem(String documentId, AddLineItem payload) throws NotValidModelException {
        return lineItemServices.addLineItem(documentId, payload);
    }

    /**
     * Add a new line item on an existing document. https://docs.veryfi.com/api/receipts-invoices/create-a-line-item/
     *
     * @param documentId ID of the document you'd like to update.
     * @param payload    line item object to add.
     * @return Added line item data. {@link CompletableFuture<String>}
     * @throws NotValidModelException when the model is not valid it throws this exception.
     */
    @Override
    public CompletableFuture<String> addLineItemAsync(String documentId, AddLineItem payload) throws NotValidModelException {
        return lineItemServices.addLineItemAsync(documentId, payload);
    }

    /**
     * Update an existing line item on an existing document. https://docs.veryfi.com/api/receipts-invoices/update-a-line-item/
     *
     * @param documentId ID of the document you'd like to update.
     * @param lineItemId ID of the line item you'd like to update.
     * @param payload    line item object to update.
     * @return Line item data with updated fields, if fields are writable. Otherwise line item data with unchanged fields. {@link String}
     * @throws NotValidModelException when the model is not valid it throws this exception.
     */
    @Override
    public String updateLineItem(String documentId, String lineItemId, UpdateLineItem payload) throws NotValidModelException {
        return lineItemServices.updateLineItem(documentId, lineItemId, payload);
    }

    /**
     * Update an existing line item on an existing document. https://docs.veryfi.com/api/receipts-invoices/update-a-line-item/
     *
     * @param documentId ID of the document you'd like to update.
     * @param lineItemId ID of the line item you'd like to update.
     * @param payload    line item object to update.
     * @return Line item data with updated fields, if fields are writable. Otherwise line item data with unchanged fields. {@link CompletableFuture<String>}
     * @throws NotValidModelException when the model is not valid it throws this exception.
     */
    @Override
    public CompletableFuture<String> updateLineItemAsync(String documentId, String lineItemId, UpdateLineItem payload) throws NotValidModelException {
        return lineItemServices.updateLineItemAsync(documentId, lineItemId, payload);
    }

    /**
     * Delete all line items on an existing document. https://docs.veryfi.com/api/receipts-invoices/delete-all-document-line-items/
     *
     * @param documentId ID of the document you'd like to delete.
     * @return the response data. {@link String}
     */
    @Override
    public String deleteLineItems(String documentId) {
        return lineItemServices.deleteLineItems(documentId);
    }

    /**
     * Delete all line items on an existing document. https://docs.veryfi.com/api/receipts-invoices/delete-all-document-line-items/
     *
     * @param documentId ID of the document you'd like to delete.
     * @return @return the response data. {@link CompletableFuture<String>}
     */
    @Override
    public CompletableFuture<String> deleteLineItemsAsync(String documentId) {
        return lineItemServices.deleteLineItemsAsync(documentId);
    }

    /**
     * Delete an existing line item on an existing document. https://docs.veryfi.com/api/receipts-invoices/delete-a-line-item/
     *
     * @param documentId ID of the document you'd like to delete.
     * @param lineItemId ID of the line item you'd like to delete.
     * @return the response data. {@link String}
     */
    @Override
    public String deleteLineItem(String documentId, String lineItemId) {
        return lineItemServices.deleteLineItem(documentId, lineItemId);
    }

    /**
     * Delete an existing line item on an existing document. https://docs.veryfi.com/api/receipts-invoices/delete-a-line-item/
     *
     * @param documentId ID of the document you'd like to delete.
     * @param lineItemId ID of the line item you'd like to delete.
     * @return the response data. {@link CompletableFuture<String>}
     */
    @Override
    public CompletableFuture<String> deleteLineItemAsync(String documentId, String lineItemId) {
        return lineItemServices.deleteLineItemAsync(documentId, lineItemId);
    }

    /**
     * Replace multiple tags on an existing document. https://docs.veryfi.com/api/receipts-invoices/add-tags-to-a-document/
     * @param documentId  ID of the document you'd like to update.
     * @param tags  tags array of tags to be added.
     * @return the response data. {@link CompletableFuture<String>}
     */
    @Override
    public String replaceTags(String documentId, List<String> tags) {
        return tagServices.replaceTags(documentId, tags);
    }

    /**
     * Replace multiple tags on an existing document. https://docs.veryfi.com/api/receipts-invoices/add-tags-to-a-document/
     * @param documentId  ID of the document you'd like to update.
     * @param tags  tags array of tags to be added.
     * @return the response data. {@link CompletableFuture<String>}
     */
    @Override
    public CompletableFuture<String> replaceTagsAsync(String documentId, List<String> tags) {
        return tagServices.replaceTagsAsync(documentId, tags);
    }

    /**
     * Add multiple tags on an existing document. https://docs.veryfi.com/api/receipts-invoices/add-tags-to-a-document/
     * @param documentId  ID of the document you'd like to update.
     * @param tags  tags array of tags to be added.
     * @return the response data. {@link CompletableFuture<String>}
     */
    @Override
    public String addTags(String documentId, List<String> tags) {
        return tagServices.addTags(documentId, tags);
    }

    /**
     * Add multiple tags on an existing document. https://docs.veryfi.com/api/receipts-invoices/add-tags-to-a-document/
     * @param documentId  ID of the document you'd like to update.
     * @param tags  tags array of tags to be added.
     * @return the response data. {@link CompletableFuture<String>}
     */
    @Override
    public CompletableFuture<String> addTagsAsync(String documentId, List<String> tags) {
        return tagServices.addTagsAsync(documentId, tags);
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
    public String getAnyDocuments(int page, int pageSize, boolean boundingBoxes, boolean confidenceDetails, JSONObject parameters) {
        return anyDocumentServices.getAnyDocuments(page, pageSize, boundingBoxes, confidenceDetails, parameters);
    }

    /**
     * Returns a json string {@link CompletableFuture<String>} list of AnyDocuments. https://docs.veryfi.com/api/anydocs/get-A-docs/
     *
     * @param page   The page number. The response is capped to maximum of 50 results per page.
     * @param pageSize The number of Documents per page.
     * @param boundingBoxes A field used to determine whether or not to return bounding_box and bounding_region for extracted fields in the Document response.
     * @param confidenceDetails A field used to determine whether or not to return the score and ocr_score fields in the Document response.
     * @param parameters Additional request parameters.
     * @return the list of previously processed AnyDocuments {@link String}
     */
    public CompletableFuture<String> getAnyDocumentsAsync(int page, int pageSize, boolean boundingBoxes, boolean confidenceDetails, JSONObject parameters) {
        return anyDocumentServices.getAnyDocumentsAsync(page, pageSize, boundingBoxes, confidenceDetails, parameters);
    }

    /**
     * Returns a json string {@link String} AnyDocument information. https://docs.veryfi.com/api/anydocs/get-a-A-doc/
     *
     * @param documentId ID of the AnyDocument you'd like to retrieve.
     * @return the data extracted from the AnyDocument {@link String}
     */
    public String getAnyDocument(String documentId) {
        return anyDocumentServices.getAnyDocument(documentId);
    }

    /**
     * Returns a json string {@link CompletableFuture<String>} AnyDocument information. https://docs.veryfi.com/api/anydocs/get-a-A-doc/
     *
     * @param documentId ID of the AnyDocument you'd like to retrieve.
     * @return the data extracted from the AnyDocument {@link String}
     */
    public CompletableFuture<String> getAnyDocumentAsync(String documentId) {
        return anyDocumentServices.getAnyDocumentAsync(documentId);
    }

    /**
     * Process a AnyDocument and extract all the fields from it. https://docs.veryfi.com/api/anydocs/process-A-doc/
     *
     * @param filePath      Path on disk to a file to submit for data extraction.
     * @param blueprintName The name of the extraction blueprints.
     * @param parameters    Additional request parameters.
     * @return the data extracted from the AnyDocument {@link String}
     */
    public String processAnyDocument(String filePath, String blueprintName, JSONObject parameters) {
        return anyDocumentServices.processAnyDocument(filePath, blueprintName, parameters);
    }

    /**
     * Process a AnyDocument and extract all the fields from it. https://docs.veryfi.com/api/anydocs/process-A-doc/
     *
     * @param filePath      Path on disk to a file to submit for data extraction.
     * @param blueprintName The name of the extraction blueprints.
     * @param parameters    Additional request parameters.
     * @return the data extracted from the AnyDocument {@link CompletableFuture<String>}
     */
    public CompletableFuture<String> processAnyDocumentAsync(String filePath, String blueprintName, JSONObject parameters) {
        return anyDocumentServices.processAnyDocumentAsync(filePath, blueprintName, parameters);
    }

    /**
     * Process a AnyDocument and extract all the fields from it. https://docs.veryfi.com/api/anydocs/process-A-doc/
     *
     * @param fileName      Name of the file to upload to the Veryfi API
     * @param fileData      Base64 encoded file data
     * @param blueprintName The name of the extraction blueprints.
     * @param parameters    Additional request parameters.
     * @return the data extracted from the AnyDocument {@link String}
     */
    public String processAnyDocument(String fileName, String fileData, String blueprintName, JSONObject parameters) {
        return anyDocumentServices.processAnyDocument(fileName, fileData, blueprintName, parameters);
    }

    /**
     * Process a AnyDocument and extract all the fields from it. https://docs.veryfi.com/api/anydocs/process-A-doc/
     *
     * @param fileName      Name of the file to upload to the Veryfi API
     * @param fileData      Base64 encoded file data
     * @param blueprintName The name of the extraction blueprints.
     * @param parameters    Additional request parameters.
     * @return the data extracted from the AnyDocument {@link CompletableFuture<String>}
     */
    public CompletableFuture<String> processAnyDocumentAsync(String fileName, String fileData, String blueprintName, JSONObject parameters) {
        return anyDocumentServices.processAnyDocumentAsync(fileName, fileData, blueprintName, parameters);
    }

    /**
     * Process AnyDocument from url and extract all the fields from it. https://docs.veryfi.com/api/anydocs/process-A-doc/
     *
     * @param fileUrl       Required if file_urls isn't specified. Publicly accessible URL to a file, e.g. "https://cdn.example.com/receipt.jpg".
     * @param fileUrls      Required if file_url isn't specifies. List of publicly accessible URLs to multiple files, e.g. ["https://cdn.example.com/receipt1.jpg", "https://cdn.example.com/receipt2.jpg"]
     * @param blueprintName The name of the extraction blueprints.
     * @param parameters    Additional request parameters
     * @return the data extracted from the AnyDocument {@link String}
     */
    public String processAnyDocumentUrl(String fileUrl, List<String> fileUrls, String blueprintName, JSONObject parameters) {
        return anyDocumentServices.processAnyDocumentUrl(fileUrl, fileUrls, blueprintName, parameters);
    }

    /**
     * Process AnyDocument from url and extract all the fields from it. https://docs.veryfi.com/api/anydocs/process-A-doc/
     *
     * @param fileUrl       Required if file_urls isn't specified. Publicly accessible URL to a file, e.g. "https://cdn.example.com/receipt.jpg".
     * @param fileUrls      Required if file_url isn't specifies. List of publicly accessible URLs to multiple files, e.g. ["https://cdn.example.com/receipt1.jpg", "https://cdn.example.com/receipt2.jpg"]
     * @param blueprintName The name of the extraction blueprints.
     * @param parameters    Additional request parameters
     * @return the data extracted from the AnyDocument {@link CompletableFuture<String>}
     */
    public CompletableFuture<String> processAnyDocumentUrlAsync(String fileUrl, List<String> fileUrls, String blueprintName, JSONObject parameters) {
        return anyDocumentServices.processAnyDocumentUrlAsync(fileUrl, fileUrls, blueprintName, parameters);
    }

    /**
     * Delete AnyDocument from Veryfi. https://docs.veryfi.com/api/anydocs/delete-a-A-doc/
     *
     * @param documentId ID of the AnyDocument you'd like to delete.
     * @return the response data. {@link String}
     */
    public String deleteAnyDocument(String documentId) {
        return anyDocumentServices.deleteAnyDocument(documentId);
    }

    /**
     * Delete AnyDocument from Veryfi. https://docs.veryfi.com/api/anydocs/delete-a-A-doc/
     *
     * @param documentId ID of the AnyDocument you'd like to delete.
     * @return the response data. {@link CompletableFuture<String>}
     */
    public CompletableFuture<String> deleteAnyDocumentAsync(String documentId) {
        return anyDocumentServices.deleteAnyDocumentAsync(documentId);
    }

    /**
     * Returns a json string {@link String} with the list of Bank Statements. https://docs.veryfi.com/api/bank-statements/get-bank-statements/
     *
     * @param page   The page number. The response is capped to maximum of 50 results per page.
     * @param pageSize The number of Documents per page.
     * @param boundingBoxes A field used to determine whether or not to return bounding_box and bounding_region for extracted fields in the Document response.
     * @param confidenceDetails A field used to determine whether or not to return the score and ocr_score fields in the Document response.
     * @param parameters Additional request parameters.
     * @return the url {@link String}
     */
    public String getBankStatements(int page, int pageSize, boolean boundingBoxes, boolean confidenceDetails, JSONObject parameters) {
        return bankStatementServices.getBankStatements(page, pageSize, boundingBoxes, confidenceDetails, parameters);
    }

    /**
     * Returns a json string {@link CompletableFuture<String>} list of BankStatements. https://docs.veryfi.com/api/bank-statements/get-bank-statements/
     *
     * @param page   The page number. The response is capped to maximum of 50 results per page.
     * @param pageSize The number of Documents per page.
     * @param boundingBoxes A field used to determine whether or not to return bounding_box and bounding_region for extracted fields in the Document response.
     * @param confidenceDetails A field used to determine whether or not to return the score and ocr_score fields in the Document response.
     * @param parameters Additional request parameters.
     * @return the list of previously processed BankStatements {@link String}
     */
    public CompletableFuture<String> getBankStatementsAsync(int page, int pageSize, boolean boundingBoxes, boolean confidenceDetails, JSONObject parameters) {
        return bankStatementServices.getBankStatementsAsync(page, pageSize, boundingBoxes, confidenceDetails, parameters);
    }

    /**
     * Returns a json string {@link String} BankStatement information. https://docs.veryfi.com/api/bank-statements/get-a-bank-statement/
     *
     * @param documentId ID of the BankStatement you'd like to retrieve.
     * @return the data extracted from the BankStatement {@link String}
     */
    public String getBankStatement(String documentId) {
        return bankStatementServices.getBankStatement(documentId);
    }

    /**
     * Returns a json string {@link CompletableFuture<String>} BankStatement information. https://docs.veryfi.com/api/bank-statements/get-a-bank-statement/
     *
     * @param documentId ID of the BankStatement you'd like to retrieve.
     * @return the data extracted from the BankStatement {@link String}
     */
    public CompletableFuture<String> getBankStatementAsync(String documentId) {
        return bankStatementServices.getBankStatementAsync(documentId);
    }

    /**
     * Process a Bank Statement and extract all the fields from it. https://docs.veryfi.com/api/bank-statements/process-a-bank-statement/
     *
     * @param filePath      Path on disk to a file to submit for data extraction.
     * @param parameters    Additional request parameters.
     * @return the data extracted from the Bank Statement {@link String}
     */
    public String processBankStatement(String filePath, JSONObject parameters) {
        return bankStatementServices.processBankStatement(filePath, parameters);
    }

    /**
     * Process a Bank Statement and extract all the fields from it. https://docs.veryfi.com/api/bank-statements/process-a-bank-statement/
     *
     * @param filePath      Path on disk to a file to submit for data extraction.
     * @param parameters    Additional request parameters.
     * @return the data extracted from the Bank Statement {@link CompletableFuture<String>}
     */
    public CompletableFuture<String> processBankStatementAsync(String filePath, JSONObject parameters) {
        return bankStatementServices.processBankStatementAsync(filePath, parameters);
    }

    /**
     * Process a Bank Statement and extract all the fields from it. https://docs.veryfi.com/api/bank-statements/process-a-bank-statement/
     *
     * @param fileName      Name of the file to upload to the Veryfi API
     * @param fileData      Base64 encoded file data
     * @param parameters    Additional request parameters
     * @return the data extracted from the Bank Statement {@link String}
     */
    public String processBankStatement(String fileName, String fileData, JSONObject parameters) {
        return bankStatementServices.processBankStatement(fileName, fileData, parameters);
    }

    /**
     * Process a Bank Statement and extract all the fields from it. https://docs.veryfi.com/api/bank-statements/process-a-bank-statement/
     *
     * @param fileName      Name of the file to upload to the Veryfi API
     * @param fileData      Base64 encoded file data
     * @param parameters    Additional request parameters
     * @return the data extracted from the Bank Statement {@link CompletableFuture<String>}
     */
    public CompletableFuture<String> processBankStatementAsync(String fileName, String fileData, JSONObject parameters) {
        return bankStatementServices.processBankStatementAsync(fileName, fileData, parameters);
    }

    /**
     * Process BankStatement from url and extract all the fields from it. https://docs.veryfi.com/api/bank-statements/process-a-bank-statement/
     *
     * @param fileUrl       Required if file_urls isn't specified. Publicly accessible URL to a file, e.g. "https://cdn.example.com/receipt.jpg".
     * @param fileUrls      Required if file_url isn't specifies. List of publicly accessible URLs to multiple files, e.g. ["https://cdn.example.com/receipt1.jpg", "https://cdn.example.com/receipt2.jpg"]
     * @param parameters    Additional request parameters
     * @return the data extracted from the BankStatement {@link String}
     */
    public String processBankStatementUrl(String fileUrl, List<String> fileUrls, JSONObject parameters) {
        return bankStatementServices.processBankStatementUrl(fileUrl, fileUrls, parameters);
    }

    /**
     * Process BankStatement from url and extract all the fields from it. https://docs.veryfi.com/api/bank-statements/process-a-bank-statement/
     *
     * @param fileUrl       Required if file_urls isn't specified. Publicly accessible URL to a file, e.g. "https://cdn.example.com/receipt.jpg".
     * @param fileUrls      Required if file_url isn't specifies. List of publicly accessible URLs to multiple files, e.g. ["https://cdn.example.com/receipt1.jpg", "https://cdn.example.com/receipt2.jpg"]
     * @param parameters    Additional request parameters
     * @return the data extracted from the BankStatement {@link CompletableFuture<String>}
     */
    public CompletableFuture<String> processBankStatementUrlAsync(String fileUrl, List<String> fileUrls, JSONObject parameters) {
        return bankStatementServices.processBankStatementUrlAsync(fileUrl, fileUrls, parameters);
    }

    /**
     * Delete BankStatement from Veryfi. https://docs.veryfi.com/api/bank-statements/delete-a-bank-statement/
     *
     * @param documentId ID of the BankStatement you'd like to delete.
     * @return the response data. {@link String}
     */
    public String deleteBankStatement(String documentId) {
        return bankStatementServices.deleteBankStatement(documentId);
    }

    /**
     * Delete BankStatement from Veryfi. https://docs.veryfi.com/api/bank-statements/delete-a-bank-statement/
     *
     * @param documentId ID of the BankStatement you'd like to delete.
     * @return the response data. {@link CompletableFuture<String>}
     */
    public CompletableFuture<String> deleteBankStatementAsync(String documentId) {
        return bankStatementServices.deleteBankStatementAsync(documentId);
    }

    /**
     * Returns a json string {@link String} with the list of Business Cards. https://docs.veryfi.com/api/business-cards/get-business-cards/
     *
     * @param page   The page number. The response is capped to maximum of 50 results per page.
     * @param pageSize The number of Documents per page.
     * @param boundingBoxes A field used to determine whether or not to return bounding_box and bounding_region for extracted fields in the Document response.
     * @param confidenceDetails A field used to determine whether or not to return the score and ocr_score fields in the Document response.
     * @param parameters Additional request parameters.
     * @return the url {@link String}
     */
    public String getBusinessCards(int page, int pageSize, boolean boundingBoxes, boolean confidenceDetails, JSONObject parameters) {
        return businessCardsServices.getBusinessCards(page, pageSize, boundingBoxes, confidenceDetails, parameters);
    }

    /**
     * Returns a json string {@link CompletableFuture<String>} list of Business Cards. https://docs.veryfi.com/api/business-cards/get-business-cards/
     *
     * @param page   The page number. The response is capped to maximum of 50 results per page.
     * @param pageSize The number of Documents per page.
     * @param boundingBoxes A field used to determine whether or not to return bounding_box and bounding_region for extracted fields in the Document response.
     * @param confidenceDetails A field used to determine whether or not to return the score and ocr_score fields in the Document response.
     * @param parameters Additional request parameters.
     * @return the list of previously processed Business Cards {@link String}
     */
    public CompletableFuture<String> getBusinessCardsAsync(int page, int pageSize, boolean boundingBoxes, boolean confidenceDetails, JSONObject parameters) {
        return businessCardsServices.getBusinessCardsAsync(page, pageSize, boundingBoxes, confidenceDetails, parameters);
    }

    /**
     * Returns a json string {@link String} Business Card information. https://docs.veryfi.com/api/business-cards/get-a-business-card/
     *
     * @param documentId ID of the Business Card you'd like to retrieve.
     * @return the data extracted from the Business Card {@link String}
     */
    public String getBusinessCard(String documentId) {
        return businessCardsServices.getBusinessCard(documentId);
    }

    /**
     * Returns a json string {@link CompletableFuture<String>} Business Card information. https://docs.veryfi.com/api/business-cards/get-a-business-card/
     *
     * @param documentId ID of the Business Card you'd like to retrieve.
     * @return the data extracted from the Business Card {@link String}
     */
    public CompletableFuture<String> getBusinessCardAsync(String documentId) {
        return businessCardsServices.getBusinessCardAsync(documentId);
    }

    /**
     * Process a Business Card and extract all the fields from it. https://docs.veryfi.com/api/business-cards/process-a-business-card/
     *
     * @param filePath      Path on disk to a file to submit for data extraction.
     * @param parameters    Additional request parameters.
     * @return the data extracted from the Business Card {@link String}
     */
    public String processBusinessCard(String filePath, JSONObject parameters) {
        return businessCardsServices.processBusinessCard(filePath, parameters);
    }

    /**
     * Process a Business Card and extract all the fields from it. https://docs.veryfi.com/api/business-cards/process-a-business-card/
     *
     * @param filePath      Path on disk to a file to submit for data extraction.
     * @param parameters    Additional request parameters.
     * @return the data extracted from the Business Card {@link CompletableFuture<String>}
     */
    public CompletableFuture<String> processBusinessCardAsync(String filePath, JSONObject parameters) {
        return businessCardsServices.processBusinessCardAsync(filePath, parameters);
    }

    /**
     * Process a Business Card and extract all the fields from it. https://docs.veryfi.com/api/business-cards/process-a-business-card/
     *
     * @param fileName      Name of the file to upload to the Veryfi API
     * @param fileData      Base64 encoded file data
     * @param parameters    Additional request parameters.
     * @return the data extracted from the Business Card {@link String}
     */
    public String processBusinessCard(String fileName, String fileData, JSONObject parameters) {
        return businessCardsServices.processBusinessCard(fileName, fileData, parameters);
    }

    /**
     * Process a Business Card and extract all the fields from it. https://docs.veryfi.com/api/business-cards/process-a-business-card/
     *
     * @param fileName      Name of the file to upload to the Veryfi API
     * @param fileData      Base64 encoded file data
     * @param parameters    Additional request parameters.
     * @return the data extracted from the Business Card {@link CompletableFuture<String>}
     */
    public CompletableFuture<String> processBusinessCardAsync(String fileName, String fileData, JSONObject parameters) {
        return businessCardsServices.processBusinessCardAsync(fileName, fileData, parameters);
    }

    /**
     * Process Business Card from url and extract all the fields from it. https://docs.veryfi.com/api/business-cards/process-a-business-card/
     *
     * @param fileUrl       Required if file_urls isn't specified. Publicly accessible URL to a file, e.g. "https://cdn.example.com/receipt.jpg".
     * @param fileUrls      Required if file_url isn't specifies. List of publicly accessible URLs to multiple files, e.g. ["https://cdn.example.com/receipt1.jpg", "https://cdn.example.com/receipt2.jpg"]
     * @param parameters    Additional request parameters
     * @return the data extracted from the Business Card {@link String}
     */
    public String processBusinessCardUrl(String fileUrl, List<String> fileUrls, JSONObject parameters) {
        return businessCardsServices.processBusinessCardUrl(fileUrl, fileUrls, parameters);
    }

    /**
     * Process Business Card from url and extract all the fields from it. https://docs.veryfi.com/api/business-cards/process-a-business-card/
     *
     * @param fileUrl       Required if file_urls isn't specified. Publicly accessible URL to a file, e.g. "https://cdn.example.com/receipt.jpg".
     * @param fileUrls      Required if file_url isn't specifies. List of publicly accessible URLs to multiple files, e.g. ["https://cdn.example.com/receipt1.jpg", "https://cdn.example.com/receipt2.jpg"]
     * @param parameters    Additional request parameters
     * @return the data extracted from the Business Card {@link CompletableFuture<String>}
     */
    public CompletableFuture<String> processBusinessCardUrlAsync(String fileUrl, List<String> fileUrls, JSONObject parameters) {
        return businessCardsServices.processBusinessCardUrlAsync(fileUrl, fileUrls, parameters);
    }

    /**
     * Delete Business Card from Veryfi. https://docs.veryfi.com/api/business-cards/delete-a-business-card/
     *
     * @param documentId ID of the Business Card you'd like to delete.
     * @return the response data. {@link String}
     */
    public String deleteBusinessCard(String documentId) {
        return businessCardsServices.deleteBusinessCard(documentId);
    }

    /**
     * Delete Business Card from Veryfi. https://docs.veryfi.com/api/business-cards/delete-a-business-card/
     *
     * @param documentId ID of the Business Card you'd like to delete.
     * @return the response data. {@link CompletableFuture<String>}
     */
    public CompletableFuture<String> deleteBusinessCardAsync(String documentId) {
        return businessCardsServices.deleteBusinessCardAsync(documentId);
    }

    /**
     * Returns a json string {@link String} with the list of Checks. https://docs.veryfi.com/api/checks/get-checks/
     *
     * @param page   The page number. The response is capped to maximum of 50 results per page.
     * @param pageSize The number of Documents per page.
     * @param boundingBoxes A field used to determine whether or not to return bounding_box and bounding_region for extracted fields in the Document response.
     * @param confidenceDetails A field used to determine whether or not to return the score and ocr_score fields in the Document response.
     * @param parameters Additional request parameters.
     * @return the url {@link String}
     */
    public String getChecks(int page, int pageSize, boolean boundingBoxes, boolean confidenceDetails, JSONObject parameters) {
        return checkServices.getChecks(page, pageSize, boundingBoxes, confidenceDetails, parameters);
    }

    /**
     * Returns a json string {@link CompletableFuture<String>} list of Checks. https://docs.veryfi.com/api/checks/get-checks/
     *
     * @param page   The page number. The response is capped to maximum of 50 results per page.
     * @param pageSize The number of Documents per page.
     * @param boundingBoxes A field used to determine whether or not to return bounding_box and bounding_region for extracted fields in the Document response.
     * @param confidenceDetails A field used to determine whether or not to return the score and ocr_score fields in the Document response.
     * @param parameters Additional request parameters.
     * @return the list of previously processed checks {@link String}
     */
    public CompletableFuture<String> getChecksAsync(int page, int pageSize, boolean boundingBoxes, boolean confidenceDetails, JSONObject parameters) {
        return checkServices.getChecksAsync(page, pageSize, boundingBoxes, confidenceDetails, parameters);
    }

    /**
     * Returns a json string {@link String} Check information. https://docs.veryfi.com/api/checks/get-a-check/
     *
     * @param documentId ID of the Check you'd like to retrieve.
     * @return the data extracted from the Check {@link String}
     */
    public String getCheck(String documentId) {
        return checkServices.getCheck(documentId);
    }

    /**
     * Returns a json string {@link CompletableFuture<String>} Check information. https://docs.veryfi.com/api/checks/get-a-check/
     *
     * @param documentId ID of the Check you'd like to retrieve.
     * @return the data extracted from the Check {@link String}
     */
    public CompletableFuture<String> getCheckAsync(String documentId) {
        return checkServices.getCheckAsync(documentId);
    }

    /**
     * Process a Check and extract all the fields from it. https://docs.veryfi.com/api/checks/process-a-check/
     *
     * @param filePath      Path on disk to a file to submit for data extraction.
     * @param parameters    Additional request parameters.
     * @return the data extracted from the Check {@link String}
     */
    public String processCheck(String filePath, JSONObject parameters) {
        return checkServices.processCheck(filePath, parameters);
    }

    /**
     * Process a Check and extract all the fields from it. https://docs.veryfi.com/api/checks/process-a-check/
     *
     * @param filePath      Path on disk to a file to submit for data extraction.
     * @param parameters    Additional request parameters.
     * @return the data extracted from the Check {@link CompletableFuture<String>}
     */
    public CompletableFuture<String> processCheckAsync(String filePath, JSONObject parameters) {
        return checkServices.processCheckAsync(filePath, parameters);
    }

    /**
     * Process a Check and extract all the fields from it. https://docs.veryfi.com/api/checks/process-a-check/
     *
     * @param fileName      Name of the file to upload to the Veryfi API
     * @param fileData      Base64 encoded file data
     * @param parameters    Additional request parameters
     * @return the data extracted from the Check {@link String}
     */
    @Override
    public String processCheck(String fileName, String fileData, JSONObject parameters) {
        return checkServices.processCheck(fileName, fileData, parameters);
    }

    /**
     * Process a Check and extract all the fields from it. https://docs.veryfi.com/api/checks/process-a-check/
     *
     * @param fileName      Name of the file to upload to the Veryfi API
     * @param fileData      Base64 encoded file data
     * @param parameters    Additional request parameters
     * @return the data extracted from the Check {@link CompletableFuture<String>}
     */
    @Override
    public CompletableFuture<String> processCheckAsync(String fileName, String fileData, JSONObject parameters) {
        return checkServices.processCheckAsync(fileName, fileData, parameters);
    }

    /**
     * Process Check from url and extract all the fields from it. https://docs.veryfi.com/api/checks/process-a-check/
     *
     * @param fileUrl       Required if file_urls isn't specified. Publicly accessible URL to a file, e.g. "https://cdn.example.com/receipt.jpg".
     * @param fileUrls      Required if file_url isn't specifies. List of publicly accessible URLs to multiple files, e.g. ["https://cdn.example.com/receipt1.jpg", "https://cdn.example.com/receipt2.jpg"]
     * @param parameters    Additional request parameters
     * @return the data extracted from the Check {@link String}
     */
    public String processCheckUrl(String fileUrl, List<String> fileUrls, JSONObject parameters) {
        return checkServices.processCheckUrl(fileUrl, fileUrls, parameters);
    }

    /**
     * Process Check from url and extract all the fields from it. https://docs.veryfi.com/api/checks/process-a-check/
     *
     * @param fileUrl       Required if file_urls isn't specified. Publicly accessible URL to a file, e.g. "https://cdn.example.com/receipt.jpg".
     * @param fileUrls      Required if file_url isn't specifies. List of publicly accessible URLs to multiple files, e.g. ["https://cdn.example.com/receipt1.jpg", "https://cdn.example.com/receipt2.jpg"]
     * @param parameters    Additional request parameters
     * @return the data extracted from the Check {@link CompletableFuture<String>}
     */
    public CompletableFuture<String> processCheckUrlAsync(String fileUrl, List<String> fileUrls, JSONObject parameters) {
        return checkServices.processCheckUrlAsync(fileUrl, fileUrls, parameters);
    }

    /**
     * Delete Check from Veryfi. https://docs.veryfi.com/api/checks/delete-a-check/
     *
     * @param documentId ID of the Check you'd like to delete.
     * @return the response data. {@link String}
     */
    public String deleteCheck(String documentId) {
        return checkServices.deleteCheck(documentId);
    }

    /**
     * Delete Check from Veryfi. https://docs.veryfi.com/api/checks/delete-a-check/
     *
     * @param documentId ID of the Check you'd like to delete.
     * @return the response data. {@link CompletableFuture<String>}
     */
    public CompletableFuture<String> deleteCheckAsync(String documentId) {
        return checkServices.deleteCheckAsync(documentId);
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
    public String getW2s(int page, int pageSize, boolean boundingBoxes, boolean confidenceDetails, JSONObject parameters) {
        return w2Services.getW2s(page, pageSize, boundingBoxes, confidenceDetails, parameters);
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
    public CompletableFuture<String> getW2sAsync(int page, int pageSize, boolean boundingBoxes, boolean confidenceDetails, JSONObject parameters) {
        return w2Services.getW2sAsync(page, pageSize, boundingBoxes, confidenceDetails, parameters);
    }

    /**
     * Returns a json string {@link String} W2 information. https://docs.veryfi.com/api/w2s/get-a-w-2/
     *
     * @param documentId ID of the W2 you'd like to retrieve.
     * @return the data extracted from the W2 {@link String}
     */
    public String getW2(String documentId) {
        return w2Services.getW2(documentId);
    }

    /**
     * Returns a json string {@link CompletableFuture<String>} W2 information. https://docs.veryfi.com/api/w2s/get-a-w-2/
     *
     * @param documentId ID of the W2 you'd like to retrieve.
     * @return the data extracted from the W2 {@link String}
     */
    public CompletableFuture<String> getW2Async(String documentId) {
        return w2Services.getW2Async(documentId);
    }

    /**
     * Process a W2 and extract all the fields from it. https://docs.veryfi.com/api/w2s/process-a-w-2/
     *
     * @param filePath      Path on disk to a file to submit for data extraction.
     * @param parameters    Additional request parameters.
     * @return the data extracted from the W2 {@link String}
     */
    public String processW2(String filePath, JSONObject parameters) {
        return w2Services.processW2(filePath, parameters);
    }

    /**
     * Process a W2 and extract all the fields from it. https://docs.veryfi.com/api/w2s/process-a-w-2/
     *
     * @param filePath      Path on disk to a file to submit for data extraction.
     * @param parameters    Additional request parameters.
     * @return the data extracted from the W2 {@link CompletableFuture<String>}
     */
    public CompletableFuture<String> processW2Async(String filePath, JSONObject parameters) {
        return w2Services.processW2Async(filePath, parameters);
    }

    /**
     * Process W2 from url and extract all the fields from it. https://docs.veryfi.com/api/w2s/process-a-w-2/
     *
     * @param fileUrl       Required if file_urls isn't specified. Publicly accessible URL to a file, e.g. "https://cdn.example.com/receipt.jpg".
     * @param fileUrls      Required if file_url isn't specifies. List of publicly accessible URLs to multiple files, e.g. ["https://cdn.example.com/receipt1.jpg", "https://cdn.example.com/receipt2.jpg"]
     * @param parameters    Additional request parameters
     * @return the data extracted from the W2 {@link String}
     */
    public String processW2Url(String fileUrl, List<String> fileUrls, JSONObject parameters) {
        return w2Services.processW2Url(fileUrl, fileUrls, parameters);
    }

    /**
     * Process W2 from url and extract all the fields from it. https://docs.veryfi.com/api/w2s/process-a-w-2/
     *
     * @param fileUrl       Required if file_urls isn't specified. Publicly accessible URL to a file, e.g. "https://cdn.example.com/receipt.jpg".
     * @param fileUrls      Required if file_url isn't specifies. List of publicly accessible URLs to multiple files, e.g. ["https://cdn.example.com/receipt1.jpg", "https://cdn.example.com/receipt2.jpg"]
     * @param parameters    Additional request parameters
     * @return the data extracted from the W2 {@link CompletableFuture<String>}
     */
    public CompletableFuture<String> processW2UrlAsync(String fileUrl, List<String> fileUrls, JSONObject parameters) {
        return w2Services.processW2UrlAsync(fileUrl, fileUrls, parameters);
    }

    /**
     * Delete W2 from Veryfi. https://docs.veryfi.com/api/w2s/delete-a-w-2/
     *
     * @param documentId ID of the W2 you'd like to delete.
     * @return the response data. {@link String}
     */
    public String deleteW2(String documentId) {
        return w2Services.deleteW2(documentId);
    }

    /**
     * Delete W2 from Veryfi. https://docs.veryfi.com/api/w2s/delete-a-w-2/
     *
     * @param documentId ID of the W2 you'd like to delete.
     * @return the response data. {@link CompletableFuture<String>}
     */
    public CompletableFuture<String> deleteW2Async(String documentId) {
        return w2Services.deleteW2Async(documentId);
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
    public String getW8BenEs(int page, int pageSize, boolean boundingBoxes, boolean confidenceDetails, JSONObject parameters) {
        return w8BenEServices.getW8BenEs(page, pageSize, boundingBoxes, confidenceDetails, parameters);
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
    public CompletableFuture<String> getW8BenEsAsync(int page, int pageSize, boolean boundingBoxes, boolean confidenceDetails, JSONObject parameters) {
        return w8BenEServices.getW8BenEsAsync(page, pageSize, boundingBoxes, confidenceDetails, parameters);
    }

    /**
     * Returns a json string {@link String} W-8BEN-E information. https://docs.veryfi.com/api/w-8ben-e/get-a-w-8-ben-e/
     *
     * @param documentId ID of the W-8BEN-E you'd like to retrieve.
     * @return the data extracted from the W-8BEN-E {@link String}
     */
    public String getW8BenE(String documentId) {
        return w8BenEServices.getW8BenE(documentId);
    }

    /**
     * Returns a json string {@link CompletableFuture<String>} W-8BEN-E information. https://docs.veryfi.com/api/w-8ben-e/get-a-w-8-ben-e/
     *
     * @param documentId ID of the W-8BEN-E you'd like to retrieve.
     * @return the data extracted from the W-8BEN-E {@link String}
     */
    public CompletableFuture<String> getW8BenEAsync(String documentId) {
        return w8BenEServices.getW8BenEAsync(documentId);
    }

    /**
     * Process a W-8BEN-E and extract all the fields from it. https://docs.veryfi.com/api/w-8ben-e/process-a-w-8-ben-e/
     *
     * @param filePath      Path on disk to a file to submit for data extraction.
     * @param parameters    Additional request parameters.
     * @return the data extracted from the W-8BEN-E {@link String}
     */
    public String processW8BenE(String filePath, JSONObject parameters) {
        return w8BenEServices.processW8BenE(filePath, parameters);
    }

    /**
     * Process a W-8BEN-E and extract all the fields from it. https://docs.veryfi.com/api/w-8ben-e/process-a-w-8-ben-e/
     *
     * @param filePath      Path on disk to a file to submit for data extraction.
     * @param parameters    Additional request parameters.
     * @return the data extracted from the W-8BEN-E {@link CompletableFuture<String>}
     */
    public CompletableFuture<String> processW8BenEAsync(String filePath, JSONObject parameters) {
        return w8BenEServices.processW8BenEAsync(filePath, parameters);
    }

    /**
     * Process W-8BEN-E from url and extract all the fields from it. https://docs.veryfi.com/api/w-8ben-e/process-a-w-8-ben-e/
     *
     * @param fileUrl       Required if file_urls isn't specified. Publicly accessible URL to a file, e.g. "https://cdn.example.com/receipt.jpg".
     * @param fileUrls      Required if file_url isn't specifies. List of publicly accessible URLs to multiple files, e.g. ["https://cdn.example.com/receipt1.jpg", "https://cdn.example.com/receipt2.jpg"]
     * @param parameters    Additional request parameters
     * @return the data extracted from the W-8BEN-E {@link String}
     */
    public String processW8BenEUrl(String fileUrl, List<String> fileUrls, JSONObject parameters) {
        return w8BenEServices.processW8BenEUrl(fileUrl, fileUrls, parameters);
    }

    /**
     * Process W-8BEN-E from url and extract all the fields from it. https://docs.veryfi.com/api/w-8ben-e/process-a-w-8-ben-e/
     *
     * @param fileUrl       Required if file_urls isn't specified. Publicly accessible URL to a file, e.g. "https://cdn.example.com/receipt.jpg".
     * @param fileUrls      Required if file_url isn't specifies. List of publicly accessible URLs to multiple files, e.g. ["https://cdn.example.com/receipt1.jpg", "https://cdn.example.com/receipt2.jpg"]
     * @param parameters    Additional request parameters
     * @return the data extracted from the W-8BEN-E {@link CompletableFuture<String>}
     */
    public CompletableFuture<String> processW8BenEUrlAsync(String fileUrl, List<String> fileUrls, JSONObject parameters) {
        return w8BenEServices.processW8BenEUrlAsync(fileUrl, fileUrls, parameters);
    }

    /**
     * Delete W-8BEN-E from Veryfi. https://docs.veryfi.com/api/w-8ben-e/delete-a-w-8-ben-e/
     *
     * @param documentId ID of the W-8BEN-E you'd like to delete.
     * @return the response data. {@link String}
     */
    public String deleteW8BenE(String documentId) {
        return w8BenEServices.deleteW8BenE(documentId);
    }

    /**
     * Delete W-8BEN-E from Veryfi. https://docs.veryfi.com/api/w-8ben-e/delete-a-w-8-ben-e/
     *
     * @param documentId ID of the W-8BEN-E you'd like to delete.
     * @return the response data. {@link CompletableFuture<String>}
     */
    public CompletableFuture<String> deleteW8BenEAsync(String documentId) {
        return w8BenEServices.deleteW8BenEAsync(documentId);
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
    public String getW9s(int page, int pageSize, boolean boundingBoxes, boolean confidenceDetails, JSONObject parameters) {
        return w9Services.getW9s(page, pageSize, boundingBoxes, confidenceDetails, parameters);
    }

    /**
     * Returns a json string {@link CompletableFuture<String>} list of W9s. https://docs.veryfi.com/api/w9s/get-w-9-s/
     *
     * @param page   The page number. The response is capped to maximum of 50 results per page.
     * @param pageSize The number of Documents per page.
     * @param boundingBoxes A field used to determine whether or not to return bounding_box and bounding_region for extracted fields in the Document response.
     * @param confidenceDetails A field used to determine whether or not to return the score and ocr_score fields in the Document response.
     * @param parameters Additional request parameters.
     * @return the list of previously processed W9s {@link String}
     */
    public CompletableFuture<String> getW9sAsync(int page, int pageSize, boolean boundingBoxes, boolean confidenceDetails, JSONObject parameters) {
        return w9Services.getW9sAsync(page, pageSize, boundingBoxes, confidenceDetails, parameters);
    }

    /**
     * Returns a json string {@link String} W9 information. https://docs.veryfi.com/api/w9s/get-a-w-9/
     *
     * @param documentId ID of the W9 you'd like to retrieve.
     * @return the data extracted from the W9 {@link String}
     */
    public String getW9(String documentId) {
        return w9Services.getW9(documentId);
    }

    /**
     * Returns a json string {@link CompletableFuture<String>} W9 information. https://docs.veryfi.com/api/w9s/get-a-w-9/
     *
     * @param documentId ID of the W9 you'd like to retrieve.
     * @return the data extracted from the W9 {@link String}
     */
    public CompletableFuture<String> getW9Async(String documentId) {
        return w9Services.getW9Async(documentId);
    }

    /**
     * Process a W9 and extract all the fields from it. https://docs.veryfi.com/api/w9s/process-a-w-9/
     *
     * @param filePath      Path on disk to a file to submit for data extraction.
     * @param parameters    Additional request parameters.
     * @return the data extracted from the W9 {@link String}
     */
    public String processW9(String filePath, JSONObject parameters) {
        return w9Services.processW9(filePath, parameters);
    }

    /**
     * Process a W9 and extract all the fields from it. https://docs.veryfi.com/api/w9s/process-a-w-9/
     *
     * @param filePath      Path on disk to a file to submit for data extraction.
     * @param parameters    Additional request parameters.
     * @return the data extracted from the W9 {@link CompletableFuture<String>}
     */
    public CompletableFuture<String> processW9Async(String filePath, JSONObject parameters) {
        return w9Services.processW9Async(filePath, parameters);
    }

    /**
     * Process W9 from url and extract all the fields from it. https://docs.veryfi.com/api/w9s/process-a-w-9/
     *
     * @param fileUrl       Required if file_urls isn't specified. Publicly accessible URL to a file, e.g. "https://cdn.example.com/receipt.jpg".
     * @param fileUrls      Required if file_url isn't specifies. List of publicly accessible URLs to multiple files, e.g. ["https://cdn.example.com/receipt1.jpg", "https://cdn.example.com/receipt2.jpg"]
     * @param parameters    Additional request parameters
     * @return the data extracted from the W9 {@link String}
     */
    public String processW9Url(String fileUrl, List<String> fileUrls, JSONObject parameters) {
        return w9Services.processW9Url(fileUrl, fileUrls, parameters);
    }

    /**
     * Process W9 from url and extract all the fields from it. https://docs.veryfi.com/api/w9s/process-a-w-9/
     *
     * @param fileUrl       Required if file_urls isn't specified. Publicly accessible URL to a file, e.g. "https://cdn.example.com/receipt.jpg".
     * @param fileUrls      Required if file_url isn't specifies. List of publicly accessible URLs to multiple files, e.g. ["https://cdn.example.com/receipt1.jpg", "https://cdn.example.com/receipt2.jpg"]
     * @param parameters    Additional request parameters
     * @return the data extracted from the W9 {@link CompletableFuture<String>}
     */
    public CompletableFuture<String> processW9UrlAsync(String fileUrl, List<String> fileUrls, JSONObject parameters) {
        return w9Services.processW9UrlAsync(fileUrl, fileUrls, parameters);
    }

    /**
     * Delete W9 from Veryfi. https://docs.veryfi.com/api/w9s/delete-a-w-9/
     *
     * @param documentId ID of the W9 you'd like to delete.
     * @return the response data. {@link String}
     */
    public String deleteW9(String documentId) {
        return w9Services.deleteW9(documentId);
    }

    /**
     * Delete W9 from Veryfi. https://docs.veryfi.com/api/w9s/delete-a-w-9/
     *
     * @param documentId ID of the W9 you'd like to delete.
     * @return the response data. {@link CompletableFuture<String>}
     */
    public CompletableFuture<String> deleteW9Async(String documentId) {
        return w9Services.deleteW9Async(documentId);
    }

    /**
     * Returns a json string {@link String} with the list of Contracts.
     *
     * @param page   The page number. The response is capped to maximum of 50 results per page.
     * @param pageSize The number of Documents per page.
     * @param parameters Additional request parameters.
     * @return the url {@link String}
     */
    public String getContracts(int page, int pageSize, JSONObject parameters) {
        return contractServices.getContracts(page, pageSize, parameters);
    }

    /**
     * Returns a json string {@link CompletableFuture<String>} list of Contracts.
     *
     * @param page   The page number. The response is capped to maximum of 50 results per page.
     * @param pageSize The number of Documents per page.
     * @param parameters Additional request parameters.
     * @return the list of previously processed Contracts {@link String}
     */
    public CompletableFuture<String> getContractsAsync(int page, int pageSize, JSONObject parameters) {
        return contractServices.getContractsAsync(page, pageSize, parameters);
    }

    /**
     * Returns a json string {@link String} with the Contract information.
     *
     * @param documentId ID of the Contract you'd like to retrieve.
     * @return the data extracted from the Contract {@link String}
     */
    public String getContract(String documentId) {
        return contractServices.getContract(documentId);
    }

    /**
     * Returns a json string {@link CompletableFuture<String>} with the Contract information.
     *
     * @param documentId ID of the Contract you'd like to retrieve.
     * @return the data extracted from the Contract {@link String}
     */
    public CompletableFuture<String> getContractAsync(String documentId) {
        return contractServices.getContractAsync(documentId);
    }

    /**
     * Process a Contract and extract all the fields from it.
     *
     * @param filePath      Path on disk to a file to submit for data extraction.
     * @param parameters    Additional request parameters.
     * @return the data extracted from the Contract {@link String}
     */
    public String processContract(String filePath, JSONObject parameters) {
        return contractServices.processContract(filePath, parameters);
    }

    /**
     * Process a Contract and extract all the fields from it.
     *
     * @param filePath      Path on disk to a file to submit for data extraction.
     * @param parameters    Additional request parameters.
     * @return the data extracted from the Contract {@link CompletableFuture<String>}
     */
    public CompletableFuture<String> processContractAsync(String filePath, JSONObject parameters) {
        return contractServices.processContractAsync(filePath, parameters);
    }

    /**
     * Process a Contract and extract all the fields from it.
     *
     * @param fileName      Name of the file to upload to the Veryfi API
     * @param fileData      Base64 encoded file data
     * @param parameters    Additional request parameters
     * @return the data extracted from the Contract {@link String}
     */
    public String processContract(String fileName, String fileData, JSONObject parameters) {
        return contractServices.processContract(fileName, fileData, parameters);
    }

    /**
     * Process a Contract and extract all the fields from it.
     *
     * @param fileName      Name of the file to upload to the Veryfi API
     * @param fileData      Base64 encoded file data
     * @param parameters    Additional request parameters
     * @return the data extracted from the Contract {@link CompletableFuture<String>}
     */
    public CompletableFuture<String> processContractAsync(String fileName, String fileData, JSONObject parameters) {
        return contractServices.processContractAsync(fileName, fileData, parameters);
    }

    /**
     * Process a W2 and extract all the fields from it. https://docs.veryfi.com/api/w2s/process-a-w-2/
     *
     * @param fileName      Name of the file to submit for data extraction.
     * @param fileData      Base64 encoded file data.
     * @param parameters    Additional request parameters.
     * @return the data extracted from the W2 {@link String}
     */
    public String processW2(String fileName, String fileData, JSONObject parameters) {
        return w2Services.processW2(fileName, fileData, parameters);
    }

    /**
     * Process a W2 and extract all the fields from it. https://docs.veryfi.com/api/w2s/process-a-w-2/
     *
     * @param fileName      Name of the file to submit for data extraction.
     * @param fileData      Base64 encoded file data.
     * @param parameters    Additional request parameters.
     * @return the data extracted from the W2 {@link CompletableFuture<String>}
     */
    public CompletableFuture<String> processW2Async(String fileName, String fileData, JSONObject parameters) {
        return w2Services.processW2Async(fileName, fileData, parameters);
    }

    /**
     * Process Contract from url and extract all the fields from it.
     *
     * @param fileUrl       Required if file_urls isn't specified. Publicly accessible URL to a file, e.g. "https://cdn.example.com/receipt.jpg".
     * @param parameters    Additional request parameters
     * @return the data extracted from the Contract {@link String}
     */
    public String processContractUrl(String fileUrl, JSONObject parameters) {
        return contractServices.processContractUrl(fileUrl, parameters);
    }

    /**
     * Process Contract from url and extract all the fields from it.
     *
     * @param fileUrl       Required if file_urls isn't specified. Publicly accessible URL to a file, e.g. "https://cdn.example.com/receipt.jpg".
     * @param parameters    Additional request parameters
     * @return the data extracted from the Contract {@link CompletableFuture<String>}
     */
    public CompletableFuture<String> processContractUrlAsync(String fileUrl, JSONObject parameters) {
        return contractServices.processContractUrlAsync(fileUrl, parameters);
    }

    /**
     * Delete a Contract from Veryfi.
     *
     * @param documentId ID of the Contract you'd like to delete.
     * @return the response data. {@link String}
     */
    public String deleteContract(String documentId) {
        return contractServices.deleteContract(documentId);
    }

    /**
     * Delete a Contract from Veryfi.
     *
     * @param documentId ID of the Contract you'd like to delete.
     * @return the response data. {@link CompletableFuture<String>}
     */
    public CompletableFuture<String> deleteContractAsync(String documentId) {
        return contractServices.deleteContractAsync(documentId);
    }

}
