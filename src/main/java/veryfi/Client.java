package veryfi;

import org.json.JSONObject;
import veryfi.models.AddLineItem;
import veryfi.models.NotValidModelException;
import veryfi.models.UpdateLineItem;
import java.net.http.HttpClient;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Veryfi API client for Java.
 * <p>
 * The instances of classes that implement this interface are thread-safe and immutable.
 */
public interface Client {
    /**
     * Returns a json string {@link String} list of documents. https://docs.veryfi.com/api/receipts-invoices/search-documents/
     * @param page   The page number. The response is capped to maximum of 50 results per page.
     * @param pageSize The number of Documents per page.
     * @param boundingBoxes A field used to determine whether or not to return bounding_box and bounding_region for extracted fields in the Document response.
     * @param confidenceDetails A field used to determine whether or not to return the score and ocr_score fields in the Document response.
     * @param parameters Additional request parameters.
     * @return the list of previously processed documents {@link String}
     */
    String getDocuments(int page, int pageSize, boolean boundingBoxes, boolean confidenceDetails, JSONObject parameters);

    /**
     * Returns a json string {@link CompletableFuture<String>} list of documents. https://docs.veryfi.com/api/receipts-invoices/search-documents/
     * @param page   The page number. The response is capped to maximum of 50 results per page.
     * @param pageSize The number of Documents per page.
     * @param boundingBoxes A field used to determine whether or not to return bounding_box and bounding_region for extracted fields in the Document response.
     * @param confidenceDetails A field used to determine whether or not to return the score and ocr_score fields in the Document response.
     * @param parameters Additional request parameters.
     * @return the list of previously processed documents {@link String}
     */
    CompletableFuture<String> getDocumentsAsync(int page, int pageSize, boolean boundingBoxes, boolean confidenceDetails, JSONObject parameters);

    /**
     * Returns a json string {@link String} document information. https://docs.veryfi.com/api/receipts-invoices/get-a-document/
     * @param documentId ID of the document you'd like to retrieve.
     * @return the data extracted from the Document {@link String}
     */
    String getDocument(String documentId);

    /**
     * Returns a json string {@link CompletableFuture<String>} document information. https://docs.veryfi.com/api/receipts-invoices/get-a-document/
     * @param documentId ID of the document you'd like to retrieve.
     * @return the data extracted from the Document {@link CompletableFuture<String>}
     */
    CompletableFuture<String> getDocumentAsync(String documentId);

    /**
     * Process a document and extract all the fields from it. https://docs.veryfi.com/api/receipts-invoices/process-a-document/
     * @param filePath Path on disk to a file to submit for data extraction
     * @param categories List of categories Veryfi can use to categorize the document
     * @param deleteAfterProcessing Delete this document from Veryfi after data has been extracted
     * @param parameters Additional request parameters
     * @return the data extracted from the Document {@link String}
     */
    String processDocument(String filePath, List<String> categories, boolean deleteAfterProcessing,
                           JSONObject parameters);

    /**
     * Process a document and extract all the fields from it. https://docs.veryfi.com/api/receipts-invoices/process-a-document/
     * @param filePath Path on disk to a file to submit for data extraction
     * @param categories List of categories Veryfi can use to categorize the document
     * @param deleteAfterProcessing Delete this document from Veryfi after data has been extracted
     * @param parameters Additional request parameters
     * @return the data extracted from the Document {@link CompletableFuture<String>}
     */
    CompletableFuture<String> processDocumentAsync(String filePath, List<String> categories,
                                                   boolean deleteAfterProcessing, JSONObject parameters);

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
    String processDocument(String fileName, String fileData, List<String> categories,
                                  boolean deleteAfterProcessing, JSONObject parameters);

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
    CompletableFuture<String> processDocumentAsync(String fileName, String fileData, List<String> categories,
                                                          boolean deleteAfterProcessing, JSONObject parameters);

    /**
     * Process Document from url and extract all the fields from it. https://docs.veryfi.com/api/receipts-invoices/process-a-document/
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
    String processDocumentUrl(String fileUrl, List<String> fileUrls, List<String> categories, boolean deleteAfterProcessing,
                              int maxPagesToProcess, boolean boostMode, String externalId, JSONObject parameters);

    /**
     * Process Document from url and extract all the fields from it. https://docs.veryfi.com/api/receipts-invoices/process-a-document/
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
    CompletableFuture<String> processDocumentUrlAsync(String fileUrl, List<String> fileUrls, List<String> categories, boolean deleteAfterProcessing,
                              int maxPagesToProcess, boolean boostMode, String externalId, JSONObject parameters);


    /**
     * Delete Document from Veryfi. https://docs.veryfi.com/api/receipts-invoices/delete-a-document/
     * @param documentId ID of the document you'd like to delete.
     * @return the response data. {@link String}
     */
    String deleteDocument(String documentId);

    /**
     * Delete Document from Veryfi. https://docs.veryfi.com/api/receipts-invoices/delete-a-document/
     * @param documentId ID of the document you'd like to delete.
     * @return the response data. {@link CompletableFuture<String>}
     */
    CompletableFuture<String> deleteDocumentAsync(String documentId);

    /**
     * Update data for a previously processed document, including almost any field like `vendor`, `date`, `notes` and etc.
     * https://docs.veryfi.com/api/receipts-invoices/update-a-document/
     * @param documentId ID of the document you'd like to update.
     * @param parameters Additional request parameters.
     * @return A document json with updated fields, if fields are writable. Otherwise a document with unchanged fields. {@link String}
     */
    String updateDocument(String documentId, JSONObject parameters);

    /**
     * Update data for a previously processed document, including almost any field like `vendor`, `date`, `notes` and etc.
     * https://docs.veryfi.com/api/receipts-invoices/update-a-document/
     * @param documentId ID of the document you'd like to update.
     * @param parameters Additional request parameters.
     * @return A document json with updated fields, if fields are writable. Otherwise a document with unchanged fields. {@link CompletableFuture<String>}
     */
    CompletableFuture<String> updateDocumentAsync(String documentId, JSONObject parameters);

    /**
     * Retrieve all line items for a document. https://docs.veryfi.com/api/receipts-invoices/get-document-line-items/
     * @param documentId ID of the document you'd like to retrieve.
     * @return List of line items extracted from the document. {@link String}
     */
    String getLineItems(String documentId);

    /**
     * Retrieve all line items for a document. https://docs.veryfi.com/api/receipts-invoices/get-document-line-items/
     * @param documentId ID of the document you'd like to retrieve.
     * @return List of line items extracted from the document. {@link CompletableFuture<String>}
     */
    CompletableFuture<String> getLineItemsAsync(String documentId);

    /**
     * Retrieve a line item for existing document by ID. https://docs.veryfi.com/api/receipts-invoices/get-a-line-item/
     * @param documentId ID of the document you'd like to retrieve.
     * @param lineItemId ID of the line item you'd like to retrieve.
     * @return Line item extracted from the document. {@link String}
     */
    String getLineItem(String documentId, String lineItemId);

    /**
     * Retrieve a line item for existing document by ID. https://docs.veryfi.com/api/receipts-invoices/get-a-line-item/
     * @param documentId ID of the document you'd like to retrieve.
     * @param lineItemId ID of the line item you'd like to retrieve.
     * @return Line item extracted from the document. {@link CompletableFuture<String>}
     */
    CompletableFuture<String> getLineItemAsync(String documentId, String lineItemId);

    /**
     * Add a new line item on an existing document. https://docs.veryfi.com/api/receipts-invoices/create-a-line-item/
     * @param documentId ID of the document you'd like to update.
     * @param payload line item object to add.
     * @return Added line item data. {@link String}
     * @throws NotValidModelException when the model is not valid it throws this exception.
     */
    String addLineItem(String documentId, AddLineItem payload) throws NotValidModelException;

    /**
     * Add a new line item on an existing document. https://docs.veryfi.com/api/receipts-invoices/create-a-line-item/
     * @param documentId ID of the document you'd like to update.
     * @param payload line item object to add.
     * @return Added line item data. {@link CompletableFuture<String>}
     * @throws NotValidModelException when the model is not valid it throws this exception.
     */
    CompletableFuture<String> addLineItemAsync(String documentId, AddLineItem payload) throws NotValidModelException;

    /**
     * Update an existing line item on an existing document. https://docs.veryfi.com/api/receipts-invoices/update-a-line-item/
     * @param documentId ID of the document you'd like to update.
     * @param lineItemId ID of the line item you'd like to update.
     * @param payload line item object to update.
     * @return Line item data with updated fields, if fields are writable. Otherwise line item data with unchanged fields. {@link String}
     * @throws NotValidModelException when the model is not valid it throws this exception.
     */
    String updateLineItem(String documentId, String lineItemId, UpdateLineItem payload) throws NotValidModelException;

    /**
     * Update an existing line item on an existing document. https://docs.veryfi.com/api/receipts-invoices/update-a-line-item/
     * @param documentId ID of the document you'd like to update.
     * @param lineItemId ID of the line item you'd like to update.
     * @param payload line item object to update.
     * @return Line item data with updated fields, if fields are writable. Otherwise line item data with unchanged fields. {@link CompletableFuture<String>}
     * @throws NotValidModelException when the model is not valid it throws this exception.
     */
    CompletableFuture<String> updateLineItemAsync(String documentId, String lineItemId, UpdateLineItem payload) throws NotValidModelException;

    /**
     * Delete all line items on an existing document. https://docs.veryfi.com/api/receipts-invoices/delete-all-document-line-items/
     * @param documentId ID of the document you'd like to delete.
     * @return the response data. {@link String}
     */
    String deleteLineItems(String documentId);

    /**
     * Delete all line items on an existing document. https://docs.veryfi.com/api/receipts-invoices/delete-all-document-line-items/
     * @param documentId ID of the document you'd like to delete.
     * @return @return the response data. {@link CompletableFuture<String>}
     */
    CompletableFuture<String> deleteLineItemsAsync(String documentId);

    /**
     * Delete an existing line item on an existing document. https://docs.veryfi.com/api/receipts-invoices/delete-a-line-item/
     * @param documentId  ID of the document you'd like to delete.
     * @param lineItemId  ID of the line item you'd like to delete.
     * @return the response data. {@link String}
     */
    String deleteLineItem(String documentId, String lineItemId);

    /**
     * Delete an existing line item on an existing document. https://docs.veryfi.com/api/receipts-invoices/delete-a-line-item/
     * @param documentId  ID of the document you'd like to delete.
     * @param lineItemId  ID of the line item you'd like to delete.
     * @return the response data. {@link CompletableFuture<String>}
     */
    CompletableFuture<String> deleteLineItemAsync(String documentId, String lineItemId);

    /**
     * Replace multiple tags on an existing document. https://docs.veryfi.com/api/receipts-invoices/add-tags-to-a-document/
     * @param documentId  ID of the document you'd like to update.
     * @param tags  tags array of tags to be added.
     * @return the response data. {@link String}
     */
    String replaceTags(String documentId, List<String> tags);

    /**
     * Replace multiple tags on an existing document. https://docs.veryfi.com/api/receipts-invoices/add-tags-to-a-document/
     * @param documentId  ID of the document you'd like to update.
     * @param tags  tags array of tags to be added.
     * @return the response data. {@link CompletableFuture<String>}
     */
    CompletableFuture<String> replaceTagsAsync(String documentId, List<String> tags);

    /**
     * Add multiple tags on an existing document. https://docs.veryfi.com/api/receipts-invoices/add-tags-to-a-document/
     * @param documentId  ID of the document you'd like to update.
     * @param tags  tags array of tags to be added.
     * @return the response data. {@link String}
     */
    String addTags(String documentId, List<String> tags);

    /**
     * Add multiple tags on an existing document. https://docs.veryfi.com/api/receipts-invoices/add-tags-to-a-document/
     * @param documentId  ID of the document you'd like to update.
     * @param tags  tags array of tags to be added.
     * @return the response data. {@link CompletableFuture<String>}
     */
    CompletableFuture<String> addTagsAsync(String documentId, List<String> tags);

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
    String getAnyDocuments(int page, int pageSize, boolean boundingBoxes, boolean confidenceDetails, JSONObject parameters);

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
    CompletableFuture<String> getAnyDocumentsAsync(int page, int pageSize, boolean boundingBoxes, boolean confidenceDetails, JSONObject parameters);

    /**
     * Returns a json string {@link String} AnyDocument information. https://docs.veryfi.com/api/anydocs/get-a-A-doc/
     *
     * @param documentId ID of the AnyDocument you'd like to retrieve.
     * @return the data extracted from the AnyDocument {@link String}
     */
    String getAnyDocument(String documentId);

    /**
     * Returns a json string {@link CompletableFuture<String>} AnyDocument information. https://docs.veryfi.com/api/anydocs/get-a-A-doc/
     *
     * @param documentId ID of the AnyDocument you'd like to retrieve.
     * @return the data extracted from the AnyDocument {@link String}
     */
    CompletableFuture<String> getAnyDocumentAsync(String documentId);

    /**
     * Process a AnyDocument and extract all the fields from it. https://docs.veryfi.com/api/anydocs/process-A-doc/
     *
     * @param filePath      Path on disk to a file to submit for data extraction.
     * @param blueprintName The name of the extraction blueprints.
     * @param parameters    Additional request parameters.
     * @return the data extracted from the AnyDocument {@link String}
     */
    String processAnyDocument(String filePath, String blueprintName, JSONObject parameters);

    /**
     * Process a AnyDocument and extract all the fields from it. https://docs.veryfi.com/api/anydocs/process-A-doc/
     *
     * @param filePath      Path on disk to a file to submit for data extraction.
     * @param blueprintName The name of the extraction blueprints.
     * @param parameters    Additional request parameters.
     * @return the data extracted from the AnyDocument {@link CompletableFuture<String>}
     */
    CompletableFuture<String> processAnyDocumentAsync(String filePath, String blueprintName, JSONObject parameters);

    /**
     * Process AnyDocument from url and extract all the fields from it. https://docs.veryfi.com/api/anydocs/process-A-doc/
     *
     * @param fileUrl       Required if file_urls isn't specified. Publicly accessible URL to a file, e.g. "https://cdn.example.com/receipt.jpg".
     * @param fileUrls      Required if file_url isn't specifies. List of publicly accessible URLs to multiple files, e.g. ["https://cdn.example.com/receipt1.jpg", "https://cdn.example.com/receipt2.jpg"]
     * @param blueprintName The name of the extraction blueprints.
     * @param parameters    Additional request parameters
     * @return the data extracted from the AnyDocument {@link String}
     */
    String processAnyDocumentUrl(String fileUrl, List<String> fileUrls, String blueprintName, JSONObject parameters);

    /**
     * Process AnyDocument from url and extract all the fields from it. https://docs.veryfi.com/api/anydocs/process-A-doc/
     *
     * @param fileUrl       Required if file_urls isn't specified. Publicly accessible URL to a file, e.g. "https://cdn.example.com/receipt.jpg".
     * @param fileUrls      Required if file_url isn't specifies. List of publicly accessible URLs to multiple files, e.g. ["https://cdn.example.com/receipt1.jpg", "https://cdn.example.com/receipt2.jpg"]
     * @param blueprintName The name of the extraction blueprints.
     * @param parameters    Additional request parameters
     * @return the data extracted from the AnyDocument {@link CompletableFuture<String>}
     */
    CompletableFuture<String> processAnyDocumentUrlAsync(String fileUrl, List<String> fileUrls, String blueprintName, JSONObject parameters);

    /**
     * Delete AnyDocument from Veryfi. https://docs.veryfi.com/api/anydocs/delete-a-A-doc/
     *
     * @param documentId ID of the AnyDocument you'd like to delete.
     * @return the response data. {@link String}
     */
    String deleteAnyDocument(String documentId);

    /**
     * Delete AnyDocument from Veryfi. https://docs.veryfi.com/api/anydocs/delete-a-A-doc/
     *
     * @param documentId ID of the AnyDocument you'd like to delete.
     * @return the response data. {@link CompletableFuture<String>}
     */
    CompletableFuture<String> deleteAnyDocumentAsync(String documentId);

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
    public String getBankStatements(int page, int pageSize, boolean boundingBoxes, boolean confidenceDetails, JSONObject parameters);

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
    CompletableFuture<String> getBankStatementsAsync(int page, int pageSize, boolean boundingBoxes, boolean confidenceDetails, JSONObject parameters);

    /**
     * Returns a json string {@link String} BankStatement information. https://docs.veryfi.com/api/bank-statements/get-a-bank-statement/
     *
     * @param documentId ID of the BankStatement you'd like to retrieve.
     * @return the data extracted from the BankStatement {@link String}
     */
    String getBankStatement(String documentId);

    /**
     * Returns a json string {@link CompletableFuture<String>} BankStatement information. https://docs.veryfi.com/api/bank-statements/get-a-bank-statement/
     *
     * @param documentId ID of the BankStatement you'd like to retrieve.
     * @return the data extracted from the BankStatement {@link String}
     */
    CompletableFuture<String> getBankStatementAsync(String documentId);

    /**
     * Process a BankStatement and extract all the fields from it. https://docs.veryfi.com/api/bank-statements/process-a-bank-statement/
     *
     * @param filePath      Path on disk to a file to submit for data extraction.
     * @param parameters    Additional request parameters.
     * @return the data extracted from the BankStatement {@link String}
     */
    String processBankStatement(String filePath, JSONObject parameters);

    /**
     * Process a BankStatement and extract all the fields from it. https://docs.veryfi.com/api/bank-statements/process-a-bank-statement/
     *
     * @param filePath      Path on disk to a file to submit for data extraction.
     * @param parameters    Additional request parameters.
     * @return the data extracted from the BankStatement {@link CompletableFuture<String>}
     */
    CompletableFuture<String> processBankStatementAsync(String filePath, JSONObject parameters);

    /**
     * Process BankStatement from url and extract all the fields from it. https://docs.veryfi.com/api/bank-statements/process-a-bank-statement/
     *
     * @param fileUrl       Required if file_urls isn't specified. Publicly accessible URL to a file, e.g. "https://cdn.example.com/receipt.jpg".
     * @param fileUrls      Required if file_url isn't specifies. List of publicly accessible URLs to multiple files, e.g. ["https://cdn.example.com/receipt1.jpg", "https://cdn.example.com/receipt2.jpg"]
     * @param parameters    Additional request parameters
     * @return the data extracted from the BankStatement {@link String}
     */
    String processBankStatementUrl(String fileUrl, List<String> fileUrls, JSONObject parameters);

    /**
     * Process BankStatement from url and extract all the fields from it. https://docs.veryfi.com/api/bank-statements/process-a-bank-statement/
     *
     * @param fileUrl       Required if file_urls isn't specified. Publicly accessible URL to a file, e.g. "https://cdn.example.com/receipt.jpg".
     * @param fileUrls      Required if file_url isn't specifies. List of publicly accessible URLs to multiple files, e.g. ["https://cdn.example.com/receipt1.jpg", "https://cdn.example.com/receipt2.jpg"]
     * @param parameters    Additional request parameters
     * @return the data extracted from the BankStatement {@link CompletableFuture<String>}
     */
    CompletableFuture<String> processBankStatementUrlAsync(String fileUrl, List<String> fileUrls, JSONObject parameters);

    /**
     * Delete BankStatement from Veryfi. https://docs.veryfi.com/api/bank-statements/delete-a-bank-statement/
     *
     * @param documentId ID of the BankStatement you'd like to delete.
     * @return the response data. {@link String}
     */
    String deleteBankStatement(String documentId);

    /**
     * Delete BankStatement from Veryfi. https://docs.veryfi.com/api/bank-statements/delete-a-bank-statement/
     *
     * @param documentId ID of the BankStatement you'd like to delete.
     * @return the response data. {@link CompletableFuture<String>}
     */
    CompletableFuture<String> deleteBankStatementAsync(String documentId);

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
    String getBusinessCards(int page, int pageSize, boolean boundingBoxes, boolean confidenceDetails, JSONObject parameters);

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
    CompletableFuture<String> getBusinessCardsAsync(int page, int pageSize, boolean boundingBoxes, boolean confidenceDetails, JSONObject parameters);

    /**
     * Returns a json string {@link String} Business Card information. https://docs.veryfi.com/api/business-cards/get-a-business-card/
     *
     * @param documentId ID of the BusinessCard you'd like to retrieve.
     * @return the data extracted from the Business Card {@link String}
     */
    String getBusinessCard(String documentId);

    /**
     * Returns a json string {@link CompletableFuture<String>} Business Card information. https://docs.veryfi.com/api/business-cards/get-a-business-card/
     *
     * @param documentId ID of the BusinessCard you'd like to retrieve.
     * @return the data extracted from the Business Card {@link String}
     */
    CompletableFuture<String> getBusinessCardAsync(String documentId);

    /**
     * Process a Business Card and extract all the fields from it. https://docs.veryfi.com/api/business-cards/process-a-business-card/
     *
     * @param filePath      Path on disk to a file to submit for data extraction.
     * @param parameters    Additional request parameters.
     * @return the data extracted from the Business Card {@link String}
     */
    String processBusinessCard(String filePath, JSONObject parameters);

    /**
     * Process a Business Card and extract all the fields from it. https://docs.veryfi.com/api/business-cards/process-a-business-card/
     *
     * @param filePath      Path on disk to a file to submit for data extraction.
     * @param parameters    Additional request parameters.
     * @return the data extracted from the Business Card {@link CompletableFuture<String>}
     */
    CompletableFuture<String> processBusinessCardAsync(String filePath, JSONObject parameters);

    /**
     * Process Business Card from url and extract all the fields from it. https://docs.veryfi.com/api/business-cards/process-a-business-card/
     *
     * @param fileUrl       Required if file_urls isn't specified. Publicly accessible URL to a file, e.g. "https://cdn.example.com/receipt.jpg".
     * @param fileUrls      Required if file_url isn't specifies. List of publicly accessible URLs to multiple files, e.g. ["https://cdn.example.com/receipt1.jpg", "https://cdn.example.com/receipt2.jpg"]
     * @param parameters    Additional request parameters
     * @return the data extracted from the Business Card {@link String}
     */
    String processBusinessCardUrl(String fileUrl, List<String> fileUrls, JSONObject parameters);

    /**
     * Process Business Card from url and extract all the fields from it. https://docs.veryfi.com/api/business-cards/process-a-business-card/
     *
     * @param fileUrl       Required if file_urls isn't specified. Publicly accessible URL to a file, e.g. "https://cdn.example.com/receipt.jpg".
     * @param fileUrls      Required if file_url isn't specifies. List of publicly accessible URLs to multiple files, e.g. ["https://cdn.example.com/receipt1.jpg", "https://cdn.example.com/receipt2.jpg"]
     * @param parameters    Additional request parameters
     * @return the data extracted from the Business Card {@link CompletableFuture<String>}
     */
    CompletableFuture<String> processBusinessCardUrlAsync(String fileUrl, List<String> fileUrls, JSONObject parameters);

    /**
     * Delete BusinessCard from Veryfi. https://docs.veryfi.com/api/business-cards/delete-a-business-card/
     *
     * @param documentId ID of the Business Card you'd like to delete.
     * @return the response data. {@link String}
     */
    String deleteBusinessCard(String documentId);

    /**
     * Delete BusinessCard from Veryfi. https://docs.veryfi.com/api/business-cards/delete-a-business-card/
     *
     * @param documentId ID of the Business Card you'd like to delete.
     * @return the response data. {@link CompletableFuture<String>}
     */
    CompletableFuture<String> deleteBusinessCardAsync(String documentId);

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
    String getChecks(int page, int pageSize, boolean boundingBoxes, boolean confidenceDetails, JSONObject parameters);

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
    CompletableFuture<String> getChecksAsync(int page, int pageSize, boolean boundingBoxes, boolean confidenceDetails, JSONObject parameters);

    /**
     * Returns a json string {@link String} Check information. https://docs.veryfi.com/api/checks/get-a-check/
     *
     * @param documentId ID of the Check you'd like to retrieve.
     * @return the data extracted from the Check {@link String}
     */
    String getCheck(String documentId);

    /**
     * Returns a json string {@link CompletableFuture<String>} Check information.
     *
     * @param documentId ID of the Check you'd like to retrieve. https://docs.veryfi.com/api/checks/get-a-check/
     * @return the data extracted from the Check {@link String}
     */
    CompletableFuture<String> getCheckAsync(String documentId);

    /**
     * Process a Check and extract all the fields from it. https://docs.veryfi.com/api/checks/process-a-check/
     *
     * @param filePath      Path on disk to a file to submit for data extraction.
     * @param parameters    Additional request parameters.
     * @return the data extracted from the Check {@link String}
     */
    String processCheck(String filePath, JSONObject parameters);

    /**
     * Process a Check and extract all the fields from it. https://docs.veryfi.com/api/checks/process-a-check/
     *
     * @param filePath      Path on disk to a file to submit for data extraction.
     * @param parameters    Additional request parameters.
     * @return the data extracted from the Check {@link CompletableFuture<String>}
     */
    CompletableFuture<String> processCheckAsync(String filePath, JSONObject parameters);

    /**
     * Process a Check and extract all the fields from it. https://docs.veryfi.com/api/checks/process-a-check/
     *
     * @param fileName      Name of the file to upload to the Veryfi API
     * @param fileData      Base64 encoded file data
     * @param parameters    Additional request parameters
     * @return the data extracted from the Check {@link String}
     */
    String processCheck(String fileName, String fileData, JSONObject parameters);

    /**
     * Process a Check and extract all the fields from it. https://docs.veryfi.com/api/checks/process-a-check/
     *
     * @param fileName      Name of the file to upload to the Veryfi API
     * @param fileData      Base64 encoded file data
     * @param parameters    Additional request parameters
     * @return the data extracted from the Check {@link CompletableFuture<String>}
     */
    CompletableFuture<String> processCheckAsync(String fileName, String fileData, JSONObject parameters);

    /**
     * Process Check from url and extract all the fields from it. https://docs.veryfi.com/api/checks/process-a-check/
     *
     * @param fileUrl       Required if file_urls isn't specified. Publicly accessible URL to a file, e.g. "https://cdn.example.com/receipt.jpg".
     * @param fileUrls      Required if file_url isn't specifies. List of publicly accessible URLs to multiple files, e.g. ["https://cdn.example.com/receipt1.jpg", "https://cdn.example.com/receipt2.jpg"]
     * @param parameters    Additional request parameters
     * @return the data extracted from the Check {@link String}
     */
    String processCheckUrl(String fileUrl, List<String> fileUrls, JSONObject parameters);

    /**
     * Process Check from url and extract all the fields from it. https://docs.veryfi.com/api/checks/process-a-check/
     *
     * @param fileUrl       Required if file_urls isn't specified. Publicly accessible URL to a file, e.g. "https://cdn.example.com/receipt.jpg".
     * @param fileUrls      Required if file_url isn't specifies. List of publicly accessible URLs to multiple files, e.g. ["https://cdn.example.com/receipt1.jpg", "https://cdn.example.com/receipt2.jpg"]
     * @param parameters    Additional request parameters
     * @return the data extracted from the Check {@link CompletableFuture<String>}
     */
    CompletableFuture<String> processCheckUrlAsync(String fileUrl, List<String> fileUrls, JSONObject parameters);

    /**
     * Delete Check from Veryfi. https://docs.veryfi.com/api/checks/delete-a-check/
     *
     * @param documentId ID of the Check you'd like to delete.
     * @return the response data. {@link String}
     */
    String deleteCheck(String documentId);

    /**
     * Delete Check from Veryfi. https://docs.veryfi.com/api/checks/delete-a-check/
     *
     * @param documentId ID of the Check you'd like to delete.
     * @return the response data. {@link CompletableFuture<String>}
     */
    CompletableFuture<String> deleteCheckAsync(String documentId);

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
    String getW2s(int page, int pageSize, boolean boundingBoxes, boolean confidenceDetails, JSONObject parameters);

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
    CompletableFuture<String> getW2sAsync(int page, int pageSize, boolean boundingBoxes, boolean confidenceDetails, JSONObject parameters);

    /**
     * Returns a json string {@link String} W2 information. https://docs.veryfi.com/api/w2s/get-a-w-2/
     *
     * @param documentId ID of the W2 you'd like to retrieve.
     * @return the data extracted from the W2 {@link String}
     */
    String getW2(String documentId);

    /**
     * Returns a json string {@link CompletableFuture<String>} W2 information. https://docs.veryfi.com/api/w2s/get-a-w-2/
     *
     * @param documentId ID of the W2 you'd like to retrieve.
     * @return the data extracted from the W2 {@link String}
     */
    CompletableFuture<String> getW2Async(String documentId);

    /**
     * Process a W2 and extract all the fields from it. https://docs.veryfi.com/api/w2s/process-a-w-2/
     *
     * @param filePath      Path on disk to a file to submit for data extraction.
     * @param parameters    Additional request parameters.
     * @return the data extracted from the W2 {@link String}
     */
    String processW2(String filePath, JSONObject parameters);

    /**
     * Process a W2 and extract all the fields from it. https://docs.veryfi.com/api/w2s/process-a-w-2/
     *
     * @param filePath      Path on disk to a file to submit for data extraction.
     * @param parameters    Additional request parameters.
     * @return the data extracted from the W2 {@link CompletableFuture<String>}
     */
    CompletableFuture<String> processW2Async(String filePath, JSONObject parameters);

    /**
     * Process W2 from url and extract all the fields from it. https://docs.veryfi.com/api/w2s/process-a-w-2/
     *
     * @param fileUrl       Required if file_urls isn't specified. Publicly accessible URL to a file, e.g. "https://cdn.example.com/receipt.jpg".
     * @param fileUrls      Required if file_url isn't specifies. List of publicly accessible URLs to multiple files, e.g. ["https://cdn.example.com/receipt1.jpg", "https://cdn.example.com/receipt2.jpg"]
     * @param parameters    Additional request parameters
     * @return the data extracted from the W2 {@link String}
     */
    String processW2Url(String fileUrl, List<String> fileUrls, JSONObject parameters);

    /**
     * Process W2 from url and extract all the fields from it. https://docs.veryfi.com/api/w2s/process-a-w-2/
     *
     * @param fileUrl       Required if file_urls isn't specified. Publicly accessible URL to a file, e.g. "https://cdn.example.com/receipt.jpg".
     * @param fileUrls      Required if file_url isn't specifies. List of publicly accessible URLs to multiple files, e.g. ["https://cdn.example.com/receipt1.jpg", "https://cdn.example.com/receipt2.jpg"]
     * @param parameters    Additional request parameters
     * @return the data extracted from the W2 {@link CompletableFuture<String>}
     */
    CompletableFuture<String> processW2UrlAsync(String fileUrl, List<String> fileUrls, JSONObject parameters);

    /**
     * Delete W2 from Veryfi. https://docs.veryfi.com/api/w2s/delete-a-w-2/
     *
     * @param documentId ID of the W2 you'd like to delete.
     * @return the response data. {@link String}
     */
    String deleteW2(String documentId);

    /**
     * Delete W2 from Veryfi. https://docs.veryfi.com/api/w2s/delete-a-w-2/
     *
     * @param documentId ID of the W2 you'd like to delete.
     * @return the response data. {@link CompletableFuture<String>}
     */
    CompletableFuture<String> deleteW2Async(String documentId);

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
    String getW8BenEs(int page, int pageSize, boolean boundingBoxes, boolean confidenceDetails, JSONObject parameters);

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
    CompletableFuture<String> getW8BenEsAsync(int page, int pageSize, boolean boundingBoxes, boolean confidenceDetails, JSONObject parameters);

    /**
     * Returns a json string {@link String} W-8BEN-E information. https://docs.veryfi.com/api/w-8ben-e/get-a-w-8-ben-e/
     *
     * @param documentId ID of the W-8BEN-E you'd like to retrieve.
     * @return the data extracted from the W-8BEN-E {@link String}
     */
    String getW8BenE(String documentId);

    /**
     * Returns a json string {@link CompletableFuture<String>} W-8BEN-E information.
     *
     * @param documentId ID of the W-8BEN-E you'd like to retrieve. https://docs.veryfi.com/api/w-8ben-e/get-a-w-8-ben-e/
     * @return the data extracted from the W-8BEN-E {@link String}
     */
    CompletableFuture<String> getW8BenEAsync(String documentId);

    /**
     * Process a W-8BEN-E and extract all the fields from it. https://docs.veryfi.com/api/w-8ben-e/process-a-w-8-ben-e/
     *
     * @param filePath      Path on disk to a file to submit for data extraction.
     * @param parameters    Additional request parameters.
     * @return the data extracted from the W-8BEN-E {@link String}
     */
    String processW8BenE(String filePath, JSONObject parameters);

    /**
     * Process a W-8BEN-E and extract all the fields from it. https://docs.veryfi.com/api/w-8ben-e/process-a-w-8-ben-e/
     *
     * @param filePath      Path on disk to a file to submit for data extraction.
     * @param parameters    Additional request parameters.
     * @return the data extracted from the W-8BEN-E {@link CompletableFuture<String>}
     */
    CompletableFuture<String> processW8BenEAsync(String filePath, JSONObject parameters);

    /**
     * Process W-8BEN-E from url and extract all the fields from it. https://docs.veryfi.com/api/w-8ben-e/process-a-w-8-ben-e/
     *
     * @param fileUrl       Required if file_urls isn't specified. Publicly accessible URL to a file, e.g. "https://cdn.example.com/receipt.jpg".
     * @param fileUrls      Required if file_url isn't specifies. List of publicly accessible URLs to multiple files, e.g. ["https://cdn.example.com/receipt1.jpg", "https://cdn.example.com/receipt2.jpg"]
     * @param parameters    Additional request parameters
     * @return the data extracted from the W-8BEN-E {@link String}
     */
    String processW8BenEUrl(String fileUrl, List<String> fileUrls, JSONObject parameters);

    /**
     * Process W-8BEN-E from url and extract all the fields from it. https://docs.veryfi.com/api/w-8ben-e/process-a-w-8-ben-e/
     *
     * @param fileUrl       Required if file_urls isn't specified. Publicly accessible URL to a file, e.g. "https://cdn.example.com/receipt.jpg".
     * @param fileUrls      Required if file_url isn't specifies. List of publicly accessible URLs to multiple files, e.g. ["https://cdn.example.com/receipt1.jpg", "https://cdn.example.com/receipt2.jpg"]
     * @param parameters    Additional request parameters
     * @return the data extracted from the W-8BEN-E {@link CompletableFuture<String>}
     */
    CompletableFuture<String> processW8BenEUrlAsync(String fileUrl, List<String> fileUrls, JSONObject parameters);

    /**
     * Delete W-8BEN-E from Veryfi. https://docs.veryfi.com/api/w-8ben-e/delete-a-w-8-ben-e/
     *
     * @param documentId ID of the W-8BEN-E you'd like to delete.
     * @return the response data. {@link String}
     */
    String deleteW8BenE(String documentId);

    /**
     * Delete W-8BEN-E from Veryfi. https://docs.veryfi.com/api/w-8ben-e/delete-a-w-8-ben-e/
     *
     * @param documentId ID of the W-8BEN-E you'd like to delete.
     * @return the response data. {@link CompletableFuture<String>}
     */
    CompletableFuture<String> deleteW8BenEAsync(String documentId);

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
    String getW9s(int page, int pageSize, boolean boundingBoxes, boolean confidenceDetails, JSONObject parameters);

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
    CompletableFuture<String> getW9sAsync(int page, int pageSize, boolean boundingBoxes, boolean confidenceDetails, JSONObject parameters);

    /**
     * Returns a json string {@link String} W9 information. https://docs.veryfi.com/api/w9s/get-a-w-9/
     *
     * @param documentId ID of the W9 you'd like to retrieve.
     * @return the data extracted from the W9 {@link String}
     */
    String getW9(String documentId);

    /**
     * Returns a json string {@link CompletableFuture<String>} W9 information. https://docs.veryfi.com/api/w9s/get-a-w-9/
     *
     * @param documentId ID of the W9 you'd like to retrieve.
     * @return the data extracted from the W9 {@link String}
     */
    CompletableFuture<String> getW9Async(String documentId);

    /**
     * Process a W9 and extract all the fields from it. https://docs.veryfi.com/api/w9s/process-a-w-9/
     *
     * @param filePath      Path on disk to a file to submit for data extraction.
     * @param parameters    Additional request parameters.
     * @return the data extracted from the W9 {@link String}
     */
    String processW9(String filePath, JSONObject parameters);

    /**
     * Process a W9 and extract all the fields from it. https://docs.veryfi.com/api/w9s/process-a-w-9/
     *
     * @param filePath      Path on disk to a file to submit for data extraction.
     * @param parameters    Additional request parameters.
     * @return the data extracted from the W9 {@link CompletableFuture<String>}
     */
    CompletableFuture<String> processW9Async(String filePath, JSONObject parameters);

    /**
     * Process W9 from url and extract all the fields from it. https://docs.veryfi.com/api/w9s/process-a-w-9/
     *
     * @param fileUrl       Required if file_urls isn't specified. Publicly accessible URL to a file, e.g. "https://cdn.example.com/receipt.jpg".
     * @param fileUrls      Required if file_url isn't specifies. List of publicly accessible URLs to multiple files, e.g. ["https://cdn.example.com/receipt1.jpg", "https://cdn.example.com/receipt2.jpg"]
     * @param parameters    Additional request parameters
     * @return the data extracted from the W9 {@link String}
     */
    String processW9Url(String fileUrl, List<String> fileUrls, JSONObject parameters);

    /**
     * Process W9 from url and extract all the fields from it. https://docs.veryfi.com/api/w9s/process-a-w-9/
     *
     * @param fileUrl       Required if file_urls isn't specified. Publicly accessible URL to a file, e.g. "https://cdn.example.com/receipt.jpg".
     * @param fileUrls      Required if file_url isn't specifies. List of publicly accessible URLs to multiple files, e.g. ["https://cdn.example.com/receipt1.jpg", "https://cdn.example.com/receipt2.jpg"]
     * @param parameters    Additional request parameters
     * @return the data extracted from the W9 {@link CompletableFuture<String>}
     */
    CompletableFuture<String> processW9UrlAsync(String fileUrl, List<String> fileUrls, JSONObject parameters);

    /**
     * Delete W9 from Veryfi. https://docs.veryfi.com/api/w9s/delete-a-w-9/
     *
     * @param documentId ID of the W9 you'd like to delete.
     * @return the response data. {@link String}
     */
    String deleteW9(String documentId);

    /**
     * Delete W9 from Veryfi. https://docs.veryfi.com/api/w9s/delete-a-w-9/
     *
     * @param documentId ID of the W9 you'd like to delete.
     * @return the response data. {@link CompletableFuture<String>}
     */
    CompletableFuture<String> deleteW9Async(String documentId);

    /**
     * Returns a json string {@link String} with the list of Contracts.
     *
     * @param page   The page number. The response is capped to maximum of 50 results per page.
     * @param pageSize The number of Documents per page.
     * @param parameters Additional request parameters.
     * @return the url {@link String}
     */
    String getContracts(int page, int pageSize, JSONObject parameters);

    /**
     * Returns a json string {@link CompletableFuture<String>} list of Contracts.
     *
     * @param page   The page number. The response is capped to maximum of 50 results per page.
     * @param pageSize The number of Documents per page.
     * @param parameters Additional request parameters.
     * @return the list of previously processed Contracts {@link String}
     */
    CompletableFuture<String> getContractsAsync(int page, int pageSize, JSONObject parameters);

    /**
     * Returns a json string {@link String} with the Contract information.
     *
     * @param documentId ID of the Contract you'd like to retrieve.
     * @return the data extracted from the Contract {@link String}
     */
    String getContract(String documentId);

    /**
     * Returns a json string {@link CompletableFuture<String>} with the Contract information.
     *
     * @param documentId ID of the Contract you'd like to retrieve.
     * @return the data extracted from the Contract {@link String}
     */
    CompletableFuture<String> getContractAsync(String documentId);

    /**
     * Process a Contract and extract all the fields from it.
     *
     * @param filePath      Path on disk to a file to submit for data extraction.
     * @param parameters    Additional request parameters.
     * @return the data extracted from the Contract {@link String}
     */
    String processContract(String filePath, JSONObject parameters);

    /**
     * Process a Contract and extract all the fields from it.
     *
     * @param filePath      Path on disk to a file to submit for data extraction.
     * @param parameters    Additional request parameters.
     * @return the data extracted from the Contract {@link CompletableFuture<String>}
     */
    CompletableFuture<String> processContractAsync(String filePath, JSONObject parameters);

    /**
     * Process Contract from url and extract all the fields from it.
     *
     * @param fileUrl       Required if file_urls isn't specified. Publicly accessible URL to a file, e.g. "https://cdn.example.com/receipt.jpg".
     * @param parameters    Additional request parameters
     * @return the data extracted from the Contract {@link String}
     */
    String processContractUrl(String fileUrl, JSONObject parameters);

    /**
     * Process Contract from url and extract all the fields from it.
     *
     * @param fileUrl       Required if file_urls isn't specified. Publicly accessible URL to a file, e.g. "https://cdn.example.com/receipt.jpg".
     * @param parameters    Additional request parameters
     * @return the data extracted from the Contract {@link CompletableFuture<String>}
     */
    CompletableFuture<String> processContractUrlAsync(String fileUrl, JSONObject parameters);

    /**
     * Delete a Contract from Veryfi.
     *
     * @param documentId ID of the Contract you'd like to delete.
     * @return the response data. {@link String}
     */
    String deleteContract(String documentId);

    /**
     * Delete a Contract from Veryfi.
     *
     * @param documentId ID of the Contract you'd like to delete.
     * @return the response data. {@link CompletableFuture<String>}
     */
    CompletableFuture<String> deleteContractAsync(String documentId);

}
