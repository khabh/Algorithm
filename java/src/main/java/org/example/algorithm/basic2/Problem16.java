package org.example.algorithm.basic2;

import java.util.Scanner;

public class Problem16 {
    private static int calcGap(boolean[] visited, int[][] board) {
        int a = 0;
        int b = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (visited[i] && visited[j]) {
                    a += board[i][j];
                    continue;
                }
                if (!visited[i] && !visited[j]) {
                    b += board[i][j];
                }
            }
        }

        return Math.abs(a - b);
    }

    private static int dfs(int[][] board, boolean[] visited, int current, int prev) {
        int result = Integer.MAX_VALUE;
        if (current == board.length / 2) {
            return calcGap(visited, board);
        }
        if (current >= 1)
            result = Math.min(result, calcGap(visited, board));

        for (int i = prev + 1; i < board.length; i++) {
            if (visited[i])
                continue;
            visited[i] = true;
            result = Math.min(result, dfs(board, visited, current + 1, i));
            if (result == 0)
                return 0;
            visited[i] = false;
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] board = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = scanner.nextInt();
            }
        }
        System.out.println(dfs(board, new boolean[n], 0, -1));
    }
}
