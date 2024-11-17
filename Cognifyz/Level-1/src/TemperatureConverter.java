import java.util.Scanner;
/*
* Task 1: Task: Temperature Converter
*
Description: Create a program that converts
temperatures between Celsius and
Fahrenheit. Prompt the user to enter a
temperature value and the unit of
measurement, and then perform the
conversion. Display the converted
temperature.
*
Skills: Basic input/output operations,
arithmetic operations.
*
* */

public class TemperatureConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter temperature value: ");
        double temp = scanner.nextDouble();
        System.out.print("Enter unit (C for Celsius, F for Fahrenheit): ");
        char unit = scanner.next().charAt(0);

        if (unit == 'C' || unit == 'c') {
            double fahrenheit = (temp * 9 / 5) + 32;
            System.out.println("Temperature in Fahrenheit: " + fahrenheit);
        } else if (unit == 'F' || unit == 'f') {
            double celsius = (temp - 32) * 5 / 9;
            System.out.println("Temperature in Celsius: " + celsius);
        } else {
            System.out.println("Invalid unit.");
        }

        scanner.close();
    }
}