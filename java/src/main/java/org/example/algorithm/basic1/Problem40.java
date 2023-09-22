package org.example.algorithm.basic1;

import java.util.Scanner;

public class Problem40 {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        int[] numbers = new int[n];
//        int[][] dp = new int[n][2];
//
//        for (int i = 0; i < n; i++) {
//            int number = scanner.nextInt();
//            numbers[i] = number;
//            dp[i][0] = number;
//            dp[i][1] = Integer.MIN_VALUE;
//        }
//
//        for (int i = 1; i < n; i++) {
//            dp[i][0] = Math.max(dp[i - 1][0], 0) + numbers[i];
//            dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][1]);
//        }
//
//        System.out.println(Math.max(dp[n - 1][0], dp[n - 1][1]));
//    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] numbers = new int[n];

        for (int i = 0; i < n; i++) {
            int number = scanner.nextInt();
            numbers[i] = number;
        }

        int prevSelectedMax = numbers[0];
        int prevNotSelectedMax = Integer.MIN_VALUE;

        for (int i = 1; i < n; i++) {
            int temp = Math.max(prevSelectedMax, 0) + numbers[i];
            prevNotSelectedMax = Math.max(prevSelectedMax, prevNotSelectedMax);
            prevSelectedMax = temp;
        }

        System.out.println(Math.max(prevNotSelectedMax, prevSelectedMax));
    }
}
