import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static int[] nums;

    public static void main(String[] args) throws IOException { 
        input();
        int left = 0;
        int right = n - 1;
        int result = Integer.MAX_VALUE;
        int[] resultValue = new int[2];
        while (left < right && result != 0) {
            int cur = nums[left] + nums[right];
            if (Math.abs(cur) < result) {
                resultValue = new int[]{nums[left], nums[right]};
                result = Math.abs(cur);
            }
            if (cur > 0) {
                right--;
            } else {
                left++;
            }
        }
        System.out.println(resultValue[0] + " " + resultValue[1]);
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        nums = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .sorted()
                .toArray();
    }
}
