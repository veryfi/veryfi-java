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

class BankStatementTests {
    String clientId = "your_client_id";
    String clientSecret = "your_client_secret";
    String username = "your_username";
    String apiKey = "your_password";
    int apiVersion = 8;
    String bankStatementUrl = "https://cdn-dev.veryfi.com/testing/veryfi-python/bankstatement.pdf";
    HttpClient httpClient;
    ClientImpl client;
    boolean mockResponses = true; // Change to “false” if you want to test your personal credential

    @BeforeEach
    void setup() {
        httpClient = mockResponses ? mock(HttpClient.class) : HttpClient.newHttpClient();
        client = (ClientImpl) VeryfiClientFactory.createClient(clientId, clientSecret, username, apiKey, apiVersion, httpClient);
    }


    @Test
    void getBankStatementsTest() throws IOException, InterruptedException {
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("bankStatements/getBankStatements.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            when(httpClient.send(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(httpResponse);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        String jsonResponse = client.getBankStatements(1, 50, false, false, null);
        JSONObject bankStatements = new JSONObject(jsonResponse);
        Assertions.assertEquals(bankStatements.getJSONArray("results").length(), 3);
    }

    @Test
    void getBankStatementTest() throws IOException, InterruptedException {
        String documentId = "4560116"; // Change to your document Id
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("bankStatements/getBankStatement.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            when(httpClient.send(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(httpResponse);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        String jsonResponse = client.getBankStatement(documentId);
        JSONObject bankStatement = new JSONObject(jsonResponse);
        Assertions.assertEquals(bankStatement.getInt("id"), Integer.parseInt(documentId));
    }

    @Test
    void processBankStatementTest() throws IOException, InterruptedException {
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("bankStatements/processBankStatement.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            when(httpClient.send(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(httpResponse);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        String jsonResponse = client.processBankStatement(getFilePath(), null);
        JSONObject bankStatement = new JSONObject(jsonResponse);
        Assertions.assertEquals(4559568, bankStatement.getJSONObject("data").getInt("id"));
    }

    @Test
    void processBankStatementBase64Test() throws IOException, InterruptedException {
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("bankStatements/processBankStatement.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            when(httpClient.send(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(httpResponse);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        String jsonResponse = client.processBankStatement(getFileName(), getFileData(), null);
        JSONObject bankStatement = new JSONObject(jsonResponse);
        Assertions.assertEquals(4559568, bankStatement.getJSONObject("data").getInt("id"));
    }

    @Test
    void processBankStatementUrlTest() throws IOException, InterruptedException {
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("bankStatements/processBankStatement.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            when(httpClient.send(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(httpResponse);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        String jsonResponse = client.processBankStatementUrl(bankStatementUrl, null, null);
        JSONObject bankStatement = new JSONObject(jsonResponse);
        Assertions.assertEquals(4559568, bankStatement.getJSONObject("data").getInt("id"));
    }

    @Test
    void processBankStatementUrlTestWithFileUrlsAndParameters() throws IOException, InterruptedException {
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("bankStatements/processBankStatement.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            when(httpClient.send(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(httpResponse);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        JSONObject parameters = new JSONObject();
        String jsonResponse = client.processBankStatementUrl("", Collections.singletonList(bankStatementUrl), parameters);
        JSONObject bankStatement = new JSONObject(jsonResponse);
        Assertions.assertEquals(4559568, bankStatement.getJSONObject("data").getInt("id"));
    }

    @Test
    void getBankStatementsAsyncTest() throws ExecutionException, InterruptedException, IOException {
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("bankStatements/getBankStatements.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            CompletableFuture<HttpResponse<String>> jsonResponseFuture = CompletableFuture.completedFuture(httpResponse);
            when(httpClient.sendAsync(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(jsonResponseFuture);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        CompletableFuture<String> jsonResponseFuture = client.getBankStatementsAsync(1, 50, false, false, null);
        String jsonResponse  = jsonResponseFuture.get();
        JSONObject bankStatements = new JSONObject(jsonResponse);
        Assertions.assertEquals(bankStatements.getJSONArray("results").length(), 3);
    }

    @Test
    void getBankStatementAsyncTest() throws ExecutionException, InterruptedException, IOException {
        String documentId = "4560116"; // Change to your document Id
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("bankStatements/getBankStatement.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            CompletableFuture<HttpResponse<String>> jsonResponseFuture = CompletableFuture.completedFuture(httpResponse);
            when(httpClient.sendAsync(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(jsonResponseFuture);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        CompletableFuture<String> jsonResponseFuture = client.getBankStatementAsync(documentId);
        String jsonResponse  = jsonResponseFuture.get();
        JSONObject bankStatements = new JSONObject(jsonResponse);
        Assertions.assertEquals(bankStatements.getInt("id"), Integer.parseInt(documentId));
    }

    @Test
    void processBankStatementAsyncTest() throws ExecutionException, InterruptedException, IOException {
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("bankStatements/processBankStatement.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            CompletableFuture<HttpResponse<String>> jsonResponseFuture = CompletableFuture.completedFuture(httpResponse);
            when(httpClient.sendAsync(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(jsonResponseFuture);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        CompletableFuture<String> jsonResponseFuture = client.processBankStatementAsync(getFilePath(), null);
        String jsonResponse  = jsonResponseFuture.get();
        JSONObject bankStatement = new JSONObject(jsonResponse);
        Assertions.assertEquals(4559568, bankStatement.getJSONObject("data").getInt("id"));
    }

    @Test
    void processBankStatementBase64AsyncTest() throws ExecutionException, InterruptedException, IOException {
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("bankStatements/processBankStatement.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            CompletableFuture<HttpResponse<String>> jsonResponseFuture = CompletableFuture.completedFuture(httpResponse);
            when(httpClient.sendAsync(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(jsonResponseFuture);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        CompletableFuture<String> jsonResponseFuture = client.processBankStatementAsync(getFileName(), getFileData(), null);
        String jsonResponse  = jsonResponseFuture.get();
        JSONObject bankStatement = new JSONObject(jsonResponse);
        Assertions.assertEquals(4559568, bankStatement.getJSONObject("data").getInt("id"));
    }

    @Test
    void processBankStatementUrlAsyncTest() throws ExecutionException, InterruptedException, IOException {
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("bankStatements/processBankStatement.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            CompletableFuture<HttpResponse<String>> jsonResponseFuture = CompletableFuture.completedFuture(httpResponse);
            when(httpClient.sendAsync(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(jsonResponseFuture);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        CompletableFuture<String> jsonResponseFuture = client.processBankStatementUrlAsync(bankStatementUrl, null, null);
        String jsonResponse = jsonResponseFuture.get();
        JSONObject bankStatement = new JSONObject(jsonResponse);
        Assertions.assertEquals(4559568, bankStatement.getJSONObject("data").getInt("id"));
    }

    @Test
    void processBankStatementUrlAsyncTestWithFileUrlsAndParameters() throws ExecutionException, InterruptedException, IOException {
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("bankStatements/processBankStatement.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            CompletableFuture<HttpResponse<String>> jsonResponseFuture = CompletableFuture.completedFuture(httpResponse);
            when(httpClient.sendAsync(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(jsonResponseFuture);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        JSONObject parameters = new JSONObject();
        CompletableFuture<String> jsonResponseFuture = client.processBankStatementUrlAsync("", Collections.singletonList(bankStatementUrl), parameters);
        String jsonResponse = jsonResponseFuture.get();
        JSONObject bankStatement = new JSONObject(jsonResponse);
        Assertions.assertEquals(4559568, bankStatement.getJSONObject("data").getInt("id"));
    }

    private String getFilePath() {
        return FileHelper.getFilePath("bankStatements/bankstatement.pdf");
    }

    private String getFileName() {
        return FileHelper.getFileName("bankStatements/bankstatement.pdf");
    }

    private String getFileData() {
        return FileHelper.getFileData("bankStatements/bankstatement.pdf");
    }

}
