import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] dp = new int[n];
        int[] heights = new int[n];
        for (int i = 0; i < n; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.fill(dp, 1);
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = (heights[i] + i < n - 1) ? dp[i + heights[i] + 1] + 1 : 1;
        }

        StringBuilder sb = new StringBuilder();
        for (int num : dp) {
            sb.append(num).append(" ");
        }
        System.out.println(sb);
    }
}
