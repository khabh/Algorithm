import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
    
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            if (n == 0) {
                break;
            }
            long[] board = new long[n + 1];
            for (int i = 0; i < n; i++) {
                board[i] = Long.parseLong(st.nextToken());
            }
            
            Stack<Integer> stack = new Stack<>();
            long result = 0;
            for (int i = 0; i <= n; i++) {
                long num = board[i];
                while (!stack.isEmpty() && board[stack.peek()] > num) {
                    int prevIndex = stack.pop();
                    int w = stack.isEmpty() ? i : i - stack.peek() - 1;
                    result = Math.max(result, w * board[prevIndex]);
                }
                stack.push(i);
            }
            sb.append(result + "\n");
        }
        System.out.println(sb);
    }
}