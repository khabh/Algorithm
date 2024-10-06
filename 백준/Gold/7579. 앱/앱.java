import java.util.*;
import java.io.*;

public class Main {

    static int n, m;
    static int[] mems, costs;

    public static void main(String[] args) throws IOException {
        input();
        int max = n * 100;
        int[][] dp = new int[n][max + 1];
        
        for (int j = costs[0]; j <= max; j++) {
            dp[0][j] = mems[0];
        }
        for (int i = 1; i < n; i++) {
            int mem = mems[i];
            int cost = costs[i];
            for (int j = 0; j <= max; j++) {
                if (j < cost) {
                    dp[i][j] = dp[i - 1][j];
                    continue;
                }
                dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - cost] + mem);
            }
        }

        for (int i = 0; i <= max; i++) {
            if (dp[n - 1][i] >= m) {
                System.out.println(i);
                return;
            }
        }
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        mems = new int[n];
        costs = new int[n];
        st = new StringTokenizer(br.readLine());
        StringTokenizer st1 = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            mems[i] = Integer.parseInt(st.nextToken());
            costs[i] = Integer.parseInt(st1.nextToken());
        }
    }
}