import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import veryfi.VeryfiClientFactory;
import veryfi.services.ClientImpl;

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

class AnyDocumentTests {
    String clientId = "your_client_id";
    String clientSecret = "your_client_secret";
    String username = "your_username";
    String apiKey = "your_password";
    int apiVersion = 8;
    String anyDocumentUrl = "https://cdn-dev.veryfi.com/testing/veryfi-python/driver_license.png";
    HttpClient httpClient;
    ClientImpl client;
    boolean mockResponses = true; // Change to “false” if you want to test your personal credential

    @BeforeEach
    void setup() {
        httpClient = mockResponses ? mock(HttpClient.class) : HttpClient.newHttpClient();
        client = (ClientImpl) VeryfiClientFactory.createClient(clientId, clientSecret, username, apiKey, apiVersion, httpClient);
    }


    @Test
    void getAnyDocumentsTest() throws IOException, InterruptedException {
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("anyDocuments/getAnyDocuments.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            when(httpClient.send(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(httpResponse);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        String jsonResponse = client.getAnyDocuments(1, 50, false, false, null);
        JSONObject anyDocuments = new JSONObject(jsonResponse);
        Assertions.assertEquals(anyDocuments.getJSONArray("results").length(), 10);
    }

    @Test
    void getAnyDocumentTest() throws IOException, InterruptedException {
        String documentId = "4560114"; // Change to your document Id
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("anyDocuments/getAnyDocument.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            when(httpClient.send(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(httpResponse);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        String jsonResponse = client.getAnyDocument(documentId);
        JSONObject anyDocument = new JSONObject(jsonResponse);
        Assertions.assertEquals(anyDocument.getInt("id"), Integer.parseInt(documentId));
    }

    @Test
    void processAnyDocumentTest() throws IOException, InterruptedException {
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("anyDocuments/processAnyDocument.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            when(httpClient.send(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(httpResponse);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        String jsonResponse = client.processAnyDocument(getFilePath(), "us_driver_license", null);
        JSONObject anyDocument = new JSONObject(jsonResponse);
        Assertions.assertEquals(4559535, anyDocument.getJSONObject("data").getInt("id"));
    }

    @Test
    void processAnyDocumentBase64Test() throws IOException, InterruptedException {
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("anyDocuments/processAnyDocument.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            when(httpClient.send(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(httpResponse);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        String jsonResponse = client.processAnyDocument(getFileName(), getFileData(), "us_driver_license", null);
        JSONObject anyDocument = new JSONObject(jsonResponse);
        Assertions.assertEquals(4559535, anyDocument.getJSONObject("data").getInt("id"));
    }

    @Test
    void processAnyDocumentUrlTest() throws IOException, InterruptedException {
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("anyDocuments/processAnyDocument.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            when(httpClient.send(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(httpResponse);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        String jsonResponse = client.processAnyDocumentUrl(anyDocumentUrl, null, "us_driver_license", null);
        JSONObject anyDocument = new JSONObject(jsonResponse);
        Assertions.assertEquals(4559535, anyDocument.getJSONObject("data").getInt("id"));
    }

    @Test
    void processAnyDocumentUrlTestWithFileUrlsAndParameters() throws IOException, InterruptedException {
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("anyDocuments/processAnyDocument.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            when(httpClient.send(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(httpResponse);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        JSONObject parameters = new JSONObject();
        String jsonResponse = client.processAnyDocumentUrl("", Collections.singletonList(anyDocumentUrl), "us_driver_license", parameters);
        JSONObject anyDocument = new JSONObject(jsonResponse);
        Assertions.assertEquals(4559535, anyDocument.getJSONObject("data").getInt("id"));
    }

    @Test
    void getAnyDocumentsAsyncTest() throws ExecutionException, InterruptedException, IOException {
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("anyDocuments/getAnyDocuments.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            CompletableFuture<HttpResponse<String>> jsonResponseFuture = CompletableFuture.completedFuture(httpResponse);
            when(httpClient.sendAsync(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(jsonResponseFuture);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        CompletableFuture<String> jsonResponseFuture = client.getAnyDocumentsAsync(1, 50, false, false, null);
        String jsonResponse  = jsonResponseFuture.get();
        JSONObject anyDocuments = new JSONObject(jsonResponse);
        Assertions.assertEquals(anyDocuments.getJSONArray("results").length(), 10);
    }

    @Test
    void getAnyDocumentAsyncTest() throws ExecutionException, InterruptedException, IOException {
        String documentId = "4560114"; // Change to your document Id
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("anyDocuments/getAnyDocument.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            CompletableFuture<HttpResponse<String>> jsonResponseFuture = CompletableFuture.completedFuture(httpResponse);
            when(httpClient.sendAsync(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(jsonResponseFuture);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        CompletableFuture<String> jsonResponseFuture = client.getAnyDocumentAsync(documentId);
        String jsonResponse  = jsonResponseFuture.get();
        JSONObject anyDocuments = new JSONObject(jsonResponse);
        Assertions.assertEquals(anyDocuments.getInt("id"), Integer.parseInt(documentId));
    }

    @Test
    void processAnyDocumentAsyncTest() throws ExecutionException, InterruptedException, IOException {
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("anyDocuments/processAnyDocument.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            CompletableFuture<HttpResponse<String>> jsonResponseFuture = CompletableFuture.completedFuture(httpResponse);
            when(httpClient.sendAsync(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(jsonResponseFuture);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        CompletableFuture<String> jsonResponseFuture = client.processAnyDocumentAsync(getFilePath(), "us_driver_license", null);
        String jsonResponse  = jsonResponseFuture.get();
        JSONObject anyDocument = new JSONObject(jsonResponse);
        Assertions.assertEquals(4559535, anyDocument.getJSONObject("data").getInt("id"));
    }

    @Test
    void processAnyDocumentBase64AsyncTest() throws ExecutionException, InterruptedException, IOException {
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("anyDocuments/processAnyDocument.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            CompletableFuture<HttpResponse<String>> jsonResponseFuture = CompletableFuture.completedFuture(httpResponse);
            when(httpClient.sendAsync(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(jsonResponseFuture);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        CompletableFuture<String> jsonResponseFuture = client.processAnyDocumentAsync(getFileName(), getFileData(), "us_driver_license", null);
        String jsonResponse  = jsonResponseFuture.get();
        JSONObject anyDocument = new JSONObject(jsonResponse);
        Assertions.assertEquals(4559535, anyDocument.getJSONObject("data").getInt("id"));
    }

    @Test
    void processAnyDocumentUrlAsyncTest() throws ExecutionException, InterruptedException, IOException {
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("anyDocuments/processAnyDocument.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            CompletableFuture<HttpResponse<String>> jsonResponseFuture = CompletableFuture.completedFuture(httpResponse);
            when(httpClient.sendAsync(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(jsonResponseFuture);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        CompletableFuture<String> jsonResponseFuture = client.processAnyDocumentUrlAsync(anyDocumentUrl, null, "us_driver_license", null);
        String jsonResponse = jsonResponseFuture.get();
        JSONObject anyDocument = new JSONObject(jsonResponse);
        Assertions.assertEquals(4559535, anyDocument.getJSONObject("data").getInt("id"));
    }

    @Test
    void processAnyDocumentUrlAsyncTestWithFileUrlsAndParameters() throws ExecutionException, InterruptedException, IOException {
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("anyDocuments/processAnyDocument.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            CompletableFuture<HttpResponse<String>> jsonResponseFuture = CompletableFuture.completedFuture(httpResponse);
            when(httpClient.sendAsync(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(jsonResponseFuture);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        JSONObject parameters = new JSONObject();
        CompletableFuture<String> jsonResponseFuture = client.processAnyDocumentUrlAsync("", Collections.singletonList(anyDocumentUrl), "us_driver_license", parameters);
        String jsonResponse = jsonResponseFuture.get();
        JSONObject anyDocument = new JSONObject(jsonResponse);
        Assertions.assertEquals(4559535, anyDocument.getJSONObject("data").getInt("id"));
    }

    private String getFilePath() {
        return FileHelper.getFilePath("anyDocuments/driver_license.png");
    }

    private String getFileName() {
        return FileHelper.getFileName("anyDocuments/driver_license.png");
    }

    private String getFileData() {
        return FileHelper.getFileData("anyDocuments/driver_license.png");
    }

}
