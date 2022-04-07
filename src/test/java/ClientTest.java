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
    String documentUrl = "https://veryfi-testing-public.s3.us-west-2.amazonaws.com/receipt.jpg";
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
        Assertions.assertEquals("In-n-out Burger", document.getJSONObject("vendor").getString("name"));
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
        Assertions.assertEquals("In-n-out Burger", document.getJSONObject("vendor").getString("name"));
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
        Assertions.assertEquals("In-n-out Burger", document.getJSONObject("vendor").getString("name"));
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
        Assertions.assertEquals("In-n-out Burger", document.getJSONObject("vendor").getString("name"));
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
        Assertions.assertEquals("In-n-out Burger", document.getJSONObject("vendor").getString("name"));
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
        Assertions.assertEquals("In-n-out Burger", document.getJSONObject("vendor").getString("name"));
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
        Assertions.assertEquals("In-n-out Burger", document.getJSONObject("vendor").getString("name"));
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
}
