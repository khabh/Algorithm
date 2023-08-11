package org.example.practice;

import java.util.*;

public class ShortestPath {

    private final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        new ShortestPath().problem2();
    }

    class Path {
        private final int target;
        private final int distance;

        public Path(int target, int distance) {
            this.target = target;
            this.distance = distance;
        }

        public int getTarget() {
            return target;
        }

        public int getDistance() {
            return distance;
        }
    }

    class Node implements Comparable<Node> {
        private final int number;
        private final int distance;

        public Node(int number, int distance) {
            this.number = number;
            this.distance = distance;
        }

        public int getNumber() {
            return number;
        }

        public int getDistance() {
            return distance;
        }

        @Override
        public int compareTo(Node o) {
            return this.distance - o.distance;
        }
    }

    private void problem2() {
        final int INFINITE = 30000000;
        int cityCount = 0;
        int totalTime = 0;
        int[] distances = new int[30001];
        Arrays.fill(distances, INFINITE);

        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int c = scanner.nextInt();

        List<List<Path>> paths = new ArrayList<>();
        for (int i = 0; i <= n; i++)
            paths.add(new ArrayList<>());

        while (m-- > 0) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int z = scanner.nextInt();

            paths.get(x).add(new Path(y, z));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(c, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int distance = current.getDistance();
            for (Path path : paths.get(current.getNumber())) {
                int cost = distance + path.getDistance();
                int target = path.getTarget();
                if (cost < distances[target]) {
                    if (distances[target] == INFINITE) {
                        cityCount++;
                        totalTime = Math.max(totalTime, cost);
                        pq.add(new Node(target, cost));
                    }
                    distances[target] = cost;
                }
            }
        }
        System.out.printf("%d %d", cityCount, totalTime);
    }

    // 미래 도시
    private static void problem1() {
        final int INFINITE = 200;
        int[][] graph = new int[101][101];
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        for (int i = 1; i <= n; i++) {
            Arrays.fill(graph[i], INFINITE);
            graph[i][i] = 0;
        }

        while (m-- > 0) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();

            graph[a][b] = graph[b][a] = 1;
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }

        int x = scanner.nextInt();
        int k = scanner.nextInt();

        int result = graph[1][k] + graph[k][x];
        if (result >= INFINITE) {
            System.out.println("-1");
        } else {
            System.out.println(result);
        }
    }

}
