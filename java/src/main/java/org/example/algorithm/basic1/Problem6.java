package org.example.algorithm.basic1;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Problem6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] result = new int[n];
        int k = scanner.nextInt();
        Queue<Integer> queue = new LinkedList<>();
        IntStream.range(1, n + 1)
                .forEach(queue::add);

        for (int index = 0; index < n; index++) {
            int order = k % queue.size();
            if (order == 0) {
                order = queue.size();
            }
            while (order-- > 1) {
                queue.add(queue.poll());
            }
            result[index] = queue.poll();
        }

        System.out.println(Arrays.toString(result)
                .replace('[', '<')
                .replace(']', '>'));
    }
}
