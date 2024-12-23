package veryfi;

import java.net.http.HttpClient;

/**
 * Veryfi credentials for API access
 */
public class Credentials {

    public final String clientId;
    public final String clientSecret;
    public final String username;
    public final String apiKey;

    /**
     * Creates an instance of {@link NetworkClient}.
     *
     * @param clientId     the {@link String} provided by Veryfi.
     * @param clientSecret the {@link String} provided by Veryfi.
     * @param username     the {@link String} provided by Veryfi.
     * @param apiKey       the {@link String} provided by Veryfi.
     */
    public Credentials(String clientId, String clientSecret, String username, String apiKey) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.username = username;
        this.apiKey = apiKey;
    }

}
