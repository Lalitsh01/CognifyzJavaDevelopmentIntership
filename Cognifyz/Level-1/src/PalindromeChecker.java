import java.util.Scanner;

/*
* Task 2: Palindrome Checker
*
Description: Implement a program that checks
whether a given word or phrase is a palindrome. A
palindrome is a word or phrase that reads the
same forwards and backward, ignoring spaces and
punctuation.
*
Skills: String manipulation, loops, conditional
statements.
*
* */

public class PalindromeChecker {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();
        if (isPalindrome(input)) {
            System.out.println(input + " - is a palindrome");
        } else {
            System.out.println(input + " - is not a palindrome");
        }
        scanner.close();
    }

    public static boolean isPalindrome(String str) {
        str = str.toLowerCase();
        String reverse = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            reverse += str.charAt(i);
        }
        return str.equals(reverse);
    }

}