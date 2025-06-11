import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import veryfi.VeryfiClientFactory;
import veryfi.services.ClientImpl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DocumentTests {
    String clientId = "your_client_id";
    String clientSecret = "your_client_secret";
    String username = "your_username";
    String apiKey = "your_password";
    int apiVersion = 8;
    String documentUrl = "https://cdn.veryfi.com/receipts/fd36b2c0-a84d-459c-9d57-c29ac5d14685/21c95fc5-0e5c-48f8-abe0-849e438296bf.jpeg";
    HttpClient httpClient;
    ClientImpl client;
    boolean mockResponses = true; // Change to “false” if you want to test your personal credential

    @BeforeEach
    void setup() {
        httpClient = mockResponses ? mock(HttpClient.class) : HttpClient.newHttpClient();
        client = (ClientImpl) VeryfiClientFactory.createClient(clientId, clientSecret, username, apiKey, apiVersion, httpClient);
    }


    @Test
    void getDocumentsTest() throws IOException, InterruptedException {
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("documents/getDocuments.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            when(httpClient.send(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(httpResponse);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        String jsonResponse = client.getDocuments(1, 50, false, false, null);
        JSONObject documents = new JSONObject(jsonResponse);
        Assertions.assertEquals(documents.length(), 2);
    }

    @Test
    void getDocumentsTestWithException() throws IOException, InterruptedException {
        when(httpClient.send(any(HttpRequest.class) , ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenThrow(new InterruptedException());

        String jsonResponse = client.getDocuments(1, 50, false, false, null);
        Assertions.assertEquals("", jsonResponse);
    }

    @Test
    void getDocumentTest() throws IOException, InterruptedException {
        String documentId = "125344108"; // Change to your document Id
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("documents/getDocument.json");
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
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("documents/processDocument.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            when(httpClient.send(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(httpResponse);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        String jsonResponse = client.processDocument(getFileName(), getFileData(), categories, false, null);
        JSONObject document = new JSONObject(jsonResponse);
        Assertions.assertEquals("Walgreens", document.getJSONObject("vendor").getString("name"));
    }

    @Test
    void processDocumentTestWithParameters() throws IOException, InterruptedException {
        List<String> categories = Arrays.asList("Advertising & Marketing", "Automotive");
        JSONObject parameters = new JSONObject();
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("documents/processDocument.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            when(httpClient.send(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(httpResponse);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        String jsonResponse = client.processDocument(getFilePath(), categories, false, parameters);
        JSONObject document = new JSONObject(jsonResponse);
        Assertions.assertEquals("Walgreens", document.getJSONObject("vendor").getString("name"));
    }

    @Test
    void processBase64DocumentTest() throws IOException, InterruptedException {
        List<String> categories = Arrays.asList("Advertising & Marketing", "Automotive");
        JSONObject parameters = new JSONObject();
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("documents/processDocument.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            when(httpClient.send(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(httpResponse);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        String jsonResponse = client.processDocument(getFilePath(), categories, false, parameters);
        JSONObject document = new JSONObject(jsonResponse);
        Assertions.assertEquals("Walgreens", document.getJSONObject("vendor").getString("name"));
    }

    @Test
    void processDocumentTestWithoutCategories() throws IOException, InterruptedException {
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("documents/processDocument.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            when(httpClient.send(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(httpResponse);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        String jsonResponse = client.processDocument(getFilePath(), null, false, null);
        JSONObject document = new JSONObject(jsonResponse);
        Assertions.assertEquals("Walgreens", document.getJSONObject("vendor").getString("name"));
    }

    @Test
    void updateDocumentTest() throws IOException, InterruptedException {
        String documentId = "125344108"; // Change to your document Id
        JSONObject parameters = new JSONObject();
        String notes = "7sty9nmjcp";
        parameters.put("notes", notes);
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("documents/updateDocument.json");
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
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("documents/deleteDocument.json");
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
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("documents/processDocument.json");
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
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("documents/processDocument.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            when(httpClient.send(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(httpResponse);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        JSONObject parameters = new JSONObject();
        String jsonResponse = client.processDocumentUrl("",
                Collections.singletonList(documentUrl), null, false, 1, false, null, parameters);
        JSONObject document = new JSONObject(jsonResponse);
        Assertions.assertEquals("Walgreens", document.getJSONObject("vendor").getString("name"));
    }

    @Test
    void getDocumentsAsyncTest() throws ExecutionException, InterruptedException, IOException {
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("documents/getDocuments.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            CompletableFuture<HttpResponse<String>> jsonResponseFuture = CompletableFuture.completedFuture(httpResponse);
            when(httpClient.sendAsync(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(jsonResponseFuture);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        CompletableFuture<String> jsonResponseFuture = client.getDocumentsAsync(1, 50, false, false, null);
        String jsonResponse  = jsonResponseFuture.get();
        JSONObject documents = new JSONObject(jsonResponse);
        Assertions.assertEquals(documents.length(), 2);
    }

    @Test
    void getDocumentAsyncTest() throws ExecutionException, InterruptedException, IOException {
        String documentId = "125344108"; // Change to your document Id
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("documents/getDocument.json");
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
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("documents/processDocument.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            CompletableFuture<HttpResponse<String>> jsonResponseFuture = CompletableFuture.completedFuture(httpResponse);
            when(httpClient.sendAsync(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(jsonResponseFuture);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        CompletableFuture<String> jsonResponseFuture = client.processDocumentAsync(getFilePath(), categories, false, null);
        String jsonResponse  = jsonResponseFuture.get();
        JSONObject document = new JSONObject(jsonResponse);
        Assertions.assertEquals("Walgreens", document.getJSONObject("vendor").getString("name"));
    }

    @Test
    void processBase64DocumentAsyncTest() throws ExecutionException, InterruptedException, IOException {
        List<String> categories = Arrays.asList("Advertising & Marketing", "Automotive");
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("documents/processDocument.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            CompletableFuture<HttpResponse<String>> jsonResponseFuture = CompletableFuture.completedFuture(httpResponse);
            when(httpClient.sendAsync(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(jsonResponseFuture);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        CompletableFuture<String> jsonResponseFuture = client.processDocumentAsync(getFileName(), getFileData(), categories, false, null);
        String jsonResponse  = jsonResponseFuture.get();
        JSONObject document = new JSONObject(jsonResponse);
        Assertions.assertEquals("Walgreens", document.getJSONObject("vendor").getString("name"));
    }

    @Test
    void updateDocumentAsyncTest() throws ExecutionException, InterruptedException, IOException {
        String documentId = "125344108"; // Change to your document Id
        JSONObject parameters = new JSONObject();
        String notes = "7sty9nmjcp";
        parameters.put("notes", notes);
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("documents/updateDocument.json");
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
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("documents/deleteDocument.json");
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
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("documents/processDocument.json");
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
    void testBadCredentials() throws IOException {
        String clientId = "bad_client_id";
        String clientSecret = "bad_client_secret";
        String username = "bad_username";
        String apiKey = "bad_password";
        int apiVersion = 7;
        ClientImpl client = (ClientImpl) VeryfiClientFactory.createClient(clientId, clientSecret, username, apiKey, apiVersion);
        String jsonResponse = client.getDocuments(1, 50, false, false, null);
        InputStream fileStream = ClassLoader.getSystemResourceAsStream("documents/badCredentials.json");
        assert fileStream != null;
        String result = new String(fileStream.readAllBytes());
        Assertions.assertEquals(result, jsonResponse);
    }

    private String getFilePath() {
        return ClassLoader.getSystemResource("documents/receipt.jpeg").getPath();
    }

    private String getFileName() {
        return getFilePath().replaceAll("^.*[/\\\\]", "");
    }

    private String getFileData() {
        File file = new File(getFilePath());
        String base64EncodedString = "";
        try {
            byte[] fileContent = Files.readAllBytes(file.toPath());
            base64EncodedString = Base64.getEncoder().encodeToString(fileContent);
        } catch (Exception e) {
            Assertions.fail(e);
        }
        return base64EncodedString;
    }
}
