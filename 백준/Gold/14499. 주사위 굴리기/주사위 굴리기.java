import java.util.*;
import java.io.*;

public class Main {

    private static final int[][] moves = {
        {},
        {2, 3, 1, 0, 4, 5},
        {3, 2, 0, 1, 4, 5},
        {4, 5, 2, 3, 1, 0},
        {5, 4, 2, 3, 0, 1}
    };
    private static final int[] dx = {0, 0, 0, -1, 1};
    private static final int[] dy = {0, 1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.valueOf(st.nextToken());
        int m = Integer.valueOf(st.nextToken());
        int x = Integer.valueOf(st.nextToken());
        int y = Integer.valueOf(st.nextToken());
        int k = Integer.valueOf(st.nextToken());
        
        int[][] board = new int[n][m];
        int[] dice = new int[6];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.valueOf(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        while (k-- > 0) {
            int dir = Integer.valueOf(st.nextToken());
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            if (nx < 0 || nx == n || ny < 0 || ny == m) {
                continue;
            }
            x = nx;
            y = ny;
            int[] nextDice = new int[6];
            for (int i = 0; i < 6; i++) {
                nextDice[i] = dice[moves[dir][i]];
            }
            dice = nextDice;
            if (board[x][y] == 0) {
                board[x][y] = dice[1];
            } else {
                dice[1] = board[x][y];
                board[x][y] = 0;
            }
            System.out.println(dice[0]);
        }
    }
}
