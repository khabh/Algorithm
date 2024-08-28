import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static int[] gap;
    static int[] absGap;

    public static void main(String[] args) throws IOException {
        input();
        int result = absGap[0];
        for (int i = 1; i < n; i++) {
            if (sameSign(gap[i - 1], gap[i])) {
                if (absGap[i - 1] < absGap[i]) {
                    result += (absGap[i] - absGap[i - 1]);
                }
                continue;
            } 
            result += absGap[i];
        }
        System.out.println(result);
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[][] board = new int[n][2];
        gap = new int[n];
        absGap = new int[n];
        for (int i = 0; i < n; i++) {
            board[i][0] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int value = Integer.parseInt(st.nextToken());
            board[i][1] = value;
            gap[i] = value - board[i][0];
            absGap[i] = Math.abs(gap[i]);
        }
    }

    private static boolean sameSign(int a, int b) {
        return a * b > 0;
    }
}