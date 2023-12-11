package veryfi;

import java.util.Arrays;
import java.util.List;

/**
 * header constants for HttpRequests.
 */
public final class Constants {
    private Constants() {
        // private constructor
    }
    /**
     * header for HttpRequest
     */
    public static final String ACCEPT = "Accept";
    /**
     * header for HttpRequest
     */
    public static final String USER_AGENT = "User-Agent";
    /**
     * header for HttpRequest
     */
    public static final String USER_AGENT_JAVA = "Java Veryfi-Java/1.0.10";
    /**
     * header for HttpRequest
     */
    public static final String APPLICATION_JSON = "application/json";
    /**
     * header for HttpRequest
     */
    public static final String CONTENT_TYPE = "Content-Type";
    /**
     * header for HttpRequest
     */
    public static final String CLIENT_ID = "Client-Id";
    /**
     * header for HttpRequest
     */
    public static final String AUTHORIZATION = "Authorization";
    /**
     * header for HttpRequest
     */
    public static final String FORM_URL_ENCODED = "application/x-www-form-urlencoded";
    /**
     * header for HttpRequest
     */
    public static final String X_VERYFI_REQUEST_TIMESTAMP = "X-Veryfi-Request-Timestamp";
    /**
     * header for HttpRequest
     */
    public static final String X_VERYFI_REQUEST_SIGNATURE = "X-Veryfi-Request-Signature";
    /**
     * header for HttpRequest
     */
    public static final String TIMESTAMP = "timestamp";
    /**
     * header for HttpRequest
     */
    public static final String SHA256 = "HmacSHA256";
    /**
     * header for HttpRequest
     */
    public static final String FILE_NAME = "file_name";
    /**
     * header for HttpRequest
     */
    public static final String FILE_DATA = "file_data";
    /**
     * header for HttpRequest
     */
    public static final String CATEGORIES = "categories";
    /**
     * header for HttpRequest
     */
    public static final String AUTO_DELETE = "auto_delete";
    /**
     * header for HttpRequest
     */
    public static final String BOOST_MODE = "boost_mode";
    /**
     * header for HttpRequest
     */
    public static final String EXTERNAL_ID = "external_id";
    /**
     * header for HttpRequest
     */
    public static final String FILE_URL = "file_url";
    /**
     * header for HttpRequest
     */
    public static final String FILE_URLS = "file_urls";
    /**
     * header for HttpRequest
     */
    public static final String MAX_PAGES_TO_PROCESS = "max_pages_to_process";

    /**
     * default list of categories.
     */
    public static final List<String> LIST_CATEGORIES = Arrays.asList(
            "Advertising & Marketing",
            "Automotive",
            "Bank Charges & Fees",
            "Legal & Professional Services",
            "Insurance",
            "Meals & Entertainment",
            "Office Supplies & Software",
            "Taxes & Licenses",
            "Travel",
            "Rent & Lease",
            "Repairs & Maintenance",
            "Payroll",
            "Utilities",
            "Job Supplies",
            "Grocery");
}
