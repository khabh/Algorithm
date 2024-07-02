import java.util.*;
import java.io.*;

class Main {

    static int n;
    static int m;
    static int[][] board;
    static int[][] dp;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.valueOf(st.nextToken());
        m = Integer.valueOf(st.nextToken());
        board = new int[n][m];
        dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.valueOf(st.nextToken());
            }
            Arrays.fill(dp[i], -1);
        }
        dp[n - 1][m - 1] = 1;

        System.out.println(dfs(0, 0));
    }

    private static int dfs(int x, int y) {
        if (dp[x][y] != -1) {
            return dp[x][y];
        }
        
        dp[x][y] = 0;
        int current = board[x][y];
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || nx >= n || ny < 0 || ny >= m || board[nx][ny] >= current) {
                continue;
            }
            dp[x][y] += dfs(nx, ny);
        }

        return dp[x][y];
    }
}
