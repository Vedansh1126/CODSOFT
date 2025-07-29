import java.util.Scanner;
import java.util.InputMismatchException;

public class GRADE_CALCULATOR_TASK_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int TOT_SUB = 0;
        while(true){
            try {
                System.out.print("ENTER YOUR TOTAL NO OF SUBJECTS: ");
                TOT_SUB = scanner.nextInt();
                if (TOT_SUB <= 0) {
                    System.out.println("Please enter a positive integer for subjects.");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter an integer value.");
                scanner.next();
            }
        }

        float[] arr = new float[TOT_SUB];
        float sum = 0;
        for (int i = 0; i < TOT_SUB; i++) {
            while(true){
                System.out.print("ENTER MARKS OF YOUR SUBJECT " +(i+1)+ " (out of 100): ");
                try {
                    float marks = scanner.nextFloat();
                    if (marks < 0 || marks > 100) {
                        System.out.println("Marks should be between 0 and 100.");
                    } else {
                        arr[i] = marks;
                        sum += marks;
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please enter a numeric value.");
                    scanner.next();
                }
            }
        }
        scanner.close();

        float avg_perc = (sum / (TOT_SUB * 100)) * 100;

        System.out.println("\nGRADE SYSTEM:");
        System.out.println("90-100 = A+");
        System.out.println("76-89  = A");
        System.out.println("61-75  = B");
        System.out.println("51-60  = C");
        System.out.println("41-50  = D");
        System.out.println("33-40  = E");
        System.out.println("Below 33 = Fail");

        System.out.printf("TOTAL MARKS SCORED = %.2f\n", sum);
        System.out.printf("PERCENTAGE = %.2f\n", avg_perc);

        if (avg_perc < 33) System.out.println("GRADE = Fail");
        else if (avg_perc <= 40) System.out.println("GRADE = E");
        else if (avg_perc <= 50) System.out.println("GRADE = D");
        else if (avg_perc <= 60) System.out.println("GRADE = C");
        else if (avg_perc <= 75) System.out.println("GRADE = B");
        else if (avg_perc <= 89) System.out.println("GRADE = A");
        else System.out.println("GRADE = A+");
    }
}
