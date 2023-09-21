package org.example.algorithm.basic1;

import java.util.Arrays;
import java.util.Scanner;

public class Problem35 {
    private final static int MAX_VALUE = 100_001;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long[][] dp = new long[MAX_VALUE][4];
        dp[1][1] = 1;
        dp[2][2] = 1;
        dp[3][3] = 1;
        for (int i = 1; i < MAX_VALUE; i++) {
            long currentSum = Arrays.stream(dp[i]).sum();
            for (int j = 1; j <= 3; j++) {
                if (i + j == MAX_VALUE)
                    break;
                dp[i + j][j] += ((currentSum - dp[i][j]) % 1_000_000_009);
            }
        }

        int t = scanner.nextInt();
        while (t-- > 0) {
            System.out.println(Arrays.stream(dp[scanner.nextInt()]).sum() % 1_000_000_009);
        }
    }
}
