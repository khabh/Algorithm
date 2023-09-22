package org.example.algorithm.basic1;

import java.util.Scanner;

public class Problem42 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        long[][] dp = new long[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][1] = 1;
        }

        for (int i = 0; i <= n; i++) {
            for (int m = 0; m <= n; m++) {
                if (m + i > n)
                    break;
                for (int j = 0; j < k; j++) {
                    dp[i + m][j + 1] = (dp[i][j] + dp[i + m][j + 1]) % 1_000_000_000;
                }
            }
        }

        System.out.println(dp[n][k]);
    }
}
