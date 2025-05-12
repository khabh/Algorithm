import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0) {
                break;
            }

            long[][] dp = new long[2 * n + 1][n + 1];

            dp[1][1] = 1;
            for (int i = 2; i <= 2 * n; i++) {
                dp[i][0] = dp[i - 1][1];
                dp[i][n] = dp[i - 1][n - 1];
                for (int j = 1; j < n; j++) {
                    dp[i][j] = dp[i - 1][j - 1]; 
                    dp[i][j] += dp[i - 1][j + 1];
                }
            }
            sb.append(dp[2 * n][0] + "\n");
        }
        System.out.println(sb);
    }
}
