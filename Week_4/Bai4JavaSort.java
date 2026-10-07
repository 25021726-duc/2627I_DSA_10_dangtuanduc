import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/** HackerRank: Java Sort. */
public class Bai4JavaSort {
    private static final class Student {
        private final int id;
        private final String name;
        private final double cgpa;

        Student(int id, String name, double cgpa) {
            this.id = id;
            this.name = name;
            this.cgpa = cgpa;
        }
    }

    private static final class StudentComparator implements Comparator<Student> {
        @Override
        public int compare(Student first, Student second) {
            int byCgpa = Double.compare(second.cgpa, first.cgpa);
            if (byCgpa != 0) {
                return byCgpa;
            }

            int byName = first.name.compareTo(second.name);
            if (byName != 0) {
                return byName;
            }

            return Integer.compare(first.id, second.id);
        }
    }

    public static void main(String[] args) throws IOException {
        FastScanner scanner = new FastScanner();
        int numberOfStudents = scanner.nextInt();
        List<Student> students = new ArrayList<>(numberOfStudents);

        for (int i = 0; i < numberOfStudents; i++) {
            int id = scanner.nextInt();
            String name = scanner.next();
            double cgpa = scanner.nextDouble();
            students.add(new Student(id, name, cgpa));
        }

        students.sort(new StudentComparator());

        StringBuilder output = new StringBuilder();
        for (Student student : students) {
            output.append(student.name).append('\n');
        }
        System.out.print(output);
    }

    private static final class FastScanner {
        private final BufferedInputStream input = new BufferedInputStream(System.in);

        String next() throws IOException {
            StringBuilder token = new StringBuilder();
            int current;
            do {
                current = input.read();
            } while (current <= ' ' && current != -1);

            while (current > ' ') {
                token.append((char) current);
                current = input.read();
            }
            return token.toString();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        double nextDouble() throws IOException {
            return Double.parseDouble(next());
        }
    }
}
