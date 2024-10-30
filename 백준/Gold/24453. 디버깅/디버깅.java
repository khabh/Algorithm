import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        boolean[] board = new boolean[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            board[Integer.parseInt(st.nextToken()) - 1] = true;
        }
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        int errorInRange = 0;
        for (int i = 0; i < x; i++) {
            if (board[i]) {
                errorInRange++;
            }
        }
        int minError = errorInRange;
        for (int remove = 0; remove + x < n; remove++) {
            if (board[remove]) {
                errorInRange--;
            }
            if (board[remove + x]) {
                errorInRange++;
            }
            minError = Math.min(minError, errorInRange);
        }
        System.out.println(minError <= y ? m - y : m - minError);
    }
}
