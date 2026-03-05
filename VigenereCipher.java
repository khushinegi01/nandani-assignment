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

        key = key.replaceAll("[^A-Za-z]", "").toUpperCase();

        // Step 1: repeat key based on number of letters in message
        String repeatedKey = buildRepeatedKey(message, key);

        // Step 2: reverse the repeated key
        String reversedKey = new StringBuilder(repeatedKey).reverse().toString();

        StringBuilder result = new StringBuilder();
        int keyIndex = 0;

        for (int i = 0; i < message.length(); i++) {

            char ch = message.charAt(i);

            if (Character.isLetter(ch)) {

                char messageChar = Character.toUpperCase(ch);
                char keyChar = reversedKey.charAt(keyIndex);

                int messageVal = messageChar - 'A';
                int keyVal = keyChar - 'A';

                int newVal;

                if (encryptMode) {
                    newVal = (messageVal + keyVal) % 26;
                } else {
                    newVal = (messageVal - keyVal + 26) % 26;
                }

                char resultChar = (char) (newVal + 'A');

                result.append(resultChar);
                keyIndex++;

            } else {

                result.append(ch);
            }
        }

        return result.toString();
    }

    private String buildRepeatedKey(String message, String key) {

        StringBuilder repeatedKey = new StringBuilder();
        int keyIndex = 0;

        for (int i = 0; i < message.length(); i++) {

            if (Character.isLetter(message.charAt(i))) {
                repeatedKey.append(key.charAt(keyIndex % key.length()));
                keyIndex++;
            }
        }

        return repeatedKey.toString();
    }

    private String readFile(String filename) {

        StringBuilder content = new StringBuilder();

        try {

            Scanner scanner = new Scanner(new File(filename));

            while (scanner.hasNextLine()) {

                content.append(scanner.nextLine());

                if (scanner.hasNextLine()) {
                    content.append("\n");
                }
            }

            scanner.close();

        } catch (FileNotFoundException e) {

            System.out.println("File not found: " + filename);
        }

        return content.toString();
    }

    public static void main(String[] args) {

        VigenereCipher vc = new VigenereCipher();

        System.out.println(vc.encrypt("encrypt_check.txt", "key_check.txt"));
        System.out.println(vc.decrypt("decrypt_check.txt", "key_check.txt"));
    }
}