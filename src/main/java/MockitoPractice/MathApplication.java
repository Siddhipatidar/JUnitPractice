package MockitoPractice;

public class MathApplication {
    private CalculatorService calculatorService;

    public MathApplication(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    public int addNumbers(int a, int b) {
        return calculatorService.Add(a, b);
    }
}

