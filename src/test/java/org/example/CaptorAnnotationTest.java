package org.example;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import CaptorAnnotationPractice.CaptorAnnotation;
import CaptorAnnotationPractice.MathRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.*;

public class CaptorAnnotationTest {
    @Mock
    private MathRepository repository;

    @InjectMocks
    private CaptorAnnotation service;

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

}
