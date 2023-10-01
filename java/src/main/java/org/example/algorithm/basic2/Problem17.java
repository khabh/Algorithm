package org.example.algorithm.basic2;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Problem17 {
    static String maxNumber;
    static String minNumber;

    private static void dfs(char[] signs, int current, int[] selected, boolean[] visited) {
        if (current == selected.length) {
            String result = Arrays.stream(selected).mapToObj(Integer::toString)
                    .collect(Collectors.joining());
            if (maxNumber.compareTo(result) < 0)
                maxNumber = result;
            if (minNumber.compareTo(result) > 0)
                minNumber = result;
            return;
        }
        int start = 0;
        int end = 10;
        if (current > 0 && signs[current - 1] == '>') {
            end =  selected[current - 1];
        }
        else if (current > 0 && signs[current - 1] == '<') {
            start =  selected[current - 1];
        }
        for (int i = start; i < end; i++) {
            if (visited[i])
                continue;
            visited[i] = true;
            selected[current] = i;
            dfs(signs, current + 1, selected, visited);
            visited[i] = false;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        char[] signs = new char[n];
        for (int i = 0; i < n; i++) {
            signs[i] = scanner.next().charAt(0);
        }
        maxNumber = "0".repeat(n + 1);
        minNumber = "9".repeat(n + 1);
        dfs(signs, 0, new int[n + 1], new boolean[10]);
        System.out.println(maxNumber);
        System.out.println(minNumber);
    }
}
