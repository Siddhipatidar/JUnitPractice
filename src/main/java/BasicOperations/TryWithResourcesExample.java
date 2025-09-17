package BasicOperations;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class TryWithResourcesExample {
    public static void main(String[] args) {
        // Using try-with-resources without catch or finally
        try (BufferedReader reader = new BufferedReader(new FileReader("test.txt"))) {
            String line = reader.readLine();
            System.out.println(line);
        } catch (IOException e) {
            System.out.println("IOException occurred: " + e.getMessage());
        }

        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Enter your name:");
            String name = sc.nextLine();
            System.out.println("Hello " + name);
}    }
}
