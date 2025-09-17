package BasicOperations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtils {
    // Writes content to a file
    public void writeToFile(Path file, String content) throws IOException {
        Files.writeString(file, content);
    }

    // Reads content from a file
    public String readFromFile(Path file) throws IOException {
        return Files.readString(file);
    }
}
