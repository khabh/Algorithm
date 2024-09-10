import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());
        int[] board = new int[h + 1];
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            if (i % 2 == 0) {
                board[1] += 1;
                board[num + 1] -= 1;
            } else {
                board[h - num + 1] += 1;
            }
        }
        int minResult = n;
        int count = 0;
        for (int i = 1; i <= h; i++) {
            board[i] += board[i - 1];
            if (board[i] == minResult) {
                count++;
            }
            if (board[i] < minResult) {
                minResult = board[i];
                count = 1;
            }
        }
        System.out.println(minResult + " " + count);
    }
}