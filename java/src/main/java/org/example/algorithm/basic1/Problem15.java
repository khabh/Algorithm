package org.example.algorithm.basic1;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Problem15 {

    private static final boolean[] isPrimeNumber = new boolean[10001];

    public static void main(String[] args) {
        Arrays.fill(isPrimeNumber, true);
        isPrimeNumber[1] = false;
        for (int i = 2; i * i <= 1000 ; i++) {
            if (isPrimeNumber[i]) {
                for (int j = i * i; j <= 1000; j += i) {
                    isPrimeNumber[j] = false;
                }
            }
        }

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println(IntStream.range(0, n)
                .map(index -> scanner.nextInt())
                .filter(number -> isPrimeNumber[number])
                .count());
    }
}
