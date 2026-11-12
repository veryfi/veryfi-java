import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import veryfi.VeryfiClientFactory;
import veryfi.services.ClientImpl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Flow;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.clearInvocations;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Checks that the {@code thinking} request parameter reaches the API body.
 */
class ThinkingTests {
    private static final String DOCUMENT_URL = "https://cdn.example.com/receipt.jpg";

    HttpClient httpClient;
    ClientImpl client;
    List<String> categories = Collections.singletonList("Job Supplies");
    static final String[] MODES = {"light", "deep"};

    @BeforeEach
    @SuppressWarnings("unchecked")
    void setup() throws IOException, InterruptedException {
        httpClient = mock(HttpClient.class);
        HttpResponse<String> httpResponse = mock(HttpResponse.class);
        when(httpClient.send(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any())).thenReturn(httpResponse);
        when(httpResponse.statusCode()).thenReturn(200);
        when(httpResponse.body()).thenReturn("{}");
        client = (ClientImpl) VeryfiClientFactory.createClient("id", "secret", "user", "key", 8, httpClient);
    }

    @Test
    void processDocumentSendsThinking() throws IOException, InterruptedException {
        for (String mode : MODES) {
            clearInvocations(httpClient);
            JSONObject parameters = new JSONObject().put("thinking", mode);
            client.processDocument(FileHelper.getFilePath("documents/receipt.jpeg"), categories, false, parameters);
            Assertions.assertEquals(mode, sentBody().getString("thinking"));
        }
    }

    @Test
    void processDocumentBase64SendsThinking() throws IOException, InterruptedException {
        for (String mode : MODES) {
            clearInvocations(httpClient);
            JSONObject parameters = new JSONObject().put("thinking", mode);
            client.processDocument("receipt.jpeg", FileHelper.getFileData("documents/receipt.jpeg"), categories, false, parameters);
            Assertions.assertEquals(mode, sentBody().getString("thinking"));
        }
    }

    @Test
    void processDocumentUrlSendsThinking() throws IOException, InterruptedException {
        for (String mode : MODES) {
            clearInvocations(httpClient);
            JSONObject parameters = new JSONObject().put("thinking", mode);
            client.processDocumentUrl(DOCUMENT_URL, null, categories, false, 1, false, null, parameters);
            Assertions.assertEquals(mode, sentBody().getString("thinking"));
        }
    }

    @Test
    void processDocumentWithoutThinkingDoesNotSendIt() throws IOException, InterruptedException {
        client.processDocument(FileHelper.getFilePath("documents/receipt.jpeg"), categories, false, null);
        Assertions.assertFalse(sentBody().has("thinking"));
    }

    @Test
    void processDocumentUrlWithoutThinkingDoesNotSendIt() throws IOException, InterruptedException {
        client.processDocumentUrl(DOCUMENT_URL, null, categories, false, 1, false, null, null);
        Assertions.assertFalse(sentBody().has("thinking"));
    }

    @Test
    void processDocumentAsyncSendsThinking() throws Exception {
        JSONObject parameters = new JSONObject().put("thinking", "deep");
        when(httpClient.sendAsync(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any()))
                .thenReturn(java.util.concurrent.CompletableFuture.completedFuture(mock(HttpResponse.class)));
        client.processDocumentAsync(FileHelper.getFilePath("documents/receipt.jpeg"), categories, false, parameters);
        ArgumentCaptor<HttpRequest> request = ArgumentCaptor.forClass(HttpRequest.class);
        verify(httpClient).sendAsync(request.capture(), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any());
        Assertions.assertEquals("deep", bodyOf(request.getValue()).getString("thinking"));
    }

    /** The JSON body of the single request sent through the mocked client. */
    private JSONObject sentBody() throws IOException, InterruptedException {
        ArgumentCaptor<HttpRequest> request = ArgumentCaptor.forClass(HttpRequest.class);
        verify(httpClient).send(request.capture(), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any());
        return bodyOf(request.getValue());
    }

    private static JSONObject bodyOf(HttpRequest request) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        request.bodyPublisher().orElseThrow().subscribe(new Flow.Subscriber<ByteBuffer>() {
            @Override public void onSubscribe(Flow.Subscription subscription) { subscription.request(Long.MAX_VALUE); }
            @Override public void onNext(ByteBuffer item) {
                byte[] bytes = new byte[item.remaining()];
                item.get(bytes);
                out.write(bytes, 0, bytes.length);
            }
            @Override public void onError(Throwable throwable) { throw new AssertionError(throwable); }
            @Override public void onComplete() { }
        });
        return new JSONObject(out.toString());
    }
}
