package org.example;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import CaptorAnnotationPractice.CaptorAnnotation;
import CaptorAnnotationPractice.MathRepository;
import StubbingPractice.CalculatorService;
import StubbingPractice.MathApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.*;

public class CaptorAnnotationTest {
    @Mock
    private MathRepository repository;

    @InjectMocks
    private CaptorAnnotation service;

    @Mock
    private CalculatorService calculatorService;

    @InjectMocks
    private MathApplication mathApp;

    @Captor
    private ArgumentCaptor<Integer> captor; // Captures arguments

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCaptorExample() {
        service.addNumbers(5, 7); // sum is 12

        // Capture the argument passed to saveSum()
        verify(repository).saveSum(captor.capture());

        // Assert the captured value
        assertEquals(12, captor.getValue());
    }

    @Test
    void testArgumentCaptor() {
        when(calculatorService.Add(anyInt(), anyInt())).thenReturn(10);

        mathApp.addNumbers(5, 3);

        verify(calculatorService).Add(captor.capture(), captor.capture());

        assertEquals(5, captor.getAllValues().get(0));
        assertEquals(3, captor.getAllValues().get(1));
    }
}
