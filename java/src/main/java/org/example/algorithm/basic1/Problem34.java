package org.example.algorithm.basic1;

import java.util.Arrays;
import java.util.Scanner;

public class Problem34 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] prices = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            prices[i] = scanner.nextInt();
        }
        int[] dp = Arrays.copyOf(prices, n + 1);

        for (int i = 1; i <= n; i++) {
            int currentPrice = dp[i];
            for (int j = 1; j <= n; j++) {
                if (i + j > n)
                    break;
                dp[i + j] = Math.min(dp[i + j], currentPrice + prices[j]);
            }
        }

        System.out.println(dp[n]);
    }
}
