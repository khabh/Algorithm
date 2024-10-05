import java.util.*;
import java.io.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] costs = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                costs[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int max = 1 << n;
        int[][][] dp = new int[n][n][max];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        // 방문한 도시 수, 직전 방문 도시, 전체 방문 도시
        dp[0][0][1 << 0] = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int cur = 1; cur < n; cur++) {
                    if (j == cur || costs[j][cur] == 0)
                        continue;
                    int curCost = costs[j][cur];
                    for (int k = 0; k < max; k++) {
                        int prev = dp[i - 1][j][k];
                        if ((k & (1 << cur)) != 0 || prev == -1) {
                            continue;
                        }
                        int temp = k | (1 << cur);
                        int cost = prev + curCost;
                        if (dp[i][cur][temp] == -1 || dp[i][cur][temp] > cost) {
                            dp[i][cur][temp] = cost;
                        }
                    }
                }
            }
        }

        int result = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int cur = dp[n - 1][i][max - 1];
            if (cur != -1 && costs[i][0] != 0) {
                result = Math.min(result, cur + costs[i][0]);
            }
        }
        System.out.println(result);
    }
}