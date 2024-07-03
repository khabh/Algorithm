import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.valueOf(br.readLine());

        while (t-- > 0) {
            int n = Integer.valueOf(br.readLine());
            int[] board = new int[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                board[i] = Integer.valueOf(st.nextToken());
            }
            long maxValue = board[n - 1];
            long result = 0;
            for (int i = n - 1; i >= 0; i--) {
                if (board[i] < maxValue) {
                    result += (maxValue - board[i]);
                }
                maxValue = Math.max(maxValue, board[i]);
            }
            System.out.println(result);
        }
    }
}