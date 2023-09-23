package org.example.algorithm.basic1;

import java.util.Arrays;
import java.util.Scanner;

public class Problem48 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] wine = new int[n];
        int[][] dp = new int[n][3]; // 직전 포도주를 마신 경우, 직전 포도주를 안 마신 경우, 나를 안 마신 경우

        for (int i = 0; i < n; i++) {
            wine[i] = scanner.nextInt();
        }
        if (n == 1) {
            System.out.println(wine[0]);
            return;
        }
        dp[0][0] = wine[0];
        dp[0][1] = wine[0];
        dp[1][0] = wine[0] + wine[1];
        dp[1][1] = wine[1];
        dp[1][2] = wine[0];

        for (int i = 2; i < n; i++) {
            dp[i][0] = dp[i - 1][1] + wine[i];
            dp[i][1] = Math.max(Math.max(dp[i - 2][0], dp[i - 2][1]), dp[i - 1][2]) + wine[i];
            dp[i][2] = Arrays.stream(dp[i - 1]).max().orElse(0);
        }

        System.out.println(Arrays.stream(dp[n - 1]).max().orElse(0));
    }
}
