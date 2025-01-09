import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringJoiner sj = new StringJoiner("\n");
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] card = new int[n];
            int[] sum = new int[n];
            int prev = 0;
            int[][] dp = new int[n][n + 1];
            for (int i = 0; i < n; i++) {
                card[i] = Integer.parseInt(st.nextToken());
                dp[i][1] = card[i];
                prev += card[i];
                sum[i] = prev;
            }
            for (int j = 2; j <= n; j++) {
                for (int i = 0; i + j - 1 < n; i++) {
                    int cur = sum[i + j - 1] - (i == 0 ? 0 : sum[i - 1]);
                    int left = cur - dp[i + 1][j - 1];
                    int right = cur - dp[i][j - 1];
                    dp[i][j] = Math.max(left, right);
                }
            }
            sj.add(String.valueOf(dp[0][n]));
        }
        System.out.println(sj.toString());
        
    }
}
