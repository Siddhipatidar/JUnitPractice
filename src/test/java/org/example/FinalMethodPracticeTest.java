package org.example;

import BDDMokitoPractice.FinalMethodPractice;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class FinalMethodPracticeTest {

    @Mock
    FinalMethodPractice paymentService;   // mocking final class + final method

    @Test
    void testFinalMethodMocking() {
        // given
        given(paymentService.processPayment(100)).willReturn("Mocked Payment Success");

        // when
        String result = paymentService.processPayment(100);

        // then
        assertEquals("Mocked Payment Success", result);

        // and also verify with BDD style
        then(paymentService).should().processPayment(100);
    }

}
