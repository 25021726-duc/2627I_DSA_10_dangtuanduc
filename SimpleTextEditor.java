import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

/** HackerRank: Simple Text Editor. */
public class SimpleTextEditor {
    private final StringBuilder text = new StringBuilder();
    private final Deque<Edit> history = new ArrayDeque<>();

    public void append(String value) {
        text.append(value);
        history.push(Edit.append(value.length()));
    }

    public void delete(int count) {
        int start = text.length() - count;
        String removed = text.substring(start);
        text.delete(start, text.length());
        history.push(Edit.delete(removed));
    }

    public char charAt(int oneBasedPosition) {
        return text.charAt(oneBasedPosition - 1);
    }

    public void undo() {
        Edit edit = history.pop();
        if (edit.appendedLength > 0) {
            text.delete(text.length() - edit.appendedLength, text.length());
        } else {
            text.append(edit.deletedText);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int numberOfOperations = Integer.parseInt(reader.readLine().trim());
        SimpleTextEditor editor = new SimpleTextEditor();
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < numberOfOperations; i++) {
            String operation = reader.readLine();
            int operationType = operation.charAt(0) - '0';

            if (operationType == 1) {
                editor.append(operation.substring(2));
            } else if (operationType == 2) {
                editor.delete(Integer.parseInt(operation.substring(2).trim()));
            } else if (operationType == 3) {
                int position = Integer.parseInt(operation.substring(2).trim());
                output.append(editor.charAt(position)).append('\n');
            } else if (operationType == 4) {
                editor.undo();
            }
        }

        System.out.print(output);
    }

    private static final class Edit {
        private final int appendedLength;
        private final String deletedText;

        private Edit(int appendedLength, String deletedText) {
            this.appendedLength = appendedLength;
            this.deletedText = deletedText;
        }

        static Edit append(int length) {
            return new Edit(length, null);
        }

        static Edit delete(String text) {
            return new Edit(0, text);
        }
    }
}
