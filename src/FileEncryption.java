import java.nio.file.Path;
import java.util.Scanner;

/**
 * This program encrypts and decrypts files using a simple AES encryption.
 *
 * @author Jordy Pinos
 * @version 1.0
 */
class FileEncryption {

  public static void main(String[] args) {
    Encryption encryption = new Encryption();
    int option = 0;

    try (Scanner scanner = new Scanner(System.in)) {
      while (true) {
        printMenu();
        System.out.print("Select an option: ");
        option = Integer.parseInt(scanner.nextLine());

        handleMenuOptions(option, scanner, encryption);
      }
    }
  }

  private static void printMenu() {
    System.out.println("""
        |---------------------------------|
        | Option 1: Encrypt               |
        | Option 2: Decryp                |
        | Option 3: Quit                  |
        |---------------------------------|""");
  }

  private static void handleMenuOptions(int option, Scanner scanner, Encryption encryption) {
    switch (option) {
      case 1 -> {
        System.out.print("Write the path of the file to encrypt: ");
        String readPath = scanner.nextLine();

        System.out.print("Write the path of the file where the encrypted content will be saved: ");
        String writePath = scanner.nextLine();

        encryption.encryptFile(Path.of(readPath), writePath);
      }
      case 2 -> {
        System.out.print("Write the path of the file to decrypt: ");
        String readPath = scanner.nextLine();

        System.out.print("Write the path of the file where the decrypted content will be saved: ");
        String writePath = scanner.nextLine();

        encryption.decryptFile(Path.of(readPath), writePath);
      }
      case 3 -> {
        System.out.println("Exiting the program.");
        System.exit(0);
      }
    }

    System.out.println("\n");
  }
}
