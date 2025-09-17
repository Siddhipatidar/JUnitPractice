package org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MathUtilsTest {

    @ParameterizedTest
    @MethodSource("provideNumbers")
    void testWithMethodSource(int number, int expected) {
        assertEquals(expected, number * 2);
    }

    static Stream<Arguments> provideNumbers() {
        return Stream.of(
                Arguments.of(1, 2),
                Arguments.of(2, 4),
                Arguments.of(3, 6)
        );
    }
}
