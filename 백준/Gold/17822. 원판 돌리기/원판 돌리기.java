import java.util.*;
import java.io.*;

public class Main {

    static int n, m;
    static int[][] board;
    static int[] up;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        board = new int[n + 1][m];
        up = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int d = (Integer.parseInt(st.nextToken()) == 0 ? -1 : 1) * Integer.parseInt(st.nextToken());            
            turn(x, d);
            boolean[][] remove = new boolean[n + 1][m];
            int sum = 0;
            int count = 0;
            boolean hasRemoved = false;
            for (int i = 1; i <= n; i++) {
                for (int j = 0; j < m; j++) {
                    int cur = get(i, j);
                    if (cur == 0)
                        continue;
                    sum += cur;
                    count++;
                    if (i < n && cur == get(i + 1, j)) {
                        remove[i][j] = true;
                        remove[i + 1][j] = true;
                        hasRemoved = true;
                    }
                    if (j < m - 1 && cur == get(i, j + 1)) {
                        remove[i][j] = true;
                        remove[i][j + 1] = true;
                        hasRemoved = true;
                        continue;
                    }
                    if (j == m - 1 && cur == get(i, 0)) {
                        remove[i][j] = true;
                        remove[i][0] = true;
                        hasRemoved = true;
                    }
                }
            }
            if (hasRemoved) {
                for (int i = 1; i <= n; i++) {
                    for (int j = 0; j < m; j++) {
                        if (remove[i][j])
                            remove(i, j);
                    }
                }
            } else {
                double avg = (sum / (double) count);
                for (int i = 1; i <= n; i++) {
                    for (int j = 0; j < m; j++) {
                        if (board[i][j] != 0) {
                            double cur = board[i][j];
                            if (cur > avg) {
                                board[i][j]--;
                            }
                            if (cur < avg) {
                                board[i][j]++;
                            }
                        }
                    }
                }
            }
        }
        System.out.println(Arrays.stream(board).flatMapToInt(Arrays::stream).sum());
    }

    private static void turn(int x, int d) {
        for (int i = x; i <= n; i += x)
            up[i] = (up[i] + d + m) % m;
    }

    private static int get(int x, int y) {
        return board[x][(up[x] + y) % (m)];
    }

    private static void remove(int x, int y) {
        y = (up[x] + y) % m;
        board[x][y] = 0;
    }
}
