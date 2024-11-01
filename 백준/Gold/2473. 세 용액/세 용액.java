import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        long[] values = new long[n];
        for (int i = 0; i < n; i++) {
            values[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(values);
        long result = Long.MAX_VALUE;
        long[] numbers = new long[3];
        for (int i = 0; i < n; i++) {
            int left = i + 1;
            int right = n - 1;
            long target = -values[i];
            
            while (left < right) {
                long sum = values[left] + values[right];
                if (sum == target) {
                    System.out.println(values[i] + " " + values[left] + " " + values[right]);
                    return;
                }
                
                long temp = Math.abs(sum - target);
                if (result > temp) {
                    result = temp;
                    numbers = new long[]{values[i], values[left], values[right]};
                }
                if (sum > target) {
                    right--;
                    continue;
                }
                left++;
            }
        }
        System.out.println(numbers[0] + " " + numbers[1] + " " +  numbers[2]);
    }
}
