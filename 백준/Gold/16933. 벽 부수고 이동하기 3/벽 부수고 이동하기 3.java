import java.util.*;
import java.io.*;

public class Main {

    static int n, m, k;
    static boolean[][] board;
    static boolean[][][] visited;
    static final int[] dx = {1, -1, 0, 0};
    static final int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        board = new boolean[n][m];
        visited = new boolean[n][m][k + 1];

        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            for (int j = 0; j < m; j++) {
                board[i][j] = input.charAt(j) == '1';
            }
        }

        PriorityQueue<Node> q = new PriorityQueue<>();
        visited[0][0][0] = true;
        q.add(new Node(0, 0, 0, 1, true));

        while (!q.isEmpty()) {
            Node node = q.poll();
            int x = node.x;
            int y = node.y;
            int used = node.used;
            int count = node.count;
            boolean day = node.day;

            if (x == n - 1 && y == m - 1) {
                System.out.println(count);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || ny < 0 || nx == n || ny == m) {
                    continue;
                }
                if (!board[nx][ny] && !visited[nx][ny][used]) {
                    visited[nx][ny][used] = true;
                    q.add(new Node(nx, ny, used, count + 1, !day));
                } else if (used < k && board[nx][ny] && !visited[nx][ny][used + 1]) {
                    visited[nx][ny][used + 1] = true;
                    if (day) {
                        q.add(new Node(nx, ny, used + 1, count + 1, false));
                    } else {
                        q.add(new Node(nx, ny, used + 1, count + 2, false));
                    }
                }
            }
        }
        System.out.println(-1);
    }

    private static class Node implements Comparable<Node> {
        int x;
        int y;
        int used;
        int count;
        boolean day;

        public Node(int x, int y, int used, int count, boolean day) {
            this.x = x;
            this.y = y;
            this.used = used;
            this.count = count;
            this.day = day;
        }

        @Override
        public int compareTo(Node o) {
            return count - o.count;
        }
    }
}
