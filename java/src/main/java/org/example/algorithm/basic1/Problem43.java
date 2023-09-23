package org.example.algorithm.basic1;

import java.util.Scanner;
import java.util.StringJoiner;

public class Problem43 {
    private final static int MAX_VALUE = 1_000_001;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        long[] dp = new long[MAX_VALUE];
        StringJoiner result = new StringJoiner("\n");
        dp[0] = 1;

        for (int i = 0; i < MAX_VALUE; i++) {
            long nextValue = dp[i];
            int maxIndex = Math.min(i + 3, MAX_VALUE - 1);
            for (int j = i + 1; j <= maxIndex; j++) {
                dp[j] = (dp[j] + nextValue) % 1_000_000_009;
            }
        }

        while (t-- > 0) {
            result.add(Long.toString(dp[scanner.nextInt()]));
        }
        System.out.println(result);
    }
}
