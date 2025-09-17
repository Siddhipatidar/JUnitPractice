package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(LoggingExtension.class)
public class SampleTest {

    @Test
    void testOne() {
        System.out.println("Running Test One");
    }

    @Test
    void testTwo() {
        System.out.println("Running Test Two");
    }

}
