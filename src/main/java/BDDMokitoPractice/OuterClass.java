package BDDMokitoPractice;

public class OuterClass {
    private class InnerClass {
        private String greet(String name) {
            return "Hello, " + name;
        }
    }
}
