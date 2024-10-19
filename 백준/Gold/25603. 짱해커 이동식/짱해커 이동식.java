import java.util.*;
import java.io.*;

public class Main {
    
    static int n, k;
    static int[] board;

    public static void main(String[] args) throws IOException { 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        board = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        
        int[] targets = Arrays.stream(board)
                    .distinct()
                    .sorted()
                    .toArray();

        if (k == 1 || targets.length == 1) {
            System.out.println(targets[targets.length - 1]);
            return;
        }
        
        int left = -1;
        int right = targets.length;

        while (left + 1 < right) {
            int mid = (left + right) / 2;
            if (check(targets[mid])) {
                right = mid;
            } else {
                left = mid;
            }
        }
        System.out.println(targets[right]);
    }

    private static boolean check(int target) {
        int count = 0;
        for (int i = 0; i < k; i++) {
            if (board[i] <= target) {
                count++;
            }
        }
        
        if (count == 0) {
            return false;
        }
        for (int i = 0; i + k < n; i++) {
            if (board[i] <= target) {
                count--;
            } 
            if (board[i + k] <= target) {
                count++;
            }
            if (count == 0) {
                return false;
            }
        }
        return true;
    }
}
