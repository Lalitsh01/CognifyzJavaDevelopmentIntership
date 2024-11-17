import java.util.Scanner;
/*
*To check the strength of a password, follow these steps:
Input the Password: Prompt the user to enter a password.
Initialize Criteria Flags: Set up boolean flags to track the presence of uppercase letters, lowercase letters, numbers, and special characters.
Analyze the Password:
Loop through each character in the password.
Update the flags based on the character type (uppercase, lowercase, digit, special).
Evaluate Strength:
Check the length of the password.
Determine the strength based on the combination of length and the presence of different character types.
Provide Feedback: Output the strength of the password as "Strong", "Medium", or "Weak".
*
*
*
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