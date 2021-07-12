import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import veryfi.Client;
import veryfi.VeryfiClientFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ClientTest {
    String clientId = "your_client_id";
    String clientSecret = "your_client_secret";
    String username = "your_username";
    String apiKey = "your_password";
    Client client = VeryfiClientFactory.createClient(clientId, clientSecret, username, apiKey);
    boolean mockResponses = true; //Put it false to test your personal credentials

    @Test
    void getDocumentsTest() throws IOException {
        if (mockResponses) {
            client = org.mockito.Mockito.mock(Client.class);
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("getDocuments.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            Mockito.when(client.getDocuments()).thenReturn(result);
        }
        String jsonResponse = client.getDocuments();
        JSONArray documents = new JSONArray(jsonResponse);
        Assertions.assertTrue(documents.length() > 0);
    }

    @Test
    void getDocumentTest() throws IOException {
        if (mockResponses) {
            client = org.mockito.Mockito.mock(Client.class);
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("getDocument.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            Mockito.when(client.getDocument("31727276")).thenReturn(result);
        }
        String jsonResponse = client.getDocument("31727276");
        JSONObject document = new JSONObject(jsonResponse);
        Assertions.assertTrue(document.getInt("id") > 0);
    }

    @Test
    void processDocumentTest() throws IOException {
        List<String> categories = Arrays.asList("Advertising & Marketing", "Automotive");
        if (mockResponses) {
            client = org.mockito.Mockito.mock(Client.class);
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("processDocument.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            Mockito.when(client.processDocument("example1.jpg", categories, false, null)).thenReturn(result);
        }
        String jsonResponse = client.processDocument("example1.jpg", categories, false, null);
        JSONObject document = new JSONObject(jsonResponse);
        Assertions.assertTrue(document.getInt("id") > 0);
    }

    @Test
    void updateDocumentTest() throws IOException {
        String documentId = "31727276";
        JSONObject parameters = new JSONObject();
        String notes = "Note updated";
        parameters.put("notes", notes);
        if (mockResponses) {
            client = org.mockito.Mockito.mock(Client.class);
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("updateDocument.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            Mockito.when(client.updateDocument(documentId, parameters)).thenReturn(result);
        }
        String jsonResponseUpdated = client.updateDocument(documentId, parameters);
        JSONObject documentJson = new JSONObject(jsonResponseUpdated);
        Assertions.assertEquals(documentJson.getString("notes"), notes);
    }

    @Test
    void deleteDocumentTest() throws IOException {
        if (mockResponses) {
            client = org.mockito.Mockito.mock(Client.class);
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("deleteDocument.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            Mockito.when(client.deleteDocument("37769185")).thenReturn(result);
        }
        String jsonResponse = client.deleteDocument("37769185");
        Assertions.assertFalse(jsonResponse.isEmpty());
    }

    @Test
    void processDocumentUrlTest() throws IOException {
        if (mockResponses) {
            client = org.mockito.Mockito.mock(Client.class);
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("processDocumentUrl.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            Mockito.when(client.processDocumentUrl("https://bloximages.chicago2.vip.townnews.com/dailylocal.com/content/tncms/assets/v3/editorial/d/77/d7745b82-1c1f-11e9-b3bb-032816e700e0/5c43798f9d036.image.jpg", null, null, false, 1, false, null, null)).thenReturn(result);
        }
        String jsonResponse = client.processDocumentUrl("https://bloximages.chicago2.vip.townnews.com/dailylocal.com/content/tncms/assets/v3/editorial/d/77/d7745b82-1c1f-11e9-b3bb-032816e700e0/5c43798f9d036.image.jpg", null, null, false, 1, false, null, null);
        JSONObject document = new JSONObject(jsonResponse);
        Assertions.assertTrue(document.getInt("id") > 0);
    }

    @Test
    void getDocumentsAsyncTest() throws ExecutionException, InterruptedException, IOException {
        if (mockResponses) {
            client = org.mockito.Mockito.mock(Client.class);
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("getDocuments.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            CompletableFuture<String> jsonResponseFuture = CompletableFuture.completedFuture(result);
            Mockito.when(client.getDocumentsAsync()).thenReturn(jsonResponseFuture);
        }
        CompletableFuture<String> jsonResponseFuture = client.getDocumentsAsync();
        String jsonResponse  = jsonResponseFuture.get();
        JSONArray documents = new JSONArray(jsonResponse);
        Assertions.assertTrue(documents.length() > 0);
    }

    @Test
    void getDocumentAsyncTest() throws ExecutionException, InterruptedException, IOException {
        if (mockResponses) {
            client = org.mockito.Mockito.mock(Client.class);
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("getDocument.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            CompletableFuture<String> jsonResponseFuture = CompletableFuture.completedFuture(result);
            Mockito.when(client.getDocumentAsync("31028657")).thenReturn(jsonResponseFuture);
        }
        CompletableFuture<String> jsonResponseFuture = client.getDocumentAsync("31028657");
        String jsonResponse  = jsonResponseFuture.get();
        JSONObject document = new JSONObject(jsonResponse);
        Assertions.assertTrue(document.getInt("id") > 0);
    }

    @Test
    void processDocumentAsyncTest() throws ExecutionException, InterruptedException, IOException {
        List<String> categories = Arrays.asList("Advertising & Marketing", "Automotive");
        if (mockResponses) {
            client = org.mockito.Mockito.mock(Client.class);
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("processDocument.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            CompletableFuture<String> jsonResponseFuture = CompletableFuture.completedFuture(result);
            Mockito.when(client.processDocumentAsync("example1.jpg", categories, false, null)).thenReturn(jsonResponseFuture);
        }
        CompletableFuture<String> jsonResponseFuture = client.processDocumentAsync("example1.jpg", categories, false, null);
        String jsonResponse  = jsonResponseFuture.get();
        JSONObject document = new JSONObject(jsonResponse);
        Assertions.assertTrue(document.getInt("id") > 0);
    }

    @Test
    void updateDocumentAsyncTest() throws ExecutionException, InterruptedException, IOException {
        String documentId = "31727276";
        JSONObject parameters = new JSONObject();
        String notes = "Note updated";
        parameters.put("notes", notes);
        if (mockResponses) {
            client = org.mockito.Mockito.mock(Client.class);
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("updateDocument.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            CompletableFuture<String> jsonResponseFuture = CompletableFuture.completedFuture(result);
            Mockito.when(client.updateDocumentAsync(documentId, parameters)).thenReturn(jsonResponseFuture);
        }
        CompletableFuture<String> jsonResponseFuture = client.updateDocumentAsync(documentId, parameters);
        JSONObject documentJson = new JSONObject(jsonResponseFuture.get());
        Assertions.assertEquals(documentJson.getString("notes"), notes);
    }

    @Test
    void deleteDocumentAsyncTest() throws ExecutionException, InterruptedException, IOException {
        if (mockResponses) {
            client = org.mockito.Mockito.mock(Client.class);
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("deleteDocument.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            CompletableFuture<String> jsonResponseFuture = CompletableFuture.completedFuture(result);
            Mockito.when(client.deleteDocumentAsync("37769185")).thenReturn(jsonResponseFuture);
        }
        CompletableFuture<String> jsonResponseFuture = client.deleteDocumentAsync("37769185");
        String jsonResponse  = jsonResponseFuture.get();
        Assertions.assertFalse(jsonResponse.isEmpty());
    }

    @Test
    void processDocumentUrlAsyncTest() throws ExecutionException, InterruptedException, IOException {
        if (mockResponses) {
            client = org.mockito.Mockito.mock(Client.class);
            InputStream fileStream = ClassLoader.getSystemResourceAsStream("processDocumentUrl.json");
            assert fileStream != null;
            String result = new String(fileStream.readAllBytes());
            CompletableFuture<String> jsonResponseFuture = CompletableFuture.completedFuture(result);
            Mockito.when(client.processDocumentUrlAsync("https://bloximages.chicago2.vip.townnews.com/dailylocal.com/content/tncms/assets/v3/editorial/d/77/d7745b82-1c1f-11e9-b3bb-032816e700e0/5c43798f9d036.image.jpg", null, null, false, 1, false, null, null)).thenReturn(jsonResponseFuture);
        }
        CompletableFuture<String> jsonResponseFuture = client.processDocumentUrlAsync("https://bloximages.chicago2.vip.townnews.com/dailylocal.com/content/tncms/assets/v3/editorial/d/77/d7745b82-1c1f-11e9-b3bb-032816e700e0/5c43798f9d036.image.jpg", null, null, false, 1, false, null, null);
        String jsonResponse  = jsonResponseFuture.get();
        JSONObject document = new JSONObject(jsonResponse);
        Assertions.assertTrue(document.getInt("id") > 0);
    }

}
