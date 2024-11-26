import java.util.Scanner;
/*
Level-2 Task2 : Password Strength Checker

Description: Create a program that checks the
strength of a password. Prompt the user to input a
password and analyze its strength based on
certain criteria, such as length, presence of
uppercase letters, lowercase letters, numbers,
and special characters. Provide feedback on the
password strength.

Skills: String manipulation, conditional
statements.

Cognifyz
Where Data Meets Intelligence
*/
public class PasswordStrengthChecker {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a password: ");
        String password = scanner.nextLine();
        String strength = checkPasswordStrength(password);
        System.out.println("Password strength: " + strength);
        scanner.close();
    }

    public static String checkPasswordStrength(String password) {
        int length = password.length();
        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUppercase = true;
            } else if (Character.isLowerCase(c)) {
                hasLowercase = true;
            } else if (Character.isDigit(c)) {
                hasNumber = true;
            } else if (!Character.isLetterOrDigit(c)) {
                hasSpecial = true;
            }
        }

        if (length >= 8 && hasUppercase && hasLowercase && hasNumber && hasSpecial) {
            return "Strong";
        } else if (length >= 6 && ((hasUppercase && hasLowercase) || (hasNumber && hasSpecial))) {
            return "Medium";
        } else {
            return "Weak";
        }
    }
}