package org.example.algorithm.basic1;

import java.util.Arrays;
import java.util.Scanner;

public class Problem54 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        if (n % 2 == 1) {
            System.out.println(0);
            return;
        }

        int[] dp = new int[31];
        dp[2] = 3;
        for (int i = 4; i <= n; i += 2) {
            dp[i] = dp[i - 2] * 3 + Arrays.stream(dp, 2, i - 2).sum() * 2 + 2;
        }

        System.out.println(dp[n]);
    }
}
