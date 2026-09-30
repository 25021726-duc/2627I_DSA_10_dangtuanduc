import java.io.BufferedInputStream;
import java.io.IOException;

/** HackerRank: Equal Stacks. */
public class EqualStacks {
    public static long equalHeight(int[] first, int[] second, int[] third) {
        long firstHeight = sum(first);
        long secondHeight = sum(second);
        long thirdHeight = sum(third);
        int firstTop = 0;
        int secondTop = 0;
        int thirdTop = 0;

        while (firstHeight != secondHeight || secondHeight != thirdHeight) {
            if (firstHeight >= secondHeight && firstHeight >= thirdHeight) {
                firstHeight -= first[firstTop++];
            } else if (secondHeight >= firstHeight && secondHeight >= thirdHeight) {
                secondHeight -= second[secondTop++];
            } else {
                thirdHeight -= third[thirdTop++];
            }
        }

        return firstHeight;
    }

    private static long sum(int[] stack) {
        long total = 0;
        for (int cylinderHeight : stack) {
            total += cylinderHeight;
        }
        return total;
    }

    public static void main(String[] args) throws IOException {
        FastScanner scanner = new FastScanner();
        int firstSize = scanner.nextInt();
        int secondSize = scanner.nextInt();
        int thirdSize = scanner.nextInt();

        int[] first = readStack(scanner, firstSize);
        int[] second = readStack(scanner, secondSize);
        int[] third = readStack(scanner, thirdSize);

        System.out.println(equalHeight(first, second, third));
    }

    private static int[] readStack(FastScanner scanner, int size) throws IOException {
        int[] stack = new int[size];
        for (int i = 0; i < size; i++) {
            stack[i] = scanner.nextInt();
        }
        return stack;
    }

    private static final class FastScanner {
        private final BufferedInputStream input = new BufferedInputStream(System.in);

        int nextInt() throws IOException {
            int current;
            do {
                current = input.read();
            } while (current <= ' ' && current != -1);

            int value = 0;
            while (current > ' ') {
                value = value * 10 + current - '0';
                current = input.read();
            }
            return value;
        }
    }
}
