<img src="https://user-images.githubusercontent.com/30125790/212157461-58bdc714-2f89-44c2-8e4d-d42bee74854e.png#gh-dark-mode-only" width="200">
<img src="https://user-images.githubusercontent.com/30125790/212157486-bfd08c5d-9337-4b78-be6f-230dc63838ba.png#gh-light-mode-only" width="200">

[![Java - version](https://img.shields.io/badge/OpenJDK-16-red)](https://openjdk.java.net/projects/jdk/16/)
[![Coverage](.github/badges/jacoco.svg)](https://github.com/veryfi/veryfi-java/actions/workflows/maven.yml)
[![Publish package to the Maven Central Repository and GitHub Packages](https://github.com/veryfi/veryfi-java/actions/workflows/maven-publish.yml/badge.svg)](https://github.com/veryfi/veryfi-java/actions/workflows/maven-publish.yml)

**veryfi** is a Java module for communicating with the [Veryfi OCR API](https://veryfi.com/api/)

## Installation

Install from [Maven](https://mvnrepository.com/), a
package manager for Java.


Install the package using Maven:
```bash
 <dependency>
     <groupId>com.veryfi</groupId>
     <artifactId>veryfi-java</artifactId>
     <version>1.0.9</version>
 </dependency>
```
Install the package using Gradle:
```bash
implementation group: 'com.veryfi', name: 'veryfi-java', version: '1.0.9'
```

## Getting Started

### Obtaining Client ID and user keys
If you don't have an account with Veryfi, please go ahead and register here: [https://hub.veryfi.com/signup/api/](https://hub.veryfi.com/signup/api/)

### Java API Client Library
The **veryfi** library can be used to communicate with Veryfi API. All available functionality is described here https://veryfi.github.io/veryfi-java/veryfi/Client.html

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

Visit https://docs.veryfi.com/ to access integration guides and usage notes in the Veryfi API Documentation Portal

If you run into any issue or need help installing or using the library, please contact support@veryfi.com.

If you found a bug in this library or would like new features added, then open an issue or pull requests against this repo!

To learn more about Veryfi visit https://www.veryfi.com/

## Tutorial


Below is an introduction to the Java SDK.


[Link to blog post →](https://www.veryfi.com/java/)
