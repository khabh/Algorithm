import java.util.*;
import java.io.*;

class Main {
    static int n;
    static int sum = 0;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = (int)Math.pow(2, Integer.valueOf(st.nextToken()));
        int q = Integer.valueOf(st.nextToken());
        int[][] board = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int num = Integer.valueOf(st.nextToken());
                board[i][j] = num;
                sum += num;
            }
        }
        st = new StringTokenizer(br.readLine());
        while (q-- > 0) {
            int width = (int)Math.pow(2, Integer.valueOf(st.nextToken()));
            int[][] next = new int[n][n];
            for (int i = 0; i < n; i++) {
                next[i] = board[i].clone();
            }
            for (int i = 0; i + width <= n; i += width) {
                for (int j = 0; j + width <= n; j += width) {
                    turn(board, next, i, j, width);   
                }
            }
            board = decrease(next);
        }
        boolean[][] visited = new boolean[n][n];
        int maxSize = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && board[i][j] > 0) {
                    maxSize = Math.max(maxSize, count(i, j, visited, board));
                }
            }
        }
        System.out.println(sum);
        System.out.println(maxSize);
    }

    private static void turn(int[][] prev, int[][] next, int x, int y, int width) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                next[x + i][y + j] = prev[x + width - 1 - j][y + i];
            }
        }
    }

    private static int[][] decrease(int[][] prev) {
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int value = prev[i][j];
                if (value == 0) {
                    continue;
                }
                int count = 0;
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if (nx < 0 || ny < 0 || nx == n || ny == n || prev[nx][ny] == 0)
                        continue; 
                    count++;
                    if (count > 2) {
                        break;
                    }
                }
                if (count < 3) {
                    value--;
                    sum--;
                }
                result[i][j] = value;
            }
        }
        return result;
    }

    private static int count(int x, int y, boolean[][] visited, int[][] board) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{x, y});
        visited[x][y] = true;
        int size = 1;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if (nx < 0 || ny < 0 || nx == n || ny == n || visited[nx][ny] || board[nx][ny] == 0)
                    continue;
                size++;
                visited[nx][ny] = true;
                q.add(new int[]{nx, ny});
            }
        }
        return size;
    }
}
