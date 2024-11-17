import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class FileEncryptDecrypt {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the file name or path: ");
        String fileName = input.nextLine().replace("\\", "/");

        System.out.print("Do you want to encrypt or decrypt the file? (e/d): ");
        String choice = input.nextLine();

        if (choice.equals("e")) {
            encryptFile(fileName);
        } else if (choice.equals("d")) {
            decryptFile(fileName);
        } else {
            System.out.println("Invalid choice.");
        }

        input.close();
    }

    public static void encryptFile(String fileName) {
        try {
            File inputFile = new File(fileName);
            File outputFile = new File("encrypted_" + inputFile.getName());

            FileInputStream inputStream = new FileInputStream(inputFile);
            FileOutputStream outputStream = new FileOutputStream(outputFile);

            int key = 5;
            int data;
            while ((data = inputStream.read()) != -1) {
                data += key;
                outputStream.write(data);
            }

            inputStream.close();
            outputStream.close();

            System.out.println("File encrypted successfully. Check the file: " + outputFile.getPath());
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void decryptFile(String fileName) {
        try {
            File inputFile = new File(fileName);
            File outputFile = new File("decrypted_" + inputFile.getName());

            FileInputStream inputStream = new FileInputStream(inputFile);
            FileOutputStream outputStream = new FileOutputStream(outputFile);

            int key = 5;
            int data;
            StringBuilder decryptedContent = new StringBuilder();
            while ((data = inputStream.read()) != -1) {
                data -= key;
                outputStream.write(data);
                decryptedContent.append((char) data);
            }

            inputStream.close();
            outputStream.close();

            System.out.println("File decrypted successfully. Check the file: " + outputFile.getPath());
            System.out.println("Decrypted content: " + decryptedContent.toString());
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}