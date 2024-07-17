import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(br.readLine());
        int[] nums = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.valueOf(st.nextToken());
        }
        Arrays.sort(nums);
        int prevIndex = 0;
        int linkedIndex = n - 1;
        int count = 0;
        while (linkedIndex > prevIndex) {
            nums[prevIndex]--;
            count++;
            linkedIndex--;
            if (nums[prevIndex] == 0) {
                prevIndex++;
            }
        }
        System.out.println(count);
    }
}
