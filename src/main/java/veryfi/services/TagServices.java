package veryfi.services;

import org.json.JSONArray;
import org.json.JSONObject;
import veryfi.Credentials;
import veryfi.NetworkClient;
import veryfi.enums.Endpoint;
import veryfi.enums.HttpMethod;

import java.net.http.HttpClient;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * API services for tags
 */
class TagServices extends NetworkClient {

    /**
     * Creates an instance of {@link TagServices}.
     *
     * @param credentials the {@link Credentials} provided by Veryfi.
     * @param apiVersion  the {@link int} api version to use Veryfi.
     */
    protected TagServices(Credentials credentials, int apiVersion) {
        super(credentials, apiVersion);
    }

    /**
     * Creates an instance of {@link TagServices}.
     *
     * @param credentials the {@link Credentials} provided by Veryfi.
     * @param apiVersion  the {@link int} api version to use Veryfi.
     * @param httpClient  {@link HttpClient} for the Veryfi API
     */
    protected TagServices(Credentials credentials, int apiVersion, HttpClient httpClient) {
        super(credentials, apiVersion, httpClient);
    }

    /**
     * Replace multiple tags on an existing document. https://docs.veryfi.com/api/receipts-invoices/add-tags-to-a-document/
     * @param documentId  ID of the document you'd like to update.
     * @param tags  tags array of tags to be added.
     * @return the response data. {@link CompletableFuture}{@code <String>}
     */
        protected String replaceTags(String documentId, List<String> tags) {
        String endpointName = Endpoint.documents.path + documentId + "/";
        JSONObject requestArguments = new JSONObject();
        JSONArray jsonArrayTags = new JSONArray(tags);
        requestArguments.put("tags", jsonArrayTags);
        return request(HttpMethod.PUT, endpointName, requestArguments);
    }

    /**
     * Replace multiple tags on an existing document. https://docs.veryfi.com/api/receipts-invoices/add-tags-to-a-document/
     * @param documentId  ID of the document you'd like to update.
     * @param tags  tags array of tags to be added.
     * @return the response data. {@link CompletableFuture}{@code <String>}
     */
        protected CompletableFuture<String> replaceTagsAsync(String documentId, List<String> tags) {
        String endpointName = Endpoint.documents.path + documentId + "/";
        JSONObject requestArguments = new JSONObject();
        JSONArray jsonArrayTags = new JSONArray(tags);
        requestArguments.put("tags", jsonArrayTags);
        return requestAsync(HttpMethod.PUT, endpointName, requestArguments);
    }

    /**
     * Add multiple tags on an existing document. https://docs.veryfi.com/api/receipts-invoices/add-tags-to-a-document/
     * @param documentId  ID of the document you'd like to update.
     * @param tags  tags array of tags to be added.
     * @return the response data. {@link CompletableFuture}{@code <String>}
     */
        protected String addTags(String documentId, List<String> tags) {
        String endpointName = Endpoint.documents.path + documentId + "/tags/";
        JSONObject requestArguments = new JSONObject();
        JSONArray jsonArrayTags = new JSONArray(tags);
        requestArguments.put("tags", jsonArrayTags);
        return request(HttpMethod.POST, endpointName, requestArguments);
    }

    /**
     * Add multiple tags on an existing document. https://docs.veryfi.com/api/receipts-invoices/add-tags-to-a-document/
     * @param documentId  ID of the document you'd like to update.
     * @param tags  tags array of tags to be added.
     * @return the response data. {@link CompletableFuture}{@code <String>}
     */
        protected CompletableFuture<String> addTagsAsync(String documentId, List<String> tags) {
        String endpointName = Endpoint.documents.path + documentId + "/tags/";
        JSONObject requestArguments = new JSONObject();
        JSONArray jsonArrayTags = new JSONArray(tags);
        requestArguments.put("tags", jsonArrayTags);
        return requestAsync(HttpMethod.POST, endpointName, requestArguments);
    }



}
