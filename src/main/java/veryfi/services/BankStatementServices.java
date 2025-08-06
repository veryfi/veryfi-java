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
 * API services for Bank Statements
 */
class BankStatementServices extends NetworkClient {

    /**
     * Creates an instance of {@link BankStatementServices}.
     *
     * @param credentials  the {@link Credentials} provided by Veryfi.
     * @param apiVersion   the {@link int} api version to use Veryfi.
     */
    protected BankStatementServices(Credentials credentials, int apiVersion) {
        super(credentials, apiVersion);
    }

    /**
     * Creates an instance of {@link BankStatementServices}.
     *
     * @param credentials  the {@link Credentials} provided by Veryfi.
     * @param apiVersion   the {@link int} api version to use Veryfi.
     * @param httpClient   {@link HttpClient} for the Veryfi API
     */
    protected BankStatementServices(Credentials credentials, int apiVersion, HttpClient httpClient) {
        super(credentials, apiVersion, httpClient);
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
    protected String getBankStatements(int page, int pageSize, boolean boundingBoxes, boolean confidenceDetails, JSONObject parameters) {
        if (parameters == null)
            parameters = new JSONObject();
        parameters.put("page", page);
        parameters.put("page_size", pageSize);
        parameters.put("bounding_boxes", boundingBoxes);
        parameters.put("confidence_details", confidenceDetails);
        return request(HttpMethod.GET, Endpoint.bankStatements.path, parameters);
    }

    /**
     * Returns a json string {@link CompletableFuture}{@code <String>} list of Bank Statements. https://docs.veryfi.com/api/bank-statements/get-bank-statements/
     *
     * @param page   The page number. The response is capped to maximum of 50 results per page.
     * @param pageSize The number of Documents per page.
     * @param boundingBoxes A field used to determine whether or not to return bounding_box and bounding_region for extracted fields in the Document response.
     * @param confidenceDetails A field used to determine whether or not to return the score and ocr_score fields in the Document response.
     * @param parameters Additional request parameters.
     * @return the list of previously processed Bank Statements {@link String}
     */
    protected CompletableFuture<String> getBankStatementsAsync(int page, int pageSize, boolean boundingBoxes, boolean confidenceDetails, JSONObject parameters) {
        if (parameters == null)
            parameters = new JSONObject();
        parameters.put("page", page);
        parameters.put("page_size", pageSize);
        parameters.put("bounding_boxes", boundingBoxes);
        parameters.put("confidence_details", confidenceDetails);
        return requestAsync(HttpMethod.GET, Endpoint.bankStatements.path, parameters);
    }

    /**
     * Returns a json string {@link String} Bank Statement information. https://docs.veryfi.com/api/bank-statements/get-a-bank-statement/
     *
     * @param documentId ID of the Bank Statement you'd like to retrieve.
     * @return the data extracted from the Bank Statement {@link String}
     */
    protected String getBankStatement(String documentId) {
        String endpointName = Endpoint.bankStatements.path + documentId + "/";
        JSONObject parameters = new JSONObject();
        parameters.put("id", documentId);
        return request(HttpMethod.GET, endpointName, parameters);
    }

    /**
     * Returns a json string {@link CompletableFuture}{@code <String>} Bank Statement information. https://docs.veryfi.com/api/bank-statements/get-a-bank-statement/
     *
     * @param documentId ID of the Bank Statement you'd like to retrieve.
     * @return the data extracted from the Bank Statement {@link String}
     */
    protected CompletableFuture<String> getBankStatementAsync(String documentId) {
        String endpointName = Endpoint.bankStatements.path + documentId + "/";
        JSONObject parameters = new JSONObject();
        parameters.put("id", documentId);
        return requestAsync(HttpMethod.GET, endpointName, parameters);
    }

    /**
     * Process a Bank Statement and extract all the fields from it. https://docs.veryfi.com/api/bank-statements/process-a-bank-statement/
     *
     * @param filePath      Path on disk to a file to submit for data extraction.
     * @param parameters    Additional request parameters.
     * @return the data extracted from the Bank Statement {@link String}
     */
    protected String processBankStatement(String filePath, JSONObject parameters) {
        parameters = addFileToParameters(filePath, parameters);
        return request(HttpMethod.POST, Endpoint.bankStatements.path, parameters);
    }

    /**
     * Process a Bank Statement and extract all the fields from it. https://docs.veryfi.com/api/bank-statements/process-a-bank-statement/
     *
     * @param filePath      Path on disk to a file to submit for data extraction.
     * @param parameters    Additional request parameters.
     * @return the data extracted from the Bank Statement {@link CompletableFuture}{@code <String>}
     */
    protected CompletableFuture<String> processBankStatementAsync(String filePath, JSONObject parameters) {
        parameters = addFileToParameters(filePath, parameters);
        return requestAsync(HttpMethod.POST, Endpoint.bankStatements.path, parameters);
    }

    /**
     * Process a Bank Statement and extract all the fields from it. https://docs.veryfi.com/api/bank-statements/process-a-bank-statement/
     *
     * @param fileName      Name of the file to upload to the Veryfi API
     * @param fileData      Base64 encoded file data
     * @param parameters    Additional request parameters
     * @return the data extracted from the Bank Statement {@link String}
     */
    protected String processBankStatement(String fileName, String fileData, JSONObject parameters) {
        parameters = addFileToParameters(fileName, fileData, parameters);
        return request(HttpMethod.POST, Endpoint.bankStatements.path, parameters);
    }

    /**
     * Process a Bank Statement and extract all the fields from it. https://docs.veryfi.com/api/bank-statements/process-a-bank-statement/
     *
     * @param fileName      Name of the file to upload to the Veryfi API
     * @param fileData      Base64 encoded file data
     * @param parameters    Additional request parameters
     * @return the data extracted from the Bank Statement {@link CompletableFuture}{@code <String>}
     */
    protected CompletableFuture<String> processBankStatementAsync(String fileName, String fileData, JSONObject parameters) {
        parameters = addFileToParameters(fileName, fileData, parameters);
        return requestAsync(HttpMethod.POST, Endpoint.bankStatements.path, parameters);
    }

    /**
     * Process Bank Statement from url and extract all the fields from it. https://docs.veryfi.com/api/bank-statements/process-a-bank-statement/
     *
     * @param fileUrl       Required if file_urls isn't specified. Publicly accessible URL to a file, e.g. "https://cdn.example.com/receipt.jpg".
     * @param fileUrls      Required if file_url isn't specifies. List of publicly accessible URLs to multiple files, e.g. ["https://cdn.example.com/receipt1.jpg", "https://cdn.example.com/receipt2.jpg"]
     * @param parameters    Additional request parameters
     * @return the data extracted from the Bank Statement {@link String}
     */
    protected String processBankStatementUrl(String fileUrl, List<String> fileUrls, JSONObject parameters) {
        parameters = addUrlToParameters(fileUrl, fileUrls, parameters);
        return request(HttpMethod.POST, Endpoint.bankStatements.path, parameters);
    }

    /**
     * Process Bank Statement from url and extract all the fields from it. https://docs.veryfi.com/api/bank-statements/process-a-bank-statement/
     *
     * @param fileUrl       Required if file_urls isn't specified. Publicly accessible URL to a file, e.g. "https://cdn.example.com/receipt.jpg".
     * @param fileUrls      Required if file_url isn't specifies. List of publicly accessible URLs to multiple files, e.g. ["https://cdn.example.com/receipt1.jpg", "https://cdn.example.com/receipt2.jpg"]
     * @param parameters    Additional request parameters
     * @return the data extracted from the Bank Statement {@link CompletableFuture}{@code <String>}
     */
    protected CompletableFuture<String> processBankStatementUrlAsync(String fileUrl, List<String> fileUrls, JSONObject parameters) {
        parameters = addUrlToParameters(fileUrl, fileUrls, parameters);
        return requestAsync(HttpMethod.POST, Endpoint.bankStatements.path, parameters);
    }

    /**
     * Delete Bank Statement from Veryfi. https://docs.veryfi.com/api/bank-statements/delete-a-bank-statement/
     *
     * @param documentId ID of the Bank Statement you'd like to delete.
     * @return the response data. {@link String}
     */
    protected String deleteBankStatement(String documentId) {
        String endpointName = Endpoint.bankStatements.path + documentId + "/";
        JSONObject parameters = new JSONObject();
        parameters.put("id", documentId);
        return request(HttpMethod.DELETE, endpointName, parameters);
    }

    /**
     * Delete Bank Statement from Veryfi. https://docs.veryfi.com/api/bank-statements/delete-a-bank-statement/
     *
     * @param documentId ID of the Bank Statement you'd like to delete.
     * @return the response data. {@link CompletableFuture}{@code <String>}
     */
    protected CompletableFuture<String> deleteBankStatementAsync(String documentId) {
        String endpointName = Endpoint.bankStatements.path + documentId + "/";
        JSONObject parameters = new JSONObject();
        parameters.put("id", documentId);
        return requestAsync(HttpMethod.DELETE, endpointName, parameters);
    }

}
