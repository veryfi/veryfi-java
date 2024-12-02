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
import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CheckTests {
    String clientId = "your_client_id";
    String clientSecret = "your_client_secret";
    String username = "your_username";
    String apiKey = "your_password";
    int apiVersion = 8;
    String checkUrl = "https://cdn-dev.veryfi.com/testing/veryfi-python/check.pdf";
    HttpClient httpClient;
    ClientImpl client;
    boolean mockResponses = true; // Change to “false” if you want to test your personal credential

    @BeforeEach
    void setup() {
        httpClient = mockResponses ? mock(HttpClient.class) : HttpClient.newHttpClient();
        client = (ClientImpl) VeryfiClientFactory.createClient(clientId, clientSecret, username, apiKey, apiVersion, httpClient);
    }


    @Test
    void getChecksTest() throws IOException, InterruptedException {
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("checks/getChecks.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            when(httpClient.send(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(httpResponse);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        String jsonResponse = client.getChecks(1, 50, false, false, null);
        JSONObject checks = new JSONObject(jsonResponse);
        Assertions.assertEquals(checks.getJSONObject("data").getJSONArray("results").length(), 50);
    }

    @Test
    void getCheckTest() throws IOException, InterruptedException {
        String documentId = "4662680"; // Change to your document Id
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("checks/processCheck.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            when(httpClient.send(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(httpResponse);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        String jsonResponse = client.getCheck(documentId);
        JSONObject check = new JSONObject(jsonResponse);
        Assertions.assertEquals(check.getJSONObject("data").getInt("id"), Integer.parseInt(documentId));
    }

    @Test
    void processCheckTest() throws IOException, InterruptedException {
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("checks/processCheck.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            when(httpClient.send(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(httpResponse);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        String jsonResponse = client.processCheck(getFilePath(), null);
        JSONObject check = new JSONObject(jsonResponse);
        Assertions.assertEquals(4662680, check.getJSONObject("data").getInt("id"));
    }

    @Test
    void processCheckUrlTest() throws IOException, InterruptedException {
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("checks/processCheck.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            when(httpClient.send(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(httpResponse);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        String jsonResponse = client.processCheckUrl(checkUrl, null, null);
        JSONObject check = new JSONObject(jsonResponse);
        Assertions.assertEquals(4662680, check.getJSONObject("data").getInt("id"));
    }

    @Test
    void processCheckUrlTestWithFileUrlsAndParameters() throws IOException, InterruptedException {
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("checks/processCheck.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            when(httpClient.send(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(httpResponse);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        JSONObject parameters = new JSONObject();
        String jsonResponse = client.processCheckUrl("", Collections.singletonList(checkUrl), parameters);
        JSONObject check = new JSONObject(jsonResponse);
        Assertions.assertEquals(4662680, check.getJSONObject("data").getInt("id"));
    }

    @Test
    void getChecksAsyncTest() throws ExecutionException, InterruptedException, IOException {
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("checks/getChecks.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            CompletableFuture<HttpResponse<String>> jsonResponseFuture = CompletableFuture.completedFuture(httpResponse);
            when(httpClient.sendAsync(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(jsonResponseFuture);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        CompletableFuture<String> jsonResponseFuture = client.getChecksAsync(1, 50, false, false, null);
        String jsonResponse  = jsonResponseFuture.get();
        JSONObject checks = new JSONObject(jsonResponse);
        Assertions.assertEquals(checks.getJSONObject("data").getJSONArray("results").length(), 50);
    }

    @Test
    void getCheckAsyncTest() throws ExecutionException, InterruptedException, IOException {
        String documentId = "4662680"; // Change to your document Id
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("checks/processCheck.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            CompletableFuture<HttpResponse<String>> jsonResponseFuture = CompletableFuture.completedFuture(httpResponse);
            when(httpClient.sendAsync(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(jsonResponseFuture);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        CompletableFuture<String> jsonResponseFuture = client.getCheckAsync(documentId);
        String jsonResponse  = jsonResponseFuture.get();
        JSONObject checks = new JSONObject(jsonResponse);
        Assertions.assertEquals(checks.getJSONObject("data").getInt("id"), Integer.parseInt(documentId));
    }

    @Test
    void processCheckAsyncTest() throws ExecutionException, InterruptedException, IOException {
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("checks/processCheck.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            CompletableFuture<HttpResponse<String>> jsonResponseFuture = CompletableFuture.completedFuture(httpResponse);
            when(httpClient.sendAsync(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(jsonResponseFuture);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        CompletableFuture<String> jsonResponseFuture = client.processCheckAsync(getFilePath(), null);
        String jsonResponse  = jsonResponseFuture.get();
        JSONObject check = new JSONObject(jsonResponse);
        Assertions.assertEquals(4662680, check.getJSONObject("data").getInt("id"));
    }

    @Test
    void processCheckUrlAsyncTest() throws ExecutionException, InterruptedException, IOException {
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("checks/processCheck.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            CompletableFuture<HttpResponse<String>> jsonResponseFuture = CompletableFuture.completedFuture(httpResponse);
            when(httpClient.sendAsync(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(jsonResponseFuture);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        CompletableFuture<String> jsonResponseFuture = client.processCheckUrlAsync(checkUrl, null, null);
        String jsonResponse = jsonResponseFuture.get();
        JSONObject check = new JSONObject(jsonResponse);
        Assertions.assertEquals(4662680, check.getJSONObject("data").getInt("id"));
    }

    @Test
    void processCheckUrlAsyncTestWithFileUrlsAndParameters() throws ExecutionException, InterruptedException, IOException {
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("checks/processCheck.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            CompletableFuture<HttpResponse<String>> jsonResponseFuture = CompletableFuture.completedFuture(httpResponse);
            when(httpClient.sendAsync(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(jsonResponseFuture);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        JSONObject parameters = new JSONObject();
        CompletableFuture<String> jsonResponseFuture = client.processCheckUrlAsync("", Collections.singletonList(checkUrl), parameters);
        String jsonResponse = jsonResponseFuture.get();
        JSONObject check = new JSONObject(jsonResponse);
        Assertions.assertEquals(4662680, check.getJSONObject("data").getInt("id"));
    }


    private String getFilePath() {
        return ClassLoader.getSystemResource("checks/check.pdf").getPath();
    }
}
