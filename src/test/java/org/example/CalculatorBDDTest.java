package org.example;

import BDDMokitoPractice.Calculator;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorBDDTest {

    @Test
    void testSquareSum() {
        Calculator calc = new Calculator();
        assertEquals(25, calc.squareSum(3, 4)); // indirectly tests private method
    }

    @Test
    void testPrivateMethodSquare() throws Exception{
        Calculator calculator=new Calculator();

        // Access private method using reflection
        Method method= Calculator.class.getDeclaredMethod("square",int.class);
        method.setAccessible(true);  // bypass private
        int result = (int) method.invoke(calculator,5);
        assertEquals(25,result);
    }

    @Test
    void testPrivateFieldSecretMessage() throws Exception {
        Calculator calculator = new Calculator();

        // Access private field using reflection
        Field field = Calculator.class.getDeclaredField("secretMessage");
        field.setAccessible(true); // bypass private

        String value = (String) field.get(calculator);

        assertEquals("JUnit Rocks!", value);

        // Update private field
        field.set(calculator, "Changed via Reflection");
        assertEquals("Changed via Reflection", field.get(calculator));
    }
}
