package PracticeUsingMokito;

import java.util.List;

public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public Student registerStudent(int id, String name, int age) {
        if (age < 18) {
            throw new IllegalArgumentException("Student must be at least 18 years old");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Student name cannot be empty");
        }
        Student student = new Student(id, name, age);
        return repository.save(student);
    }

    public Student getStudent(int id) {
        return repository.findById(id);
    }

    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    public boolean removeStudent(int id) {
        return repository.deleteById(id);
    }

}
