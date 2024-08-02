import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] board = new int[n][2];
        int[] dp = new int[n + 1];
        int max = 0;
        for (int i = 0; i < n; i++) {
            board[i][0] = scanner.nextInt();
            board[i][1] = scanner.nextInt();
        }
        for (int i = 0; i < n; i++) {
            max = Math.max(dp[i], max);
            dp[i] = max;
            int day = board[i][0];
            int cost = board[i][1];
            if (day + i > n) {
                continue;
            }
            dp[day + i] = Math.max(dp[day + i], max + cost);
        }
        System.out.println(Math.max(max, dp[n]));
    }
}
