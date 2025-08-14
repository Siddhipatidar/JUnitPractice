package org.example;

import BasicOperations.MathOperations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    @Test
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
}
