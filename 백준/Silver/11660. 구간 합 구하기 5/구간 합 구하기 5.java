import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] board = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            board[i][1] = Integer.parseInt(st.nextToken());
            for (int j = 2; j <= n; j++) {
                board[i][j] = board[i][j - 1] + Integer.parseInt(st.nextToken());
            }
        }
        for (int j = 1; j <= n; j++) {
            for (int i = 2; i <= n; i++) {
                board[i][j] += board[i - 1][j];
            }
        }
        StringJoiner sj = new StringJoiner("\n");
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            
            sj.add(String.valueOf(board[x2][y2] - board[x1 - 1][y2] - board[x2][y1 - 1] + board[x1 - 1][y1 - 1]));
        }
        System.out.println(sj.toString());
    }
}
