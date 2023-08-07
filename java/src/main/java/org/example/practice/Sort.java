package org.example.practice;

import java.util.*;

public class Sort {

    public static void main(String[] args) {
        problem3();
    }

    static Scanner scanner = new Scanner(System.in);

    private static int[] getNumbers() {
        return Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .sorted()
                .toArray();
    }

    // 두 배열의 원소 교체
    public static void problem3() {
        String[] input = scanner.nextLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);

        int[] a = getNumbers();
        int[] b = getNumbers();
        int index = n - 1;

        for (int i = 0; i < k; i++) {
            if (b[index] <= a[i])
                break;
            a[i] = b[index--];
        }

        System.out.println(Arrays.stream(a).sum());
    }

    // 성적이 낮은 순서로 학생 출력하기
    public static void problem2() {
        class Student implements Comparable<Student> {
            private final int score;
            private final String name;

            public Student(int score, String name) {
                this.score = score;
                this.name = name;
            }

            public String getName() {
                return name;
            }

            public int getScore() {
                return score;
            }

            @Override
            public int compareTo(Student student) {
                return this.score - student.getScore();
            }
        }

        List<Student> students = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        while (n-- > 0) {
            String name = scanner.next();
            int score = scanner.nextInt();
            students.add(new Student(score, name));
        }

        scanner.close();

        Collections.sort(students);

        for (Student student : students) {
            System.out.printf("%s ", student.getName());
        }
    }

    // 위에서 아래로
    public static void problem1() {
        int[] numbers = new int[500];
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }

        Arrays.sort(numbers, 0, n - 1);

        for (int i = 0; i < n; i++) {
            System.out.printf("%d ", numbers[i]);
        }
    }

    // 선택 정렬
    public static void sort1() {
        int[] arr = new int[] {7, 5, 9, 0, 3, 1, 6, 2, 4, 8};

        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }

        for (int j : arr) {
            System.out.printf("%d ", j);

        }
    }

    // 삽입 정렬
    public static void sort2() {
        int[] arr = new int[] {7, 5, 9, 0, 3, 1, 6, 2, 4, 8};

        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    int temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                }
                else
                    break;
            }
        }

        for (int j : arr) {
            System.out.printf("%d ", j);
        }
    }

    private static void quickSort(int[] arr, int start, int end) {
        if (start >= end)
            return;

        int pivot = arr[start];
        int left = start + 1;
        int right = end;

        while (left <= right) {
            while (left <= end && arr[left] <= pivot)
                left++;
            while (right > start && arr[right] >= pivot)
                right--;
            if (left > right) {
                arr[start] = arr[right];
                arr[right] = pivot;
            }
            else {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
            }
        }
        quickSort(arr, start, right - 1);
        quickSort(arr, right + 1, end);
    }

    // 퀵 정렬
    public static void sort3() {
        int[] arr = new int[] {7, 5, 9, 0, 3, 1, 6, 2, 4, 8};

        quickSort(arr, 0, arr.length - 1);

        for (int j : arr) {
            System.out.printf("%d ", j);
        }
    }

    // 계수 정렬
    public static void sort4() {
        int[] arr = new int[] {7, 5, 9, 0, 3, 1, 6, 2, 4, 8};

        int[] count = new int[20];
        Arrays.fill(count, 0);

        for (int i : arr) {
            count[i]++;
        }

        for (int i = 0; i < count.length; i++) {
            while (count[i]-- > 0) {
                System.out.printf("%d ", i);
            }
        }
    }
}
