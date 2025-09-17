package org.example;

import Practice.Student1;
import Practice.Student1Repository;
import Practice.Student1Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Student1ServiceTest {
    private Student1Repository repository;
    private Student1Service service;

    @BeforeEach
    void setUp() {
        repository = new Student1Repository();
        service = new Student1Service(repository);
    }

    // ---------------- Register Student ----------------
    @Test
    void testRegisterStudentPositive() {
        Student1 student = service.registerStudent(1, "Alice", 20);
        assertEquals("Alice", student.getName());
        assertEquals(20, student.getAge());
        assertNotNull(repository.findById(1));
    }

    @Test
    void testRegisterStudentNegativeAge() {
        assertThrows(IllegalArgumentException.class,
                () -> service.registerStudent(2, "Bob", 16),
                "Should throw exception if age < 18");
    }

    @Test
    void testRegisterStudentNegativeName() {
        assertThrows(IllegalArgumentException.class,
                () -> service.registerStudent(3, "  ", 20),
                "Should throw exception if name is blank");
    }

    // ---------------- Get Student ----------------
    @Test
    void testGetStudentPositive() {
        service.registerStudent(4, "Charlie", 22);
        Student1 student = service.getStudent(4);
        assertNotNull(student);
        assertEquals("Charlie", student.getName());
    }

    @Test
    void testGetStudentNegativeNotFound() {
        Student1 student = service.getStudent(999);
        assertNull(student, "Student with ID 999 should not exist");
    }

    // ---------------- Get All Students ----------------
    @Test
    void testGetAllStudentsPositive() {
        service.registerStudent(5, "David", 21);
        service.registerStudent(6, "Eva", 23);

        List<Student1> students = service.getAllStudents();
        assertEquals(2, students.size());
    }

    @Test
    void testGetAllStudentsNegativeEmptyList() {
        List<Student1> students = service.getAllStudents();
        assertTrue(students.isEmpty(), "Initially, no students should exist");
    }

    // ---------------- Remove Student ----------------
    @Test
    void testRemoveStudentPositive() {
        service.registerStudent(7, "Frank", 24);
        boolean removed = service.removeStudent(7);
        assertTrue(removed);
        assertNull(service.getStudent(7));
    }

    @Test
    void testRemoveStudentNegativeNotFound() {
        boolean removed = service.removeStudent(1000);
        assertFalse(removed);
    }
}
