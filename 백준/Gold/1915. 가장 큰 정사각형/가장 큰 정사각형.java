import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        char[][] board = new char[n][m];

        for (int i = 0; i < n; i++) {
            board[i] = br.readLine().toCharArray();
        }

        int[][][] length = new int[n][m][2];
        int[][] dp = new int[n][m];
        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == '1') {
                    int left = 1 + (i > 0 ? length[i - 1][j][0] : 0);
                    int up = 1 + (j > 0 ? length[i][j - 1][1] : 0);
                    length[i][j][0] = left;
                    length[i][j][1] = up;
                    int prevSize = (i > 0 && j > 0 ? dp[i - 1][j - 1] : 0);
                    int curSize = Math.min(prevSize + 1, Math.min(left, up));
                    dp[i][j] = curSize;
                    result = Math.max(result, curSize * curSize);
                }
            }
        }

        System.out.println(result);
    }
}