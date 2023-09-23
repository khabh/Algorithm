package org.example.algorithm.basic1;

import java.util.Arrays;
import java.util.Scanner;

public class Problem46 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] dp = new int[n + 1][10];
        Arrays.fill(dp[1], 1);

        for (int i = 1; i < n; i++) {
            int total = 0;
            for (int j = 0; j <= 9; j++) {
                total = (total + dp[i][j]) % 10007;
                dp[i + 1][j] = total;
            }
        }

        System.out.println(Arrays.stream(dp[n]).sum() % 10007);
    }
}
