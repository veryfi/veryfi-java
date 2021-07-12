package veryfi;

import org.json.JSONObject;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Veryfi API client for Java.
 * <p>
 * The instances of classes that implement this interface are thread-safe and immutable.
 */
public interface Client {
    /**
     * Returns a json string {@link String} list of documents.
     * @return the list of previously processed documents {@link String}
     */
    String getDocuments();

    /**
     * Returns a json string {@link CompletableFuture<String>} list of documents.
     * @return the list of previously processed documents {@link String}
     */
    CompletableFuture<String> getDocumentsAsync();

    /**
     * Returns a json string {@link String} document information
     * @param documentId ID of the document you'd like to retrieve.
     * @return the data extracted from the Document {@link String}
     */
    String getDocument(String documentId);

    /**
     * Returns a json string {@link CompletableFuture<String>} document information.
     * @param documentId ID of the document you'd like to retrieve.
     * @return the data extracted from the Document {@link CompletableFuture<String>}
     */
    CompletableFuture<String> getDocumentAsync(String documentId);

    /**
     * Process a document and extract all the fields from it
     * @param filePath Path on disk to a file to submit for data extraction
     * @param categories List of categories Veryfi can use to categorize the document
     * @param deleteAfterProcessing Delete this document from Veryfi after data has been extracted
     * @param parameters Additional request parameters
     * @return the data extracted from the Document {@link String}
     */
    String processDocument(String filePath, List<String> categories, boolean deleteAfterProcessing,
                           JSONObject parameters);

    /**
     * Process a document and extract all the fields from it
     * @param filePath Path on disk to a file to submit for data extraction
     * @param categories List of categories Veryfi can use to categorize the document
     * @param deleteAfterProcessing Delete this document from Veryfi after data has been extracted
     * @param parameters Additional request parameters
     * @return the data extracted from the Document {@link CompletableFuture<String>}
     */
    CompletableFuture<String> processDocumentAsync(String filePath, List<String> categories,
                                                   boolean deleteAfterProcessing, JSONObject parameters);

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
    String processDocumentUrl(String fileUrl, List<String> fileUrls, List<String> categories, boolean deleteAfterProcessing,
                              int maxPagesToProcess, boolean boostMode, String externalId, JSONObject parameters);

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
    CompletableFuture<String> processDocumentUrlAsync(String fileUrl, List<String> fileUrls, List<String> categories, boolean deleteAfterProcessing,
                              int maxPagesToProcess, boolean boostMode, String externalId, JSONObject parameters);


    /**
     * Delete Document from Veryfi
     * @param documentId ID of the document you'd like to delete.
     * @return the response data. {@link String}
     */
    String deleteDocument(String documentId);

    /**
     * Delete Document from Veryfi
     * @param documentId ID of the document you'd like to delete.
     * @return the response data. {@link CompletableFuture<String>}
     */
    CompletableFuture<String> deleteDocumentAsync(String documentId);

    /**
     * Update data for a previously processed document, including almost any field like `vendor`, `date`, `notes` and etc.
     * @param documentId ID of the document you'd like to update.
     * @param parameters Additional request parameters
     * @return A document json with updated fields, if fields are writable. Otherwise a document with unchanged fields. {@link String}
     */
    String updateDocument(String documentId, JSONObject parameters);

    /**
     * Update data for a previously processed document, including almost any field like `vendor`, `date`, `notes` and etc.
     * @param documentId ID of the document you'd like to update.
     * @param parameters Additional request parameters
     * @return A document json with updated fields, if fields are writable. Otherwise a document with unchanged fields. {@link CompletableFuture<String>}
     */
    CompletableFuture<String> updateDocumentAsync(String documentId, JSONObject parameters);

    /**
     * Change the default API version
     * @param version of the API by default is 7
     */
    void setAPIVersion(int version);

    /**
     * Define new time out for the requests in seconds
     * @param timeOut of the http requests in seconds
     */
    void setTimeOut(int timeOut);

    /**
     * By default is https://api.veryfi.com/api/;
     * @param baseUrl for the Veryfi API
     */
    void setBaseUrl(String baseUrl);

}
