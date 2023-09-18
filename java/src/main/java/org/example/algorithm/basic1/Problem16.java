package org.example.algorithm.basic1;

import java.util.Arrays;
import java.util.Scanner;

public class Problem16 {
    private final static int MAX_VALUE = 1_000_001;
    private final static boolean[] isPrimeNumber = new boolean[MAX_VALUE];

    public static void main(String[] args) {
        Arrays.fill(isPrimeNumber, true);
        isPrimeNumber[1] = false;
        isPrimeNumber[0] = false;
        for (int i = 2; i * i < MAX_VALUE; i++) {
            if (!isPrimeNumber[i])
                continue;
            for (int j = i * i; j < MAX_VALUE; j += i) {
                isPrimeNumber[j] = false;
            }
        }

        Scanner scanner = new Scanner(System.in);
        StringBuilder result = new StringBuilder();
        while (true) {
            int number = scanner.nextInt();
            boolean notWrong = false;
            if (number == 0)
                break;
            for (int i = 2; i <= number / 2; i++) {
                if (isPrimeNumber[i] && isPrimeNumber[number - i]) {
                    result.append(number).append(" = ")
                            .append(i).append(" + ")
                            .append(number - i).append("\n");
                    notWrong = true;
                    break;
                }
            }
            if (!notWrong)
                result.append("Goldbach's conjecture is wrong.\n");
        }
        System.out.println(result);
        scanner.close();
    }
}
