package org.example.boj;

import java.util.*;

public class Problem3079 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[] times = new int[n];
        for (int i = 0; i < n; i++) {
            times[i] = scanner.nextInt();
        }
        Arrays.sort(times);
        long start = 0;
        long end = times[n - 1] * (long)m;
        long result = Long.MAX_VALUE;
        while (start <= end) {
            long mid = (start + end) / 2;
            long count = maxSearch(times, mid, m);
            if (count >= m) {
                end = mid - 1;
                result = Math.min(result, mid);
            } else {
                start = mid + 1;
            }
        }
        System.out.println(result);
    }

    private static long maxSearch(int[] times, long t, int m) {
        long totalCount = 0;
        for (int time : times) {
            totalCount += (t / time);
            if (totalCount >= m) {
                return totalCount;
            }
        }
        return totalCount;
    }
}
