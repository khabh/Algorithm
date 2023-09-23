package org.example.algorithm.basic1;

import java.util.Arrays;
import java.util.Scanner;

public class Problem44 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] costs = new int[n][3];
//        int[][] dp = new int[][]

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                costs[i][j] = scanner.nextInt();
            }
        }

        int[] dp = costs[0];

        for (int i = 1; i < n; i++) {
            int nextRed = costs[i][0] + Math.min(dp[1], dp[2]);
            int nextGreen = costs[i][1] + Math.min(dp[0], dp[2]);
            dp[2] = costs[i][2] + Math.min(dp[1], dp[0]);
            dp[1] = nextGreen;
            dp[0] = nextRed;
        }

        System.out.println(Arrays.stream(dp).min().orElse(0));
    }
}
