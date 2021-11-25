import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import veryfi.ClientImpl;
import veryfi.VeryfiClientFactory;

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
    ClientImpl client = (ClientImpl) VeryfiClientFactory.createClient(clientId, clientSecret, username, apiKey);
    boolean mockResponses = true; // Change to “false” if you want to test your personal credential

    @Test
    void getDocumentsTest() throws IOException, InterruptedException {
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
        JSONArray documents = new JSONArray(jsonResponse);
        Assertions.assertTrue(documents.length() > 0);
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
        String jsonResponse = client.processDocument("receipt.png", categories, false, null);
        JSONObject document = new JSONObject(jsonResponse);
        Assertions.assertEquals("The Home Depot", document.getJSONObject("vendor").getString("name"));
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
        String jsonResponse = client.processDocument("receipt.png", categories, false, parameters);
        JSONObject document = new JSONObject(jsonResponse);
        Assertions.assertEquals("The Home Depot", document.getJSONObject("vendor").getString("name"));
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
        String jsonResponse = client.processDocument("receipt.png", null, false, null);
        JSONObject document = new JSONObject(jsonResponse);
        Assertions.assertEquals("The Home Depot", document.getJSONObject("vendor").getString("name"));
    }

    @Test
    void updateDocumentTest() throws IOException, InterruptedException {
        String documentId = "31727276"; // Change to your document Id
        JSONObject parameters = new JSONObject();
        String notes = "Note updated";
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
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("processDocumentUrl.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            when(httpClient.send(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(httpResponse);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        String jsonResponse = client.processDocumentUrl("https://cdn.veryfi.com/receipts/92233902-c94a-491d-a4f9-0d61f9407cd2.pdf", null, null, false, 1, false, null, null);
        JSONObject document = new JSONObject(jsonResponse);
        Assertions.assertEquals("Rumpke Waste & Recycling", document.getJSONObject("vendor").getString("name"));
    }

    @Test
    void processDocumentUrlTestWithFileUrlsAndParameters() throws IOException, InterruptedException {
        if (mockResponses) {
            HttpClient httpClient = mock(HttpClient.class);
            client.setHttpClient(httpClient);
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("processDocumentUrl.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            when(httpClient.send(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(httpResponse);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        JSONObject parameters = new JSONObject();
        parameters.put("p1", "p1value");
        String jsonResponse = client.processDocumentUrl("https://cdn.veryfi.com/receipts/92233902-c94a-491d-a4f9-0d61f9407cd2.pdf",
                Collections.singletonList("https://cdn.veryfi.com/receipts/92233902-c94a-491d-a4f9-0d61f9407cd2.pdf"), null, false, 1, false, null, parameters);
        JSONObject document = new JSONObject(jsonResponse);
        Assertions.assertEquals("Rumpke Waste & Recycling", document.getJSONObject("vendor").getString("name"));
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
        JSONArray documents = new JSONArray(jsonResponse);
        Assertions.assertTrue(documents.length() > 0);
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
        CompletableFuture<String> jsonResponseFuture = client.processDocumentAsync("receipt.png", categories, false, null);
        String jsonResponse  = jsonResponseFuture.get();
        JSONObject document = new JSONObject(jsonResponse);
        Assertions.assertEquals("The Home Depot", document.getJSONObject("vendor").getString("name"));
    }

    @Test
    void updateDocumentAsyncTest() throws ExecutionException, InterruptedException, IOException {
        String documentId = "31727276"; // Change to your document Id
        JSONObject parameters = new JSONObject();
        String notes = "Note updated";
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
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("processDocumentUrl.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            CompletableFuture<HttpResponse<String>> jsonResponseFuture = CompletableFuture.completedFuture(httpResponse);
            when(httpClient.sendAsync(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(jsonResponseFuture);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        CompletableFuture<String> jsonResponseFuture = client.processDocumentUrlAsync("https://cdn.veryfi.com/receipts/92233902-c94a-491d-a4f9-0d61f9407cd2.pdf", null, null, false, 1, false, null, null);
        String jsonResponse  = jsonResponseFuture.get();
        JSONObject document = new JSONObject(jsonResponse);
        Assertions.assertEquals("Rumpke Waste & Recycling", document.getJSONObject("vendor").getString("name"));
    }
}
