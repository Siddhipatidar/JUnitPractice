package Practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student1Repository {

    private final Map<Integer, Student1> database = new HashMap<>();

    // Save student
    public Student1 save(Student1 student) {
        database.put(student.getId(), student);
        return student;
    }

    // Find by ID
    public Student1 findById(int id) {
        return database.get(id);
    }

    // Find all
    public List<Student1> findAll() {
        return new ArrayList<>(database.values());
    }

    // Delete by ID
    public boolean deleteById(int id) {
        return database.remove(id) != null;
    }

}
