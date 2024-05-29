import java.util.*;
import java.io.*;

class Main {
    static int n = 5;
    static int result = Integer.MAX_VALUE;
    static int[] dx = {1, 0, -1, 0, 0, 0};
    static int[] dy = {0, 1, 0, -1, 0, 0};
    static int[] dz = {0, 0, 0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        int n = 5;
        char[][][] board = new char[n][n][n];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                String[] input = br.readLine().split(" ");
                for (int k = 0; k < n; k++) {
                    board[i][j][k] = input[k].charAt(0);
                }
            }
        }
        flip(0, board, new char[n][n][n]);
        if (result == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }
        System.out.println(result);
    }

    private static void flip(int index, char[][][] board, char[][][] result) {
        if (index == n) {
            shuffle(result, new char[n][n][n], 0, new boolean[n]);
            return;
        }

        char[][] temp = new char[5][5];
        for (int i = 0; i < n; i++) {
            temp[i] = board[index][i].clone();
        }
        for (int k = 0; k < 4; k++) {
            for (int i = 0; i < n; i++) {
                result[index][i] = temp[i].clone();
            }
            flip(index + 1, board, result);
            temp = turn(temp);
        }
    }

    private static char[][] turn(char[][] prev) {
        char[][] temp = new char[5][5];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                temp[i][j] = prev[n - j - 1][i];
            }
        }
        return temp;
    }

    private static void shuffle(char[][][] board, char[][][] result, int index, boolean[] visited) {
        if (index == n) {
            count(result);
            return;
        }
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            result[index] = board[i];
            shuffle(board, result, index + 1, visited);
            visited[i] = false;
        }
    }

    private static void count(char[][][] board) {
        if (board[0][0][0] == '0') {
            return;
        }
        boolean[][][] visited = new boolean[n][n][n];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0, 0, 0});
        visited[0][0][0] = true;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int i = 0; i < 6; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                int nz = cur[2] + dz[i];
                if (nx < 0 || nx == n || ny < 0 || ny == n || nz < 0 || nz == n || visited[nx][ny][nz] || board[nx][ny][nz] == '0') {
                    continue;
                }
                if (nx == n - 1 && ny == n -1 && nz == n - 1) {
                    result = Math.min(cur[3] + 1, result);
                    return;
                }
                q.add(new int[]{nx, ny, nz, cur[3] + 1});
                visited[nx][ny][nz] = true;
            }
        }
    }
}
