import java.util.Scanner;

public class Main {
    private static final int INF = 1_000_000_000;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int k = scanner.nextInt();

        int[][] dp = new int[101][101];
        for (int i = 1; i <= 100; i++) {
            dp[i][0] = 1;
            dp[0][i] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = Math.min(INF, dp[i - 1][j] + dp[i][j - 1]);
            }
        }

        if (k > dp[n][m]) {
            System.out.println("-1");
            return;
        }

        while (n > 0 || m > 0) {
            if (n == 0) {
                System.out.print('z');
                m--;
                continue;
            }
            if (m == 0) {
                System.out.print('a');
                n--;
                continue;
            }
            int count = dp[n - 1][m];
            if (k <= count) {
                System.out.print('a');
                n--;
                continue;
            }
            System.out.print('z');
            k -= count;
            m--;
        }
    }
}
