public static String redact(String text, String[] redactableWords) {

    String result = "";
    String currentWord = "";

    for (int i = 0; i < text.length(); i++) {

        char ch = text.charAt(i);

        if ((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z')) {

            currentWord += ch;

        } else {

            if (currentWord.length() > 0) {

                boolean redact = false;

                String lowerWord = currentWord.toLowerCase();

                for (int j = 0; j < redactableWords.length; j++) {

                    if (lowerWord.equals(redactableWords[j].toLowerCase())) {
                        redact = true;
                        break;
                    }
                }

                if (redact) {

                    for (int k = 0; k < currentWord.length(); k++) {
                        result += "#";
                    }

                } else {

                    result += currentWord;

                }

                currentWord = "";
            }

            result += ch;
        }
    }

    if (currentWord.length() > 0) {

        boolean redact = false;

        String lowerWord = currentWord.toLowerCase();

        for (int j = 0; j < redactableWords.length; j++) {

            if (lowerWord.equals(redactableWords[j].toLowerCase())) {
                redact = true;
                break;
            }
        }

        if (redact) {

            for (int k = 0; k < currentWord.length(); k++) {
                result += "#";
            }

        } else {

            result += currentWord;

        }
    }

    return result;
       
}