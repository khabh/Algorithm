import java.util.*;
import java.io.*;

public class Main {

    static int n, k;
    static long[] board;
    static long[] count;

    public static void main(String[] args) throws IOException { 
        input();
        count = new long[n];
        count[0] = (long) Math.ceil(board[0] / (double) k);
        long result = count[0];
        long prevSum = k * count[0];
        long prevCountSum = count[0];
        for (int i = 1; i < n; i++) {
            prevSum -= prevCountSum;
            prevCountSum -= (i >= k ? count[i - k] : 0);
            count[i] = (long) Math.ceil(Math.max(board[i] - prevSum, 0) / (double) k);
            result += count[i];
            prevSum += (k * count[i]);
            prevCountSum += count[i];
        }
        System.out.println(result);
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        board = Arrays.stream(br.readLine().split(" "))
                    .mapToLong(Long::parseLong)
                    .toArray();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            long target = Long.parseLong(st.nextToken());
            board[i] = Math.max(0, target - board[i]);
        }
    }
}
