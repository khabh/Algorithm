package org.example.algorithm.basic1;

import java.util.Scanner;
import java.util.StringJoiner;

public class Problem19 {
    private static int getGCD(int a, int b) {
        if (a < b) {
            return getGCD(b, a);
        }
        int m = a % b;
        while (m != 0) {
            a = b;
            b = m;
            m = a % b;
        }

        return b;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        int[] numbers = new int[100];
        StringJoiner stringJoiner = new StringJoiner("\n");

        while (t-- > 0) {
            int n = scanner.nextInt();
            long result = 0L;
            for (int i = 0; i < n; i++) {
                numbers[i] = scanner.nextInt();
            }
            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    result += getGCD(numbers[i], numbers[j]);
                }
            }
            stringJoiner.add(Long.toString(result));
        }

        System.out.println(stringJoiner);
        scanner.close();
    }
}
