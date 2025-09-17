package org.example;

import BasicOperations.Calculator;
import BasicOperations.Day;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

import java.io.InputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    private Calculator calculator;

    // Runs only once before all test methods
    @BeforeAll
    static void setupAll() {
        System.out.println("🔥 Starting Calculator Tests...");
    }

    // Runs before each test method
    @BeforeEach
    void setup() {
        calculator = new Calculator();
        System.out.println("➡️  New test started");
    }

    // A normal test with display name
    @Test
    @Tag("fast")
    @DisplayName("✅ Test addition should return correct sum")
    void testAddition() {
        System.out.println("Addition Operation");
        assertEquals(5, calculator.add(2, 3));
    }

    // Disabled test (will not run)
    @Test
    @Disabled("🚧 Not implemented yet")
    void testDivisionByZero() {
        assertThrows(ArithmeticException.class, () -> calculator.divide(10, 0));
    }

    // Another test
    @Test
    @DisplayName("✅ Test division should return correct quotient")
    void testDivision() {
        assertEquals(2, calculator.divide(4, 2));
    }

    @Test
    void testDivisionByZeroNegative() {
        System.out.println("Enter Exception Block");
        assertThrows(ArithmeticException.class, () -> calculator.divide(10, 0)); // invalid case
    }



    // Runs after each test method
    @AfterEach
    void tearDown() {
        System.out.println("✔️  Test finished");
    }

    // Runs only once after all test methods
    @AfterAll
    static void tearDownAll() {
        System.out.println("🏁 All Calculator Tests completed");
    }

    @Nested
    @DisplayName("Addition Tests")
    class AddTests {
        @Test
        void testAddPositive() {
            Assertions.assertEquals(5, calculator.add(2, 3));
        }

        @Test
        void testAddNegative() {
            Assertions.assertEquals(-1, calculator.add(-2, 1));
        }
    }

    @Tag("math")
    @Test
    void testMultiply() {
        System.out.println("Multiply Operation");
        assertEquals(6, calculator.multiply(2, 3));
    }

   // @RepeatedTest(5)
    @Test
    @Timeout(value = 500,unit = TimeUnit.MILLISECONDS)
    void testTimeout() throws InterruptedException{
        System.out.println("Testing");
        Thread.sleep(400);
    }

    // -----------------------
    // Positive test using ValueSource
    // -----------------------
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4})
    void testMultiplyByZero(int num) {
        assertEquals(0, calculator.multiply(num, 0));
    }

    // -----------------------
    // Negative test using ValueSource
    // -----------------------
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, -5, -10})
    void testMultiplyByZero_Negative(int num) {
        assertNotEquals(10, calculator.multiply(num, 0));  // deliberately wrong value
        assertNotEquals(-1, calculator.multiply(num, 0));  // another wrong value
    }

    // -----------------------
    // Test multiple parameters using CsvSource
    // -----------------------
    @ParameterizedTest
    @CsvSource({
            "1, 2, 2",
            "3, 5, 15",
            "0, 10, 0",
            "-2, 4, -8",
            "7, 0, 0"
    })
    void testMultiplyWithCsv(int a, int b, int expected) {
        assertEquals(expected, calculator.multiply(a, b),
                () -> a + " * " + b + " should be " + expected);
    }

    // -----------------------
    // Test multiple parameters using Json file
    // -----------------------

    // ✅ DTO defined inside the test class
    static class MultiplyTestData {
        public int a;
        public int b;
        public int expected;

        // Default constructor needed by Jackson
        public MultiplyTestData() {}

        public MultiplyTestData(int a, int b, int expected) {
            this.a = a;
            this.b = b;
            this.expected = expected;
        }
    }

    static Stream<MultiplyTestData> multiplyDataProvider() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        // Load JSON from resources folder
        InputStream inputStream = CalculatorTest.class
                .getClassLoader()
                .getResourceAsStream("multiply.json");

        List<MultiplyTestData> dataList =
                mapper.readValue(inputStream, new TypeReference<>() {});

        return dataList.stream();
    }

    @ParameterizedTest
    @MethodSource("multiplyDataProvider")
    void testMultiplyWithJson(MultiplyTestData data) {
        assertEquals(data.expected,
                calculator.multiply(data.a, data.b),
                () -> data.a + " * " + data.b + " should be " + data.expected);
    }


    // -----------------------
    // Negative test using CsvSource
    // -----------------------
    @ParameterizedTest
    @CsvSource({
            "1, 0, 1",   // deliberately wrong expected
            "-5, 0, 5"   // deliberately wrong expected
    })
    void testMultiplyWithCsv_Negative(int a, int b, int expected) {
        assertNotEquals(expected, calculator.multiply(a, b),
                () -> "Multiplying " + a + " by " + b + " should NOT be " + expected);
    }


    @ParameterizedTest
    @CsvSource({
            "2147483647, 2",
            "-2147483648, 2",
            "1073741824, 3"
    })
    void testMultiplicationWithOverflowNegative(int a, int b) {
        System.out.println("Running overflow test for: " + a + " * " + b);
        assertThrows(ArithmeticException.class, () -> calculator.multiply(a, b));
        System.out.println("✅ Exception caught for: " + a + " * " + b);
    }

    @ParameterizedTest
    @EnumSource(Day.class)
    //@NullSource
    void testEnum(Day day){
        assertNotNull(day);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/test-data.csv", numLinesToSkip = 1)
    void testAdditionFromCsv(int a, int b, int expected) {
        Calculator calculator = new Calculator();      // create logic object
        int result = calculator.add(a, b);             // call logic method
        assertEquals(expected, result);                // verify result
    }

}
