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
 * API services for Contracts
 */
class ContractServices extends NetworkClient {

    /**
     * Creates an instance of {@link ContractServices}.
     *
     * @param credentials  the {@link Credentials} provided by Veryfi.
     * @param apiVersion   the {@link int} api version to use Veryfi.
     */
    protected ContractServices(Credentials credentials, int apiVersion) {
        super(credentials, apiVersion);
    }

    /**
     * Creates an instance of {@link ContractServices}.
     *
     * @param credentials  the {@link Credentials} provided by Veryfi.
     * @param apiVersion   the {@link int} api version to use Veryfi.
     * @param httpClient   {@link HttpClient} for the Veryfi API
     */
    protected ContractServices(Credentials credentials, int apiVersion, HttpClient httpClient) {
        super(credentials, apiVersion, httpClient);
    }

    /**
     * Returns a json string {@link String} with the list of Contracts.
     *
     * @param page   The page number. The response is capped to maximum of 50 results per page.
     * @param pageSize The number of Documents per page.
     * @param parameters Additional request parameters.
     * @return the url {@link String}
     */
    protected String getContracts(int page, int pageSize, JSONObject parameters) {
        if (parameters == null)
            parameters = new JSONObject();
        parameters.put("page", page);
        parameters.put("page_size", pageSize);
        return request(HttpMethod.GET, Endpoint.contracts.path, parameters);
    }

    /**
     * Returns a json string {@link CompletableFuture<String>} list of Contracts.
     *
     * @param page   The page number. The response is capped to maximum of 50 results per page.
     * @param pageSize The number of Documents per page.
     * @param parameters Additional request parameters.
     * @return the list of previously processed Contracts {@link String}
     */
    protected CompletableFuture<String> getContractsAsync(int page, int pageSize, JSONObject parameters) {
        if (parameters == null)
            parameters = new JSONObject();
        parameters.put("page", page);
        parameters.put("page_size", pageSize);
        return requestAsync(HttpMethod.GET, Endpoint.contracts.path, parameters);
    }

    /**
     * Returns a json string {@link String} with the Contract information.
     *
     * @param documentId ID of the Contract you'd like to retrieve.
     * @return the data extracted from the Contract {@link String}
     */
    protected String getContract(String documentId) {
        String endpointName = Endpoint.contracts.path + documentId + "/";
        JSONObject parameters = new JSONObject();
        parameters.put("id", documentId);
        return request(HttpMethod.GET, endpointName, parameters);
    }

    /**
     * Returns a json string {@link CompletableFuture<String>} with the Contract information.
     *
     * @param documentId ID of the Contract you'd like to retrieve.
     * @return the data extracted from the Contract {@link String}
     */
    protected CompletableFuture<String> getContractAsync(String documentId) {
        String endpointName = Endpoint.contracts.path + documentId + "/";
        JSONObject parameters = new JSONObject();
        parameters.put("id", documentId);
        return requestAsync(HttpMethod.GET, endpointName, parameters);
    }

    /**
     * Process a Contract and extract all the fields from it.
     *
     * @param filePath      Path on disk to a file to submit for data extraction.
     * @param parameters    Additional request parameters.
     * @return the data extracted from the Contract {@link String}
     */
    protected String processContract(String filePath, JSONObject parameters) {
        parameters = addFileToParameters(filePath, parameters);
        return request(HttpMethod.POST, Endpoint.contracts.path, parameters);
    }

    /**
     * Process a Contract and extract all the fields from it.
     *
     * @param filePath      Path on disk to a file to submit for data extraction.
     * @param parameters    Additional request parameters.
     * @return the data extracted from the Contract {@link CompletableFuture<String>}
     */
    protected CompletableFuture<String> processContractAsync(String filePath, JSONObject parameters) {
        parameters = addFileToParameters(filePath, parameters);
        return requestAsync(HttpMethod.POST, Endpoint.contracts.path, parameters);
    }

    /**
     * Process a Contract and extract all the fields from it.
     *
     * @param fileName      Name of the file to upload to the Veryfi API
     * @param fileData      Base64 encoded file data
     * @param parameters    Additional request parameters
     * @return the data extracted from the Contract {@link String}
     */
    protected String processContract(String fileName, String fileData, JSONObject parameters) {
        parameters = addFileToParameters(fileName, fileData, parameters);
        return request(HttpMethod.POST, Endpoint.contracts.path, parameters);
    }

    /**
     * Process a Contract and extract all the fields from it.
     *
     * @param fileName      Name of the file to upload to the Veryfi API
     * @param fileData      Base64 encoded file data
     * @param parameters    Additional request parameters
     * @return the data extracted from the Contract {@link CompletableFuture<String>}
     */
    protected CompletableFuture<String> processContractAsync(String fileName, String fileData, JSONObject parameters) {
        parameters = addFileToParameters(fileName, fileData, parameters);
        return requestAsync(HttpMethod.POST, Endpoint.contracts.path, parameters);
    }

    /**
     * Process Contract from url and extract all the fields from it.
     *
     * @param fileUrl       Required if file_urls isn't specified. Publicly accessible URL to a file, e.g. "https://cdn.example.com/receipt.jpg".
     * @param parameters    Additional request parameters
     * @return the data extracted from the Contract {@link String}
     */
    protected String processContractUrl(String fileUrl, JSONObject parameters) {
        parameters = addUrlToParameters(fileUrl, null, parameters);
        return request(HttpMethod.POST, Endpoint.contracts.path, parameters);
    }

    /**
     * Process Contract from url and extract all the fields from it.
     *
     * @param fileUrl       Required if file_urls isn't specified. Publicly accessible URL to a file, e.g. "https://cdn.example.com/receipt.jpg".
     * @param parameters    Additional request parameters
     * @return the data extracted from the Contract {@link CompletableFuture<String>}
     */
    protected CompletableFuture<String> processContractUrlAsync(String fileUrl, JSONObject parameters) {
        parameters = addUrlToParameters(fileUrl, null, parameters);
        return requestAsync(HttpMethod.POST, Endpoint.contracts.path, parameters);
    }

    /**
     * Delete a Contract from Veryfi.
     *
     * @param documentId ID of the Contract you'd like to delete.
     * @return the response data. {@link String}
     */
    protected String deleteContract(String documentId) {
        String endpointName = Endpoint.contracts.path + documentId + "/";
        JSONObject parameters = new JSONObject();
        parameters.put("id", documentId);
        return request(HttpMethod.DELETE, endpointName, parameters);
    }

    /**
     * Delete a Contract from Veryfi.
     *
     * @param documentId ID of the Contract you'd like to delete.
     * @return the response data. {@link CompletableFuture<String>}
     */
    protected CompletableFuture<String> deleteContractAsync(String documentId) {
        String endpointName = Endpoint.contracts.path + documentId + "/";
        JSONObject parameters = new JSONObject();
        parameters.put("id", documentId);
        return requestAsync(HttpMethod.DELETE, endpointName, parameters);
    }

}
