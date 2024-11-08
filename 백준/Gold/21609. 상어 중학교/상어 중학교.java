import java.util.*;
import java.io.*;

class Main {

    private static final Result NONE = new Result(-1, -1, -1, -1, List.of());
    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};
    private static int n;
    private static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int score = 0;
        while (true) {
            Result result = NONE;
            boolean[][] visited = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visited[i][j] && board[i][j] > 0) {
                        Result cur = findResult(i, j, visited);
                        if (cur.size == 1) {
                            continue;
                        }
                        result = Result.compare(result, cur);
                    }
                }
            }
            if (result == NONE) {
                break;
            }
            for (int[] node : result.removed) {
                board[node[0]][node[1]] = -2;
            }
            score += (result.size * result.size);
            fall();
            turn();
            fall();
        }
        System.out.println(score);
    }

    private static void fall() {
        for (int j = 0; j < n; j++) {
            for (int i = n - 2; i >= 0; i--) {
                if (board[i][j] < 0) {
                    continue;
                }
                int x = i;
                while (x + 1 < n && board[x + 1][j] == -2) {
                    x++;
                }
                if (x == i) {
                    continue;
                }
                board[x][j] = board[i][j];
                board[i][j] = -2;
            }
        }
    }

    private static void turn() {
        int[][] next = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                next[n - 1 - j][i] = board[i][j];
            }
        }
        board = next;
    }

    private static Result findResult(int a, int b, boolean[][] visited) {
        List<int[]> q = new ArrayList<>();
        int index = 0;
        q.add(new int[]{a, b});
        visited[a][b] = true;
        int size = 1;
        int num = board[a][b];
        Queue<int[]> clear = new LinkedList<>();
        while (index != q.size()) {
            int[] node = q.get(index++);
            int x = node[0];
            int y = node[1];
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || ny < 0 || nx == n || ny == n || visited[nx][ny]) {
                    continue;
                }
                int next = board[nx][ny];
                if (next != 0 && next != num) {
                    continue;
                }
                if (next == 0) {
                    clear.add(new int[]{nx, ny});
                }
                visited[nx][ny] = true;
                size++;
                q.add(new int[]{nx, ny});
            }
        }

        int rainbowCount = clear.size();
        while (!clear.isEmpty()) {
            int[] node = clear.poll();
            visited[node[0]][node[1]] = false;
        }
        return new Result(a, b, size, rainbowCount, q);
    }

    static class Result {
        int x;
        int y;
        int size;
        int rainbowCount;
        List<int[]> removed;

        public Result(int x, int y, int size, int rainbowCount, List<int[]> removed) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.rainbowCount = rainbowCount;
            this.removed = removed;
        }

        public int compareTo(Result r) {
            if (size != r.size) {
                return size - r.size;
            }
            if (rainbowCount != r.rainbowCount) {
                return rainbowCount - r.rainbowCount;
            }
            if (x != r.x) {
                return x - r.x;
            }
            return y - r.y;
        }

        public static Result compare(Result r1, Result r2) {
            if (r1.compareTo(r2) > 0) {
                return r1;
            }
            return r2;
        }
    }
}
