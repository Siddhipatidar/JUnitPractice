package BDDMokitoPractice;

public class Calculator {
    public int squareSum(int a, int b) {
        return square(a) + square(b);  // private method used
    }

    private int square(int n) {
        return n * n;
    }
    private String secretMessage = "JUnit Rocks!";
}
