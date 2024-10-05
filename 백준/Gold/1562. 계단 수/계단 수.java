import java.util.*;
import java.io.*;

public class Main {

    private static final int MOD = 1_000_000_000;
    private static long[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int max = 1 << 10;
        dp = new long[n][10][max];
        for (int i = 1; i <= 9; i++) {
            dp[0][i][1 << i] = 1;
        }
        for (int i = 1; i < n; i++) {
            for (int k = 1; k < max; k++) {
                update(i, 0, 1, k);
                update(i, 9, 8, k);
                for (int j = 1; j <= 8; j++) {
                    update(i, j, j - 1, k);
                    update(i, j, j + 1, k);
                }
            }
        }

        long result = 0;
        for (int i = 0; i <= 9; i++) {
            result += dp[n - 1][i][max - 1];
            result %= MOD;
        }
        System.out.println(result);
    }

    private static void update(int i, int j1, int j2, int k) {
        int temp = k | (1 << j1);
        dp[i][j1][temp] += dp[i - 1][j2][k];
        dp[i][j1][temp] %= MOD;
    }
}