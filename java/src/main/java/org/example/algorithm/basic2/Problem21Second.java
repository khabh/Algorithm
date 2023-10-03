package org.example.algorithm.basic2;

import java.util.Arrays;
import java.util.Scanner;

public class Problem21Second {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] board = new int[n][m];
        int result = -1;

        for (int i = 0; i < n; i++) {
            board[i] = Arrays.stream(scanner.next().split("")).mapToInt(Integer::parseInt).toArray();
        }
        for (int type = 0; type < (1 << (n * m)); type++) {

            int typeSum = 0;

            for (int i = 0; i < n; i++) {
                int currentSum = 0;
                for (int j = 0; j < m; j++) {
                    int k = i * m + j;
                    if ((type & (1 << k)) != 0) {
                        currentSum = currentSum * 10 + board[i][j];
                    } else {
                        typeSum += currentSum;
                        currentSum = 0;
                    }
                }
                typeSum += currentSum;
            }

            for (int j = 0; j < m; j++) {
                int currentSum = 0;
                for (int i = 0; i < n; i++) {
                    int k = i * m + j;
                    if ((type & (1 << k)) == 0) {
                        currentSum = currentSum * 10 + board[i][j];
                    } else {
                        typeSum += currentSum;
                        currentSum = 0;
                    }
                }
                typeSum += currentSum;
            }
            result = Math.max(result, typeSum);
        }

        System.out.println(result);
    }
}