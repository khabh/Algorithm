package org.example.algorithm.basic1;

import java.util.Arrays;
import java.util.Scanner;

public class Problem53 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] numbers = new int[n];

        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }
        int[] firstDP = Arrays.copyOf(numbers, n);

        for (int i = 1; i < n; i++) {
            firstDP[i] += Math.max(firstDP[i - 1], 0);
        }
        int result = Arrays.stream(firstDP).max().orElse(0);
        if (n < 3) {
            System.out.println(result);
            return;
        }

        int[] secondDP = Arrays.copyOf(firstDP, n);
        secondDP[2] = firstDP[0] + numbers[2];
        for (int i = 3; i < n; i++) {
            secondDP[i] = Math.max(secondDP[i - 1], firstDP[i - 2]) + numbers[i];
        }

        System.out.println(Math.max(result, Arrays.stream(secondDP).max().orElse(0)));
    }
}
