package org.example.solve;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.stream.IntStream;

public class DynamicProgramming {
    public static void main(String[] args) {
        new DynamicProgramming().problem6();
    }

    public void problem6() {
//        0 c a t
//      0 0 1 2 3
//      c 1 1
//      u 2
//      t 3
        Scanner scanner = new Scanner(System.in);
        String a = scanner.next();
        String b = scanner.next();
        int aSize = a.length(), bSize = b.length();
        int[][] count = new int[bSize + 1][aSize + 1];
        for (int index = 1; index <= aSize; index++)
            count[0][index] = index;
        for (int index = 1; index <= bSize; index++)
            count[index][0] = index;
        for (int i = 1; i <= bSize; i++) {
            for (int j = 1; j <= aSize; j++) {
                if (a.charAt(j - 1) == b.charAt(i - 1)) {
                    count[i][j] = count[i - 1][j - 1];
                    continue;
                }
                int minCount = Math.min(count[i][j - 1] + 1, count[i - 1][j] + 1);
                count[i][j] = Math.min(minCount, count[i - 1][j - 1] + 1);
            }
        }

        System.out.println(Arrays.stream(count[bSize]).min().orElse(0));
    }

    // 못생긴 수 두 번째 풀이
    public void problem5_2() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] numbers = new int[n + 1];
        numbers[1] = 1;
        int next2 = 2;
        int next3 = 3;
        int next5 = 5;
        int i2 = 1, i3 = 1, i5 = 1;
        for (int i = 2; i <= n; i++) {
            numbers[i] = Math.min(next2, next3);
            numbers[i] = Math.min(numbers[i], next5);
            if (numbers[i] == next2) {
                i2++;
                next2 = numbers[i2] * 2;
            }
            if (numbers[i] == next3) {
                i3++;
                next3 = numbers[i3] * 3;
            }
            if (numbers[i] == next5) {
                i5++;
                next5 = numbers[i5] * 5;
            }
        }
        System.out.println(numbers[n]);
    }

    // 못생긴 수 첫 번째 풀이
    public void problem5() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int count = 0;
        int pre = 0;
        PriorityQueue<Integer> numbers = new PriorityQueue<>();
        numbers.add(1);
        while (true) {
            int number = numbers.poll();
            if (number == pre)
                continue;
            pre = number;
            count++;
            if (count == n) {
                System.out.println(number);
                return;
            }
            numbers.add(number * 2);
            numbers.add(number * 3);
            numbers.add(number * 5);
        }
    }

    public void problem4() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] numbers = new int[n];
        int[] dp = new int[n];
        int result = 1;
        for (int index = 0; index < n; index++) {
            numbers[index] = scanner.nextInt();
        }
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            int maxValue = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (numbers[i] < numbers[j]) {
                    maxValue = Math.max(maxValue, dp[j]);
                }
            }
            dp[i] = maxValue + 1;
            result = Math.max(dp[i], result);
        }
        System.out.println(n - result);
    }

    // 퇴사
    public void problem3() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] dp = new int[n + 10];

        for (int day = 1; day <= n; day++) {
            dp[day] = Arrays.stream(dp, 1, day + 1).max().orElse(0);
            int t = scanner.nextInt();
            int p = scanner.nextInt();
            int nextDay = day + t;
            dp[nextDay] = Math.max(dp[nextDay], dp[day] + p);
        }

        System.out.println(Arrays.stream(dp, 1, n + 2).max().orElse(0));
    }

    // 정수 삼각형
    public void problem2() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] numbers = new int[n][n];
        IntStream.range(0, n)
                .forEach(index -> {
                    for (int i = 0; i <= index; i++) {
                        numbers[index][i] = scanner.nextInt();
                    }
                });

        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                numbers[i][j] += Math.max(numbers[i + 1][j], numbers[i + 1][j + 1]);
            }
        }
        System.out.println(numbers[0][0]);
    }

    // 금광
    public void problem1() {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();

        while (t-- > 0) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            int[][] board = new int[n][m];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    board[i][j] = scanner.nextInt();
                }
            }

            for (int i = 1; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    int maxValue = board[j][i - 1];
                    if (j > 0)
                        maxValue = Math.max(maxValue, board[j - 1][i - 1]);
                    if (j < n - 1)
                        maxValue = Math.max(maxValue, board[j + 1][i - 1]);
                    board[j][i] += maxValue;
                }
            }

            int result = 0;
            for (int i = 0; i < n; i++) {
                result = Math.max(result, board[i][m - 1]);
            }

            System.out.println(result);
        }
    }
}
