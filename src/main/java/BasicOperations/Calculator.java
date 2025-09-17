package BasicOperations;

public class Calculator {

    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int divide(int a, int b) {
//        if (b == 0) {
//            throw new ArithmeticException("Cannot divide by zero");
//        }
        return a / b;
    }

    public int multiply(int a, int b) {
        long result = (long) a * b;  // cast to long to detect overflow
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
            throw new ArithmeticException("Integer overflow");
        }
        return (int) result;
    }

}
