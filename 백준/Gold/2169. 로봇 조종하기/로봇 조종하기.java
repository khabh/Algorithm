import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] board = new int[n][m];
        
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] dp = new int[m];
        dp[0] = board[0][0];
        for (int i = 1; i < m; i++) {
            dp[i] = dp[i - 1] + board[0][i];
        }
        
        for (int i = 1; i < n; i++) {
            int[] fromLeft = new int[m];
            int[] fromRight = new int[m];
            
            fromLeft[0] = dp[0] + board[i][0];
            for (int j = 1; j < m; j++) {
                fromLeft[j] = Math.max(fromLeft[j - 1], dp[j]) + board[i][j];
            }

            fromRight[m - 1] = dp[m - 1] + board[i][m - 1];
            for (int j = m - 2; j >= 0; j--) {
                fromRight[j] = Math.max(fromRight[j + 1], dp[j]) + board[i][j];
            }

            for (int j = 0; j < m; j++) {
                dp[j] = Math.max(fromLeft[j], fromRight[j]);
            }
        }

        System.out.println(dp[m - 1]);
    }
}
