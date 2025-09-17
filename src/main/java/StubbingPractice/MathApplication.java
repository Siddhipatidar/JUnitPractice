package StubbingPractice;

public class MathApplication {
    private CalculatorService calculatorService;

    public MathApplication(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    public int addNumbers(int a, int b) {
        return calculatorService.Add(a, b);
    }
    public int divideNumbers(int a,int b){ return calculatorService.divide(a,b); }
}

