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
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TagTests {
    String clientId = "your_client_id";
    String clientSecret = "your_client_secret";
    String username = "your_username";
    String apiKey = "your_password";
    int apiVersion = 8;
    HttpClient httpClient;
    ClientImpl client;
    boolean mockResponses = true; // Change to “false” if you want to test your personal credential

    @BeforeEach
    void setup() {
        httpClient = mockResponses ? mock(HttpClient.class) : HttpClient.newHttpClient();
        client = (ClientImpl) VeryfiClientFactory.createClient(clientId, clientSecret, username, apiKey, apiVersion, httpClient);
    }

    @Test
    void replaceTagsTest() throws IOException, InterruptedException {
        String documentId = "125344108";
        InputStream fileStream = ClassLoader.getSystemResourceAsStream("tags/replaceTags.json");
        assert fileStream != null;
        String result = new String(fileStream.readAllBytes());
        HttpResponse<String> httpResponse = mock(HttpResponse.class);
        when(httpClient.send(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(httpResponse);
        when(httpResponse.statusCode()).thenReturn(200);
        when(httpResponse.body()).thenReturn(result);
        String response = client.replaceTags(documentId, List.of("TAG1", "TAG2", "TAG3"));
        JSONObject jsonResponse = new JSONObject(response);
        Assertions.assertEquals("ok", jsonResponse.getString("status"));
    }

    @Test
    void replaceTagsAsyncTest() throws IOException, ExecutionException, InterruptedException {
        String documentId = "125344108";
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("tags/replaceTags.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            CompletableFuture<HttpResponse<String>> jsonResponseFutureMock = CompletableFuture.completedFuture(httpResponse);
            when(httpClient.sendAsync(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(jsonResponseFutureMock);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        CompletableFuture<String> responseFuture = client.replaceTagsAsync(documentId, List.of("TAG1", "TAG2", "TAG3"));
        String response = responseFuture.get();
        JSONObject jsonResponse = new JSONObject(response);
        Assertions.assertEquals("ok", jsonResponse.getString("status"));
    }

    @Test
    void addTagsTest() throws IOException, InterruptedException {
        String documentId = "125344108";
        InputStream fileStream = ClassLoader.getSystemResourceAsStream("tags/addTags.json");
        assert fileStream != null;
        String result = new String(fileStream.readAllBytes());
        HttpResponse<String> httpResponse = mock(HttpResponse.class);
        when(httpClient.send(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(httpResponse);
        when(httpResponse.statusCode()).thenReturn(200);
        when(httpResponse.body()).thenReturn(result);
        String response = client.addTags(documentId, List.of("TAG1", "TAG2", "TAG3"));
        JSONObject jsonResponse = new JSONObject(response);
        Assertions.assertEquals("ok", jsonResponse.getString("status"));
    }

    @Test
    void addTagsAsyncTest() throws IOException, ExecutionException, InterruptedException {
        String documentId = "125344108";
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("tags/addTags.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            CompletableFuture<HttpResponse<String>> jsonResponseFutureMock = CompletableFuture.completedFuture(httpResponse);
            when(httpClient.sendAsync(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(jsonResponseFutureMock);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        CompletableFuture<String> responseFuture = client.addTagsAsync(documentId, List.of("TAG1", "TAG2", "TAG3"));
        String response = responseFuture.get();
        JSONObject jsonResponse = new JSONObject(response);
        Assertions.assertEquals("ok", jsonResponse.getString("status"));
    }

}
