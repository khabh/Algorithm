package org.example.algorithm.basic1;

import java.util.Arrays;
import java.util.Scanner;

public class Problem50 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] numbers = new int[n];
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
            dp[i] = numbers[i];
        }

        for (int i = 0; i < n; i++) {
            int currentValue = dp[i];
            for (int j = i + 1; j < n; j++) {
                if (numbers[j] > numbers[i]) {
                    dp[j] = Math.max(dp[j], currentValue + numbers[j]);
                }
            }
        }
        System.out.println(Arrays.stream(dp).max().orElse(0));
    }
}
