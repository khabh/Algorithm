import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        if (n == 1) {
            System.out.println(1);
            return;
        }

        int[][] dp = new int[n + 1][2];
        dp[2][1] = 1;
        
        for (int i = 3; i <= n; i++) {
            int half = i / 2;
            if (i % 2 == 0) {
                dp[i][0] = 1 + dp[half - 1][0] + dp[half][0];
            } else {
                dp[i][0] = dp[half][0] * 2 + 1;
            }
            dp[i][1] = 1 + dp[i - 1][0];
        }

        int max = 0;
        for (int i = 1; i <= n; i++) {
            max = Math.max(max, 1 + dp[i - 1][1] + dp[n - i][1]);
        }
        System.out.println(max);
    }
}