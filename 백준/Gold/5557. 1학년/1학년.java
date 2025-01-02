import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        long[] dp = new long[21];
        dp[nums[0]] = 1;
        for (int i = 1; i < n - 1; i++) {
            int num = nums[i];
            long[] next = new long[21];
            for (int j = 0; j <= 20; j++) {
                if (j + num <= 20) {
                    next[j + num] += dp[j];
                }
                if (j - num >= 0) {
                    next[j - num] += dp[j];
                }
            }
            dp = next;
        }
        System.out.println(dp[nums[n - 1]]);
    }
}
