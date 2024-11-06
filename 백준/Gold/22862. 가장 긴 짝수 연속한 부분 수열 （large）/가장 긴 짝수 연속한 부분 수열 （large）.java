import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] board = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            board[i] = Integer.parseInt(st.nextToken());
        }
        int left = 0;
        int right = 0;
        int count = 0;
        int length = 0;
        int result = 0;
        while (left <= right) {
            while (count < k && right < n) {
                while (count < k && right < n && odd(board[right])) {
                    count++;
                    right++;
                }
                while (right < n && !odd(board[right])) {
                    length++;
                    right++;
                }
            }
            result = Math.max(result, length);
            if (right == n) {
                break;
            }
            if (count == k) {
                while (left <= right && !odd(board[left])) {
                    left++;
                    length--;
                }
                while (left <= right && odd(board[left])) {
                    left++;
                    count--;
                }
            }
        }
        System.out.println(result);
    }

    private static boolean odd(long num) {
        return num % 2 == 1;
    }
}
