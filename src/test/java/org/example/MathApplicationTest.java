package org.example;

import static org.mockito.AdditionalMatchers.gt;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import StubbingPractice.CalculatorService;
import StubbingPractice.EmailService;
import StubbingPractice.LoggerService;
import StubbingPractice.MathApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MathApplicationTest {

    @BeforeEach
    public void test1() {
        System.out.println("Test 1 BeforeEach");
    }
    @Test
    @DisplayName("This is custom name.")
    public void test3() {
        System.out.println("Test 3");
    }

    @Disabled
    @Test
    public void test4() {
        System.out.println("Test 4");
    }
    @Test
    public void test5() {
        System.out.println("Test 5");
    }


//thenReturn
    @Test
    void testAddNumbers() {
        System.out.println("MathApplicationTest");
        // Create mock object of CalculatorService
        CalculatorService mockService = Mockito.mock(CalculatorService.class);

        // Define behavior of mock
        when(mockService.Add(10, 20)).thenReturn(30);

        // Inject mock into MathApplication
        MathApplication mathApp = new MathApplication(mockService);

        // Test
        assertEquals(30, mathApp.addNumbers(10, 20));

        // Verify that mockService.add was called with correct parameters
        verify(mockService).Add(10, 20);
    }

    //thenThrow
    @Test
    public void testDivideNumbersThenThrow(){
        CalculatorService mockService = Mockito.mock(CalculatorService.class);

        // Define behavior of mock
        when(mockService.divide(10,0)).thenThrow(new ArithmeticException("Division by zero"));

        // Inject mock into MathApplication
        MathApplication mathApp = new MathApplication(mockService);

        assertThrows(ArithmeticException.class,()->mathApp.divideNumbers(10,0));
        verify(mockService).divide(10,0);
    }

    // Multiple Returns
    @Test
    public void testAddNumbersMultipleReturns() {
        CalculatorService mockService = Mockito.mock(CalculatorService.class);
        MathApplication mathApp = new MathApplication(mockService);

        when(mockService.Add(5, 5))
                .thenReturn(10)
                .thenReturn(20);

        assertEquals(10, mathApp.addNumbers(5, 5)); // first call
        assertEquals(20, mathApp.addNumbers(5, 5)); // second call
    }

    // thenAnswer
    @Test
    public void testAddNumbersThenAnswer() {
        CalculatorService mockService = Mockito.mock(CalculatorService.class);
        MathApplication mathApp = new MathApplication(mockService);

        when(mockService.Add(Mockito.anyInt(), Mockito.anyInt()))
                .thenAnswer(invocation -> {
                    int a = invocation.getArgument(0);
                    int b = invocation.getArgument(1);
                    return a + b; // real addition
                });

        assertEquals(7, mathApp.addNumbers(3, 4));
        assertEquals(15, mathApp.addNumbers(10, 5));
    }

    //doReturn
    @Test
    public void testSpyWithDoReturn() {
        CalculatorService realService = new CalculatorService();
        CalculatorService spyService = Mockito.spy(realService);
        MathApplication mathApp = new MathApplication(spyService);

        // Stubbing using doReturn
        doReturn(100).when(spyService).Add(2, 3);

        assertEquals(100, mathApp.addNumbers(2, 3)); // stubbed
        assertEquals(9, mathApp.addNumbers(4, 5));   // real method executed
    }

    // doAnswer
    @Test
    void testDoAnswer() {
        CalculatorService mockService = mock(CalculatorService.class);

        // Custom behavior: return sum + 10
        doAnswer(invocation -> {
            int a = invocation.getArgument(0);
            int b = invocation.getArgument(1);
            return a + b + 10;
        }).when(mockService).Add(anyInt(), anyInt());

        assertEquals(15, mockService.Add(2, 3)); // 2+3+10 = 15
        assertEquals(25, mockService.Add(5, 10)); // 5+10+10 = 25
    }

    // doNothing
    @Test
    void testDoNothing() {
        LoggerService logger = mock(LoggerService.class);

        // Explicitly tell Mockito: do nothing when log() is called
        doNothing().when(logger).log(anyString());

        // Call the method
        logger.log("This will not actually log");

        // Verify that the method was called
        verify(logger).log("This will not actually log");
    }

    //doThrow
    @Test
    void testDoThrow() {
        EmailService emailService = mock(EmailService.class);

        // Make void method throw exception
        doThrow(new RuntimeException("Invalid email"))
                .when(emailService).sendEmail(eq("wrong@mail.com"), anyString());

        // Calling with correct email -> works fine
        emailService.sendEmail("test@mail.com", "Hello");

        // Calling with wrong email -> throws exception
        try {
            emailService.sendEmail("wrong@mail.com", "Hello");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage()); // Output: Invalid email
        }
    }

    @Test
    void testVerifyExamples() {
        // Create a mock
        CalculatorService mockService = mock(CalculatorService.class);
        MathApplication mathApp = new MathApplication(mockService);

        verifyNoInteractions(mockService);


        // Call some methods
        mockService.Add(2, 3);
        mockService.Add(2, 3);
        mockService.divide( 4, 2);

        // ✅ verify(mock).methodName()
        // Checks if method was called at least once
        verify(mockService).divide(4, 2);

        // ✅ verify(mock, times(n))
        // Checks if method was called exactly n times
        verify(mockService, times(2)).Add(2, 3);

        // ✅ verify(mock, never())
        // Verifies method was never called
        verify(mockService, never()).Add(10, 20);

        // ✅ verify(mock, atLeast(n))
        // Method should be called at least n times
        verify(mockService, atLeast(1)).Add(2, 3);

        // ✅ verify(mock, atMost(n))
        // Method should be called at most n times
        verify(mockService, atMost(2)).Add(2, 3);
        mockService.divide(6, 3);
        verify(mockService).divide(6,3);
        verifyNoMoreInteractions(mockService);

    }

    @Test
    void testAny() {
        CalculatorService mockService = mock(CalculatorService.class);
        MathApplication app = new MathApplication(mockService);

        // Stub: respond to ANY two integers
        when(mockService.Add(anyInt(), anyInt())).thenReturn(100);

        assertEquals(100, app.addNumbers(5, 10));
        assertEquals(100, app.addNumbers(999, -111)); // also works
    }

    @Test
    void testEq() {
        CalculatorService mockService = mock(CalculatorService.class);
        MathApplication app = new MathApplication(mockService);

        // Stub: exact first argument (2), any second argument
        when(mockService.Add(eq(2), anyInt())).thenReturn(50);

        assertEquals(50, app.addNumbers(2, 3));
        assertEquals(50, app.addNumbers(2, 999));
        assertEquals(0, app.addNumbers(5, 3)); // default 0 because not stubbed
    }

    @Test
    void testArgThat() {
        CalculatorService mockService = mock(CalculatorService.class);
        MathApplication app = new MathApplication(mockService);

        // Custom matcher: sum only if first argument is positive
        when(mockService.Add(gt(0), anyInt())).thenReturn(200);

        assertEquals(200, app.addNumbers(5, 3));   // 5 > 0 matches
        assertEquals(0, app.addNumbers(-1, 3));    // -1 doesn't match, default 0
    }


}
