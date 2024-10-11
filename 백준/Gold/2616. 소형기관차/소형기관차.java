import java.util.*;
import java.io.*;

public class Main {

    static int n, m;
    static int[] board;

    public static void main(String[] args) throws IOException {
        input();
        int[][] dp = new int[n + 1][4];
        for (int i = 0; i <= n; i++) {
            int maxCount = Math.min(m, n - i);
            if (i > 0) {
                for (int j = 0; j < 4; j++) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j]);
                }
            }
            for (int count = 1; count <= maxCount; count++) {
                int sum = getSum(i, i + count - 1);
                for (int j = 0; j < 3; j++) {
                    dp[i + count][j + 1] = Math.max(dp[i + count][j + 1], dp[i][j] + sum);
                }
            }
        }
        System.out.println(Arrays.stream(dp[n]).max().getAsInt());
    }

    private static int getSum(int startIndex, int endIndex) {
        return board[endIndex] - (startIndex > 0 ? board[startIndex - 1] : 0); 
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = Arrays.stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();
        for (int i = 1; i < n; i++) {
            board[i] += board[i - 1];
        }
        m = Integer.parseInt(br.readLine());
    }
}