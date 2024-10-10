import java.util.*;
import java.io.*;
import java.util.stream.*;

public class Main {

    static int n, m;
    static boolean[][] board;
    static boolean[][] visited;
    static int[][] result;

    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        input();
        visited = new boolean[n][m];
        result = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!board[i][j]) {
                    result[i][j] = (result[i][j] + 1) % 10;
                    continue;
                }
                if (!visited[i][j]) {
                    bfs(i, j);
                }
            }
        }
        StringJoiner sj = new StringJoiner("\n");
        for (int i = 0; i < n; i++) {
            sj.add(Arrays.stream(result[i])
             .mapToObj(String::valueOf)
             .collect(Collectors.joining()));
        }
        System.out.println(sj);
    }

    private static void bfs(int a, int b) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{a, b});
        visited[a][b] = true;
        int count = 0;
        Set<Integer> links = new HashSet<>();

        while (!q.isEmpty()) {
            int[] node = q.poll();
            int x = node[0];
            int y = node[1];
            count++;
            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + x;
                int ny = dy[i] + y;
                if (nx < 0 || ny < 0 || nx == n || ny == m || visited[nx][ny])
                    continue;
                if (board[nx][ny]) {
                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny});
                    continue;
                }
                links.add(nx * m + ny);
            }
        }
        for (int link : links) {
            int y = link % m;
            int x = (link - y) / m;
            result[x][y] = (result[x][y] + count) % 10;
        }
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            for (int j = 0; j < m; j++) {
                board[i][j] = input.charAt(j) == '0';
            }
        }
    }
}