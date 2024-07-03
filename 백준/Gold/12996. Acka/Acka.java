import java.util.*;

class Main {

    static int[][] delta = {
        {1, 0, 0}, {0, 1, 0},
        {0, 0, 1}, {1, 1, 0},
        {0, 1, 1}, {1, 0, 1},
        {1, 1, 1}
    };
    static long[][][][] dp;
    static int s, c1, c2, c3;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        s = scanner.nextInt();
        c1 = scanner.nextInt();
        c2 = scanner.nextInt();
        c3 = scanner.nextInt();

        dp = new long[s + 1][c1 + 1][c2 + 1][c3 + 1];
        for (int i = 0; i <= s; i++) {
            for (int j = 0; j <= c1; j++) {
                for (int k = 0; k <= c2; k++) {
                    Arrays.fill(dp[i][j][k], 0);
                }
            }
        }

        dp[0][0][0][0] = 1;

        for (int cur = 1; cur <= s; cur++) {
            for (int i = 0; i <= c1; i++) {
                for (int j = 0; j <= c2; j++) {
                    for (int k = 0; k <= c3; k++) {
                        update(cur, i, j, k);
                    }
                }
            }
        }
        System.out.println(dp[s][c1][c2][c3]);
    }

    private static void update(int cur, int x, int y, int z) {
        long prev = dp[cur - 1][x][y][z]; 
        if (prev == 0) {
            return;
        }
        for (int[] d : delta) {
            int nx = x + d[0];
            int ny = y + d[1];
            int nz = z + d[2];
            if (nx > c1 || ny > c2 || nz > c3) {
                continue;
            }
            dp[cur][nx][ny][nz] = (dp[cur][nx][ny][nz] + prev) % 1_000_000_007;
        }
    }
}
