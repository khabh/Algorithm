package org.example.algorithm.basic1;

import java.util.Arrays;
import java.util.Scanner;

public class Problem36 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long[] prev = new long[10];
        long[] next = new long[10];
        Arrays.fill(prev, 1, 9, 1);
        for (int i = 1; i < 10; i++) {
            prev[i] = 1;
        }

        for (int i = 2; i <= n; i++) {
            next[0] = prev[1];
            next[9] = prev[8];
            for (int j = 1; j < 9; j++) {
                next[j] = (prev[j - 1] + prev[j + 1]) % 1_000_000_000;
            }
            prev = next.clone();
        }

        System.out.println(Arrays.stream(prev).sum() % 1_000_000_000);
    }
}
