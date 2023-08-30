package org.example.solve;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class ShortestPath {

    public static void main(String[] args) {
        new ShortestPath().problem4();
    }

    // 숨바꼭질
    public class Node implements Comparable<Node> {
        int number;
        int distance;

        public Node(int number, int distance) {
            this.number = number;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            if (distance != o.distance)
                return distance - o.distance;
            return number - o.distance;
        }
    }

    public void problem4() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        boolean[][] board = new boolean[n + 1][n + 1];
        boolean[] visited = new boolean[n + 1];
        int m = scanner.nextInt();
        int maxShortestDistance = 0;
        int index = 1;
        int count = 1;

        Queue<Node> nodes = new PriorityQueue<>();

        while (m-- > 0) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            board[a][b] = true;
            board[b][a] = true;
        }

        visited[1] = true;
        for (int i = 2; i <= n; i++) {
            if (board[1][i]) {
                nodes.add(new Node(i, 1));
                visited[i] = true;
            }
        }

        while (!nodes.isEmpty()) {
            Node node = nodes.poll();
            int nextDistance = node.distance + 1;
            if (node.distance > maxShortestDistance) {
                maxShortestDistance = node.distance;
                index = node.number;
                count = 1;
            } else if (node.distance == maxShortestDistance) {
                count++;
            }

            for (int i = 2; i <= n; i++) {
                 if (visited[i] || !board[node.number][i])
                     continue;
                 visited[i] = true;
                 nodes.add(new Node(i, nextDistance));
            }
        }

        System.out.printf("%d %d %d", index, maxShortestDistance, count);
    }

    // 화성 탐사
    public class Path implements Comparable<Path> {
        int endX;
        int endY;
        int distance;

        public Path(int endX, int endY, int distance) {
            this.endX = endX;
            this.endY = endY;
            this.distance = distance;
        }

        @Override
        public int compareTo(Path path) {
            return distance - path.distance;
        }
    }

    public void problem3() {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        while (t-- > 0) {
            int n = scanner.nextInt();
            int[][] board = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    board[i][j] = scanner.nextInt();
                }
            }
            PriorityQueue<Path> paths = new PriorityQueue<>();
            paths.add(new Path(1, 0, board[0][0] + board[1][0]));
            paths.add(new Path(0, 1, board[0][0] + board[0][1]));
            board[0][0] = -1;

            while (!paths.isEmpty()) {
                Path path = paths.poll();
                int x = path.endX;
                int y = path.endY;
                if (x == n - 1 && y == n - 1) {
                    System.out.println(path.distance);
                    break;
                }
                board[x][y] = -1;
                for (int i = 0; i < 4; i++) {
                    int nextX = x + dx[i];
                    int nextY = y + dy[i];
                    if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= n || board[nextX][nextY] == -1)
                        continue;
                    paths.add(new Path(nextX, nextY, path.distance + board[nextX][nextY]));
                }
            }
        }
    }

    // 정확한 순위
    public void problem2() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] comparison = new int[n + 1][n + 1];
        int m = scanner.nextInt();

        while (m-- > 0) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            comparison[a][b] = -1;
            comparison[b][a] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (comparison[i][j] == 0)
                    continue;
                int order = comparison[i][j];
                for (int k = 1; k <= n; k++) {
                    if (comparison[i][k] == 0 && order == comparison[j][k]) {
                        comparison[i][k] = order;
                    }
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            System.out.println(Arrays.toString(comparison[i]));
        }

        System.out.println(Arrays.stream(comparison)
                .filter(c -> Arrays.stream(c).filter(order -> order == 0).count() == 2)
                .count());
    }

    // 플로이드
    public void problem1() {
        int INF = Integer.MAX_VALUE / 2;
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }
        int m = scanner.nextInt();
        while (m-- > 0) {
            int a = scanner.nextInt() - 1;
            int b = scanner.nextInt() - 1;
            int c = scanner.nextInt();
            dist[a][b] = Math.min(c, dist[a][b]);
        }

        for (int stopover = 0; stopover < n; stopover++) {
            for (int start = 0; start < n; start++) {
                for (int end = 0; end < n; end++) {
                    dist[start][end] = Math.min(dist[start][end], dist[start][stopover] + dist[stopover][end]);
                }
            }
        }

        StringBuilder resultMaker = new StringBuilder();
        for (int start = 0; start < n; start++) {
            for (int end = 0; end < n; end++) {
                if (dist[start][end] == INF) {
                    dist[start][end] = 0;
                }
            }
            resultMaker.append(Arrays.toString(dist[start]).replaceAll("[^0-9 ]", ""));
            resultMaker.append('\n');
        }
        System.out.println(resultMaker);
    }
}
