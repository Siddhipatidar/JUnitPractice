package CaptorAnnotationPractice;

// CalculatorService.java
public class CaptorAnnotation {
    private final MathRepository repository;

    public CaptorAnnotation(MathRepository repository) {
        this.repository = repository;
    }

    public int addNumbers(int a, int b) {
        int sum = a + b;
        repository.saveSum(sum); // saving to DB or log
        return sum;
    }
}

