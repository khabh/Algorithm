import java.util.*;
import java.io.*;

public class Main {

    static int n, m;
    static char[][] board;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int broke = 0;
    static int total = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new char[n][m];
        for (int i = 0; i < n; i++) {
            board[i] = br.readLine().toCharArray();
        }
        int[] root = start();
        Queue<int[]> q = fromRoot(root);
        while (!q.isEmpty()) {
            int[] b = q.poll();
            int x = b[0];
            int y = b[1];
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || ny < 0 || nx == n || ny == m) {
                    continue;
                }
                char cur = board[nx][ny];
                if (cur == '*') {
                    board[nx][ny] = '.';
                    broke++;
                    q.add(new int[]{nx, ny});
                    continue;
                }
                if (cur == '#') {
                    board[nx][ny] = '*';
                }
            }
        }
        System.out.println(broke + " " + (total - broke));
    }

    private static int[] start() {
        int[] root = new int[2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char cur = board[i][j];
                if (cur == '*' || cur == '#') {
                    total++;
                    continue;
                }
                if (cur == '@') {
                    board[i][j] = '.';
                    root = new int[]{i, j};
                }
            }
        }
        return root;
    }

    private static Queue<int[]> fromRoot(int[] root) {
        Queue<int[]> q = new LinkedList<>();
        int x = root[0];
        int y = root[1];
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j <= 2; j++) {
                int nx = x + dx[i] * j;
                int ny = y + dy[i] * j;
                if (nx < 0 || ny < 0 || nx == n || ny == m) {
                    break;
                }
                char cur = board[nx][ny];
                if (cur == '|') {
                    break;
                }
                if (cur == '*') {
                    board[nx][ny] = '.';
                    broke++;
                    q.add(new int[]{nx, ny});
                    continue;
                }
                if (cur == '#') {
                    board[nx][ny] = '*';
                }
            }
        }
        return q;
    }
}
