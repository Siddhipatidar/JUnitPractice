package org.example;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import MockitoPractice.CalculatorService;
import MockitoPractice.MathApplication;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MathApplicationTest {

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
}
