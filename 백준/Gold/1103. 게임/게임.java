import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static int result = 0;
    private static int n;
    private static int m;
    private static int[][] board;
    private static int[][] dp;
    private static boolean[][] visited;
    private static final int[] dx = {1, 0, 0, -1};
    private static final int[] dy = {0, 1, -1, 0};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        getBoard(n, m, scanner);
        dp = new int[n][m];
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        System.out.println(solve(0, 0));
    }

    private static int solve(int x, int y) {
        if (visited[x][y]) {
            return -1;
        }
        if (dp[x][y] != -1) {
            return dp[x][y];
        }
        visited[x][y] = true;
        dp[x][y] = 0;
        int move = board[x][y];
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i] * move;
            int ny = y + dy[i] * move;
            int nextResult;
            if (nx < 0 || ny < 0 || nx >= n || ny >= m || board[nx][ny] == -1) {
                nextResult = 0;
            } else {
                nextResult = solve(nx, ny);
            }
            if (nextResult == -1) {
                return -1;
            }
            dp[x][y] = Math.max(dp[x][y], nextResult + 1);
        }
        visited[x][y] = false;
        return dp[x][y];
    }

    private static void getBoard(int n, int m, Scanner scanner) {
        board = new int[n][m];
        for (int i = 0; i < n; i++) {
            String input = scanner.next();
            for (int j = 0; j < m; j++) {
                if (input.charAt(j) == 'H') {
                    board[i][j] = -1;
                    continue;
                }
                board[i][j] = input.charAt(j) - '0';
            }
        }
    }
}
