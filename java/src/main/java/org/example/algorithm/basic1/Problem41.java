package org.example.algorithm.basic1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem41 {

    private static int[] getSquareNumbers(int n) {
        List<Integer> squares = new ArrayList<>();
        for (int i = 1; i * i <= n; i++) {
            squares.add(i * i);
        }

        return squares.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] squareNumbers = getSquareNumbers(n);
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = i;
        }

        for (int i = 0; i <= n; i++) {
            int nextValue = dp[i] + 1;
            for (int squareNumber : squareNumbers) {
                if (squareNumber + i > n)
                    break;
                dp[i + squareNumber] = Math.min(dp[i + squareNumber], nextValue);
            }
        }

        System.out.println(dp[n]);
    }
}
