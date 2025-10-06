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
        System.out.print("Seleccione una opcion: ");
        option = Integer.parseInt(scanner.nextLine());

        handleMenuOptions(option, scanner, encryption);
      }
    }
  }

  private static void printMenu() {
    System.out.println("""
        |---------------------------------|
        | Opcion 1: Cifrar                |
        | Opcion 2: Descifrar             |
        | Opcion 3: Salir                 |
        |---------------------------------|""");
  }

  private static void handleMenuOptions(int option, Scanner scanner, Encryption encryption) {
    switch (option) {
      case 1 -> {
        System.out.print("Escriba la ruta del archivo a cifrar: ");
        String readPath = scanner.nextLine();

        System.out.print("Escriba la ruta del archivo donde se va a guardar el cifrado: ");
        String writePath = scanner.nextLine();

        System.out.println(readPath);
        encryption.encryptFile(Path.of(readPath), writePath);
      }
      case 2 -> {
        System.out.print("Escriba la ruta del archivo a descifrar: ");
        String readPath = scanner.nextLine();

        System.out.print("Escriba la ruta del archivo donde se va a guardar el descifrado: ");
        String writePath = scanner.nextLine();

        encryption.decryptFile(Path.of(readPath), writePath);
      }
      case 3 -> {
        System.out.println("Saliendo...");
        System.exit(0);
      }
    }

    System.out.println("\n");
  }
}
