package org.example.algorithm.basic2;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Problem13 {
    private static final StringJoiner result = new StringJoiner("\n");


    private static void dfs(int[] numbers, int[] selected, int currentIndex, int prev) {
        if (currentIndex == selected.length) {
            result.add(Arrays.stream(selected)
                    .mapToObj(Integer::toString)
                    .collect(Collectors.joining(" ")));
            return;
        }
        int maxIndex = currentIndex - 5 + numbers.length;
        for (int i = prev + 1; i < maxIndex; i++) {
            selected[currentIndex] = numbers[i];
            dfs(numbers, selected, currentIndex + 1, i);
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        StringJoiner result = new StringJoiner("\n");

        while (true) {
            int k = scanner.nextInt();
            if (k == 0) {
                break;
            }
            int[] numbers = new int[k];
            for (int i = 0; i < k; i++) {
                numbers[i] = scanner.nextInt();
            }
            dfs(numbers, new int[6], 0, -1);
            result.add("");
        }
        System.out.println(result);
    }
}
