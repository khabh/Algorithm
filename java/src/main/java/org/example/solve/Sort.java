package org.example.solve;

import java.util.*;
import java.util.stream.IntStream;

public class Sort {
    public static void main(String[] args) {
        new Sort().problem4();
    }


    public void problem4() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int result = 0;
        PriorityQueue<Integer> cards = new PriorityQueue<>();
        IntStream.range(0, n)
                .map(index -> scanner.nextInt())
                .forEach(cards::add);

        while (cards.size() >= 2) {
            int sum = cards.poll() + cards.poll();
            result += sum;
            cards.add(sum);
        }

        System.out.println(result);
    }

    class Problem3 {
        class Stage implements Comparable<Stage> {
            final int number;
            double failRate;

            public Stage(int number) {
                this.number = number;
            }

            public void setFailRate(int total, int failedCount) {
                if (total == 0) {
                    failRate = 0;
                    return;
                }
                failRate = failedCount / (double)total;
            }

            public int getNumber() {
                return number;
            }

            @Override
            public int compareTo(Stage stage) {
                if (failRate < stage.failRate)
                    return 1;
                if (failRate == stage.failRate) {
                    return number - stage.number;
                }
                return -1;
            }
        }
        public int[] solution(int N, int[] userStages) {
            List<Stage> stages = new ArrayList<>();
            Arrays.sort(userStages);
            int index = 0;
            int arrivedCount = userStages.length;
            IntStream.range(1, N + 1)
                    .forEach(stageNumber ->
                            stages.add(new Stage(stageNumber)));

            for (Stage stage : stages) {
                int count = 0;
                int fail = 0;

                while (index < userStages.length && userStages[index] <= stage.number) {
                    if (userStages[index] == stage.number)
                        fail++;
                    count++;
                    index++;
                }
                stage.setFailRate(arrivedCount, fail);
                arrivedCount -= count;
            }

            return stages.stream()
                    .sorted()
                    .mapToInt(Stage::getNumber)
                    .toArray();
        }
    }

    public void problem2() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long result = Long.MAX_VALUE;
        int resultPosition = -1;
        int[] houses = new int[n];
        IntStream.range(0, n)
                .forEach(index -> houses[index] = scanner.nextInt());
        Arrays.sort(houses);
        int left = 0;
        int right = Arrays.stream(houses).sum();

        for (int i = 0; i < n; i++) {
            long distance = (long)right - left + houses[i] * (2 * i - n);
            if (distance < result) {
                resultPosition = houses[i];
                result = distance;
            }
            right -= houses[i];
            left += houses[i];
        }
        System.out.println(resultPosition);
    }

    public class Student implements Comparable<Student> {
        private final String name;
        private final int kor;
        private final int eng;
        private final int math;

        public Student(String name, int kor, int eng, int math) {
            this.name = name;
            this.kor = kor;
            this.eng = eng;
            this.math = math;
        }

        @Override
        public int compareTo(Student student) {
            if (student.kor != kor) {
                return student.kor - kor;
            }
            if (student.eng != eng) {
                return eng - student.eng;
            }
            if (student.math != math) {
                return student.math - math;
            }
            return name.compareTo(student.name);
        }
    }

    // 국영수
    public void problem1() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Student[] students = new Student[n];
        IntStream.range(0, n)
                .forEach(index -> {
                    students[index] = new Student(scanner.next(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
                });

        Arrays.stream(students).sorted()
                .forEach(student -> System.out.println(student.name));
    }
}
