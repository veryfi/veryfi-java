import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import veryfi.ClientImpl;
import veryfi.VeryfiClientFactory;
import veryfi.models.AddLineItem;
import veryfi.models.NotValidModelException;
import veryfi.models.UpdateLineItem;

import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ClientTest {
    String clientId = "your_client_id";
    String clientSecret = "your_client_secret";
    String username = "your_username";
    String apiKey = "your_password";
    String documentUrl = "https://cdn.veryfi.com/receipts/fd36b2c0-a84d-459c-9d57-c29ac5d14685/21c95fc5-0e5c-48f8-abe0-849e438296bf.jpeg";
    ClientImpl client = (ClientImpl) VeryfiClientFactory.createClient(clientId, clientSecret, username, apiKey);
    boolean mockResponses = true; // Change to “false” if you want to test your personal credential

    @Test
    void getDocumentsTest() throws IOException, InterruptedException {
        client.setTimeOut(200);
        client.setBaseUrl("https://api.veryfi.com/api/");
        if (mockResponses) {
            HttpClient httpClient = mock(HttpClient.class);
            client.setHttpClient(httpClient);
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("getDocuments.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            when(httpClient.send(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(httpResponse);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        String jsonResponse = client.getDocuments();
        JSONObject documents = new JSONObject(jsonResponse);
        Assertions.assertEquals(documents.length(), 2);
    }

    @Test
    void getDocumentsTestWithException() throws IOException, InterruptedException {
        HttpClient httpClient = mock(HttpClient.class);
        client.setHttpClient(httpClient);
        when(httpClient.send(any(HttpRequest.class) , ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenThrow(new InterruptedException());

        String jsonResponse = client.getDocuments();
        Assertions.assertEquals("", jsonResponse);
    }

    @Test
    void getDocumentTest() throws IOException, InterruptedException {
        String documentId = "31727276"; // Change to your document Id
        if (mockResponses) {
            HttpClient httpClient = mock(HttpClient.class);
            client.setHttpClient(httpClient);
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("getDocument.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            when(httpClient.send(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(httpResponse);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        String jsonResponse = client.getDocument(documentId);
        JSONObject document = new JSONObject(jsonResponse);
        Assertions.assertEquals(document.getInt("id"), Integer.parseInt(documentId));
    }

    @Test
    void processDocumentTest() throws IOException, InterruptedException {
        List<String> categories = Arrays.asList("Advertising & Marketing", "Automotive");
        if (mockResponses) {
            HttpClient httpClient = mock(HttpClient.class);
            client.setHttpClient(httpClient);
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("processDocument.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            when(httpClient.send(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(httpResponse);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        String jsonResponse = client.processDocument("receipt.jpeg", categories, false, null);
        JSONObject document = new JSONObject(jsonResponse);
        Assertions.assertEquals("Walgreens", document.getJSONObject("vendor").getString("name"));
    }

    @Test
    void processDocumentTestWithParameters() throws IOException, InterruptedException {
        List<String> categories = Arrays.asList("Advertising & Marketing", "Automotive");
        JSONObject parameters = new JSONObject();
        parameters.put("p1", "p1value");
        if (mockResponses) {
            HttpClient httpClient = mock(HttpClient.class);
            client.setHttpClient(httpClient);
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("processDocument.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            when(httpClient.send(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(httpResponse);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        String jsonResponse = client.processDocument("receipt.jpeg", categories, false, parameters);
        JSONObject document = new JSONObject(jsonResponse);
        Assertions.assertEquals("Walgreens", document.getJSONObject("vendor").getString("name"));
    }

    @Test
    void processDocumentTestWithoutCategories() throws IOException, InterruptedException {
        if (mockResponses) {
            HttpClient httpClient = mock(HttpClient.class);
            client.setHttpClient(httpClient);
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("processDocument.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            when(httpClient.send(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(httpResponse);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        String jsonResponse = client.processDocument("receipt.jpeg", null, false, null);
        JSONObject document = new JSONObject(jsonResponse);
        Assertions.assertEquals("Walgreens", document.getJSONObject("vendor").getString("name"));
    }

    @Test
    void updateDocumentTest() throws IOException, InterruptedException {
        String documentId = "31727276"; // Change to your document Id
        JSONObject parameters = new JSONObject();
        String notes = "7sty9nmjcp";
        parameters.put("notes", notes);
        if (mockResponses) {
            HttpClient httpClient = mock(HttpClient.class);
            client.setHttpClient(httpClient);
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("updateDocument.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = (HttpResponse<String>) mock(HttpResponse.class);
            when(httpClient.send(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(httpResponse);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        String jsonResponseUpdated = client.updateDocument(documentId, parameters);
        JSONObject documentJson = new JSONObject(jsonResponseUpdated);
        Assertions.assertEquals(documentJson.getString("notes"), notes);
    }

    @Test
    void deleteDocumentTest() throws IOException, InterruptedException {
        String documentId = "37769185"; // Change to your document Id
        if (mockResponses) {
            HttpClient httpClient = mock(HttpClient.class);
            client.setHttpClient(httpClient);
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("deleteDocument.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            when(httpClient.send(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(httpResponse);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        String jsonResponse = client.deleteDocument(documentId);
        Assertions.assertFalse(jsonResponse.isEmpty());
    }

    @Test
    void processDocumentUrlTest() throws IOException, InterruptedException {
        if (mockResponses) {
            HttpClient httpClient = mock(HttpClient.class);
            client.setHttpClient(httpClient);
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("processDocument.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            when(httpClient.send(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(httpResponse);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        String jsonResponse = client.processDocumentUrl(documentUrl, null, null, false, 1, false, null, null);
        JSONObject document = new JSONObject(jsonResponse);
        Assertions.assertEquals("Walgreens", document.getJSONObject("vendor").getString("name"));
    }

    @Test
    void processDocumentUrlTestWithFileUrlsAndParameters() throws IOException, InterruptedException {
        if (mockResponses) {
            HttpClient httpClient = mock(HttpClient.class);
            client.setHttpClient(httpClient);
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("processDocument.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            when(httpClient.send(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(httpResponse);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        JSONObject parameters = new JSONObject();
        parameters.put("p1", "p1value");
        String jsonResponse = client.processDocumentUrl(documentUrl,
                Collections.singletonList(documentUrl), null, false, 1, false, null, parameters);
        JSONObject document = new JSONObject(jsonResponse);
        Assertions.assertEquals("Walgreens", document.getJSONObject("vendor").getString("name"));
    }

    @Test
    void getDocumentsAsyncTest() throws ExecutionException, InterruptedException, IOException {
        if (mockResponses) {
            HttpClient httpClient = mock(HttpClient.class);
            client.setHttpClient(httpClient);
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("getDocuments.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            CompletableFuture<HttpResponse<String>> jsonResponseFuture = CompletableFuture.completedFuture(httpResponse);
            when(httpClient.sendAsync(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(jsonResponseFuture);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        CompletableFuture<String> jsonResponseFuture = client.getDocumentsAsync();
        String jsonResponse  = jsonResponseFuture.get();
        JSONObject documents = new JSONObject(jsonResponse);
        Assertions.assertEquals(documents.length(), 2);
    }

    @Test
    void getDocumentAsyncTest() throws ExecutionException, InterruptedException, IOException {
        String documentId = "31727276"; // Change to your document Id
        if (mockResponses) {
            HttpClient httpClient = mock(HttpClient.class);
            client.setHttpClient(httpClient);
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("getDocument.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            CompletableFuture<HttpResponse<String>> jsonResponseFuture = CompletableFuture.completedFuture(httpResponse);
            when(httpClient.sendAsync(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(jsonResponseFuture);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        CompletableFuture<String> jsonResponseFuture = client.getDocumentAsync(documentId);
        String jsonResponse  = jsonResponseFuture.get();
        JSONObject document = new JSONObject(jsonResponse);
        Assertions.assertEquals(document.getInt("id"), Integer.parseInt(documentId));
    }

    @Test
    void processDocumentAsyncTest() throws ExecutionException, InterruptedException, IOException {
        List<String> categories = Arrays.asList("Advertising & Marketing", "Automotive");
        if (mockResponses) {
            HttpClient httpClient = mock(HttpClient.class);
            client.setHttpClient(httpClient);
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("processDocument.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            CompletableFuture<HttpResponse<String>> jsonResponseFuture = CompletableFuture.completedFuture(httpResponse);
            when(httpClient.sendAsync(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(jsonResponseFuture);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        CompletableFuture<String> jsonResponseFuture = client.processDocumentAsync("receipt.jpeg", categories, false, null);
        String jsonResponse  = jsonResponseFuture.get();
        JSONObject document = new JSONObject(jsonResponse);
        Assertions.assertEquals("Walgreens", document.getJSONObject("vendor").getString("name"));
    }

    @Test
    void updateDocumentAsyncTest() throws ExecutionException, InterruptedException, IOException {
        String documentId = "31727276"; // Change to your document Id
        JSONObject parameters = new JSONObject();
        String notes = "7sty9nmjcp";
        parameters.put("notes", notes);
        if (mockResponses) {
            HttpClient httpClient = mock(HttpClient.class);
            client.setHttpClient(httpClient);
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("updateDocument.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            CompletableFuture<HttpResponse<String>> jsonResponseFuture = CompletableFuture.completedFuture(httpResponse);
            when(httpClient.sendAsync(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(jsonResponseFuture);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        CompletableFuture<String> jsonResponseFuture = client.updateDocumentAsync(documentId, parameters);
        JSONObject documentJson = new JSONObject(jsonResponseFuture.get());
        Assertions.assertEquals(documentJson.getString("notes"), notes);
    }

    @Test
    void deleteDocumentAsyncTest() throws ExecutionException, InterruptedException, IOException {
        String documentId = "37769185"; // Change to your document Id
        if (mockResponses) {
            HttpClient httpClient = mock(HttpClient.class);
            client.setHttpClient(httpClient);
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("deleteDocument.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            CompletableFuture<HttpResponse<String>> jsonResponseFuture = CompletableFuture.completedFuture(httpResponse);
            when(httpClient.sendAsync(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(jsonResponseFuture);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        CompletableFuture<String> jsonResponseFuture = client.deleteDocumentAsync(documentId);
        String jsonResponse  = jsonResponseFuture.get();
        Assertions.assertFalse(jsonResponse.isEmpty());
    }

    @Test
    void processDocumentUrlAsyncTest() throws ExecutionException, InterruptedException, IOException {
        if (mockResponses) {
            HttpClient httpClient = mock(HttpClient.class);
            client.setHttpClient(httpClient);
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("processDocument.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            CompletableFuture<HttpResponse<String>> jsonResponseFuture = CompletableFuture.completedFuture(httpResponse);
            when(httpClient.sendAsync(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(jsonResponseFuture);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        CompletableFuture<String> jsonResponseFuture = client.processDocumentUrlAsync(documentUrl, null, null, false, 1, false, null, null);
        String jsonResponse  = jsonResponseFuture.get();
        JSONObject document = new JSONObject(jsonResponse);
        Assertions.assertEquals("Walgreens", document.getJSONObject("vendor").getString("name"));
    }

    @Test
    void getLineItemsTest() throws IOException, InterruptedException {
        String documentId = "44691518";
        if (mockResponses) {
            HttpClient httpClient = mock(HttpClient.class);
            client.setHttpClient(httpClient);
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("getLineItems.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            when(httpClient.send(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(httpResponse);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        String response = client.getLineItems(documentId);
        JSONObject jsonResponse = new JSONObject(response);
        Assertions.assertFalse(jsonResponse.getJSONArray("line_items").isEmpty());
    }

    @Test
    void getLineItemsAsyncTest() throws ExecutionException, InterruptedException, IOException {
        String documentId = "44691518";
        if (mockResponses) {
            HttpClient httpClient = mock(HttpClient.class);
            client.setHttpClient(httpClient);
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("getLineItems.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            CompletableFuture<HttpResponse<String>> jsonResponseFuture = CompletableFuture.completedFuture(httpResponse);
            when(httpClient.sendAsync(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(jsonResponseFuture);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        CompletableFuture<String> responseFuture = client.getLineItemsAsync(documentId);
        String response  = responseFuture.get();
        JSONObject jsonResponse = new JSONObject(response);
        Assertions.assertFalse(jsonResponse.getJSONArray("line_items").isEmpty());
    }

    @Test
    void getLineItemTest() throws IOException, InterruptedException {
        String documentId = "44691518";
        String lineItemId = "101170751";
        if (mockResponses) {
            HttpClient httpClient = mock(HttpClient.class);
            client.setHttpClient(httpClient);
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("getLineItem.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            when(httpClient.send(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(httpResponse);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        String response = client.getLineItem(documentId, lineItemId);
        JSONObject jsonResponse = new JSONObject(response);
        Assertions.assertFalse(jsonResponse.isEmpty());
        Assertions.assertEquals(lineItemId, String.valueOf(jsonResponse.getInt("id")));
    }

    @Test
    void getLineItemAsyncTest() throws IOException, InterruptedException, ExecutionException {
        String documentId = "44691518";
        String lineItemId = "101170751";
        if (mockResponses) {
            HttpClient httpClient = mock(HttpClient.class);
            client.setHttpClient(httpClient);
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("getLineItem.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            CompletableFuture<HttpResponse<String>> jsonResponseFuture = CompletableFuture.completedFuture(httpResponse);
            when(httpClient.sendAsync(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(jsonResponseFuture);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        CompletableFuture<String> responseFuture = client.getLineItemAsync(documentId, lineItemId);
        String response  = responseFuture.get();
        JSONObject jsonResponse = new JSONObject(response);
        Assertions.assertFalse(jsonResponse.isEmpty());
        Assertions.assertEquals(lineItemId, String.valueOf(jsonResponse.getInt("id")));
    }

    @Test
    void updateLineItemTest() throws IOException, InterruptedException, NotValidModelException {
        String documentId = "44691518";
        String lineItemId = "101170751";
        if (mockResponses) {
            HttpClient httpClient = mock(HttpClient.class);
            client.setHttpClient(httpClient);
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("updateLineItem.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            when(httpClient.send(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(httpResponse);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        UpdateLineItem updateLineItem = new UpdateLineItem();
        updateLineItem.description = "TEST";
        String response = client.updateLineItem(documentId, lineItemId, updateLineItem);
        JSONObject jsonResponse = new JSONObject(response);
        Assertions.assertFalse(jsonResponse.isEmpty());
        Assertions.assertEquals("TEST", jsonResponse.getString("description"));
    }

    @Test
    void updateLineItemAsyncTest() throws IOException, InterruptedException, NotValidModelException, ExecutionException {
        String documentId = "44691518";
        String lineItemId = "101170751";
        if (mockResponses) {
            HttpClient httpClient = mock(HttpClient.class);
            client.setHttpClient(httpClient);
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("updateLineItem.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            CompletableFuture<HttpResponse<String>> jsonResponseFuture = CompletableFuture.completedFuture(httpResponse);
            when(httpClient.sendAsync(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(jsonResponseFuture);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        UpdateLineItem updateLineItem = new UpdateLineItem();
        updateLineItem.description = "TEST";
        CompletableFuture<String> responseFuture = client.updateLineItemAsync(documentId, lineItemId, updateLineItem);
        String response = responseFuture.get();
        JSONObject jsonResponse = new JSONObject(response);
        Assertions.assertFalse(jsonResponse.isEmpty());
        Assertions.assertEquals("TEST", jsonResponse.getString("description"));
    }

    @Test
    void addLineItemTest() throws IOException, InterruptedException, NotValidModelException {
        String documentId = "44691518";
        if (mockResponses) {
            HttpClient httpClient = mock(HttpClient.class);
            client.setHttpClient(httpClient);
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("addLineItem.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            when(httpClient.send(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(httpResponse);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        AddLineItem addLineItem = new AddLineItem(20, "TEST", 20.1f);
        addLineItem.sku = "aqw";
        String response = client.addLineItem(documentId, addLineItem);
        JSONObject jsonResponse = new JSONObject(response);
        Assertions.assertFalse(jsonResponse.isEmpty());
        Assertions.assertEquals(addLineItem.order, jsonResponse.getInt("order"));
        Assertions.assertEquals(addLineItem.description, jsonResponse.getString("description"));
        Assertions.assertEquals(addLineItem.total, jsonResponse.getFloat("total"));
        Assertions.assertEquals(addLineItem.sku, jsonResponse.getString("sku"));
        if (!mockResponses) {
            String lineItemId = String.valueOf(jsonResponse.getInt("id"));
            response = client.deleteLineItem(documentId, lineItemId);
            JSONObject deleteResponse = new JSONObject(response);
            Assertions.assertEquals("ok", deleteResponse.getString("status"));
        }
    }

    @Test
    void addLineItemAsyncTest() throws IOException, InterruptedException, NotValidModelException, ExecutionException {
        String documentId = "44691518";
        if (mockResponses) {
            HttpClient httpClient = mock(HttpClient.class);
            client.setHttpClient(httpClient);
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("addLineItem.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            CompletableFuture<HttpResponse<String>> jsonResponseFuture = CompletableFuture.completedFuture(httpResponse);
            when(httpClient.sendAsync(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(jsonResponseFuture);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        AddLineItem addLineItem = new AddLineItem(20, "TEST", 20.1f);
        addLineItem.sku = "aqw";
        CompletableFuture<String> responseFuture = client.addLineItemAsync(documentId, addLineItem);
        String response = responseFuture.get();
        JSONObject jsonResponse = new JSONObject(response);
        Assertions.assertFalse(jsonResponse.isEmpty());
        Assertions.assertEquals(addLineItem.order, jsonResponse.getInt("order"));
        Assertions.assertEquals(addLineItem.description, jsonResponse.getString("description"));
        Assertions.assertEquals(addLineItem.total, jsonResponse.getFloat("total"));
        Assertions.assertEquals(addLineItem.sku, jsonResponse.getString("sku"));
        if (!mockResponses) {
            String lineItemId = String.valueOf(jsonResponse.getInt("id"));
            responseFuture = client.deleteLineItemAsync(documentId, lineItemId);
            response = responseFuture.get();
            JSONObject deleteResponse = new JSONObject(response);
            Assertions.assertEquals("ok", deleteResponse.getString("status"));
        }
    }

    @Test
    void deleteLineItemTest() throws IOException, InterruptedException {
        String documentId = "44691518";
        String lineItemId = "189951682";
        if (mockResponses) {
            HttpClient httpClient = mock(HttpClient.class);
            client.setHttpClient(httpClient);
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("deleteLineItem.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            when(httpClient.send(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(httpResponse);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
            String response = client.deleteLineItem(documentId, lineItemId);
            JSONObject jsonResponse = new JSONObject(response);
            Assertions.assertEquals("ok", jsonResponse.getString("status"));
        } else {
            Assertions.assertTrue(true); // Tested before.
        }
    }

    @Test
    void deleteLineItemAsyncTest() throws IOException, InterruptedException, ExecutionException {
        String documentId = "44691518";
        String lineItemId = "189951682";
        if (mockResponses) {
            HttpClient httpClient = mock(HttpClient.class);
            client.setHttpClient(httpClient);
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("deleteLineItem.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            CompletableFuture<HttpResponse<String>> jsonResponseFutureMock = CompletableFuture.completedFuture(httpResponse);
            when(httpClient.sendAsync(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(jsonResponseFutureMock);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
            CompletableFuture<String> jsonResponseFuture = client.deleteLineItemAsync(documentId, lineItemId);
            String jsonResponse = jsonResponseFuture.get();
            JSONObject deleteResponse = new JSONObject(jsonResponse);
            Assertions.assertEquals("ok", deleteResponse.getString("status"));
        } else {
            Assertions.assertTrue(true); // Tested before.
        }
    }

    @Test
    void deleteLineItemsTest() throws IOException, InterruptedException {
        String documentId = "51208553";
        if (mockResponses) {
            HttpClient httpClient = mock(HttpClient.class);
            client.setHttpClient(httpClient);
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("deleteLineItems.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            when(httpClient.send(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(httpResponse);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        String response = client.deleteLineItems(documentId);
        JSONObject jsonResponse = new JSONObject(response);
        Assertions.assertEquals("ok", jsonResponse.getString("status"));
    }

    @Test
    void deleteLineItemsAsyncTest() throws IOException, ExecutionException, InterruptedException {
        String documentId = "51208553";
        if (mockResponses) {
            HttpClient httpClient = mock(HttpClient.class);
            client.setHttpClient(httpClient);
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("deleteLineItems.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            CompletableFuture<HttpResponse<String>> jsonResponseFutureMock = CompletableFuture.completedFuture(httpResponse);
            when(httpClient.sendAsync(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(jsonResponseFutureMock);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        CompletableFuture<String> responseFuture = client.deleteLineItemsAsync(documentId);
        String response = responseFuture.get();
        JSONObject jsonResponse = new JSONObject(response);
        Assertions.assertEquals("ok", jsonResponse.getString("status"));
    }

    @Test
    void testBadCredentials() throws IOException {
        String clientId = "bad_client_id";
        String clientSecret = "bad_client_secret";
        String username = "bad_username";
        String apiKey = "bad_password";
        int apiVersion = 7;
        ClientImpl client = (ClientImpl) VeryfiClientFactory.createClient(clientId, clientSecret, username, apiKey, apiVersion);
        String jsonResponse = client.getDocuments();
        InputStream fileStream = ClassLoader.getSystemResourceAsStream("badCredentials.json");
        assert fileStream != null;
        String result = new String(fileStream.readAllBytes());
        Assertions.assertEquals(result, jsonResponse);
    }

    @Test
    void testAddLineItemThrow() {
        AddLineItem addLineItem = new AddLineItem(3, "hola", 33.3f);
        addLineItem.total = null;
        Assertions.assertThrows(NotValidModelException.class, addLineItem::toJsonObject);
    }

    @Test
    void testUpdateLineItemThrow() {
        UpdateLineItem updateLineItem = new UpdateLineItem();
        Assertions.assertThrows(NotValidModelException.class, updateLineItem::toJsonObject);
    }
}
