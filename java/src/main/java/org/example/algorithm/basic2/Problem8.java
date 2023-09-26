package org.example.algorithm.basic2;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Problem8 {
    public static void main(String[] args) {
        new Problem8().solve();
    }

    private boolean[] visited;
    private int[] selected;

    private void dfs(int currentIndex, StringJoiner result) {
        if (currentIndex == selected.length) {
            result.add(Arrays.stream(selected)
                    .mapToObj(Integer::toString)
                    .collect(Collectors.joining(" ")));
            return;
        }

        for (int i = 1; i < visited.length; i++) {
            if (visited[i])
                continue;
            visited[i] = true;
            selected[currentIndex] = i;
            dfs(currentIndex + 1, result);
            visited[i] = false;
        }
    }

    public void solve() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        visited = new boolean[n + 1];
        selected = new int[m];
        StringJoiner result = new StringJoiner("\n");
        dfs(0, result);

        System.out.println(result);
    }
}
