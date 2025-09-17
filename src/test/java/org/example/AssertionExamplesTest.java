package org.example;

import BasicOperations.MathOperations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

public class AssertionExamplesTest {
    private MathOperations mathOperations = new MathOperations();

    // assertEquals
    @Test
    @BeforeEach
    void testAssertEquals() {
        System.out.println("Before Each");
        assertEquals(5, mathOperations.add(2, 3));
    }

    // assertNotEquals

    void testAssertNotEquals() {
        System.out.println("Test 1");
        assertNotEquals(5, mathOperations.add(2, 2));
    }

    // assertTrue
    @Test
    void testAssertTrue() {
        System.out.println("Test 2");
        assertTrue(mathOperations.isPositive(10));
    }

    // assertFalse
    @Test
    void testAssertFalse() {
        System.out.println("Test 3");
        assertFalse(mathOperations.isPositive(-5));
    }

    // assertNull
    @Test
    void testAssertNull() {
        System.out.println("Test 4");
        String str = null;
        assertNull(str);
    }

    // assertNotNull
    @Test
    void testAssertNotNull() {
        System.out.println("Test 5");
        assertNotNull(mathOperations.getHello());
    }

    // assertArrayEquals
    @Test
    void testAssertArrayEquals() {
        System.out.println("Test 6");
        assertArrayEquals(new int[]{1, 2, 3}, mathOperations.getArray());
    }

    // assertSame
    @Test
    void testAssertSame() {
        System.out.println("Test 7");
        String text = mathOperations.getHello();
        String reference = text;
        assertSame(text, reference);
    }

    // assertNotSame
    @Test
    void testAssertNotSame() {
        System.out.println("Test 8");
        String text1 = mathOperations.getHello();
        String text2 = mathOperations.getHelloString();
        assertNotSame(text1, text2);
    }

    // assertThrows
    @Test
    void testAssertThrows() {
        System.out.println("Test 9");
        assertThrows(ArithmeticException.class, mathOperations::throwArithmeticException);
    }

    @Test
    void testIterableEquals() {
        List<String> expected = List.of("A", "B", "C");
        List<String> actual   = List.of("A", "B", "C");
   System.out.println("Print");
        assertIterableEquals(expected, actual, "Lists are not equal!");
    }

    @Test
    void testAssumeTrue() {
        assumeTrue(System.getProperty("os.name").contains("Windows"),
                "Test skipped because not running on Windows");

        System.out.println("Running on Windows ✅");
    }

    @Test
    void testAssumeFalse() {
        assumeFalse(System.getenv("CI") != null,
                "Test skipped because running in CI environment");

        System.out.println("Running locally ✅");
    }

    @Test
    void testAssumingThat() {
        int x = 10;

        assumingThat(x > 5, () -> {
            System.out.println("Condition true, running this part ✅");
        });

        // This always runs, regardless of assumption
        System.out.println("This runs anyway 🚀");
    }

    @Test
    void testLinesMatch() {
        List<String> expected = List.of("Hello", "World", ".*JUnit.*");
        List<String> actual   = List.of("Hello", "World", "Welcome to JUnit 5");

        System.out.println("Assert Line Matching");
        assertLinesMatch(expected, actual,"Assert Line Matching test is failed");
    }

}
