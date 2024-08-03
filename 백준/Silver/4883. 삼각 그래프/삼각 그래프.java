import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringJoiner sj = new StringJoiner("\n");
        int count = 1;

        while (true) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0) {
                break;
            }

            int[][] board = new int[n][3];
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 3; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int[][] dp = new int[n][3];
            dp[0][1] = board[0][1];
            dp[0][2] = board[0][1] + board[0][2];
            dp[1][0] = board[1][0] + dp[0][1];
            dp[1][1] = board[1][1] + getMin(dp[0][1], dp[0][2], dp[1][0]);
            dp[1][2] = board[1][2] + getMin(dp[0][1], dp[0][2], dp[1][1]);
            for (int i = 2; i < n; i++) {
                dp[i][0] = board[i][0] + getMin(dp[i - 1][0], dp[i - 1][1]);
                dp[i][1] = board[i][1] + getMin(dp[i - 1][0], dp[i - 1][1], dp[i - 1][2], dp[i][0]);
                dp[i][2] = board[i][2] + getMin(dp[i - 1][1], dp[i - 1][2], dp[i][1]);
            }
            sj.add(String.format("%d. %d", count, dp[n - 1][1]));
            count++;
        }
        System.out.println(sj);
    }

    private static int getMin(int... nums) {
        int result = Integer.MAX_VALUE;
        for (int num : nums) {
            result = Math.min(result, num);
        }
        return result;
    }
}