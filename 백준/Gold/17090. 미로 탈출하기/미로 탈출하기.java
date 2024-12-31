import java.util.*;
import java.io.*;

class Main {

    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};
    private static final Map<Character, Integer> map = Map.of(
        'U', 1,
        'R', 2,
        'D', 0,
        'L', 3
    );

    private static int n, m;
    private static char[][] board;
    private static Boolean[][] able;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new char[n][m];
        able = new Boolean[n][m];
        for (int i = 0; i < n; i++) {
            board[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (able[i][j] != null)
                    continue;
                dfs(i, j);
            }
        }
        int count = 0;

        for (Boolean[] row : able) {
            for (Boolean value : row) {
                if (Boolean.TRUE == value) {
                    count++;
                }
            }
        }

        System.out.println(count);
    }

    private static boolean dfs(int x, int y) {
        if (able[x][y] != null) {
            return able[x][y];
        }
        able[x][y] = false;
        int index = map.get(board[x][y]);
        int nx = x + dx[index];
        int ny = y + dy[index];
        if (nx < 0 || nx == n || ny < 0 || ny == m) {
            able[x][y] = true;
            return true;
        }
        able[x][y] = dfs(nx, ny);
        return able[x][y];
    }
}
