package org.example.algorithm.basic1;

import java.util.Scanner;

public class Problem37 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long prevZero = 0;
        long prevOne = 1;

        for (int i = 2; i <= n; i++) {
            prevZero += prevOne;
            prevOne = prevZero - prevOne;
        }

        System.out.println(prevOne + prevZero);
    }
}
