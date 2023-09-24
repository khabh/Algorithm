package org.example.algorithm.basic1;

import java.util.Arrays;
import java.util.Scanner;

public class Problem51 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] numbers = new int[n];
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }

        for (int i = 0; i < n - 1; i++) {
            int currentNumber = numbers[i];
            int nextValue = dp[i] + 1;
            for (int j = i + 1; j < n; j++) {
                if (numbers[j] < currentNumber && dp[j] < nextValue) {
                    dp[j] = nextValue;
                }
            }
        }

        System.out.println(Arrays.stream(dp).max().orElse(0));
    }
}
