public class Redactor {

    public static String redact(String text, String[] redactableWords) {
        String result = "";
        String currentWord = "";

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            // If letter, build the word
            if ((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z')) {
                currentWord += ch;
            } else {
                // Word ended, check if it should be redacted
                if (currentWord.length() > 0) {
                    if (shouldRedact(currentWord, redactableWords)) {
                        result += hashWord(currentWord.length());
                    } else {
                        result += currentWord;
                    }
                    currentWord = "";
                }
                // Add non-letter character unchanged
                result += ch;
            }
        }

        // Check last word (if text ends with a letter)
        if (currentWord.length() > 0) {
            if (shouldRedact(currentWord, redactableWords)) {
                result += hashWord(currentWord.length());
            } else {
                result += currentWord;
            }
        }

        return result;
    }

    private static boolean shouldRedact(String word, String[] redactableWords) {
        String lowerWord = toLowerCase(word);

        for (int i = 0; i < redactableWords.length; i++) {
            if (lowerWord.equals(toLowerCase(redactableWords[i]))) {
                return true;
            }
        }
        return false;
    }

    private static String hashWord(int length) {
        String hashes = "";
        for (int i = 0; i < length; i++) {
            hashes += "#";
        }
        return hashes;
    }

    private static String toLowerCase(String str) {
        String lower = "";
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch >= 'A' && ch <= 'Z') {
                lower += (char)(ch + 32);
            } else {
                lower += ch;
            }
        }
        return lower;
    }
    public static void main(String[] args) {
        String text = "The quick brown fox jumps over the lazy DOG!";
        String[] words = {"Fox", "jumps", "dog"};

        String result = Redactor.redact(text, words);
        System.out.println(result);
    }
}


