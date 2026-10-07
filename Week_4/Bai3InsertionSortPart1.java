import java.io.BufferedInputStream;
import java.io.IOException;

/** HackerRank: Insertion Sort - Part 1. */
public class Bai3InsertionSortPart1 {
    public static void insertIntoSorted(int[] values, StringBuilder output) {
        int valueToInsert = values[values.length - 1];
        int index = values.length - 2;

        while (index >= 0 && values[index] > valueToInsert) {
            values[index + 1] = values[index];
            appendArray(values, output);
            index--;
        }

        values[index + 1] = valueToInsert;
        appendArray(values, output);
    }

    private static void appendArray(int[] values, StringBuilder output) {
        for (int i = 0; i < values.length; i++) {
            if (i > 0) {
                output.append(' ');
            }
            output.append(values[i]);
        }
        output.append('\n');
    }

    public static void main(String[] args) throws IOException {
        FastScanner scanner = new FastScanner();
        int size = scanner.nextInt();
        int[] values = new int[size];
        for (int i = 0; i < size; i++) {
            values[i] = scanner.nextInt();
        }

        StringBuilder output = new StringBuilder();
        insertIntoSorted(values, output);
        System.out.print(output);
    }

    private static final class FastScanner {
        private final BufferedInputStream input = new BufferedInputStream(System.in);

        int nextInt() throws IOException {
            int current;
            do {
                current = input.read();
            } while (current <= ' ' && current != -1);

            int sign = 1;
            if (current == '-') {
                sign = -1;
                current = input.read();
            }

            int value = 0;
            while (current > ' ') {
                value = value * 10 + current - '0';
                current = input.read();
            }
            return sign * value;
        }
    }
}
