import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int[] table;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        String pattern = br.readLine();

        makeTable(pattern);
        System.out.println(removePattern(input, pattern));
    }

    private static void makeTable(String pattern) {
        table = new int[pattern.length()];
        int j = 0;

        for (int i = 1; i < pattern.length(); i++) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = table[j - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(j)) {
                table[i] = ++j;
            }
        }
    }

    private static String removePattern(String parent, String pattern) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < parent.length(); i++) {
            sb.append(parent.charAt(i));

            if (sb.length() >= pattern.length()) {
                boolean isMatch = true;

                for (int k = 0; k < pattern.length(); k++) {
                    if (sb.charAt(sb.length() - pattern.length() + k) != pattern.charAt(k)) {
                        isMatch = false;
                        break;
                    }
                }
                if (isMatch) {
                    sb.setLength(sb.length() - pattern.length());
                }
            }
        }
        return sb.isEmpty() ? "FRULA" : sb.toString();
    }