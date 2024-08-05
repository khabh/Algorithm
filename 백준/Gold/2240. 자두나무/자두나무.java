import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int[][] dp = new int[t + 1][w + 1];
        
        int[] nums = new int[t + 1];
        for (int i = 1; i <= t; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 1; i <= t; i++) {
            if (nums[i] == 1) {
                dp[i][0] = dp[i - 1][0] + 1;
            } else {
                dp[i][0] = dp[i - 1][0];
            }
            for (int j = 1; j <= w; j++) {
                if ((nums[i] == 1 && (j % 2 == 0)) || (nums[i] == 2 && (j % 2 == 1))) {
                    dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + 1;
                    continue;
                }
                dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]);
            }
        }
        System.out.println(Arrays.stream(dp[t]).max().getAsInt());
    }
}