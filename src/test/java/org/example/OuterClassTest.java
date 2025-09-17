package org.example;

import BDDMokitoPractice.OuterClass;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OuterClassTest {

    @Test
    void testPrivateInnerClassMethod() throws Exception {
        // 1. Get the private inner class type
        Class<?> innerClass = Class.forName("BDDMokitoPractice.OuterClass$InnerClass");

        // 2. Get the constructor of the private inner class (needs OuterClass instance)
        Constructor<?> constructor = innerClass.getDeclaredConstructor(OuterClass.class);
        constructor.setAccessible(true);

        // 3. Create an instance of OuterClass
        OuterClass outer = new OuterClass();

        // 4. Create an instance of the private inner class
        Object innerObject = constructor.newInstance(outer);

        // 5. Access the private method "greet"
        Method greetMethod = innerClass.getDeclaredMethod("greet", String.class);
        greetMethod.setAccessible(true);

        // 6. Invoke the method on innerObject
        String result = (String) greetMethod.invoke(innerObject, "Siddhi");

        // 7. Assert result
        assertEquals("Hello, Siddhi", result);
    }

}
