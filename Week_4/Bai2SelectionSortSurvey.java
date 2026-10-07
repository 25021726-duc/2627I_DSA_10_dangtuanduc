import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;

/** Bai 2: benchmark Selection Sort and compare it with Insertion Sort. */
public class Bai2SelectionSortSurvey {
    private static final int[] DEFAULT_SIZES = {1_000, 2_000, 4_000, 8_000};
    private static final long RANDOM_SEED = 2026L;

    @FunctionalInterface
    private interface SortAlgorithm {
        void sort(int[] values);
    }

    public static void selectionSort(int[] values) {
        for (int i = 0; i < values.length - 1; i++) {
            int minimumIndex = i;
            for (int j = i + 1; j < values.length; j++) {
                if (values[j] < values[minimumIndex]) {
                    minimumIndex = j;
                }
            }

            int temporary = values[i];
            values[i] = values[minimumIndex];
            values[minimumIndex] = temporary;
        }
    }

    public static void insertionSort(int[] values) {
        for (int i = 1; i < values.length; i++) {
            int value = values[i];
            int j = i - 1;
            while (j >= 0 && values[j] > value) {
                values[j + 1] = values[j];
                j--;
            }
            values[j + 1] = value;
        }
    }

    private static double averageMilliseconds(
            int[] source, int repetitions, SortAlgorithm algorithm) {
        for (int i = 0; i < 2; i++) {
            int[] warmup = Arrays.copyOf(source, source.length);
            algorithm.sort(warmup);
        }

        long totalNanoseconds = 0;
        for (int run = 0; run < repetitions; run++) {
            int[] values = Arrays.copyOf(source, source.length);
            long start = System.nanoTime();
            algorithm.sort(values);
            totalNanoseconds += System.nanoTime() - start;

            if (!isSorted(values)) {
                throw new IllegalStateException("Sorting algorithm produced an invalid result");
            }
        }
        return totalNanoseconds / 1_000_000.0 / repetitions;
    }

    private static boolean isSorted(int[] values) {
        for (int i = 1; i < values.length; i++) {
            if (values[i - 1] > values[i]) {
                return false;
            }
        }
        return true;
    }

    private static int[] randomData(int size, long seed) {
        Random random = new Random(seed);
        int[] values = new int[size];
        for (int i = 0; i < size; i++) {
            values[i] = random.nextInt();
        }
        return values;
    }

    private static int[] sortedData(int size) {
        int[] values = new int[size];
        for (int i = 0; i < size; i++) {
            values[i] = i;
        }
        return values;
    }

    private static int[] reverseData(int size) {
        int[] values = new int[size];
        for (int i = 0; i < size; i++) {
            values[i] = size - i;
        }
        return values;
    }

    private static int[] equalData(int size) {
        int[] values = new int[size];
        Arrays.fill(values, 42);
        return values;
    }

    private static void printComparison(String dataType, int[] values, int repetitions) {
        double insertionTime = averageMilliseconds(values, repetitions,
                Bai2SelectionSortSurvey::insertionSort);
        double selectionTime = averageMilliseconds(values, repetitions,
                Bai2SelectionSortSurvey::selectionSort);
        String faster = insertionTime <= selectionTime ? "insertion" : "selection";

        System.out.printf(Locale.US, "%s,%d,%d,%.6f,%.6f,%s%n",
                dataType, values.length, repetitions, insertionTime, selectionTime, faster);
    }

    private static void benchmarkGeneratedData() {
        for (int size : DEFAULT_SIZES) {
            printComparison("random", randomData(size, RANDOM_SEED + size), 5);
            printComparison("sorted", sortedData(size), 3);
            printComparison("reverse", reverseData(size), 3);
            printComparison("equal", equalData(size), 3);
        }
    }

    private static void benchmarkFiles(String[] fileNames) throws IOException {
        for (String fileName : fileNames) {
            int[] values = readAllInts(Path.of(fileName));
            printComparison("file:" + Path.of(fileName).getFileName(), values, 3);
        }
    }

    private static void warmUpJvm() {
        int[] sample = randomData(2_000, RANDOM_SEED);
        for (int run = 0; run < 20; run++) {
            insertionSort(Arrays.copyOf(sample, sample.length));
            selectionSort(Arrays.copyOf(sample, sample.length));
        }
    }

    private static int[] readAllInts(Path path) throws IOException {
        try (FastIntReader reader = new FastIntReader(path)) {
            int[] values = new int[1_024];
            int size = 0;
            Integer value;

            while ((value = reader.nextInt()) != null) {
                if (size == values.length) {
                    values = Arrays.copyOf(values, values.length * 2);
                }
                values[size++] = value;
            }
            return Arrays.copyOf(values, size);
        }
    }

    public static void main(String[] args) throws IOException {
        warmUpJvm();
        System.out.println("data_type,size,runs,insertion_ms,selection_ms,faster");
        if (args.length > 0) {
            benchmarkFiles(args);
        }
        benchmarkGeneratedData();
    }

    private static final class FastIntReader implements AutoCloseable {
        private final BufferedInputStream input;

        FastIntReader(Path path) throws IOException {
            input = new BufferedInputStream(java.nio.file.Files.newInputStream(path));
        }

        Integer nextInt() throws IOException {
            int current;
            do {
                current = input.read();
            } while (current != -1 && current <= ' ');

            if (current == -1) {
                return null;
            }

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

        @Override
        public void close() throws IOException {
            input.close();
        }
    }
}
