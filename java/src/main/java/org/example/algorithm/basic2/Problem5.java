package org.example.algorithm.basic2;

import java.util.*;

public class Problem5 {
    private static final int[] dx = {1, 0, 0, -1};
    private static final int[] dy = {0, 1, -1, 0};

    private static int dfs(int x, int y, int sum, int count, int[][] board) {
        if (count == 3) {
            return sum + board[x][y];
        }
        int temp = board[x][y];
        int result = 0;
        sum += temp;
        count++;
        board[x][y] = 0;

        if (count == 3) {
            int prevX = -1;
            int prevY = -1;
            for (int i = 0; i < 4; i++) {
                int nextX = x + dx[i];
                int nextY = y + dy[i];
                if (nextX == board.length || nextX < 0 || nextY == board[0].length || nextY < 0 || board[nextX][nextY] != 0)
                    continue;
                prevX = nextX;
                prevY = nextY;
                break;
            }
            for (int i = 0; i < 4; i++) {
                int nextX = prevX + dx[i];
                int nextY = prevY + dy[i];
                if (nextX == board.length || nextX < 0 || nextY == board[0].length || nextY < 0 || board[nextX][nextY] == 0)
                    continue;
                result = Math.max(result, sum + board[nextX][nextY]);
            }
        }

        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];
            if (nextX == board.length || nextX < 0 || nextY == board[0].length || nextY < 0 || board[nextX][nextY] == 0)
                continue;
            result = Math.max(result, dfs(nextX, nextY, sum, count, board));
        }

        board[x][y] = temp;

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] board = new int[n][m];
        int result = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] = scanner.nextInt();
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                result = Math.max(result, dfs(i, j, 0, 0, board));
            }
        }

        System.out.println(result);
    }
}
