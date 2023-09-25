package org.example.algorithm.basic2;

import java.util.Scanner;

public class Problem2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            board[i] = scanner.next().toCharArray();
        }

        int maxResult = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                char current = board[i][j];
                if (i + 1 < n && current != board[i + 1][j]) {
                    maxResult = Math.max(maxResult, switchAndCalcResult(i, j, i + 1, j, board));
                }
                if (j + 1 < n && current != board[i][j + 1]) {
                    maxResult = Math.max(maxResult, switchAndCalcResult(i, j, i, j + 1, board));
                }
            }
            if (maxResult == n)
                break;
        }

        System.out.println(maxResult);
    }

    private static int calcResult(char[][] board) {
        int result = 1;
        for (char[] chars : board) {
            int count = 1;
            for (int j = 1; j < board.length; j++) {
                if (chars[j] == chars[j - 1]) {
                    count++;
                } else {
                    result = Math.max(count, result);
                    count = 1;
                }
            }
            result = Math.max(count, result);
        }

        if (result == board.length) {
            return result;
        }

        for (int i = 0; i < board.length; i++) {
            int count = 1;
            for (int j = 1; j < board.length; j++) {
                if (board[j][i] == board[j - 1][i]) {
                    count++;
                } else {
                    result = Math.max(count, result);
                    count = 1;
                }
            }
            result = Math.max(count, result);
        }

        return result;
    }

    private static int switchAndCalcResult(int x, int y, int nextX, int nextY, char[][] board) {
        char temp = board[x][y];
        board[x][y] = board[nextX][nextY];
        board[nextX][nextY] = temp;
        int result = calcResult(board);
        board[nextX][nextY] = board[x][y];
        board[x][y] = temp;

        return result;
    }
}
