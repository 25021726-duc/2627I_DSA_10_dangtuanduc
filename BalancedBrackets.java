import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

/** HackerRank: Balanced Brackets. */
public class BalancedBrackets {
    public static boolean isBalanced(String expression) {
        Deque<Character> openingBrackets = new ArrayDeque<>();

        for (int i = 0; i < expression.length(); i++) {
            char bracket = expression.charAt(i);

            if (bracket == '(' || bracket == '[' || bracket == '{') {
                openingBrackets.push(bracket);
                continue;
            }

            if (openingBrackets.isEmpty()) {
                return false;
            }

            char opening = openingBrackets.pop();
            if ((bracket == ')' && opening != '(')
                    || (bracket == ']' && opening != '[')
                    || (bracket == '}' && opening != '{')) {
                return false;
            }
        }

        return openingBrackets.isEmpty();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int numberOfExpressions = Integer.parseInt(reader.readLine().trim());
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < numberOfExpressions; i++) {
            String expression = reader.readLine();
            output.append(isBalanced(expression == null ? "" : expression.trim()) ? "YES" : "NO");
            if (i + 1 < numberOfExpressions) {
                output.append('\n');
            }
        }

        System.out.print(output);
    }
}
