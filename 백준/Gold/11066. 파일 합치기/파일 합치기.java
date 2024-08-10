
import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int[] nums = new int[n + 1];
            int[][] dp = new int[n + 1][n + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                nums[i] = nums[i - 1] + Integer.parseInt(st.nextToken());
                Arrays.fill(dp[i], Integer.MAX_VALUE);
                dp[i][i] = 0;
            }
            for (int i = 1; i < n; i++) {
                dp[i][i + 1] = nums[i + 1] - nums[i - 1];
            }
            for (int k = 2; k <= n; k++) {
                for (int i = 1; i <= n; i++) {
                    int max = Math.min(n, k + i - 1);
                    int gap = nums[max] - nums[i - 1];
                    for (int j = i; j < max; j++) {
                        dp[i][max] = Math.min(dp[i][j] + dp[j + 1][max] + gap, dp[i][max]);
                    }
                }
            }
            System.out.println(dp[1][n]);
        }
    }
}