import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import java.util.Base64;

/**
 * This class provides methods to encrypt and decrypt files using DES
 * encryption.
 *
 * @author Jordy Pinos
 * @version 1.0
 */
class Encryption {

  SecretKey secretKey;
  Cipher aesCipher;

  /**
   * Constructor that initializes the encryption system by generating a secret key
   * and setting up the DES cipher.
   */
  public Encryption() {
    try {
      // Generate a secret key for DES encryption
      KeyGenerator keygen = KeyGenerator.getInstance("DES");
      secretKey = keygen.generateKey();

      // Initialize the cipher for encryption
      aesCipher = Cipher.getInstance("DES");

    } catch (NoSuchAlgorithmException e) {
      System.out.println("No such algorithm.");
      e.printStackTrace();
      return;
    } catch (Exception e) {
      System.out.println("Error initializing cipher.");
      e.printStackTrace();
      return;
    }
  }

  /**
   * Encrypts the content of a file and writes the encrypted content to another
   * file.
   *
   * @param readPath  The path of the file to be encrypted.
   * @param writePath The path where the encrypted file will be saved.
   */
  public void encryptFile(Path readPath, String writePath) {

    // 1. Read the file to be encrypted
    byte[] fileContent = readFile(readPath);

    if (fileContent == null)
      return;

    // 2. Encrypt the file content
    byte[] encodedContent;
    try {
      aesCipher.init(Cipher.ENCRYPT_MODE, secretKey);
      encodedContent = aesCipher.doFinal(fileContent);
    } catch (Exception e) {
      System.out.println("Error encrypting file.");
      e.printStackTrace();
      return;
    }

    // 3. Encode the encrypted content to a string format (Base64)
    String encryptedContent = Base64.getEncoder().encodeToString(encodedContent);

    // 4. Check if the output file exists, if not create it
    if (!createNewFile(writePath)) {
      return;
    }

    // 5. Write the encrypted lines to the output file
    writeFile(writePath, encryptedContent);
  }

  /**
   * Decrypts the content of a file and writes the decrypted content to another
   * file.
   *
   * @param readPath  The path of the file to be decrypted.
   * @param writePath The path where the decrypted file will be saved.
   */
  public void decryptFile(Path readPath, String writePath) {

    // 1. Read the file to be decrypted
    byte[] fileContent = readFile(readPath);

    if (fileContent == null)
      return;

    // 2. Decode the Base64 encoded content
    byte[] decodedContent = Base64.getDecoder().decode(fileContent);

    // 3. Decrypt the file content
    byte[] decryptedContent;
    try {
      aesCipher.init(Cipher.DECRYPT_MODE, secretKey);
      decryptedContent = aesCipher.doFinal(decodedContent);
    } catch (Exception e) {
      System.out.println("Error decrypting file.");
      e.printStackTrace();
      return;
    }

    // 4. Check if the output file exists, if not create it
    if (!createNewFile(writePath)) {
      return;
    }

    // 5. Write the decrypted lines to the output file
    writeFile(writePath, new String(decryptedContent));
  }

  /**
   * Reads the content of a file and returns it as a byte array.
   * 
   * @param path The path to the file.
   * @return The content of the file as a byte array, or null if an error occurs.
   */
  private static byte[] readFile(Path path) {
    try {
      return Files.readAllBytes(path);
    } catch (IOException e) {
      System.out.println("Error reading file.");
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Creates a new file at the specified path if it does not already exist.
   * 
   * @param path The path where the file should be created.
   * @return true if the file was created or already exists, false if an error
   *         occurred.
   */
  private static boolean createNewFile(String path) {
    File outputFile = new File(path);
    try {
      if (outputFile.createNewFile()) {
        System.out.println("Output file created: " + outputFile.getName());
      } else {
        System.out.println("Output file already exists.");
      }
      return true;
    } catch (IOException e) {
      System.out.println("Error creating output file.");
      e.printStackTrace();
      return false;
    }
  }

  /**
   * Writes the given content to a file at the specified path.
   * 
   * @param path    The path to the file.
   * @param content The content to be written to the file.
   */
  private static void writeFile(String path, String content) {
    try (FileWriter writer = new FileWriter(path)) {
      writer.write(content);
      System.out.println("File written successfully.");
    } catch (IOException e) {
      System.out.println("Error writing to output file.");
      e.printStackTrace();
    }
  }
}
