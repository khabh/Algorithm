package org.example.algorithm.basic2;

import java.util.Arrays;
import java.util.Scanner;

public class Problem12 {

    private static int calcCost(int[][] costs, int[] selected) {
        int maxIndex = selected.length - 1;
        int count = costs[selected[maxIndex]][selected[0]];
        if (count == 0)
            return Integer.MAX_VALUE;
        for (int i = 0; i < maxIndex; i++) {
            count += costs[selected[i]][selected[i + 1]];
        }

        return count;
    }

    private static int dfs(int[][] costs, boolean[] visited, int[] selected, int index) {
        if (index == selected.length) {
            return calcCost(costs, selected);
        }
        int minCost = Integer.MAX_VALUE;
        int prevSelected = -1;
        if (index > 0) {
            prevSelected = selected[index - 1];
        }
        for (int i = 0; i < costs.length; i++) {
            if (visited[i] || (prevSelected != -1 && costs[prevSelected][i] == 0))
                continue;
            selected[index] = i;
            visited[i] = true;
            minCost = Math.min(minCost, dfs(costs, visited, selected, index + 1));
            visited[i] = false;
        }

        return minCost;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] costs = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                costs[i][j] = scanner.nextInt();
            }
        }

        System.out.println(dfs(costs, new boolean[n], new int[n], 0));
    }
}
