import java.util.*;
import java.io.*;

class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] board = new int[n][n];
        int[] sum = new int[2 * n];
        sum[0] = 1;
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int zero = Integer.parseInt(st.nextToken());
            int one = Integer.parseInt(st.nextToken());
            if (one > 0) {
                sum[zero] += 1;
                sum[one + zero] += 1;
            } else {
                sum[one + zero] += 2;
            }
        }
        for (int i = 1; i < 2 * n; i++) {
            sum[i] += sum[i - 1];
        }
        StringBuilder back = new StringBuilder();
        for (int i = n; i < 2 * n - 1; i++) {
            back.append(" " + sum[i]);
        }
        StringBuilder result = new StringBuilder();
        for (int i = n - 1; i >= 0; i--) {
            result.append(sum[i])
                .append(back)
                .append("\n");
        }
        System.out.println(result);
    }
}