package org.example;

import MockitoAndLogicTesting.CalculatorService;
import MockitoAndLogicTesting.MathApplication;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CalculatorAndMathAppTest {

    // 1️⃣ TEST REAL LOGIC (No Mocks)
    @Test
    void testCalculatorServiceLogic() {
        System.out.println("MockitoAndLogicTesting");
        CalculatorService calc = new CalculatorService(); // real object
        assertEquals(30, calc.add(10, 20), "10 + 20 should be 30");
        assertEquals(0, calc.add(-5, 5), "-5 + 5 should be 0");
    }

    // 2️⃣ TEST WITH MOCKITO (Interaction Test)
    @Test
    void testMathApplicationWithMock() {
        System.out.println("MockitoAndLogicTesting");
        // Create a mock of CalculatorService
        CalculatorService mockService = Mockito.mock(CalculatorService.class);

        // Define behavior for specific inputs
        when(mockService.add(10, 20)).thenReturn(30);

        // Inject mock into MathApplication
        MathApplication app = new MathApplication(mockService);

        // Test using mocked behavior
        assertEquals(30, app.addNumbers(10, 20));

        // Verify the interaction
        verify(mockService).add(10, 20);
    }
}
