package org.example;

import StubbingPractice.ExternalService;
import StubbingPractice.MyApp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MyAppTest {
    @Test
    void testRetryLogic() {
        ExternalService mockService = mock(ExternalService.class);

        // First call throws exception, second call returns success
        when(mockService.fetchData())
                .thenThrow(new RuntimeException("Temporary failure"))
                .thenReturn("Success on retry");

        MyApp app = new MyApp(mockService);

        assertEquals("Success on retry", app.getDataWithRetry());
    }

    @Test
    void testChainedWithRepeatedLastValue() {
        List<String> mockedList = mock(List.class);

        when(mockedList.size())
                .thenReturn(1)
                .thenReturn(2)
                .thenReturn(3);

        // First call -> 1
        assertEquals(1, mockedList.size());

        // Second call -> 2
        assertEquals(2, mockedList.size());

        // Third call -> 3
        assertEquals(3, mockedList.size());

        // Fourth call -> still 3 (last value keeps repeating)
        assertEquals(3, mockedList.size());
    }

}
