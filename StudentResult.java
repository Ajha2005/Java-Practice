import java.util.Scanner;

class InvalidMarksException extends Exception {
    public InvalidMarksException(String message) {
        super(message);
    }
}

public class StudentResult {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numSubjects = 3;
        int[] marks = new int[numSubjects];
        boolean allValid = true;

        for (int i = 0; i < numSubjects; i++) {
            try {
                System.out.print("Enter marks for subject " + (i + 1) + ": ");
                int m = Integer.parseInt(sc.nextLine());
                validateMarks(m); // throws if out of range
                marks[i] = m;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
                allValid = false;
            } catch (InvalidMarksException e) {
                System.out.println("Invalid marks: " + e.getMessage());
                allValid = false;
            }
        }

        if (allValid) {
            int total = 0;
            for (int m : marks) total += m;
            double percentage = (total * 100.0) / (numSubjects * 100);
            System.out.println("Total = " + total);
            System.out.println("Percentage = " + percentage + "%");
            System.out.println("Grade = " + getGrade(percentage));
        } else {
            System.out.println("Result not calculated – please re-enter all marks correctly.");
        }

        sc.close();
    }

    static void validateMarks(int m) throws InvalidMarksException {
        if (m < 0 || m > 100) {
            throw new InvalidMarksException("Marks must be between 0 and 100, entered: " + m);
        }
    }

    static String getGrade(double percentage) {
        if (percentage >= 90) return "A";
        else if (percentage >= 75) return "B";
        else if (percentage >= 60) return "C";
        else if (percentage >= 40) return "D";
         else return "F";
}
}