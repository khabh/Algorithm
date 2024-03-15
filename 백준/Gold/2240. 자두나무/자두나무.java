import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        int w = scanner.nextInt();
        int[][] dp = new int[t + 1][w + 1];
        int result = 0;


        int[] board = new int[t + 1];
        for (int i = 1; i <= t; i++) {
            board[i] = scanner.nextInt() - 1;
        }

        for (int time = 1; time <= t; time++) {
            for (int move = 0; move <= w; move++) {
                int current = move % 2;
                int next = board[time];
                if (move == 0) {
                    if (next == move) {
                        dp[time][0] = dp[time - 1][0] + 1;
                    } else {
                        dp[time][0] = dp[time - 1][0];
                    }
                    continue;
                }
                if (current == next) {
                    dp[time][move] = Math.max(dp[time - 1][move - 1], dp[time - 1][move] + 1);
                    continue;
                }
                dp[time][move] = Math.max(dp[time - 1][move], dp[time - 1][move - 1] + 1);
            }
        }

        for (int time = 1; time <= t; time++) {
            for (int move = 0; move <= w; move++) {
                result = Math.max(result, dp[time][move]);
            }
        }
        System.out.println(result);
    }
}
