####[Docs](https://veryfi.github.io/veryfi-java/veryfi/package-summary.html)
![Veryfi Logo](https://cdn.veryfi.com/logos/veryfi-logo-wide-github.png)

[![Java - version](https://img.shields.io/badge/OpenJDK-16-red)](https://openjdk.java.net/projects/jdk/16/)
[![Coverage](.github/badges/jacoco.svg)](https://github.com/veryfi/veryfi-java/actions/workflows/maven.yml)


**veryfi** is a Java module for communicating with the [Veryfi OCR API](https://veryfi.com/api/)

## Installation

Install from [Maven](https://mvnrepository.com/), a
package manager for Java.


Install the package from Maven:
```bash
 <dependency>
     <groupId>com.veryfi</groupId>
     <artifactId>veryfi-java</artifactId>
     <version>1.0.0</version>
 </dependency>
```
You may need to run the above commands with `sudo`.

## Getting Started

### Obtaining Client ID and user keys
If you don't have an account with Veryfi, please go ahead and register here: [https://hub.veryfi.com/signup/api/](https://hub.veryfi.com/signup/api/)

### Java API Client Library
The **veryfi** library can be used to communicate with Veryfi API. All available functionality is described here https://veryfi.github.io/veryfi-java/reference/veryfi/#client

Below is the sample script using **veryfi** to OCR and extract data from a document:

```java
import veryfi.*;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String clientId = "your_client_id";
        String clientSecret = "your_client_secret";
        String username = "your_username";
        String apiKey = "your_password";
        Client client = VeryfiClientFactory.createClient(clientId, clientSecret, username, apiKey);
        List<String> categories = Arrays.asList("Advertising & Marketing", "Automotive");
        String jsonResponse = client.processDocument("example1.jpg", categories, false, null);
    }
}
``` 

Update a document
```java
import veryfi.*;
import org.json.JSONObject;

public class Main {
    public static void main(String[] args) {
        String clientId = "your_client_id";
        String clientSecret = "your_client_secret";
        String username = "your_username";
        String apiKey = "your_password";
        Client client = VeryfiClientFactory.createClient(clientId, clientSecret, username, apiKey);
        String documentId = "your_document_id";
        JSONObject parameters = new JSONObject();
        parameters.put("category", "Meals & Entertainment");
        parameters.put("total", 11.23);
        String jsonResponse = client.updateDocument(documentId, parameters);
    }
}
```


## Need help?
If you run into any issue or need help installing or using the library, please contact support@veryfi.com.

If you found a bug in this library or would like new features added, then open an issue or pull requests against this repo!

To learn more about Veryfi visit https://www.veryfi.com/

