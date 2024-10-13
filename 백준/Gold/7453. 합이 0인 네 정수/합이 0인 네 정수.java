import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static long[][] nums;

    public static void main(String[] args) throws IOException { 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        nums = new long[n][4];
        for (int i = 0; i < n; i++) {
            nums[i] = Arrays.stream(br.readLine().split(" "))
                            .mapToLong(Long::parseLong)
                            .toArray();
        } 
        long[] abs = group(0, 1);
        long[] cds = group(2, 3);
        int max = n * n;
        long result = 0;
        int left = 0;
        int right = max - 1;

        while (left < max && right >= 0) {
            long sum = abs[left] + cds[right];

            if (sum == 0) {
                long leftCount = 1;
                long rightCount = 1;

                while (left + 1 < max && abs[left] == abs[left + 1]) {
                    left++;
                    leftCount++;
                }
                while (right - 1 >= 0 && cds[right] == cds[right - 1]) {
                    right--;
                    rightCount++;
                }

                result += (leftCount * rightCount);
                right--;
                left++;
                continue;
            }
            if (sum > 0) {
                right--;
            } else {
                left++;
            }
        }
        
        System.out.println(result);
    }

    private static long[] group(int a, int b) {
        long[] result = new long[n * n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[count++] = nums[i][a] + nums[j][b];
            }
        }
        Arrays.sort(result);
        return result;
    }
}
