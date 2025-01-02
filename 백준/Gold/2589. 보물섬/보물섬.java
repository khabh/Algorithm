import java.util.*;
import java.io.*;

class Main {

    static int n, m;
    static boolean[][] board;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            for (int j = 0; j < m; j++) {
                board[i][j] = input.charAt(j) == 'L';
            }
        }
        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j]) {
                    result = Math.max(result, bfs(i, j));
                }
            }
        }
        System.out.println(result);
    }

    private static int bfs(int a, int b) {
        boolean[][] visited = new boolean[n][m];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{a, b, 0});
        visited[a][b] = true;
        int result = 0;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int dist = cur[2];
            result = dist;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || ny < 0 || nx == n || ny == m || !board[nx][ny] || visited[nx][ny]) {
                    continue;
                }
                q.add(new int[]{nx, ny, dist + 1});
                visited[nx][ny] = true;
            }
        }
        return result;
    }
}
