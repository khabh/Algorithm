import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] board = new int[n];
        for (int i = 0; i < n; i++) {
            board[i] = Integer.parseInt(st.nextToken());
        }

        Set<Integer> set = new HashSet<>();
        int left = 0;
        int right = 0;
        set.add(board[right]);
        long result = 0;
        while (true) {
            result += set.size();
            if (++right == n) {
                break;
            }
            int cur = board[right];
            while (set.contains(cur)) {
                set.remove(board[left]);
                left++;
            }
            set.add(cur);
        }
        System.out.println(result);
    }
}
