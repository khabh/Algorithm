import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][] board;
    static int n, m;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];
    
        boolean[][] visited = new boolean[n][m];
        int count = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 1) {
                    count++;
                }
            }
        }
        if (count == 0) {
            System.out.println("0\n0");
            return;
        }

        visited[0][0] = true;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0});
        Set<Integer> set = getNext(visited, q);
        
        int time = 1;
        while (set.size() != count) {
            time++;
            count -= set.size();
            q = new LinkedList<>();
            for (int node : set) {
                int x = node / m;
                int y = node % m;
                q.add(new int[]{x, y});
                board[x][y] = 0;
                visited[x][y] = true;
            }
            set = getNext(visited, q);
        }
        System.out.println(time);
        System.out.println(count);
    }

    private static Set<Integer> getNext(boolean[][] visited, Queue<int[]> q) {
        Set<Integer> set = new HashSet<>();
        while (!q.isEmpty()) {
            int[] node = q.poll();
            int x = node[0];
            int y = node[1];
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || ny < 0 || nx == n || ny == m || visited[nx][ny]) {
                    continue;
                }
                if (board[nx][ny] == 1) {
                    set.add(nx * m + ny);
                } else {
                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny});
                }
            }
        }
        return set;
    }
}
