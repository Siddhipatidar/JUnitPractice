package Practice;

import java.util.List;

public class Student1Service {
    private final Student1Repository repository;

    public Student1Service(Student1Repository repository) {
        this.repository = repository;
    }

    public Student1 registerStudent(int id, String name, int age) {
        if (age < 18) {
            throw new IllegalArgumentException("Student must be at least 18 years old");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Student name cannot be empty");
        }
        Student1 student = new Student1(id, name, age);
        return repository.save(student);
    }

    public Student1 getStudent(int id) {
        return repository.findById(id);
    }

    public List<Student1> getAllStudents() {
        return repository.findAll();
    }

    public boolean removeStudent(int id) {
        return repository.deleteById(id);
    }
}
