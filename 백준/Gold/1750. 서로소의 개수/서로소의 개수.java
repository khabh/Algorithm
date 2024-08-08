
import java.util.*;
import java.io.*;

public class Main {

    private static final int MAX = 100_000;
    private static final int MOD = 10_000_003;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] nums = new int[n];
        int[][] dp = new int[n][MAX + 1]; // dp[i][j]: i번째 숫자까지 고려했을 때 최대공약수가 j인 경우의 개수
        
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            nums[i] = num;
            dp[i][num] = 1; // i번째 숫자 하나만 선택하는 경우
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= MAX; j++) {
                if (dp[i - 1][j] == 0) {
                    continue;
                }
                // 1. dp[i - 1][j]의 경우의 수들에 nums[i]를 추가하지 않는 경우

                dp[i][j] += dp[i - 1][j];
                
                // 2. dp[i - 1][j]의 경우의 수들에 nums[i]를 추가하는 경우   

                int value = gcd(nums[i], j);

                // ex: nums[i] = 10, j = 6
                // gcd(10, 6) = 2
                // gcd(a, b, c) = gcd(gcd(a, b), c) -> dp[i - 1][6]의 모든 경우의 수에 10을 추가로 선택한 후의 최대공약수는 모두 2
                // dp[i][2] += dp[i - 1][6];
                dp[i][value] += dp[i - 1][j];
                dp[i][value] %= MOD;
            }
        }
        System.out.println(dp[n - 1][1]);
    }

    private static int gcd(int a, int b) {
        if (a < b) {
            return gcd(b, a);
        } 
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}