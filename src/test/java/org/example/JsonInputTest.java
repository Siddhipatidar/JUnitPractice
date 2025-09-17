package org.example;

import BasicOperations.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonInputTest {

    @Test
    void testUsersFromFile() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        // Load file from resources folder
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("doc.json");
        assertNotNull(inputStream, "doc.json file not found in resources!");

        // Convert JSON → List<User>
        List<User> users = mapper.readValue(inputStream, new TypeReference<List<User>>() {});

        // ✅ Assertions
      //  assertEquals(9, users.size(), "Should contain 9 users");

        assertEquals("Siddhi", users.get(0).getName());
        assertEquals(23, users.get(0).getAge());

        assertEquals("Neha", users.get(8).getName());
        assertEquals(26, users.get(8).getAge());

        // Example: check all ages are >= 24
        //assertTrue(users.stream().allMatch(u -> u.getAge() >= 24));
    }

}
