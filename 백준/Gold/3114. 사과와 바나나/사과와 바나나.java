import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] a = new int[n][m];
        int[][] b = new int[n][m];
        int[][] total = new int[n][m];
        int[][][] board = new int[n][m][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                String cur = st.nextToken();
                board[i][j][cur.charAt(0) - 'A'] = Integer.parseInt(cur.substring(1));
            }
        }
        for (int j = 0; j < m; j++) {
            for (int i = 1; i < n; i++) {
                b[i][j] = b[i - 1][j] + board[i - 1][j][1];
                int ai = n - 1 - i;
                a[ai][j] = a[ai + 1][j] + board[ai + 1][j][0];
            }
            for (int i = 0; i < n; i++) {
                total[i][j] = a[i][j] + b[i][j];
            }
        }
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            dp[i][0] = a[i][0];
        }
        for (int j = 1; j < m; j++) {
            dp[0][j] = dp[0][j - 1] + a[0][j];
            for (int i = 1; i < n; i++) {
                dp[i][j] = Math.max(
                    dp[i - 1][j] - board[i][j][0], 
                    Math.max(dp[i - 1][j - 1], dp[i][j - 1]) + total[i][j]
                );
            }
        }
        System.out.println(dp[n - 1][m - 1]);
    }
}
