package org.example.algorithm.basic1;

import java.util.Scanner;

public class Problem49 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] numbers = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                numbers[i][j] = scanner.nextInt();
            }
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                numbers[i][j] += Math.max(numbers[i + 1][j], numbers[i + 1][j + 1]);
            }
        }

        System.out.println(numbers[0][0]);
    }
}
