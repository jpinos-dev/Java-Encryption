# Java Encryption and Decryption Example

This repository contains a simple Java program that demonstrates how to encrypt and decrypt text using a simple character shifting based on a private key.

## How It Works
The program uses a private key to shift characters in the input text for encryption and reverses the process for decryption. Each character in the input text is shifted by the ASCII value of the corresponding character in the private key.

## Code Explanation
- The `encrypt` method takes a plaintext string and a private key, and returns the encrypted text.
- The `decrypt` method takes the encrypted text and the same private key, and returns the original plaintext.
- The `main` method shows a menu for the user to choose between encryption and decryption.

## Usage
1. Compile the Java program using a Java compiler.
2. Run the program.
3. Follow the prompts to enter the text and private key for encryption or decryption.
4. The program will display the encrypted or decrypted text based on your choice.

## Why?
This example is useful for understanding basic concepts of encryption and decryption in Java. It can be a starting point for more complex encryption algorithms and techniques. Feel free to modify and enhance the code as needed for your specific use cases!
