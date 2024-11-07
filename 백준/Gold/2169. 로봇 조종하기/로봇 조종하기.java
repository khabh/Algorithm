import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] board = new int[n][m];
        int[] total = new int[n];
        
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int cur = 0;
            for (int j = 0; j < m; j++) {
                int value = Integer.parseInt(st.nextToken());
                cur += value;
                board[i][j] = value;
            }
            total[i] = cur;
        }

        int[] dp = new int[m];
        dp[0] = board[0][0];
        for (int i = 1; i < m; i++) {
            dp[i] = dp[i - 1] + board[0][i];
        }
        for (int i = 1; i < n; i++) {
            int[] fromTop = new int[m];
            for (int j = 0; j < m; j++) {
                fromTop[j] = board[i][j] + dp[j];
            }
            int[] fromLeft = new int[m];
            int[] fromRight = new int[m];
            for (int j = 0; j < m; j++) {
                fromLeft[j] = fromTop[j];
                if (j > 0) {
                    fromLeft[j] = Math.max(fromLeft[j], board[i][j] + fromLeft[j - 1]);
                }
            }
            for (int j = m -1; j >= 0; j--) {
                fromRight[j] = fromTop[j];
                if (j + 1 < m) {
                    fromRight[j] = Math.max(fromRight[j], board[i][j] + fromRight[j + 1]);
                }
                dp[j] = Math.max(fromLeft[j], fromRight[j]);
            }
        }

        System.out.println(dp[m - 1]);
    }
}
