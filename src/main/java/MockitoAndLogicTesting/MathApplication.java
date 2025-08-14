package MockitoAndLogicTesting;

public class MathApplication {
    private CalculatorService calculatorService;

    public MathApplication(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    public int addNumbers(int a, int b) {
        return calculatorService.add(a, b);
    }
}