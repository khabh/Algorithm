import java.util.*;

class Solution {
    public int solution(int[][] board, int[][] skills) {
        int n = board.length;
        int m = board[0].length;
        int[][] changes = new int[n][m];
        for (int[] skill : skills) {
            int r1 = skill[1];
            int c1 = skill[2];
            int r2 = skill[3];
            int c2 = skill[4];
            int degree = skill[5];
            if (skill[0] == 1) {
                degree *= -1;
            }
            changes[r1][c1] += degree;
            if (r2 + 1 < n) {
                changes[r2 + 1][c1] -= degree;
                if (c2 + 1 < m)
                    changes[r2 + 1][c2 + 1] += degree;
            }
            if (c2 + 1 < m) {
                changes[r1][c2 + 1] -= degree;
            }
        } 
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < m; j++) {
                changes[i][j] += changes[i][j - 1];
            }
        }
        for (int j = 0; j < m; j++) {
            for (int i = 1; i < n; i++) {
                changes[i][j] += changes[i - 1][j];
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (changes[i][j] + board[i][j] > 0)
                    count++;
            }
        }
        return count;
    }
}