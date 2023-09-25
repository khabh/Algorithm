package org.example.algorithm.basic2;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

public class Problem1 {

    private static void printResult(int a, int b, int[] height) {
        StringJoiner stringJoiner = new StringJoiner("\n");
        for (int i = 0; i < 9; i++) {
            if (i == a || i == b)
                continue;
            stringJoiner.add(Integer.toString(height[i]));
        }

        System.out.println(stringJoiner);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] height = new int[9];

        for (int i = 0; i < 9; i++) {
            height[i] = scanner.nextInt();
        }

        int totalHeight = Arrays.stream(height).sum();
        Arrays.sort(height);

        for (int i = 0; i < 8; i++) {
            int temp = totalHeight - height[i];
            for (int j = i + 1; j < 9; j++) {
                if (temp - height[j] == 100) {
                    printResult(i, j, height);
                    return;
                }
                if (temp - height[j] < 100)
                    break;
            }
        }
    }
}
