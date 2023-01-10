import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String text = scanner.nextLine();

        Pattern passwordDeclarationPattern = Pattern.compile("(?i)(password):?\\s*");
        Matcher passwordDeclarationMatcher = passwordDeclarationPattern.matcher(text);

        Pattern passwordCharactersPattern = Pattern.compile("[a-zA-Z\\d]");
        int matchCount = 0;

        while (passwordDeclarationMatcher.find()) {
            matchCount++;
            int passwordStartIndex = passwordDeclarationMatcher.end();
            StringBuilder password = new StringBuilder();

            while (true) {
                Matcher passwordCharactersMatcher = passwordCharactersPattern.matcher("" + text.charAt(passwordStartIndex));
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