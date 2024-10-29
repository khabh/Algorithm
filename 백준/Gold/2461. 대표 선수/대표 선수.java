import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] board = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(board[i]);
        }

        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(ints -> ints[0]));
        int min = Integer.MAX_VALUE;
        int max = -1;
        for (int i = 0; i < n; i++) {
            int value = board[i][0];
            min = Math.min(min, value);
            max = Math.max(max, value);
            q.add(new int[]{value, i, 0});
        }

        int result = max - min;
        boolean minFixed = false;
        while (true) {
            int[] cur = q.poll();
            int i = cur[1];
            int j = cur[2];
            if (j == m) {
                break;
            }
            j++;
            int tmp = j == m ? Integer.MAX_VALUE : board[i][j];
            q.add(new int[]{tmp, i, j});
            if (q.peek()[2] == m) {
                break;
            }
            if (!minFixed) {
                min = q.peek()[0];
                if (q.peek()[2] == m - 1) {
                    minFixed = true;
                }
            }
            if (j != m)
                max = Math.max(max, tmp);
            result = Math.min(result, max - min);
        }
        System.out.println(result);
    }
}
