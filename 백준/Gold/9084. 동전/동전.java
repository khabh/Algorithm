import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.valueOf(br.readLine());
        while (t-- > 0) {
            int n = Integer.valueOf(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = Integer.valueOf(st.nextToken());
            }
            int m = Integer.parseInt(br.readLine());
            int[] dp = new int[m + 1];
            dp[0] = 1;

            for (int i = 0; i < n; i++) {
                for (int j = 1; j <= m; j++) {
                    if (j - nums[i] >= 0) {
                        dp[j] += dp[j - nums[i]];
                    }
                }
            }
            System.out.println(dp[m]);
        }
    }
}