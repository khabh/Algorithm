import java.util.*;
import java.io.*;

public class Main {

    
    public static void main(String[] args) throws IOException { 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] nums = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
        boolean[] dp = new boolean[7];
        dp[1] = true;
        
        for (int num : nums) {
            boolean[] next = new boolean[7];
            for (int i = 0; i < 7; i++) {
                if (!dp[i]) {
                    continue;
                }
                next[i] = true;
                int nextVal = (num + i) % 7;
                next[nextVal] = true;
            }
            dp = next;
            if (dp[5]) {
                System.out.println("YES");
                return;
            }
        }

        System.out.println("NO");
    }
}
