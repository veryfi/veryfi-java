package veryfi;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

/**
 * Utility class for Base64 encoding operations.
 * <p>
 * This class provides helper methods for converting files to Base64 encoded strings
 * that can be used with the Veryfi API. It handles both file paths and File objects,
 * and automatically generates the appropriate data URI prefix based on file extension.
 * </p>
 */
public class Base64Helper {

    /**
     * Converts a file to Base64 encoded string using the file path.
     * <p>
     * This method reads the file from the specified path, encodes it to Base64,
     * and prepends the appropriate data URI prefix based on the file extension.
     * </p>
     * 
     * @param filePath The path to the file to encode
     * @return Base64 encoded string with data URI prefix
     * @throws IOException if the file cannot be read
     */
    public static String getBase64FileContent(String filePath) throws IOException {
        String fileName = filePath.replaceAll("^.*[/\\\\]", "");
        File file = new File(filePath);
        return getBase64FileContent(file);
    }

    /**
     * Converts a file to Base64 encoded string using a File object.
     * <p>
     * This method reads the file content, encodes it to Base64, and prepends
     * the appropriate data URI prefix based on the file extension.
     * </p>
     * 
     * @param file The file to encode
     * @return Base64 encoded string with data URI prefix
     * @throws IOException if the file cannot be read
     */
    public static String getBase64FileContent(File file) throws IOException {
        String fileData = "";
        byte[] fileContent = Files.readAllBytes(file.toPath());
        fileData = Base64.getEncoder().encodeToString(fileContent);
        return getUriPrefix(file) + fileData;
    }

    /**
     * Generates the data URI prefix for a file based on its extension.
     * <p>
     * This method determines the MIME type based on the file extension and
     * returns the appropriate data URI prefix. If no extension is found,
     * it defaults to "image/png".
     * </p>
     * 
     * @param file The file to generate the prefix for
     * @return Data URI prefix string (e.g., "data:image/jpeg;base64,")
     */
    public static String getUriPrefix(File file) {
        String extension = getFileExtension(file);
        if (extension.isEmpty())
            extension = "png";
        return "data:image/" + extension + ";base64,";
    }

    /**
     * Extracts the file extension from a File object.
     * <p>
     * This method parses the filename to extract the extension (everything after
     * the last dot). If no extension is found or the file has no name, an empty
     * string is returned.
     * </p>
     * 
     * @param file The file to extract the extension from
     * @return The file extension (without the dot) or empty string if no extension
     */
    protected static String getFileExtension(File file) {
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex + 1);
        } else {
            return "";
        }
    }

}
