package org.example.algorithm.basic2;

import java.util.Scanner;

public class Problem7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int digitCount = String.valueOf(n).length();
        int minStandard = (int)Math.pow(10, String.valueOf(n).length() - 1);
        long count = (n - minStandard + 1) * digitCount;

        while (digitCount > 1) {
            digitCount--;
            minStandard /= 10;
            count += ((minStandard * 9) * digitCount);
        }

        System.out.println(count);
    }
}
