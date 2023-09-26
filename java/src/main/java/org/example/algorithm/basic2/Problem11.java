package org.example.algorithm.basic2;

import java.util.Scanner;

public class Problem11 {
    public static void main(String[] args) {
        new Problem11().solve();
    }

    private int calcSum(int[] numbers) {
        int sum = 0;
        for (int i = 0; i < numbers.length -1; i++) {
            sum += Math.abs(numbers[i] - numbers[i + 1]);
        }

        return sum;
    }

    private int dfs(int currentIndex, int[] numbers, boolean[] visited, int[] selected) {
        if (currentIndex == numbers.length) {
            return calcSum(selected);
        }
        int result = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (visited[i])
                continue;
            visited[i] = true;
            selected[currentIndex] = numbers[i];
            result = Math.max(result, dfs(currentIndex + 1, numbers, visited, selected));
            visited[i] = false;
        }

        return result;
    }

    public void solve() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] numbers = new int[n];

        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }

        System.out.println(dfs(0, numbers, new boolean[n], new int[n]));
    }
}
