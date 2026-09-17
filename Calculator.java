import java.util.Scanner;

class InvalidOperatorException extends Exception {
    public InvalidOperatorException(String message) {
        super(message);
    }
}

public class Calculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Enter first number: ");
            double num1 = Double.parseDouble(sc.nextLine());

            System.out.print("Enter second number: ");
            double num2 = Double.parseDouble(sc.nextLine());

            System.out.print("Enter operator (+, -, *, /): ");
            String op = sc.nextLine().trim();

            double result = calculate(num1, num2, op);
            System.out.println("Result = " + result);
        } catch (NumberFormatException e) {
            System.out.println("Invalid numeric input: please enter valid numbers.");
        } catch (ArithmeticException e) {
            System.out.println("Arithmetic error: " + e.getMessage());
        } catch (InvalidOperatorException e) {
            System.out.println("Invalid operator: " + e.getMessage());
        } finally {
            sc.close();
        }
    }

    static double calculate(double a, double b, String op) throws InvalidOperatorException {
        switch (op) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/":
                if (b == 0)
                    throw new ArithmeticException("Division by zero is not allowed");
                return a / b;
            default:
                throw new InvalidOperatorException("'" + op + "' is not a supported operator");
        }
    }
}