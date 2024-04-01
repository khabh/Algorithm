import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        List<Student> students = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            students.add(new Student(i));
        }
        for (int i = 0; i < m; i++) {
            Student prev = students.get(scanner.nextInt() - 1);
            Student next = students.get(scanner.nextInt() - 1);
            prev.addNext(next);
        }

        Queue<Student> q = students.stream()
                .filter(student -> student.prevCount == 0)
                .collect(Collectors.toCollection(ArrayDeque::new));

        List<Integer> result = new ArrayList<>();

        while (!q.isEmpty()) {
            Student current = q.poll();
            result.add(current.order);
            current.freeNext(q);
        }

        System.out.println(result.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" ")));
    }

    private static class Student {
        int order;
        int prevCount = 0;
        List<Student> next = new ArrayList<>();

        public Student(int order) {
            this.order = order;
        }

        public void addNext(Student student) {
            next.add(student);
            student.prevCount++;
        }

        public void freeNext(Queue<Student> students) {
            next.forEach(student -> {
                student.prevCount--;
                if (student.prevCount == 0) {
                    students.add(student);
                }
            });
        }
    }
}
