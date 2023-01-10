import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String text = scanner.nextLine();

        String regex = "password:?\\s*";
        Pattern passwordDeclarationPattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher passwordDeclarationMatcher = passwordDeclarationPattern.matcher(text);

        Pattern passwordCharactersPattern = Pattern.compile("[a-zA-Z\\d]");
        int matchCount = 0;
        ArrayList<Integer> passwordsStartIndices = new ArrayList<>();

        while (passwordDeclarationMatcher.find()) {
            if (!passwordsStartIndices.isEmpty()) {
                int matchStartIndex = passwordDeclarationMatcher.start();
                int lastPasswordStartIndex = passwordsStartIndices.get(passwordsStartIndices.size() - 1);
                if (matchStartIndex == lastPasswordStartIndex) {
                    continue;
                }
            }

            matchCount++;
            int passwordStartIndex = passwordDeclarationMatcher.end();
            passwordsStartIndices.add(passwordStartIndex);

            StringBuilder password = new StringBuilder();

            while (passwordStartIndex < text.length()) {
                String stringCharacter = String.valueOf(text.charAt(passwordStartIndex));
                Matcher passwordCharactersMatcher = passwordCharactersPattern.matcher(stringCharacter);
                if (passwordCharactersMatcher.matches()) {
                    password.append(text.charAt(passwordStartIndex));
                    passwordStartIndex++;
                } else {
                    break;
                }
            }

            System.out.println(password);
        }

        if (matchCount == 0) {
            System.out.println("No passwords found.");
        }
    }
}