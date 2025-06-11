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

class W2Tests {
    String clientId = "your_client_id";
    String clientSecret = "your_client_secret";
    String username = "your_username";
    String apiKey = "your_password";
    int apiVersion = 8;
    String w2Url = "https://cdn.veryfi.com/wp-content/uploads/image.png";
    HttpClient httpClient;
    ClientImpl client;
    boolean mockResponses = true; // Change to “false” if you want to test your personal credential

    @BeforeEach
    void setup() {
        httpClient = mockResponses ? mock(HttpClient.class) : HttpClient.newHttpClient();
        client = (ClientImpl) VeryfiClientFactory.createClient(clientId, clientSecret, username, apiKey, apiVersion, httpClient);
    }


    @Test
    void getW2sTest() throws IOException, InterruptedException {
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("w2s/getW2s.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            when(httpClient.send(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(httpResponse);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        String jsonResponse = client.getW2s(1, 50, false, false, null);
        JSONObject w2s = new JSONObject(jsonResponse);
        Assertions.assertEquals(w2s.getJSONObject("data").getJSONArray("results").length(), 50);
    }

    @Test
    void getW2Test() throws IOException, InterruptedException {
        String documentId = "4559395"; // Change to your document Id
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("w2s/processW2.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            when(httpClient.send(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(httpResponse);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        String jsonResponse = client.getW2(documentId);
        JSONObject w2s = new JSONObject(jsonResponse);
        Assertions.assertEquals(w2s.getJSONObject("data").getInt("id"), Integer.parseInt(documentId));
    }

    @Test
    void processW2Test() throws IOException, InterruptedException {
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("w2s/processW2.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            when(httpClient.send(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(httpResponse);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        String jsonResponse = client.processW2(getFilePath(), null);
        JSONObject w2s = new JSONObject(jsonResponse);
        Assertions.assertEquals(4559395, w2s.getJSONObject("data").getInt("id"));
    }

    @Test
    void processW2Base64Test() throws IOException, InterruptedException {
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("w2s/processW2.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            when(httpClient.send(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(httpResponse);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        String jsonResponse = client.processW2(getFileName(), getFileData(), null);
        JSONObject w2s = new JSONObject(jsonResponse);
        Assertions.assertEquals(4559395, w2s.getJSONObject("data").getInt("id"));
    }

    @Test
    void processW2UrlTest() throws IOException, InterruptedException {
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("w2s/processW2.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            when(httpClient.send(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(httpResponse);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        String jsonResponse = client.processW2Url(w2Url, null, null);
        JSONObject w2s = new JSONObject(jsonResponse);
        Assertions.assertEquals(4559395, w2s.getJSONObject("data").getInt("id"));
    }

    @Test
    void processW2UrlTestWithFileUrlsAndParameters() throws IOException, InterruptedException {
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("w2s/processW2.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            when(httpClient.send(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(httpResponse);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        JSONObject parameters = new JSONObject();
        String jsonResponse = client.processW2Url("", Collections.singletonList(w2Url), parameters);
        JSONObject w2s = new JSONObject(jsonResponse);
        Assertions.assertEquals(4559395, w2s.getJSONObject("data").getInt("id"));
    }

    @Test
    void getW2sAsyncTest() throws ExecutionException, InterruptedException, IOException {
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("w2s/getW2s.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            CompletableFuture<HttpResponse<String>> jsonResponseFuture = CompletableFuture.completedFuture(httpResponse);
            when(httpClient.sendAsync(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(jsonResponseFuture);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        CompletableFuture<String> jsonResponseFuture = client.getW2sAsync(1, 50, false, false, null);
        String jsonResponse  = jsonResponseFuture.get();
        JSONObject w2s = new JSONObject(jsonResponse);
        Assertions.assertEquals(w2s.getJSONObject("data").getJSONArray("results").length(), 50);
    }

    @Test
    void getW2AsyncTest() throws ExecutionException, InterruptedException, IOException {
        String documentId = "4559395"; // Change to your document Id
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("w2s/processW2.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            CompletableFuture<HttpResponse<String>> jsonResponseFuture = CompletableFuture.completedFuture(httpResponse);
            when(httpClient.sendAsync(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(jsonResponseFuture);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        CompletableFuture<String> jsonResponseFuture = client.getW2Async(documentId);
        String jsonResponse  = jsonResponseFuture.get();
        JSONObject w2s = new JSONObject(jsonResponse);
        Assertions.assertEquals(w2s.getJSONObject("data").getInt("id"), Integer.parseInt(documentId));
    }

    @Test
    void processW2AsyncTest() throws ExecutionException, InterruptedException, IOException {
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("w2s/processW2.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            CompletableFuture<HttpResponse<String>> jsonResponseFuture = CompletableFuture.completedFuture(httpResponse);
            when(httpClient.sendAsync(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(jsonResponseFuture);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        CompletableFuture<String> jsonResponseFuture = client.processW2Async(getFilePath(), null);
        String jsonResponse  = jsonResponseFuture.get();
        JSONObject w2s = new JSONObject(jsonResponse);
        Assertions.assertEquals(4559395, w2s.getJSONObject("data").getInt("id"));
    }

    @Test
    void processW2Base64AsyncTest() throws ExecutionException, InterruptedException, IOException {
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("w2s/processW2.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            CompletableFuture<HttpResponse<String>> jsonResponseFuture = CompletableFuture.completedFuture(httpResponse);
            when(httpClient.sendAsync(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(jsonResponseFuture);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        CompletableFuture<String> jsonResponseFuture = client.processW2Async(getFileName(), getFileData(), null);
        String jsonResponse  = jsonResponseFuture.get();
        JSONObject w2s = new JSONObject(jsonResponse);
        Assertions.assertEquals(4559395, w2s.getJSONObject("data").getInt("id"));
    }

    @Test
    void processW2UrlAsyncTest() throws ExecutionException, InterruptedException, IOException {
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("w2s/processW2.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            CompletableFuture<HttpResponse<String>> jsonResponseFuture = CompletableFuture.completedFuture(httpResponse);
            when(httpClient.sendAsync(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(jsonResponseFuture);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        CompletableFuture<String> jsonResponseFuture = client.processW2UrlAsync(w2Url, null, null);
        String jsonResponse = jsonResponseFuture.get();
        JSONObject w2s = new JSONObject(jsonResponse);
        Assertions.assertEquals(4559395, w2s.getJSONObject("data").getInt("id"));
    }

    @Test
    void processW2UrlAsyncTestWithFileUrlsAndParameters() throws ExecutionException, InterruptedException, IOException {
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("w2s/processW2.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            CompletableFuture<HttpResponse<String>> jsonResponseFuture = CompletableFuture.completedFuture(httpResponse);
            when(httpClient.sendAsync(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(jsonResponseFuture);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        JSONObject parameters = new JSONObject();
        CompletableFuture<String> jsonResponseFuture = client.processW2UrlAsync("", Collections.singletonList(w2Url), parameters);
        String jsonResponse = jsonResponseFuture.get();
        JSONObject w2s = new JSONObject(jsonResponse);
        Assertions.assertEquals(4559395, w2s.getJSONObject("data").getInt("id"));
    }

    private String getFilePath() {
        return FileHelper.getFilePath("w2s/w2.png");
    }

    private String getFileName() {
        return FileHelper.getFileName("w2s/w2.png");
    }

    private String getFileData() {
        return FileHelper.getFileData("w2s/w2.png");
    }

}
