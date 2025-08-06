import org.junit.jupiter.api.Assertions;
import veryfi.Base64Helper;
import java.io.File;

/**
 * Utility class for file operations in tests.
 * <p>
 * This class provides helper methods for test classes to work with files,
 * including getting file paths, extracting file names, and converting files
 * to Base64 encoded strings for API testing.
 * </p>
 */
public class FileHelper {

    /**
     * Gets the absolute file path for a resource file.
     * <p>
     * This method uses the system class loader to locate a resource file
     * and returns its absolute path on the filesystem.
     * </p>
     * 
     * @param filename The name of the resource file to locate
     * @return The absolute path to the file
     */
    protected static String getFilePath(String filename) {
        return ClassLoader.getSystemResource(filename).getPath();
    }

    /**
     * Extracts the filename from a full file path.
     * <p>
     * This method removes the directory portion of a file path and returns
     * only the filename with extension.
     * </p>
     * 
     * @param filename The full file path
     * @return The filename without the directory path
     */
    protected static String getFileName(String filename) {
        return getFilePath(filename).replaceAll("^.*[/\\\\]", "");
    }

    /**
     * Converts a resource file to Base64 encoded string.
     * <p>
     * This method reads a resource file, converts it to Base64 encoding,
     * and returns the encoded string. If the conversion fails, the test
     * will fail with an assertion error.
     * </p>
     * 
     * @param filename The name of the resource file to convert
     * @return Base64 encoded string representation of the file
     */
    protected static String getFileData(String filename) {
        File file = new File(getFilePath(filename));
        String fileData = "";
        try {
            fileData = Base64Helper.getBase64FileContent(file);
        } catch (Exception e) {
            Assertions.fail(e);
        }
        return fileData;
    }

}
