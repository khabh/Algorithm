import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] dp = new int[n + 1][k + 1];
        int[][] bags = new int[n + 1][2];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            bags[i][0] = Integer.parseInt(st.nextToken());
            bags[i][1] = Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i <= n; i++) {
            int curW = bags[i][0];
            int curV = bags[i][1];
            for (int j = 0; j <= k; j++) {
                int maxVal = dp[i - 1][j];
                int prevW = j - curW;
                if (prevW >= 0) {
                    maxVal = Math.max(maxVal, dp[i - 1][prevW] + curV);
                }                
                dp[i][j] = maxVal;
            }
        }
        System.out.println(dp[n][k]);
    }
}