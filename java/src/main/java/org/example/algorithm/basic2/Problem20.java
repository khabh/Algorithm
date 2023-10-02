package org.example.algorithm.basic2;

import java.util.Scanner;

public class Problem20 {

    private static int dfs(int[] numbers, int s, boolean[] visited, int currentSum, int prevIndex) {
        if (prevIndex == numbers.length - 1)
            return 0;
        int totalCount = 0;
        for (int i = prevIndex + 1; i < numbers.length; i++) {
            if (visited[i])
                continue;
            int nextSum = currentSum + numbers[i];
            if (nextSum == s)
                totalCount++;
            visited[i] = true;
            totalCount += dfs(numbers, s, visited, nextSum, i);
            visited[i] = false;
        }

        return totalCount;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int s = scanner.nextInt();
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }

        System.out.println(dfs(numbers, s, new boolean[n], 0, -1));
    }
}
