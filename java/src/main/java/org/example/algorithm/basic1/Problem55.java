package org.example.algorithm.basic1;

import java.util.Arrays;
import java.util.Scanner;

public class Problem55 {
    static int[][] costs;

    private static int calcCost(int firstPicked, int n) {
        int[] dp = new int[] {costs[firstPicked][0] + costs[0][1], costs[firstPicked][0] + costs[1][1], costs[firstPicked][0] + costs[2][1]};
        dp[firstPicked] = Integer.MAX_VALUE;

        for (int i = 2; i < n; i++) {
            int nextRed = Math.min(dp[1], dp[2]) + costs[0][i];
            int nextGreen = Math.min(dp[2], dp[0]) + costs[1][i];
            dp[2] = Math.min(dp[0], dp[1]) + costs[2][i];
            dp[0] = nextRed;
            dp[1] = nextGreen;
        }

        if (firstPicked == 0) {
            return Math.min(dp[1], dp[2]);
        }
        if (firstPicked == 1) {
            return Math.min(dp[0], dp[2]);
        }
        return Math.min(dp[0], dp[1]);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        costs = new int[3][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                costs[j][i] = scanner.nextInt();
            }
        }

        System.out.println(Math.min(calcCost(0, n), Math.min(calcCost(1, n), calcCost(2, n))));
    }
}
