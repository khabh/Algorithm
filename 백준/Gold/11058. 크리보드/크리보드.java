import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long[] dp = new long[101];
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1] + 1;
            if (i <= 3) {
                continue;
            }
            for (int j = 3; i - j > 0; j++) {
                dp[i] = Math.max(dp[i], dp[i - j] * (j - 1));
            }
        }
        System.out.println(dp[n]);
    }
}
