import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] nums = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        int left = 0;
        int right = n - 1;
        int result = Integer.MAX_VALUE;
        while (left < right) {
            int cur = nums[left] + nums[right];
            result = getResult(result, cur);
            if (cur < 0) {
                left++;
            } else {
                right--;
            }
        }
        System.out.println(result);
    }

    private static int getResult(int a, int b) {
        int absA = Math.abs(a);
        int absB = Math.abs(b);
        if (absA <= absB) {
            return a;
        }
        return b;
    }
}