import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.valueOf(br.readLine());
        
        while (t-- > 0) {
            int n = Integer.valueOf(br.readLine());
            String input = br.readLine();
            br.readLine();
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = input.charAt(i) - '0';
            }
            if (n == 1) {
                System.out.println(nums[0]);
            } else if (n % 3 == 0) {
                System.out.println(getMid(0, n / 3, nums));
            } else if ((n - 2) % 3 == 0) {
                System.out.println(getMid(2, (n - 2) / 3, nums) + nums[0]);
            } else {
                System.out.println(getMid(2, (n - 4) / 3, nums) + nums[0] + nums[n - 1]);
            }
        }
    }

    private static int getMid(int start, int count, int[] nums) {
        start += 1;
        int result = 0;
        while (count-- > 0) {
            result += nums[start];
            start += 3;
        }
        return result;
    }
}
