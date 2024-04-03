import java.util.*;

public class Main {
    private static int[] dx = {1, 0, -1, 0};
    private static int[] dy = {0, 1, 0, -1};
    private static int n;
    private static int m;
    private static boolean[][][] board;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        m = scanner.nextInt();
        n = scanner.nextInt();
        board = new boolean[n][m][4];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int value = scanner.nextInt();
                for (int k = 0; k < 4; k++) {
                    if (((1 << k) & value) != 0) {
                        board[i][j][3 - k] = true;
                    }
                }
            }
        }

        int[][] result = new int[n][m];
        int count = 0;
        Map<Integer, Integer> sizes = new HashMap<>();
        int maxSize = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (result[i][j] == 0) {
                    int size = updateResult(result, i, j, ++count);
                    sizes.put(count, size);
                    maxSize = Math.max(size, maxSize);
                }
            }
        }

        int maxSumSize = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i < n - 1 && result[i][j] != result[i + 1][j]) {
                    maxSumSize = Math.max(sizes.get(result[i][j]) + sizes.get(result[i + 1][j]), maxSumSize);
                }
                if (j < m - 1 && result[i][j] != result[i][j + 1]) {
                    maxSumSize = Math.max(sizes.get(result[i][j]) + sizes.get(result[i][j + 1]), maxSumSize);
                }
            }
        }

        System.out.println(count);
        System.out.println(maxSize);
        System.out.println(maxSumSize);
    }

    private static int updateResult(int[][] result, int a, int b, int count) {
        int size = 1;
        boolean[][] visited = new boolean[n][m];
        visited[a][b] = true;
        result[a][b] = count;
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(a, b));
        while (!q.isEmpty()) {
            Node node = q.poll();
            int x = node.x;
            int y = node.y;
            for (int i = 0; i < 4; i++) {
                if (board[x][y][i])
                    continue;
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || ny < 0 || nx >= n || ny >= m || visited[nx][ny])
                    continue;
                size++;
                visited[nx][ny] = true;
                result[nx][ny] = count;
                q.add(new Node(nx, ny));
            }
        }
        return size;
    }

    private static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
