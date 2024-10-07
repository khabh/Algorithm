import java.util.*;
import java.io.*;

public class Main {

    static int n, m, f, startX, startY;
    static int[][] board;
    static Map<Integer, int[]> targets = new HashMap<>();
    static Comparator<int[]> cmp = (first, second) -> {
        if (first[2] != second[2]) {
            return first[2] - second[2];
        }
        if (first[0] != second[0]) {
            return first[0] - second[0];
        }
        return first[1] - second[1];
    };

    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        input();
        while (m-- > 0) {
            if (moveTo(2, false) && moveTo(3, true)) {
                continue;
            }
            System.out.println(-1);
            return;
        }
        System.out.println(f);
    }

    private static boolean moveTo(int value, boolean add) {
        PriorityQueue<int[]> q = new PriorityQueue<>(cmp);
        q.add(new int[]{startX, startY, 0});
        boolean[][] visited = new boolean[n][n];
        visited[startX][startY] = true;

        while (!q.isEmpty()) {
            int[] node = q.poll();
            int x = node[0];
            int y = node[1];
            int nextCost = node[2] + 1;
            if (node[2] > f) {
                return false;
            }
            if (board[x][y] >= value && board[x][y] - value != 1) {
                board[x][y] -= value;
                startX = x;
                startY = y;
                if (add) {
                    f += node[2];
                } else {
                    int[] target = targets.get(x * n + y);
                    board[target[0]][target[1]] += 3;
                    f -= node[2];
                }
                return true;
            }
            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + x;
                int ny = dy[i] + y;
                if (nx < 0 || nx == n || ny < 0 || ny == n || board[nx][ny] == 1 || visited[nx][ny])
                    continue;
                visited[nx][ny] = true;
                q.add(new int[]{nx, ny, nextCost});
            }
        }
        return false;
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        f = Integer.parseInt(st.nextToken());
        
        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        startX = Integer.parseInt(st.nextToken()) - 1;
        startY = Integer.parseInt(st.nextToken()) - 1;

        for (int i = 0; i < m; i++) {
            int[] nums = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
            board[nums[0] - 1][nums[1] - 1] = 2;
            targets.put((nums[0] - 1) * n + (nums[1] - 1), new int[] {nums[2] - 1, nums[3] - 1});
        }
    }
}