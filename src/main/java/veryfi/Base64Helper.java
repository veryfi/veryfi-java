package veryfi;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

public class Base64Helper {

    public static String getBase64FileContent(String filePath) throws IOException {
        String fileName = filePath.replaceAll("^.*[/\\\\]", "");
        File file = new File(filePath);
        return getBase64FileContent(file);
    }

    public static String getBase64FileContent(File file) throws IOException {
        String fileData = "";
        byte[] fileContent = Files.readAllBytes(file.toPath());
        fileData = Base64.getEncoder().encodeToString(fileContent);
        return getUriPrefix(file) + fileData;
    }

    public static String getUriPrefix(File file) {
        String extension = getFileExtension(file);
        if (extension.isEmpty())
            extension = "png";
        return "data:image/" + extension + ";base64,";
    }

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
