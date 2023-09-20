package org.example.algorithm.basic1;

import java.util.Arrays;
import java.util.Scanner;

public class Problem29 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        int[] dp = new int[number + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[1] = 0;

        for (int i = 1; i <= number; i++) {
            int nextValue = dp[i] + 1;
            if (i + 1 <= number) {
                dp[i + 1] = Math.min(dp[i + 1], nextValue);
            }
            if (i * 2 <= number) {
                dp[i * 2] = Math.min(dp[i * 2], nextValue);
            }
            if (i * 3 <= number) {
                dp[i * 3] = Math.min(dp[i * 3], nextValue);
            }
        }

        System.out.println(dp[number]);
    }
}
