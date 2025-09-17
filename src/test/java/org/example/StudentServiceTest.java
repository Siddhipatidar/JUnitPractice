package org.example;

import PracticeUsingMokito.Student;
import PracticeUsingMokito.StudentRepository;
import PracticeUsingMokito.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository repository; // mocked dependency

    @InjectMocks
    private StudentService service; // service under test with mock injected


    // ---------------- Register Student ----------------
    @Test
    void testRegisterStudentPositive() {
        Student alice = new Student(1, "Alice", 20);
        when(repository.save(any(Student.class))).thenReturn(alice);

        Student student = service.registerStudent(1, "Alice", 20);

        assertEquals("Alice", student.getName());
        verify(repository, times(1)).save(any(Student.class)); // ensure save was called
    }

    @Test
    void testRegisterStudentNegativeAge() {
        assertThrows(IllegalArgumentException.class,
                () -> service.registerStudent(2, "Bob", 16));

        verify(repository, never()).save(any()); // repo should not be called
    }

    @Test
    void testRegisterStudentNegativeName() {
        assertThrows(IllegalArgumentException.class,
                () -> service.registerStudent(3, "  ", 20));

        verify(repository, never()).save(any());
    }

    // ---------------- Get Student ----------------
    @Test
    void testGetStudentPositive() {
        Student charlie = new Student(4, "Charlie", 22);
        when(repository.findById(4)).thenReturn(charlie);

        Student student = service.getStudent(4);

        assertNotNull(student);
        assertEquals("Charlie", student.getName());
        verify(repository, times(1)).findById(4);
    }

    @Test
    void testGetStudentNegativeNotFound() {
        when(repository.findById(999)).thenReturn(null);

        Student student = service.getStudent(999);

        assertNull(student);
        verify(repository, times(1)).findById(999);
    }

    // ---------------- Get All Students ----------------
    @Test
    void testGetAllStudentsPositive() {
        List<Student> mockList = Arrays.asList(
                new Student(5, "David", 21),
                new Student(6, "Eva", 23)
        );
        when(repository.findAll()).thenReturn(mockList);

        List<Student> students = service.getAllStudents();

        assertEquals(2, students.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testGetAllStudentsNegativeEmptyList() {
        when(repository.findAll()).thenReturn(List.of());

        List<Student> students = service.getAllStudents();

        assertTrue(students.isEmpty());
        verify(repository, times(1)).findAll();
    }

    // ---------------- Remove Student ----------------
    @Test
    void testRemoveStudentPositive() {
        when(repository.deleteById(7)).thenReturn(true);

        boolean removed = service.removeStudent(7);

        assertTrue(removed);
        verify(repository, times(1)).deleteById(7);
    }

    @Test
    void testRemoveStudentNegativeNotFound() {
        when(repository.deleteById(1000)).thenReturn(false);

        boolean removed = service.removeStudent(1000);

        assertFalse(removed);
        verify(repository, times(1)).deleteById(1000);
    }

}
