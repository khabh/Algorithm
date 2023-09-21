package org.example.algorithm.basic1;

import java.util.*;

public class Problem39 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] numbers = new int[n];
        int[] dp = new int[n];
        int[] prev = new int[n];
        for (int i = 0; i < n; i++) {
            prev[i] = i;
        }
        int maxIndex = 0;
        int maxValue = 1;
        Arrays.fill(dp, 1);

        for (int i = 0; i < n; i++) {
            int number = scanner.nextInt();
            for (int j = 0; j < i; j++) {
                if (numbers[j] < number && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                    prev[i] = j;
                    if (dp[i] > maxValue) {
                        maxValue = dp[i];
                        maxIndex = i;
                    }
                }
            }
            numbers[i] = number;
        }

        Stack<String> result = new Stack<>();
        while (prev[maxIndex] != maxIndex) {
            result.push(Integer.toString(numbers[maxIndex]));
            maxIndex = prev[maxIndex];
        }
        result.push(Integer.toString(numbers[maxIndex]));
        System.out.println(maxValue);
        StringJoiner resultMaker = new StringJoiner(" ");
        while (!result.isEmpty()) {
            resultMaker.add(result.pop());
        }
        System.out.println(resultMaker);
    }
}
