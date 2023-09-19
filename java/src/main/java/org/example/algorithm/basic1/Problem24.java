package org.example.algorithm.basic1;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.stream.IntStream;

public class Problem24 {
    private static final boolean[] isPrime = new boolean[1_000_001];

    public static void main(String[] args) {
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        for (int i = 2; i * i < 1_000_001; i++) {
            if (!isPrime[i])
                continue;
            for (int j = i * i; j < 1_000_001; j += i) {
                isPrime[j] = false;
            }
        }
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        StringJoiner stringJoiner = new StringJoiner("\n");

        while (t-- > 0) {
            int n = scanner.nextInt();
            stringJoiner.add(Long.toString(IntStream.range(2, n / 2 + 1)
                    .filter(index -> isPrime[index] && isPrime[n - index])
                    .count()));
        }
        System.out.println(stringJoiner);
    }
}
