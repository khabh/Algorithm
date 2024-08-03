import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] nums = new int[n];
            int[] dp = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
                dp[i] = nums[i];
            }
            int result = dp[0];
            for (int i = 1; i < n; i++) {
                if (dp[i - 1] > 0) {
                    dp[i] += dp[i - 1];
                }
                result = Math.max(result, dp[i]);
            }
            System.out.println(result);
        }
    }
}
