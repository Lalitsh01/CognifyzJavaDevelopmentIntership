import java.util.Random;
import java.util.Scanner;

/*
* Task 4: Random Password Generator
*
* Description: Build a program that generates a
random password for the user. Prompt the user to
enter the desired length of the password and
specify whether it should include numbers,
lowercase letters, uppercase letters, and special
characters. Generate the password accordingly
and display it to the user.
*
Skills: Random number generation, string
manipulation, user input.
* */

public class RandomPasswordGenerator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the desired length of the password: ");
        int length = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Include numbers? (yes/no): ");
        boolean includeNumbers = scanner.nextLine().equalsIgnoreCase("yes");

        System.out.print("Include lowercase letters? (yes/no): ");
        boolean includeLowercase = scanner.nextLine().equalsIgnoreCase("yes");

        System.out.print("Include uppercase letters? (yes/no): ");
        boolean includeUppercase = scanner.nextLine().equalsIgnoreCase("yes");

        System.out.print("Include special characters? (yes/no): ");
        boolean includeSpecial = scanner.nextLine().equalsIgnoreCase("yes");

        String password = generatePassword(length, includeNumbers, includeLowercase, includeUppercase, includeSpecial);
        System.out.println("Generated password: " + password);

        scanner.close();
    }

    public static String generatePassword(int length, boolean includeNumbers, boolean includeLowercase, boolean includeUppercase, boolean includeSpecial) {
        String numbers = "0123456789";
        String lowercase = "abcdefghijklmnopqrstuvwxyz";
        String uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String special = "!@#$%^&*()-_=+<>?";

        String allowedChars = "";
        if (includeNumbers) allowedChars += numbers;
        if (includeLowercase) allowedChars += lowercase;
        if (includeUppercase) allowedChars += uppercase;
        if (includeSpecial) allowedChars += special;

        Random random = new Random();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(allowedChars.length());
            password.append(allowedChars.charAt(index));
        }

        return password.toString();
    }
}