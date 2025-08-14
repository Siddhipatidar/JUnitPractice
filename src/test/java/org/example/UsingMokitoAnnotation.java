package org.example;

import MockitoAndLogicTesting.CalculatorService;
import MockitoAndLogicTesting.MathApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UsingMokitoAnnotation {
    @Mock
    private CalculatorService calculatorService; // Mock dependency

    @InjectMocks
    private MathApplication mathApplication; // Inject mock into this

    @Test
    void testAddWithMockAnnotations() {
        System.out.println("UsingMockitoAnnotation");
        // Define mock behavior
        when(calculatorService.add(10, 20)).thenReturn(30);

        // Call method
        int result = mathApplication.addNumbers(10, 20);

        // Assert
        assertEquals(30, result);

        // Verify interaction
        verify(calculatorService).add(10, 20);
    }
}
