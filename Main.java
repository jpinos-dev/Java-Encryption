import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This is my custom encryption program.
 *
 * @author Jordy Pinos
 * @version 1.0
 */
class Main {

  /// The private key used for encryption and decryption.
  private static final String PRIVATE_KEY = "SOL";

  /// The total number of ASCII characters considered for the encryption.
  private static final int ASCII_TOTAL = 95;

  /// The starting ASCII value for the encryption range.
  private static final int ASCII_START = 32;

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    while (true) {
      printMenu();
      int option = Integer.parseInt(reader.readLine());
      handleMenuOptions(option, reader);
    }
  }

  public static String encrypt(String message) {
    StringBuilder encryptedMessage = new StringBuilder();

    for (int i = 0; i < message.length(); i++) {
      char currentChar = message.charAt(i);
      char keyChar = PRIVATE_KEY.charAt(i % PRIVATE_KEY.length());

      int currentCharValue = currentChar - ASCII_START;
      int keyCharValue = keyChar - ASCII_START;
      int encryptedCharValue = (currentCharValue + keyCharValue) % ASCII_TOTAL;

      encryptedMessage.append((char) (encryptedCharValue + ASCII_START));
    }

    return encryptedMessage.toString();
  }

  public static String decrypt(String encryptedMessage) {
    StringBuilder uncryptedMessage = new StringBuilder();

    for (int i = 0; i < encryptedMessage.length(); i++) {
      char currentChar = encryptedMessage.charAt(i);
      char keyChar = PRIVATE_KEY.charAt(i % PRIVATE_KEY.length());

      int currentCharValue = currentChar - ASCII_START;
      int keyCharValue = keyChar - ASCII_START;
      int decryptedCharValue = (currentCharValue - keyCharValue + ASCII_TOTAL) % ASCII_TOTAL;

      uncryptedMessage.append((char) (decryptedCharValue + ASCII_START));
    }

    return uncryptedMessage.toString();
  }

  private static void printMenu() {
    System.out.println("1. Encrypt a message");
    System.out.println("2. Decrypt a message");
    System.out.println("3. Exit");
    System.out.print("Choose an option: ");
  }

  private static void handleMenuOptions(int option, BufferedReader reader) throws IOException {
    switch (option) {
      case 1 -> {
        String message;
        System.out.print("Enter the message to encrypt: ");
        message = reader.readLine();
        String encryptedMessage = encrypt(message);

        System.out.println("Encrypted Message: " + encryptedMessage);

        System.out.print("Do you want to decrypt it? (yes/no): ");
        String response = reader.readLine();
        if (response.equalsIgnoreCase("yes")) {
          String decryptedMessage = decrypt(encryptedMessage);
          System.out.println("Decrypted Message: " + decryptedMessage);
        }

        System.out.println("-------------------------------");
      }
      case 2 -> {
        String encryptedMessage;
        System.out.print("Enter the message to decrypt: ");
        encryptedMessage = reader.readLine();
        String decryptedMessage = decrypt(encryptedMessage);

        System.out.println("Decrypted Message: " + decryptedMessage);
        System.out.println("-------------------------------");
      }
      case 3 -> {
        System.out.println("Exiting the program.");
        System.exit(0);
      }
    }
  }
}

