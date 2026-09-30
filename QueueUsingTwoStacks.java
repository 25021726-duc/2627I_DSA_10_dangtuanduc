import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

/** HackerRank: Queue using Two Stacks. */
public class QueueUsingTwoStacks {
    private final Deque<Integer> newest = new ArrayDeque<>();
    private final Deque<Integer> oldest = new ArrayDeque<>();

    public void enqueue(int value) {
        newest.push(value);
    }

    public int dequeue() {
        moveToOldestWhenNeeded();
        return oldest.pop();
    }

    public int peek() {
        moveToOldestWhenNeeded();
        return oldest.peek();
    }

    private void moveToOldestWhenNeeded() {
        if (oldest.isEmpty()) {
            while (!newest.isEmpty()) {
                oldest.push(newest.pop());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        FastScanner scanner = new FastScanner();
        int numberOfQueries = scanner.nextInt();
        QueueUsingTwoStacks queue = new QueueUsingTwoStacks();
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < numberOfQueries; i++) {
            int queryType = scanner.nextInt();
            if (queryType == 1) {
                queue.enqueue(scanner.nextInt());
            } else if (queryType == 2) {
                queue.dequeue();
            } else if (queryType == 3) {
                output.append(queue.peek()).append('\n');
            }
        }

        System.out.print(output);
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
