package SpyAnnotationPractice;

public class SpyAnnotation {
    public int multiply(int a, int b) {
        return a * b;
    }

    public int square(int a) {
        return multiply(a, a); // calls another method
    }
}
