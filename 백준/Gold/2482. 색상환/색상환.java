import java.util.*;

class Main {

    private static final int MOD = 1_000_000_003;
    private static int n, k;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        k = scanner.nextInt();

        int[][][] dp = new int[n][2][k + 1];
        dp[0][0][0] = 1;
        dp[1][1][1] = 1; 
        dp[1][0][0] = 1; 
        solve(dp);
        int result = (dp[n - 1][1][k] + dp[n - 1][0][k]) % MOD;
        dp = new int[n][2][k + 1];
        dp[1][0][1] = 1;
        solve(dp);
        result = (result + dp[n - 1][0][k]) % MOD;
        System.out.println(result);
    }

    private static void solve(int[][][] dp) {
        for (int i = 2; i < n; i++) {
            int[][] prev = dp[i - 1];
            for (int j = 0; j <= k; j++) {
                if (prev[0][j] != 0) {
                    dp[i][0][j] = (dp[i][0][j] + prev[0][j]) % MOD;
                    if (j < k)
                    dp[i][1][j + 1] = (dp[i][1][j + 1] + prev[0][j]) % MOD;
                }
                if (prev[1][j] != 0) {
                    dp[i][0][j] = (dp[i][0][j] +  prev[1][j]) % MOD;
                }
            }
        }
    }
}
