package org.example;

import BasicOperations.FileUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileUtilsTest {
    @Test
    void testTempDir(@TempDir Path tempDir) throws IOException, InterruptedException {
        //FileUtils fileUtils = new FileUtils();
        Path file = tempDir.resolve("test.txt");
        Files.writeString(file, "Hello");
        String content = Files.readString(file);
        assertEquals("Hello", content);
        System.out.println("Temp file path: " + file);
        Thread.sleep(5000);
    }

//    @Test
//    void testWholeFile(@TempDir Path tempDir) throws IOException {
//        Path file = tempDir.resolve("largeFile.txt");
//
//        // Generate expected content
//        StringBuilder expected = new StringBuilder();
//        IntStream.rangeClosed(1, 10000).forEach(i -> expected.append("Line ").append(i).append("\n"));
//
//        // Write to file
//        Files.writeString(file, expected.toString());
//
//        // Read entire file content
//        String actual = Files.readString(file);
//
//        // Compare whole content
//        assertEquals(expected.toString(), actual);
//    }
}
