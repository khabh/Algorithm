package org.example.algorithm.basic1;

import java.util.Scanner;

public class Problem18 {
    private static long getCount(int number, int count) {
        long result = 0;
        while (number >= count) {
            number /= count;
            result += number;
        }

        return result;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        long fiveCount = getCount(n, 5) - getCount(m, 5) - getCount(n - m, 5);
        long twoCount = getCount(n, 2) - getCount(m, 2) - getCount(n - m, 2);

        System.out.println(Math.min(fiveCount, twoCount));
    }
}
