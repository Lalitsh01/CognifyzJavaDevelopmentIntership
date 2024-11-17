import java.util.Scanner;

/*
* Task 3: Student Grade Calculator
*
* Description: create a program that
calculates and displays the average grade of
a student. Prompt the user to enter the
number of grades to be entered, and then
input each grade. Calculate the average and
display it to the user.
*
Skills: Loops, arrays, basic arithmetic
operations.
*
* */

public class StudentGradeCalculator {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        System.out.print("Enter the number of grades to be entered: ");
        int numGrades = scn.nextInt();

        int sum = 0;
        for (int i = 1; i <= numGrades; i++) {
            System.out.print("Enter grade " + i + ": ");
            int grade = scn.nextInt();
            sum += grade;
        }

        double average = (double) sum / numGrades;
        System.out.println("The average grade is: " + average);
        scn.close();
    }
}