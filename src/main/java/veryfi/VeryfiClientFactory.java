package veryfi;

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
     * @return the new instance.
     */
    public static Client createClient(String clientId, String clientSecret, String username, String apiKey) {
        return new ClientImpl(clientId, clientSecret, username, apiKey);
    }
}
