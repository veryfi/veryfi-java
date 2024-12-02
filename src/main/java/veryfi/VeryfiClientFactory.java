package veryfi;

import veryfi.services.ClientImpl;

import java.net.http.HttpClient;

/**
 * Factory for creating instances of {@link Client}.
 */
public final class VeryfiClientFactory {

    private VeryfiClientFactory() {
        /* Private Constructor */
    }

    /**
     * Creates an instance of {@link Client}.
     * @param clientId the {@link String} provided by Veryfi.
     * @param clientSecret the {@link String} provided by Veryfi.
     * @param username the {@link String} provided by Veryfi.
     * @param apiKey the {@link String} provided by Veryfi.
     * @return the new instance with default api version (8).
     */
    public static Client createClient(String clientId, String clientSecret, String username, String apiKey) {
        int apiVersion = 8;
        return new ClientImpl(clientId, clientSecret, username, apiKey, apiVersion);
    }

    /**
     * Creates an instance of {@link Client}.
     * @param clientId     the {@link String} provided by Veryfi.
     * @param clientSecret the {@link String} provided by Veryfi.
     * @param username     the {@link String} provided by Veryfi.
     * @param apiKey       the {@link String} provided by Veryfi.
     * @param apiVersion   the {link int} api version to use Veryfi.
     * @return             the new instance with custom api version.
     */
    public static Client createClient(String clientId, String clientSecret, String username, String apiKey, int apiVersion) {
        return new ClientImpl(clientId, clientSecret, username, apiKey, apiVersion);
    }

    /**
     * Creates an instance of {@link Client}.
     * @param clientId     the {@link String} provided by Veryfi.
     * @param clientSecret the {@link String} provided by Veryfi.
     * @param username     the {@link String} provided by Veryfi.
     * @param apiKey       the {@link String} provided by Veryfi.
     * @param apiVersion   the {link int} api version to use Veryfi.
     * @param httpClient   {@link HttpClient} for the Veryfi API
     * @return             the new instance with custom api version.
     */
    public static Client createClient(String clientId, String clientSecret, String username, String apiKey, int apiVersion, HttpClient httpClient) {
        return new ClientImpl(clientId, clientSecret, username, apiKey, apiVersion, httpClient);
    }
}
