import java.util.*;

class Solution {
    public int solution(int n, int[] tops) {
        int[][] dp = new int[n + 1][2];
        dp[0][0] = 1; // 중간 삼각형이 다른 도형이랑 결합되지 않은 경우
        dp[0][1] = 1; // 중간 삼각형이 다른 도형이랑 결합된 경우
        if (tops[0] == 1) {
            dp[0][1]++;
        }
        for (int i = 1; i <= n; i++) {
            dp[i][1] = (dp[i - 1][0] + dp[i - 1][1]) % 10007;
            dp[i][0] = (dp[i][1] + dp[i - 1][0]) % 10007;
            if (i < n && tops[i] == 1) {
                dp[i][1] = (dp[i][0] + dp[i][1]) % 10007;
            }
        }
        
        return dp[n][0];
    }
}