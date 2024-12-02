package veryfi.services;

import org.json.JSONObject;
import veryfi.Credentials;
import veryfi.NetworkClient;
import veryfi.enums.Endpoint;
import veryfi.enums.HttpMethod;
import veryfi.models.AddLineItem;
import veryfi.models.NotValidModelException;
import veryfi.models.UpdateLineItem;

import java.net.http.HttpClient;
import java.util.concurrent.CompletableFuture;

/**
 * API services for line items
 */
class LineItemServices extends NetworkClient {

    /**
     * Creates an instance of {@link LineItemServices}.
     *
     * @param credentials the {@link Credentials} provided by Veryfi.
     * @param apiVersion  the {@link int} api version to use Veryfi.
     */
    protected LineItemServices(Credentials credentials, int apiVersion) {
        super(credentials, apiVersion);
    }

    /**
     * Creates an instance of {@link LineItemServices}.
     *
     * @param credentials  the {@link Credentials} provided by Veryfi.
     * @param apiVersion   the {@link int} api version to use Veryfi.
     * @param httpClient   {@link HttpClient} for the Veryfi API
     */
    protected LineItemServices(Credentials credentials, int apiVersion, HttpClient httpClient) {
        super(credentials, apiVersion, httpClient);
    }

    /**
     * Retrieve all line items for a document.
     *
     * @param documentId ID of the document you'd like to retrieve.
     * @return List of line items extracted from the document. {@link String}
     */
    protected String getLineItems(String documentId) {
        String endpointName = Endpoint.documents.path + documentId + "/line-items/";
        JSONObject requestArguments = new JSONObject();
        return request(HttpMethod.GET, endpointName, requestArguments);
    }

    /**
     * Retrieve all line items for a document.
     *
     * @param documentId ID of the document you'd like to retrieve.
     * @return List of line items extracted from the document. {@link CompletableFuture <String>}
     */
    protected CompletableFuture<String> getLineItemsAsync(String documentId) {
        String endpointName = Endpoint.documents.path + documentId + "/line-items/";
        JSONObject requestArguments = new JSONObject();
        return requestAsync(HttpMethod.GET, endpointName, requestArguments);
    }

    /**
     * Retrieve a line item for existing document by ID.
     *
     * @param documentId ID of the document you'd like to retrieve.
     * @param lineItemId ID of the line item you'd like to retrieve.
     * @return Line item extracted from the document. {@link String}
     */
    protected String getLineItem(String documentId, String lineItemId) {
        String endpointName = Endpoint.documents.path + documentId + "/line-items/" + lineItemId;
        JSONObject requestArguments = new JSONObject();
        return request(HttpMethod.GET, endpointName, requestArguments);
    }

    /**
     * Retrieve a line item for existing document by ID.
     *
     * @param documentId ID of the document you'd like to retrieve.
     * @param lineItemId ID of the line item you'd like to retrieve.
     * @return Line item extracted from the document. {@link CompletableFuture<String>}
     */
    protected CompletableFuture<String> getLineItemAsync(String documentId, String lineItemId) {
        String endpointName = Endpoint.documents.path + documentId + "/line-items/" + lineItemId;
        JSONObject requestArguments = new JSONObject();
        return requestAsync(HttpMethod.GET, endpointName, requestArguments);
    }

    /**
     * Add a new line item on an existing document.
     *
     * @param documentId ID of the document you'd like to update.
     * @param payload    line item object to add.
     * @return Added line item data. {@link String}
     * @throws NotValidModelException when the model is not valid it throws this exception.
     */
    protected String addLineItem(String documentId, AddLineItem payload) throws NotValidModelException {
        String endpointName = Endpoint.documents.path + documentId + "/line-items/";
        JSONObject requestArguments = payload.toJsonObject();
        return request(HttpMethod.POST, endpointName, requestArguments);
    }

    /**
     * Add a new line item on an existing document.
     *
     * @param documentId ID of the document you'd like to update.
     * @param payload    line item object to add.
     * @return Added line item data. {@link CompletableFuture<String>}
     * @throws NotValidModelException when the model is not valid it throws this exception.
     */
    protected CompletableFuture<String> addLineItemAsync(String documentId, AddLineItem payload) throws NotValidModelException {
        String endpointName = Endpoint.documents.path + documentId + "/line-items/";
        JSONObject requestArguments = payload.toJsonObject();
        return requestAsync(HttpMethod.POST, endpointName, requestArguments);
    }

    /**
     * Update an existing line item on an existing document.
     *
     * @param documentId ID of the document you'd like to update.
     * @param lineItemId ID of the line item you'd like to update.
     * @param payload    line item object to update.
     * @return Line item data with updated fields, if fields are writable. Otherwise line item data with unchanged fields. {@link String}
     * @throws NotValidModelException when the model is not valid it throws this exception.
     */
    protected String updateLineItem(String documentId, String lineItemId, UpdateLineItem payload) throws NotValidModelException {
        String endpointName = Endpoint.documents.path + documentId + "/line-items/" + lineItemId;
        JSONObject requestArguments = payload.toJsonObject();
        return request(HttpMethod.PUT, endpointName, requestArguments);
    }

    /**
     * Update an existing line item on an existing document.
     *
     * @param documentId ID of the document you'd like to update.
     * @param lineItemId ID of the line item you'd like to update.
     * @param payload    line item object to update.
     * @return Line item data with updated fields, if fields are writable. Otherwise line item data with unchanged fields. {@link CompletableFuture<String>}
     * @throws NotValidModelException when the model is not valid it throws this exception.
     */
    protected CompletableFuture<String> updateLineItemAsync(String documentId, String lineItemId, UpdateLineItem payload) throws NotValidModelException {
        String endpointName = Endpoint.documents.path + documentId + "/line-items/" + lineItemId;
        JSONObject requestArguments;
        requestArguments = payload.toJsonObject();
        return requestAsync(HttpMethod.PUT, endpointName, requestArguments);
    }

    /**
     * Delete all line items on an existing document.
     *
     * @param documentId ID of the document you'd like to delete.
     * @return the response data. {@link String}
     */
    protected String deleteLineItems(String documentId) {
        String endpointName = Endpoint.documents.path + documentId + "/line-items/";
        JSONObject requestArguments = new JSONObject();
        return request(HttpMethod.DELETE, endpointName, requestArguments);
    }

    /**
     * Delete all line items on an existing document.
     *
     * @param documentId ID of the document you'd like to delete.
     * @return @return the response data. {@link CompletableFuture<String>}
     */
    protected CompletableFuture<String> deleteLineItemsAsync(String documentId) {
        String endpointName = Endpoint.documents.path + documentId + "/line-items/";
        JSONObject requestArguments = new JSONObject();
        return requestAsync(HttpMethod.DELETE, endpointName, requestArguments);
    }

    /**
     * Delete an existing line item on an existing document.
     *
     * @param documentId ID of the document you'd like to delete.
     * @param lineItemId ID of the line item you'd like to delete.
     * @return the response data. {@link String}
     */
    protected String deleteLineItem(String documentId, String lineItemId) {
        String endpointName = Endpoint.documents.path + documentId + "/line-items/" + lineItemId;
        JSONObject requestArguments = new JSONObject();
        return request(HttpMethod.DELETE, endpointName, requestArguments);
    }

    /**
     * Delete an existing line item on an existing document.
     *
     * @param documentId ID of the document you'd like to delete.
     * @param lineItemId ID of the line item you'd like to delete.
     * @return the response data. {@link CompletableFuture<String>}
     */
    protected CompletableFuture<String> deleteLineItemAsync(String documentId, String lineItemId) {
        String endpointName = Endpoint.documents.path + documentId + "/line-items/" + lineItemId;
        JSONObject requestArguments = new JSONObject();
        return requestAsync(HttpMethod.DELETE, endpointName, requestArguments);
    }


}
