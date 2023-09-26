package org.example.algorithm.basic2;

import java.util.Scanner;
import java.util.StringJoiner;

public class Problem9 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] numbers = new int[n];
        StringJoiner result = new StringJoiner(" ");

        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }

        int minReverse = n - 1;
        while (minReverse > 0 && numbers[minReverse] < numbers[minReverse -1])
            minReverse--;
        if (minReverse == 0) {
            System.out.println("-1");
            return;
        }

        int switchedIndex = n - 1;
        int standard = numbers[minReverse - 1];

        while (switchedIndex > minReverse && numbers[switchedIndex] < standard)
            switchedIndex--;

        numbers[minReverse - 1] = numbers[switchedIndex];
        numbers[switchedIndex] = standard;

        for (int i = 0; i < minReverse; i++) {
            result.add(String.valueOf(numbers[i]));
        }
        for (int i = n - 1; i >= minReverse; i--) {
            result.add(String.valueOf(numbers[i]));
        }
        System.out.println(result);
    }
}
