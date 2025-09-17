package org.example;

import BasicOperations.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class StudentTest{
    @Test
    void testStudentProperties() {
        Student student = new Student();

        assertAll("Student Properties",
                () -> assertEquals("John", student.name),
                () -> assertEquals(20, student.age),
                () -> assertTrue(student.marks > 80)
        );
    }
    @Test
    void testTimeout() {
        // This takes 1 second
        assertTimeout(Duration.ofMillis(500), () -> {
            Thread.sleep(200);
        });
    }

    @Test
    void testTimeoutPreemptively() {
        // This takes 1 second
        assertTimeoutPreemptively(Duration.ofMillis(500), () -> {
            Thread.sleep(200);
        });
    }

//    @Test
//    @Timeout(value = 2) // default unit = seconds
//    void testWithTimeout() throws InterruptedException {
//        Thread.sleep(3000); // sleeps 3s -> fails
//    }

    @Test
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    void testWithTimeoutMillis() throws InterruptedException {
        Thread.sleep(300); // 300 ms < 500 ms -> passes
    }


}
