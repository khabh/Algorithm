package org.example.algorithm.basic2;

import java.util.*;
import java.util.stream.Collectors;

public class Problem31 {

    private static int MAX_VALUE;

    private static boolean isValidPosition(int position, int nextValue, int[] times) {
        if (position < 0 || position >= MAX_VALUE || times[position] != -1)
            return false;
        times[position] = nextValue;
        return true;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        MAX_VALUE = Math.max(k * 2 + 1, n + 1);
        int[] times = new int[MAX_VALUE];
        Arrays.fill(times, -1);
        times[n] = 0;

        int[] prev = new int[MAX_VALUE];
        for (int i = 0; i < MAX_VALUE; i++) {
            prev[i] = i;
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.add(n);

        while (times[k] == -1 && !queue.isEmpty()) {
            int position = queue.poll();
            int nextTime = times[position] + 1;
            if (isValidPosition(position + 1, nextTime, times)) {
                prev[position + 1] = position;
                queue.add(position + 1);
            }
            if (isValidPosition(position - 1, nextTime, times)) {
                prev[position - 1] = position;
                queue.add(position - 1);
            }
            if (isValidPosition(position * 2, nextTime, times)) {
                prev[position * 2] = position;
                queue.add(position * 2);
            }
        }

        List<Integer> result = new ArrayList<>();
        int index = k;
        while (prev[index] != index) {
            result.add(index);
            index = prev[index];
        }
        result.add(n);
        Collections.reverse(result);
        System.out.println(times[k]);
        System.out.println(result.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }
}
