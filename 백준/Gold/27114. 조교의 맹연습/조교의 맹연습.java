import java.util.*;
import java.io.*;

class Main {

    private static int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[][] arr = new int[][] {
            {Integer.valueOf(st.nextToken()), -1}, 
            {Integer.valueOf(st.nextToken()), 1},
            {Integer.valueOf(st.nextToken()), 2}
        };
        int k = Integer.valueOf(st.nextToken());
        int[][] dp = new int[k + 1][4];
        for (int i = 0; i <= k; i++) Arrays.fill(dp[i], INF);
        dp[0][0] = 0;
        for (int i = 0; i <= k; i++) {
            for (int[] a : arr) {
                int num = a[0];
                if (i + num > k) {
                    continue;
                }
                for (int j = 0; j < 4; j++) {
                    if (dp[i][j] == INF) {
                        continue;
                    }
                    int nextPos = turn(j, a[1]);
                    dp[i + num][nextPos] = Math.min(dp[i + num][nextPos], dp[i][j] + 1);
                }
            }
        }
        if (dp[k][0] == INF) {
            System.out.println(-1);
            return;
        }
        System.out.println(dp[k][0]);
    }

    private static int turn(int prev, int del) {
        int next = (prev + del) % 4;
        if (next < 0) {
            next += 4;
        }
        return next;
    }
}
