import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class VigenereCipher implements Cipher {

    @Override
    public String encrypt(String messageFile, String keyFile) {
        String message = readFile(messageFile);
        String key = readFile(keyFile);

        return process(message, key, true);
    }

    @Override
    public String decrypt(String messageFile, String keyFile) {
        String message = readFile(messageFile);
        String key = readFile(keyFile);

        return process(message, key, false);
    }

    private String process(String message, String key, boolean encryptMode) {

        key = key.trim().toUpperCase();

        // Build reversed repeated key
        String builtKey = buildKey(message, key);

        String result = "";
        int keyIndex = 0;

        for (int i = 0; i < message.length(); i++) {

            char ch = message.charAt(i);

            if (Character.isLetter(ch)) {

                char upperChar = Character.toUpperCase(ch);
                char keyChar = key.charAt(keyIndex % key.length());

                int messageVal = upperChar - 'A';
                int keyVal = keyChar - 'A';

                int newVal;

                if (encryptMode) {
                    newVal = (messageVal + keyVal) % 26;
                } else {
                    newVal = (messageVal - keyVal + 26) % 26;
                }

                result += (char) (newVal + 'A');

                keyIndex++;

            } else {
                result += ch;
            }
        }

        return result;
    }

    // Build reversed repeated key
    private String buildKey(String message, String key) {

        String repeatedKey = "";
        int keyIndex = 0;

        for (int i = 0; i < message.length(); i++) {

            if (Character.isLetter(message.charAt(i))) {

                repeatedKey += key.charAt(keyIndex % key.length());
                keyIndex++;
            }
        }

        // Reverse the key
        String reversedKey = new StringBuilder(repeatedKey).reverse().toString();

        return reversedKey;
    }

    private String readFile(String filename) {

        String content = "";

        try {

            Scanner scanner = new Scanner(new File(filename));

            while (scanner.hasNextLine()) {

                content += scanner.nextLine();

                if (scanner.hasNextLine()) {
                    content += "\n";
                }
            }

            scanner.close();

        } catch (FileNotFoundException e) {

            System.out.println("File not found: " + filename);
        }

        return content;
    }

    public static void main(String[] args) {

        VigenereCipher vc = new VigenereCipher();

        System.out.println("Encrypted:");
        System.out.println(vc.encrypt("encrypt_check.txt", "key_check.txt"));

        System.out.println("\nDecrypted:");
        System.out.println(vc.decrypt("decrypt_check.txt", "key_check.txt"));
    }
}