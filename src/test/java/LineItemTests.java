import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import veryfi.VeryfiClientFactory;
import veryfi.models.AddLineItem;
import veryfi.models.NotValidModelException;
import veryfi.models.UpdateLineItem;
import veryfi.services.ClientImpl;

import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LineItemTests {
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
    void getLineItemsTest() throws IOException, InterruptedException {
        String documentId = "125344108";
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("lineItems/getLineItems.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            HttpResponse<String> httpResponse = mock(HttpResponse.class);
            when(httpClient.send(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(httpResponse);
            when(httpResponse.statusCode()).thenReturn(200);
            when(httpResponse.body()).thenReturn(result);
        }
        String response = client.getLineItems(documentId);
        JSONObject jsonResponse = new JSONObject(response);
        if (mockResponses) {
            Assertions.assertFalse(jsonResponse.getJSONArray("line_items").isEmpty());
        } else {
            Assertions.assertTrue(jsonResponse.getJSONArray("line_items").isEmpty());
        }
    }

    @Test
    void getLineItemsAsyncTest() throws ExecutionException, InterruptedException, IOException {
        String documentId = "125344108";
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("lineItems/getLineItems.json");
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
        if (mockResponses) {
            Assertions.assertFalse(jsonResponse.getJSONArray("line_items").isEmpty());
        } else {
            Assertions.assertTrue(jsonResponse.getJSONArray("line_items").isEmpty());
        }
    }

    @Test
    void getLineItemTest() throws IOException, InterruptedException {
        String documentId = "125344108";
        String lineItemId = "101170751";
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("lineItems/getLineItem.json");
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
    }

    @Test
    void getLineItemAsyncTest() throws IOException, InterruptedException, ExecutionException {
        String documentId = "125344108";
        String lineItemId = "101170751";
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("lineItems/getLineItem.json");
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
    }

    @Test
    void updateLineItemTest() throws IOException, InterruptedException, NotValidModelException {
        String documentId = "125344108";
        String lineItemId = "101170751";
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("lineItems/updateLineItem.json");
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
    }

    @Test
    void updateLineItemAsyncTest() throws IOException, InterruptedException, NotValidModelException, ExecutionException {
        String documentId = "125344108";
        String lineItemId = "101170751";
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("lineItems/updateLineItem.json");
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
    }

    @Test
    void addLineItemTest() throws IOException, InterruptedException, NotValidModelException {
        String documentId = "125344108";
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("lineItems/addLineItem.json");
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
        String documentId = "125344108";
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("lineItems/addLineItem.json");
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
        String documentId = "125344108";
        String lineItemId = "189951682";
        InputStream fileStream = ClassLoader.getSystemResourceAsStream("lineItems/deleteLineItem.json");
        assert fileStream != null;
        String result = new String(fileStream.readAllBytes());
        HttpResponse<String> httpResponse = mock(HttpResponse.class);
        when(httpClient.send(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(httpResponse);
        when(httpResponse.statusCode()).thenReturn(200);
        when(httpResponse.body()).thenReturn(result);
        String response = client.deleteLineItem(documentId, lineItemId);
        JSONObject jsonResponse = new JSONObject(response);
        Assertions.assertEquals("ok", jsonResponse.getString("status"));
    }

    @Test
    void deleteLineItemAsyncTest() throws IOException, InterruptedException, ExecutionException {
        String documentId = "125344108";
        String lineItemId = "189951682";
        InputStream fileStream = ClassLoader.getSystemResourceAsStream("lineItems/deleteLineItem.json");
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
    }

    @Test
    void deleteLineItemsTest() throws IOException, InterruptedException {
        String documentId = "125344108";
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("lineItems/deleteLineItems.json");
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
        String documentId = "125344108";
        if (mockResponses) {
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("lineItems/deleteLineItems.json");
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
