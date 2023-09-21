package org.example.algorithm.basic1;

import java.util.Scanner;

public class Problem32 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        int[] dp = new int[12];
        dp[0] = 1;
        for (int i = 0; i <= 11; i++) {
            int nextValue = dp[i];
            if (i + 1 <= 11) {
                dp[i + 1] += nextValue;
            }
            if (i + 2 <= 11) {
                dp[i + 2] += nextValue;
            }
            if (i + 3 <= 11) {
                dp[i + 3] += nextValue;
            }
        }


        while (t-- > 0) {
            int number = scanner.nextInt();
            System.out.println(dp[number]);
        }
    }
}
