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
        Arrays.sort(cds);
        long result = 0;

        for (long ab : abs) {
            long target = -ab;
            int lower = lowerBound(cds, target);
            int upper = upperBound(cds, target);
            if (lower < upper || lower == -1 || upper == abs.length) {
                continue;
            }
            result += (lower - upper + 1);
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
        return result;
    }

    private static int lowerBound(long[] arr, long target) {
        int start = -1;
        int end = arr.length;
        while (start + 1 < end) {
            int mid = (start + end) / 2;
            if (arr[mid] <= target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        return start;
    }

    private static int upperBound(long[] arr, long target) {
        int start = -1;
        int end = arr.length;
        while (start + 1 < end) {
            int mid = (start + end) / 2;
            if (arr[mid] >= target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        return end;
    }
}
