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
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                String cur = st.nextToken();
                int num = Integer.parseInt(cur.substring(1));
                if (cur.charAt(0) == 'A') {
                    a[i][j] = num;
                } else {
                    b[i][j] = num;
                }
            }
        }
        for (int j = 0; j < m; j++) {
            for (int i = 1; i < n; i++) {
                b[i][j] += b[i - 1][j];
                int ai = n - 1 - i;
                a[ai][j] += a[ai + 1][j];
            }
            for (int i = 0; i < n; i++) {
                total[i][j] = (i < n - 1 ? a[i + 1][j] : 0) + (i > 0 ? b[i - 1][j] : 0);
            }
        }
        int[][] dp = new int[n][m];
        for (int i = 0; i < n - 1; i++) {
            dp[i][0] = a[i + 1][0];
        }
        for (int j = 1; j < m; j++) {
            dp[0][j] = dp[0][j - 1] + a[1][j];
            for (int i = 1; i < n; i++) {
                dp[i][j] = Math.max(
                    dp[i - 1][j] - (a[i][j] - (i < n - 1 ? a[i + 1][j] : 0)), 
                    Math.max(dp[i - 1][j - 1], dp[i][j - 1]) + total[i][j]
                );
            }
        }
        System.out.println(dp[n - 1][m - 1]);
    }
}
