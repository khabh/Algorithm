import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException { 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] nums = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .sorted()
                .toArray();
        int result = 0;
        for (int i = 0; i < n; i++) {
            int target = nums[i];
            int left = 0;
            int right = n - 1;
            
            while (left < right) {
                int cur = nums[left] + nums[right];
                if (cur == target) {
                    if (left != i && right != i) {
                        result++;
                        break;
                    }
                    if (left == i) {
                        left++;
                    } else {
                        right--;
                    }
                    continue;
                } 
                if (cur < target) {
                    left++;
                }
                if (cur > target) {
                    right--;

                }
            }
        }
        System.out.println(result);
    }
}