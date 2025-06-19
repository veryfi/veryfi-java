import org.junit.jupiter.api.Assertions;
import veryfi.Base64Helper;
import java.io.File;

public class FileHelper {

    protected static String getFilePath(String filename) {
        return ClassLoader.getSystemResource(filename).getPath();
    }

    protected static String getFileName(String filename) {
        return getFilePath(filename).replaceAll("^.*[/\\\\]", "");
    }

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
