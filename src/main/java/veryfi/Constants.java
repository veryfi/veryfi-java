package veryfi;

import java.util.Arrays;
import java.util.List;

public final class Constants {
    public static final String ACCEPT = "Accept";
    public static final String USER_AGENT = "User-Agent";
    public static final String USER_AGENT_JAVA = "Java Veryfi-Java/1.0.0";
    public static final String APPLICATION_JSON = "application/json";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String CLIENT_ID = "Client-Id";
    public static final String AUTHORIZATION = "Authorization";
    public static final String FORM_URL_ENCODED = "application/x-www-form-urlencoded";
    public static final String X_VERYFI_REQUEST_TIMESTAMP = "X-Veryfi-Request-Timestamp";
    public static final String X_VERYFI_REQUEST_SIGNATURE = "X-Veryfi-Request-Signature";
    public static final String TIMESTAMP = "timestamp";
    public static final String SHA256 = "HmacSHA256";
    public static final String FILE_NAME = "file_name";
    public static final String FILE_DATA = "file_data";
    public static final String CATEGORIES = "categories";
    public static final String AUTO_DELETE = "auto_delete";
    public static final String BOOST_MODE = "boost_mode";
    public static final String EXTERNAL_ID = "external_id";
    public static final String FILE_URL = "file_url";
    public static final String FILE_URLS = "file_urls";
    public static final String MAX_PAGES_TO_PROCESS = "max_pages_to_process";

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
