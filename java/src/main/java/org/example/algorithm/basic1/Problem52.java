package org.example.algorithm.basic1;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Problem52 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] numbers = new int[n];
        int[] increaseDP = new int[n];
        int[] decreaseDP = new int[n];

        Arrays.fill(increaseDP, 1);
        Arrays.fill(decreaseDP, 1);

        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }

        for (int i = 0; i < n - 1; i++) {
            int currentNumber = numbers[i];
            int nextValue = increaseDP[i] + 1;
            for (int j = i + 1; j < n; j++) {
                if (numbers[j] > currentNumber && increaseDP[j] < nextValue)
                    increaseDP[j] = nextValue;
            }
        }

        for (int i = n - 1; i > 0; i--) {
            int currentNumber = numbers[i];
            int nextValue = decreaseDP[i] + 1;
            for (int j = 0; j < i; j++) {
                if (numbers[j] > currentNumber && decreaseDP[j] < nextValue)
                    decreaseDP[j] = nextValue;
            }
        }

        System.out.println(IntStream.range(0, n)
                .map(standard -> increaseDP[standard] + decreaseDP[standard] - 1)
                .max()
                .orElse(0));
    }
}
