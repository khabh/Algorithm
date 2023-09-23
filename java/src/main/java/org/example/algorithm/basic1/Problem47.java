package org.example.algorithm.basic1;

import java.util.Scanner;
import java.util.StringJoiner;

public class Problem47 {
    static int[][] stickers;
    static int[][] dp;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        StringJoiner resultSum = new StringJoiner("\n");

        while (t-- > 0) {
            int n = scanner.nextInt();
            stickers = new int[2][n];
            dp = new int [2][n];
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < n; j++) {
                    stickers[i][j] = scanner.nextInt();
                }
            }

            dp[0][0] = stickers[0][0];
            dp[1][0] = stickers[1][0];

            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < 2; j++) {
                    int currentValue = dp[j][i];
                    updateScore(1 - j, i + 1, currentValue);
                    if (i + 2 < n) {
                        updateScore(1 - j, i + 2, currentValue);
                        updateScore(j, i + 2, currentValue);
                    }
                }
            }

            int result = Math.max(dp[0][n - 1], dp[1][n - 1]);
            if (n > 1) {
                result = Math.max(result, Math.max(dp[0][n - 2], dp[1][n - 2]));
            }
            resultSum.add(Integer.toString(result));
        }

        System.out.println(resultSum);
    }

    private static void updateScore(int x, int y, int currentValue) {
        dp[x][y] = Math.max(dp[x][y], currentValue + stickers[x][y]);
    }
}
