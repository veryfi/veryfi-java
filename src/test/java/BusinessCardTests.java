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

class BusinessCardTests {
    String clientId = "your_client_id";
    String clientSecret = "your_client_secret";
    String username = "your_username";
    String apiKey = "your_password";
    int apiVersion = 8;
    String businessCardUrl = "https://cdn-dev.veryfi.com/testing/veryfi-python/business_card.jpg";
    HttpClient httpClient;
    ClientImpl client;
    boolean mockResponses = true; // Change to “false” if you want to test your personal credential

    @BeforeEach
    void setup() {
        httpClient = mockResponses ? mock(HttpClient.class) : HttpClient.newHttpClient();
        client = (ClientImpl) VeryfiClientFactory.createClient(clientId, clientSecret, username, apiKey, apiVersion, httpClient);
    }


    @Test
    void getBusinessCardsTest() throws IOException, InterruptedException {
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("businessCards/getBusinessCards.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            when(httpClient.send(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(httpResponse);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        String jsonResponse = client.getBusinessCards(1, 50, false, false, null);
        JSONObject businessCards = new JSONObject(jsonResponse);
        Assertions.assertEquals(businessCards.getJSONObject("data").getJSONArray("results").length(), 50);
    }

    @Test
    void getBusinessCardTest() throws IOException, InterruptedException {
        String documentId = "4662609"; // Change to your document Id
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("businessCards/processBusinessCard.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            when(httpClient.send(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(httpResponse);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        String jsonResponse = client.getBusinessCard(documentId);
        JSONObject businessCard = new JSONObject(jsonResponse);
        Assertions.assertEquals(businessCard.getJSONObject("data").getInt("id"), Integer.parseInt(documentId));
    }

    @Test
    void processBusinessCardTest() throws IOException, InterruptedException {
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("businessCards/processBusinessCard.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            when(httpClient.send(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(httpResponse);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        String jsonResponse = client.processBusinessCard(getFilePath(), null);
        JSONObject businessCard = new JSONObject(jsonResponse);
        Assertions.assertEquals(4662609, businessCard.getJSONObject("data").getInt("id"));
    }

    @Test
    void processBusinessCardUrlTest() throws IOException, InterruptedException {
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("businessCards/processBusinessCard.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            when(httpClient.send(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(httpResponse);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        String jsonResponse = client.processBusinessCardUrl(businessCardUrl, null, null);
        JSONObject businessCard = new JSONObject(jsonResponse);
        Assertions.assertEquals(4662609, businessCard.getJSONObject("data").getInt("id"));
    }

    @Test
    void processBusinessCardUrlTestWithFileUrlsAndParameters() throws IOException, InterruptedException {
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("businessCards/processBusinessCard.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            when(httpClient.send(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(httpResponse);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        JSONObject parameters = new JSONObject();
        String jsonResponse = client.processBusinessCardUrl("", Collections.singletonList(businessCardUrl), parameters);
        JSONObject businessCard = new JSONObject(jsonResponse);
        Assertions.assertEquals(4662609, businessCard.getJSONObject("data").getInt("id"));
    }

    @Test
    void getBusinessCardsAsyncTest() throws ExecutionException, InterruptedException, IOException {
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("businessCards/getBusinessCards.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            CompletableFuture<HttpResponse<String>> jsonResponseFuture = CompletableFuture.completedFuture(httpResponse);
            when(httpClient.sendAsync(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(jsonResponseFuture);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        CompletableFuture<String> jsonResponseFuture = client.getBusinessCardsAsync(1, 50, false, false, null);
        String jsonResponse  = jsonResponseFuture.get();
        JSONObject businessCards = new JSONObject(jsonResponse);
        Assertions.assertEquals(businessCards.getJSONObject("data").getJSONArray("results").length(), 50);
    }

    @Test
    void getBusinessCardAsyncTest() throws ExecutionException, InterruptedException, IOException {
        String documentId = "4662609"; // Change to your document Id
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("businessCards/processBusinessCard.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            CompletableFuture<HttpResponse<String>> jsonResponseFuture = CompletableFuture.completedFuture(httpResponse);
            when(httpClient.sendAsync(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(jsonResponseFuture);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        CompletableFuture<String> jsonResponseFuture = client.getBusinessCardAsync(documentId);
        String jsonResponse  = jsonResponseFuture.get();
        JSONObject businessCards = new JSONObject(jsonResponse);
        Assertions.assertEquals(businessCards.getJSONObject("data").getInt("id"), Integer.parseInt(documentId));
    }

    @Test
    void processBusinessCardAsyncTest() throws ExecutionException, InterruptedException, IOException {
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("businessCards/processBusinessCard.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            CompletableFuture<HttpResponse<String>> jsonResponseFuture = CompletableFuture.completedFuture(httpResponse);
            when(httpClient.sendAsync(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(jsonResponseFuture);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        CompletableFuture<String> jsonResponseFuture = client.processBusinessCardAsync(getFilePath(), null);
        String jsonResponse  = jsonResponseFuture.get();
        JSONObject businessCard = new JSONObject(jsonResponse);
        Assertions.assertEquals(4662609, businessCard.getJSONObject("data").getInt("id"));
    }

    @Test
    void processBusinessCardUrlAsyncTest() throws ExecutionException, InterruptedException, IOException {
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("businessCards/processBusinessCard.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            CompletableFuture<HttpResponse<String>> jsonResponseFuture = CompletableFuture.completedFuture(httpResponse);
            when(httpClient.sendAsync(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(jsonResponseFuture);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        CompletableFuture<String> jsonResponseFuture = client.processBusinessCardUrlAsync(businessCardUrl, null, null);
        String jsonResponse = jsonResponseFuture.get();
        JSONObject businessCard = new JSONObject(jsonResponse);
        Assertions.assertEquals(4662609, businessCard.getJSONObject("data").getInt("id"));
    }

    @Test
    void processBusinessCardUrlAsyncTestWithFileUrlsAndParameters() throws ExecutionException, InterruptedException, IOException {
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("businessCards/processBusinessCard.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            CompletableFuture<HttpResponse<String>> jsonResponseFuture = CompletableFuture.completedFuture(httpResponse);
            when(httpClient.sendAsync(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(jsonResponseFuture);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        JSONObject parameters = new JSONObject();
        CompletableFuture<String> jsonResponseFuture = client.processBusinessCardUrlAsync("", Collections.singletonList(businessCardUrl), parameters);
        String jsonResponse = jsonResponseFuture.get();
        JSONObject businessCard = new JSONObject(jsonResponse);
        Assertions.assertEquals(4662609, businessCard.getJSONObject("data").getInt("id"));
    }

    @Test
    void processBusinessCardBase64Test() throws IOException, InterruptedException {
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("businessCards/processBusinessCard.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            when(httpClient.send(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(httpResponse);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        String jsonResponse = client.processBusinessCard(getFileName(), getFileData(), null);
        JSONObject businessCard = new JSONObject(jsonResponse);
        Assertions.assertEquals(4662609, businessCard.getJSONObject("data").getInt("id"));
    }

    @Test
    void processBusinessCardBase64AsyncTest() throws ExecutionException, InterruptedException, IOException {
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("businessCards/processBusinessCard.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            CompletableFuture<HttpResponse<String>> jsonResponseFuture = CompletableFuture.completedFuture(httpResponse);
            when(httpClient.sendAsync(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(jsonResponseFuture);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        CompletableFuture<String> jsonResponseFuture = client.processBusinessCardAsync(getFileName(), getFileData(), null);
        String jsonResponse = jsonResponseFuture.get();
        JSONObject businessCard = new JSONObject(jsonResponse);
        Assertions.assertEquals(4662609, businessCard.getJSONObject("data").getInt("id"));
    }

    private String getFilePath() {
        return FileHelper.getFilePath("businessCards/business_card.jpg");
    }

    private String getFileName() {
        return FileHelper.getFileName("businessCards/business_card.jpg");
    }

    private String getFileData() {
        return FileHelper.getFileData("businessCards/business_card.jpg");
    }
}
