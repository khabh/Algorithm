import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        int[][] board = new int[n][2];
        int[] dp = new int[t + 1];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            board[i][0] = Integer.parseInt(st.nextToken());
            board[i][1] = Integer.parseInt(st.nextToken());
        }
        for (int[] sub : board) {
            int time = sub[0];
            int score = sub[1];
            int[] cur = new int[t + 1];
            for (int i = 0; i < t; i++) {
                cur[i] = dp[i];
            }
            for (int i = time; i <= t; i++) {
                cur[i] = Math.max(dp[i - time] + score, dp[i]);
            }
            dp = cur;
        }
        System.out.println(Arrays.stream(dp).max().getAsInt());
    }
}