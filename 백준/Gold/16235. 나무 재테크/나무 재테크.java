import java.util.*;
import java.io.*;

class Main {

    private static final int[] dx = {1, 1, 1, 0, 0, -1, -1, -1};
    private static final int[] dy = {1, 0, -1, 1, -1, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        PriorityQueue<Integer>[][] map = new PriorityQueue[n][n];
        int[][] board = new int[n][n];
        int[][] add = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            Arrays.fill(board[i], 5);
            for (int j = 0; j < n; j++) {
                add[i][j] = Integer.parseInt(st.nextToken());
                map[i][j] = new PriorityQueue<>();
            }
        }
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1].add(Integer.parseInt(st.nextToken()));
        }
        while (k-- > 0) {
            int[][] next = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    PriorityQueue<Integer> q = map[i][j];
                    PriorityQueue<Integer> nextQ = new PriorityQueue<>();
                    while (!q.isEmpty() && q.peek() <= board[i][j]) {
                        int tree = q.poll();
                        board[i][j] -= tree;
                        nextQ.add(++tree);
                        if (tree % 5 == 0) {
                            next[i][j]++;
                        }
                    }
                    while (!q.isEmpty()) {
                        board[i][j] += Math.floor(q.poll() / (double) 2);
                    }
                    board[i][j] += add[i][j];
                    map[i][j] = nextQ;
                }
            }
            for (int a = 0; a < n; a++) {
                for (int b = 0; b < n; b++) {
                    if (next[a][b] == 0)
                        continue;
                    for (int i = 0; i < 8; i++) {
                        int nx = a + dx[i];
                        int ny = b + dy[i];
                        if (nx < 0 || nx == n || ny < 0 || ny == n) {
                            continue;
                        }
                        for (int j = 0; j < next[a][b]; j++) {
                            map[nx][ny].add(1);
                        }
                    }
                }
            }
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result += map[i][j].size();
            }
        }
        
        System.out.println(result);
    }
}
