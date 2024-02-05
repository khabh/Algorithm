package org.example.boj;

import java.util.*;
import java.util.stream.Collectors;

public class Main2660 {

    static class Con {
        int first;
        int second;

        public Con(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] graph = new int[n + 1][n + 1];
        int INF = n + 1;
        for (int i = 1; i <= n; i++) {
            Arrays.fill(graph[i], INF);
            graph[i][i] = 0;
            graph[i][0] = 0;
        }
        Queue<Con> q = new LinkedList<>();

        while (true) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();

            if (a == -1) {
                break;
            }

            graph[a][b] = 1;
            graph[b][a] = 1;
            q.add(new Con(a, b));
        }

        while (!q.isEmpty()) {
            Con cur = q.poll();
            int a = cur.first;
            int b = cur.second;
            int count = graph[a][b];
            for (int i = 1; i <= n; i++) {
                if (i == a || i == b || (graph[a][i] == INF && graph[b][i] == INF)) {
                    continue;
                }
                if (graph[a][i] + count < graph[b][i]) {
                    int temp = graph[a][i] + count;
                    graph[b][i] = temp;
                    graph[i][b] = temp;
                    q.add(new Con(b, i));
                    continue;
                }
                if (graph[b][i] + count < graph[a][i]) {
                    int temp = graph[b][i] + count;
                    graph[a][i] = temp;
                    graph[i][a] = temp;
                    q.add(new Con(a, i));
                }
            }
        }

        List<Integer> result = new ArrayList<>();
        int minCount = n;
        for (int i = 1; i <= n; i++) {
            int current = Arrays.stream(graph[i])
                    .max()
                    .getAsInt();
            if (current > minCount) {
                continue;
            }
            if (current < minCount) {
                minCount = current;
                result = new ArrayList<>();
            }
            result.add(i);
        }

        System.out.println(minCount + " " + result.size());
        System.out.println(result.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" ")));
    }
}
