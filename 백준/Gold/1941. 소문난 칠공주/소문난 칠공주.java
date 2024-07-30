import java.util.*;
import java.io.*;

public class Main {

    private static final int[] dx = {0, 0, 1, -1};
    private static final int[] dy = {1, -1, 0, 0};
    private static int result = 0;
    private static boolean[][] visited = new boolean[5][5];
    private static char[][] board = new char[5][5];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 5; i++) {
            String input = br.readLine();
            for (int j = 0; j < 5; j++) {
                board[i][j] = input.charAt(j);
            }
        }
        solve(0, 0, 0);
        System.out.println(result);
    }

    private static void solve(int count, int Y, int start) {
        if (count == 7) {
            if (Y <= 3 && isValid()) {
                result++;
            }
            return;
        }
        for (int i = start; i < 25; i++) {
            int x = i / 5;
            int y = i % 5;
            visited[x][y] = true;
            if (board[x][y] == 'Y') {
                solve(count + 1, Y + 1, i + 1);
            } else {
                solve(count + 1, Y, i + 1);
            }
            visited[x][y] = false;
        }
    }

    private static boolean isValid() {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (visited[i][j]) {
                    q.add(i * 5 + j);
                    break;
                }
            }
            if (!q.isEmpty()) {
                break;
            }
        }
        Set<Integer> set = new HashSet<>();
        set.add(q.peek());
        while (!q.isEmpty()) {
            int pos = q.poll();
            int x = pos / 5;
            int y = pos % 5;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                int nextPos = nx * 5 + ny;
                if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5 || !visited[nx][ny] || set.contains(nextPos))
                    continue;
                set.add(nextPos);
                q.add(nextPos);
            }
        }
        return set.size() == 7;
    }
}
