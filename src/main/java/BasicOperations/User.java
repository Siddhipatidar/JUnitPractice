package BasicOperations;

public class User {
    private String name;
    private int age;

    // getters, setters, constructor, toString
    public User() {}
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public String getName() { return name; }
    public int getAge() { return age; }
}

