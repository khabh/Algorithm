package org.example.algorithm.basic2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Problem33 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int maxIndex = Math.max(n + 1, k * 2 + 1);
        int[] times = new int[maxIndex];
        Queue<Integer> queue = new LinkedList<>();
        Arrays.fill(times, -1);
        times[n] = 0;
        queue.add(n);
        while (times[k] == -1) {
            int current = queue.poll();
            int currentTime = times[current];
            int next = current * 2;
            while (next < maxIndex && times[next] == -1) {
                times[next] = currentTime;
                queue.add(next);
                next *= 2;
            }
            if (current > 0 && times[current - 1] == -1) {
                queue.add(current - 1);
                times[current - 1] = currentTime + 1;
            }
            if (current + 1 < maxIndex && times[current + 1] == -1) {
                queue.add(current + 1);
                times[current + 1] = currentTime + 1;
            }
        }
        System.out.println(times[k]);
    }
}
