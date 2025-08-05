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
 * API services for Document Classification
 */
class ClassifyServices extends NetworkClient {

    /**
     * Creates an instance of {@link ClassifyServices}.
     *
     * @param credentials  the {@link Credentials} provided by Veryfi.
     * @param apiVersion   the {@link int} api version to use Veryfi.
     */
    protected ClassifyServices(Credentials credentials, int apiVersion) {
        super(credentials, apiVersion);
    }

    /**
     * Creates an instance of {@link ClassifyServices}.
     *
     * @param credentials  the {@link Credentials} provided by Veryfi.
     * @param apiVersion   the {@link int} api version to use Veryfi.
     * @param httpClient   {@link HttpClient} for the Veryfi API
     */
    protected ClassifyServices(Credentials credentials, int apiVersion, HttpClient httpClient) {
        super(credentials, apiVersion, httpClient);
    }

    /**
     * Classify a document and extract all the fields from it. https://docs.veryfi.com/api/classify/classify-a-document/
     *
     * @param filePath      Path on disk to a file to submit for data extraction.
     * @param parameters    Additional request parameters.
     * @return the data extracted from the document {@link String}
     */
    protected String classifyDocument(String filePath, JSONObject parameters) {
        parameters = addFileToParameters(filePath, parameters);
        return request(HttpMethod.POST, Endpoint.classify.path, parameters);
    }

    /**
     * Classify a document and extract all the fields from it. https://docs.veryfi.com/api/classify/classify-a-document/
     *
     * @param filePath      Path on disk to a file to submit for data extraction.
     * @param parameters    Additional request parameters.
     * @return the data extracted from the document {@link CompletableFuture<String>}
     */
    protected CompletableFuture<String> classifyDocumentAsync(String filePath, JSONObject parameters) {
        parameters = addFileToParameters(filePath, parameters);
        return requestAsync(HttpMethod.POST, Endpoint.classify.path, parameters);
    }

    /**
     * Classify a document and extract all the fields from it. https://docs.veryfi.com/api/classify/classify-a-document/
     *
     * @param fileName      Name of the file to upload to the Veryfi API
     * @param fileData      Base64 encoded file data
     * @param parameters    Additional request parameters.
     * @return the data extracted from the document {@link String}
     */
    protected String classifyDocument(String fileName, String fileData, JSONObject parameters) {
        parameters = addFileToParameters(fileName, fileData, parameters);
        return request(HttpMethod.POST, Endpoint.classify.path, parameters);
    }

    /**
     * Classify a document and extract all the fields from it. https://docs.veryfi.com/api/classify/classify-a-document/
     *
     * @param fileName      Name of the file to upload to the Veryfi API
     * @param fileData      Base64 encoded file data
     * @param parameters    Additional request parameters.
     * @return the data extracted from the document {@link CompletableFuture<String>}
     */
    protected CompletableFuture<String> classifyDocumentAsync(String fileName, String fileData, JSONObject parameters) {
        parameters = addFileToParameters(fileName, fileData, parameters);
        return requestAsync(HttpMethod.POST, Endpoint.classify.path, parameters);
    }

    /**
     * Classify a document and extract all the fields from it. https://docs.veryfi.com/api/classify/classify-a-document/
     *
     * @param fileUrl       Required if file_urls isn't specified. Publicly accessible URL to a file, e.g. "https://cdn.example.com/receipt.jpg".
     * @param fileUrls      Required if file_url isn't specifies. List of publicly accessible URLs to multiple files, e.g. ["https://cdn.example.com/receipt1.jpg", "https://cdn.example.com/receipt2.jpg"]
     * @param parameters    Additional request parameters.
     * @return the data extracted from the document {@link String}
     */
    protected String classifyDocumentUrl(String fileUrl, List<String> fileUrls, JSONObject parameters) {
        parameters = addUrlToParameters(fileUrl, fileUrls, parameters);
        return request(HttpMethod.POST, Endpoint.classify.path, parameters);
    }

    /**
     * Classify a document and extract all the fields from it. https://docs.veryfi.com/api/classify/classify-a-document/
     *
     * @param fileUrl       Required if file_urls isn't specified. Publicly accessible URL to a file, e.g. "https://cdn.example.com/receipt.jpg".
     * @param fileUrls      Required if file_url isn't specifies. List of publicly accessible URLs to multiple files, e.g. ["https://cdn.example.com/receipt1.jpg", "https://cdn.example.com/receipt2.jpg"]
     * @param parameters    Additional request parameters.
     * @return the data extracted from the document {@link CompletableFuture<String>}
     */
    protected CompletableFuture<String> classifyDocumentUrlAsync(String fileUrl, List<String> fileUrls, JSONObject parameters) {
        parameters = addUrlToParameters(fileUrl, fileUrls, parameters);
        return requestAsync(HttpMethod.POST, Endpoint.classify.path, parameters);
    }

}
