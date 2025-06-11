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
 * API services for Business Cards
 */
class BusinessCardsServices extends NetworkClient {

    /**
     * Creates an instance of {@link BusinessCardsServices}.
     *
     * @param credentials  the {@link Credentials} provided by Veryfi.
     * @param apiVersion   the {@link int} api version to use Veryfi.
     */
    protected BusinessCardsServices(Credentials credentials, int apiVersion) {
        super(credentials, apiVersion);
    }

    /**
     * Creates an instance of {@link BusinessCardsServices}.
     *
     * @param credentials  the {@link Credentials} provided by Veryfi.
     * @param apiVersion   the {@link int} api version to use Veryfi.
     * @param httpClient   {@link HttpClient} for the Veryfi API
     */
    protected BusinessCardsServices(Credentials credentials, int apiVersion, HttpClient httpClient) {
        super(credentials, apiVersion, httpClient);
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
    protected String getBusinessCards(int page, int pageSize, boolean boundingBoxes, boolean confidenceDetails, JSONObject parameters) {
        if (parameters == null)
            parameters = new JSONObject();
        parameters.put("page", page);
        parameters.put("page_size", pageSize);
        parameters.put("bounding_boxes", boundingBoxes);
        parameters.put("confidence_details", confidenceDetails);
        return request(HttpMethod.GET, Endpoint.businessCards.path, parameters);
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
    protected CompletableFuture<String> getBusinessCardsAsync(int page, int pageSize, boolean boundingBoxes, boolean confidenceDetails, JSONObject parameters) {
        if (parameters == null)
            parameters = new JSONObject();
        parameters.put("page", page);
        parameters.put("page_size", pageSize);
        parameters.put("bounding_boxes", boundingBoxes);
        parameters.put("confidence_details", confidenceDetails);
        return requestAsync(HttpMethod.GET, Endpoint.businessCards.path, parameters);
    }

    /**
     * Returns a json string {@link String} Business Card information. https://docs.veryfi.com/api/business-cards/get-a-business-card/
     *
     * @param documentId ID of the Business Card you'd like to retrieve.
     * @return the data extracted from the Business Card {@link String}
     */
    protected String getBusinessCard(String documentId) {
        String endpointName = Endpoint.businessCards.path + documentId + "/";
        JSONObject parameters = new JSONObject();
        parameters.put("id", documentId);
        return request(HttpMethod.GET, endpointName, parameters);
    }

    /**
     * Returns a json string {@link CompletableFuture<String>} Business Card information. https://docs.veryfi.com/api/business-cards/get-a-business-card/
     *
     * @param documentId ID of the Business Card you'd like to retrieve.
     * @return the data extracted from the Business Card {@link String}
     */
    protected CompletableFuture<String> getBusinessCardAsync(String documentId) {
        String endpointName = Endpoint.businessCards.path + documentId + "/";
        JSONObject parameters = new JSONObject();
        parameters.put("id", documentId);
        return requestAsync(HttpMethod.GET, endpointName, parameters);
    }

    /**
     * Process a Business Card and extract all the fields from it. https://docs.veryfi.com/api/business-cards/process-a-business-card/
     *
     * @param filePath      Path on disk to a file to submit for data extraction.
     * @param parameters    Additional request parameters.
     * @return the data extracted from the Business Card {@link String}
     */
    protected String processBusinessCard(String filePath, JSONObject parameters) {
        parameters = addFileToParameters(filePath, parameters);
        return request(HttpMethod.POST, Endpoint.businessCards.path, parameters);
    }

    /**
     * Process a Business Card and extract all the fields from it. https://docs.veryfi.com/api/business-cards/process-a-business-card/
     *
     * @param filePath      Path on disk to a file to submit for data extraction.
     * @param parameters    Additional request parameters.
     * @return the data extracted from the Business Card {@link CompletableFuture<String>}
     */
    protected CompletableFuture<String> processBusinessCardAsync(String filePath, JSONObject parameters) {
        parameters = addFileToParameters(filePath, parameters);
        return requestAsync(HttpMethod.POST, Endpoint.businessCards.path, parameters);
    }

    /**
     * Process a Business Card and extract all the fields from it. https://docs.veryfi.com/api/business-cards/process-a-business-card/
     *
     * @param fileName      Name of the file to upload to the Veryfi API
     * @param fileData      Base64 encoded file data
     * @param parameters    Additional request parameters.
     * @return the data extracted from the Business Card {@link String}
     */
    protected String processBusinessCard(String fileName, String fileData, JSONObject parameters) {
        parameters = addFileToParameters(fileName, fileData, parameters);
        return request(HttpMethod.POST, Endpoint.businessCards.path, parameters);
    }

    /**
     * Process a Business Card and extract all the fields from it. https://docs.veryfi.com/api/business-cards/process-a-business-card/
     *
     * @param fileName      Name of the file to upload to the Veryfi API
     * @param fileData      Base64 encoded file data
     * @param parameters    Additional request parameters.
     * @return the data extracted from the Business Card {@link CompletableFuture<String>}
     */
    protected CompletableFuture<String> processBusinessCardAsync(String fileName, String fileData, JSONObject parameters) {
        parameters = addFileToParameters(fileName, fileData, parameters);
        return requestAsync(HttpMethod.POST, Endpoint.businessCards.path, parameters);
    }

    /**
     * Process Business Card from url and extract all the fields from it. https://docs.veryfi.com/api/business-cards/process-a-business-card/
     *
     * @param fileUrl       Required if file_urls isn't specified. Publicly accessible URL to a file, e.g. "https://cdn.example.com/receipt.jpg".
     * @param fileUrls      Required if file_url isn't specifies. List of publicly accessible URLs to multiple files, e.g. ["https://cdn.example.com/receipt1.jpg", "https://cdn.example.com/receipt2.jpg"]
     * @param parameters    Additional request parameters
     * @return the data extracted from the Business Card {@link String}
     */
    protected String processBusinessCardUrl(String fileUrl, List<String> fileUrls, JSONObject parameters) {
        parameters = addUrlToParameters(fileUrl, fileUrls, parameters);
        return request(HttpMethod.POST, Endpoint.businessCards.path, parameters);
    }

    /**
     * Process Business Card from url and extract all the fields from it. https://docs.veryfi.com/api/business-cards/process-a-business-card/
     *
     * @param fileUrl       Required if file_urls isn't specified. Publicly accessible URL to a file, e.g. "https://cdn.example.com/receipt.jpg".
     * @param fileUrls      Required if file_url isn't specifies. List of publicly accessible URLs to multiple files, e.g. ["https://cdn.example.com/receipt1.jpg", "https://cdn.example.com/receipt2.jpg"]
     * @param parameters    Additional request parameters
     * @return the data extracted from the Business Card {@link CompletableFuture<String>}
     */
    protected CompletableFuture<String> processBusinessCardUrlAsync(String fileUrl, List<String> fileUrls, JSONObject parameters) {
        parameters = addUrlToParameters(fileUrl, fileUrls, parameters);
        return requestAsync(HttpMethod.POST, Endpoint.businessCards.path, parameters);
    }

    /**
     * Delete Business Card from Veryfi. https://docs.veryfi.com/api/business-cards/delete-a-business-card/
     *
     * @param documentId ID of the Business Card you'd like to delete.
     * @return the response data. {@link String}
     */
    protected String deleteBusinessCard(String documentId) {
        String endpointName = Endpoint.businessCards.path + documentId + "/";
        JSONObject parameters = new JSONObject();
        parameters.put("id", documentId);
        return request(HttpMethod.DELETE, endpointName, parameters);
    }

    /**
     * Delete Business Card from Veryfi. https://docs.veryfi.com/api/business-cards/delete-a-business-card/
     *
     * @param documentId ID of the Business Card you'd like to delete.
     * @return the response data. {@link CompletableFuture<String>}
     */
    protected CompletableFuture<String> deleteBusinessCardAsync(String documentId) {
        String endpointName = Endpoint.businessCards.path + documentId + "/";
        JSONObject parameters = new JSONObject();
        parameters.put("id", documentId);
        return requestAsync(HttpMethod.DELETE, endpointName, parameters);
    }

}
