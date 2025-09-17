package org.example;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import SpyAnnotationPractice.SpyAnnotation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SpyAnnotationTest {
    @Spy
    private SpyAnnotation mathUtils;

//    @BeforeEach
//    void setup() {
//        MockitoAnnotations.openMocks(this);
//    }


    @Test
    void testSpyCallsRealMethods() {
        // Real method will be called
        assertEquals(9, mathUtils.square(3));
    }

    @Test
    void testSpyWithStubbedMethod() {
        // Stub multiply method
        when(mathUtils.multiply(3, 3)).thenReturn(100);

        // Now square will call multiply, but multiply is mocked
        assertEquals(100, mathUtils.square(3));
    }
}
