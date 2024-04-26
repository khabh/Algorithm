import java.util.*;

class Main {

    public static void main(String[] args) {
        int n = Integer.valueOf(new Scanner(System.in).next());
        int[] dp = new int[31];
        dp[0] = 1;
        dp[2] = 3;
        for (int i = 4; i <= 30; i += 2) {
            dp[i] = dp[i - 2] * 3;
            for (int j = i; j >= 4; j -= 2) {
                dp[i] += (dp[i - j] * 2);
            }
        }
        System.out.println(dp[n]);
    }
}
