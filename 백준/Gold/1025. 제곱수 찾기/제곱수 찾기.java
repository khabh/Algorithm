import java.util.*;

public class Main {
    private static int result = -1;
    private static int n;
    private static int m;
    private static final int[][] board = new int[9][9];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            String line = scanner.next();
            for (int j = 0; j < m; j++) {
                board[i][j] = line.charAt(j) - '0';
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                iterate(i, j);
            }
        }
        System.out.println(result);
    }

    private static void iterate(int x, int y) {
        for (int ni = -n; ni < n; ni++) {
            for (int nj = -m; nj < m; nj++) {
                calculate(x, y, ni, nj);
            }
        }
    }

    private static void calculate(int x, int y, int nx, int ny) {
        if (nx == 0 && ny == 0) {
            return;
        }
        int t = 0;
        while (x < n && x >= 0 && y < m && y >= 0) {
            t = t * 10 + board[x][y];
            if (Math.abs(Math.sqrt(t) - (int)Math.sqrt(t)) < 1e-10) {
                result = Math.max(t, result);
            }
            x += nx;
            y += ny;
        }
    }
}
