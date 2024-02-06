package org.example.boj;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Main19238 {

    static class Node implements Comparable<Node> {
        int count;
        int x;
        int y;

        public Node(int count, int x, int y) {
            this.count = count;
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Node o) {
            if (count != o.count) {
                return count - o.count;
            }
            if (x != o.x) {
                return x - o.x;
            }
            return y - o.y;
        }
    }

    static int n;
    static int m;
    static int total;
    static char[][] board;
    static int x;
    static int y;
    static int[][][] graph;
    private static final int[] dx = {-1, 0, 1, 0};
    private static final int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        total = scanner.nextInt();
        board = new char[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                board[i][j] = scanner.next().charAt(0);
            }
        }
        x = scanner.nextInt();
        y = scanner.nextInt();
        graph = new int[n + 1][n + 1][3];
        for (int i = 1; i <= m; i++) {
            int x1 = scanner.nextInt();
            int y1 = scanner.nextInt();
            int x2 = scanner.nextInt();
            int y2 = scanner.nextInt();
            graph[x1][y1] = new int[] {i, x2, y2};
        }
        boolean[] guest = new boolean[m + 1];
        while (total > 0) {
            PriorityQueue<Node> q = new PriorityQueue<>();
            q.add(new Node(0, x, y));
            boolean[][] visited = new boolean[n + 1][n + 1];
            int temp = total;
            while (!q.isEmpty()) {
                Node node = q.poll();
                total = Math.min(temp - node.count, total);
                if (total <= 0) {
                    System.out.println(-1);
                    return;
                }
                int guestInfo = graph[node.x][node.y][0];
                if (guestInfo != 0 && !guest[guestInfo]) {
                    x = node.x;
                    y = node.y;
                    guest[guestInfo] = true;
                    int dist = calcDist(graph[node.x][node.y][1],  graph[node.x][node.y][2]);
                    if (dist == -1) {
                        System.out.println(-1);
                        return;
                    }
                    total += dist;
                    boolean isEnd = true;
                    for (int i = 1; i <= m; i++) {
                        if (!guest[i]) {
                            isEnd = false;
                            break;
                        }
                    }
                    if (isEnd) {
                        System.out.println(total);
                        return;
                    }
                    break;
                }
                for (int i = 0; i < 4; i++) {
                    int nx = node.x + dx[i];
                    int ny = node.y + dy[i];
                    if (nx < 1 || ny < 1 || nx > n || ny > n || visited[nx][ny] || board[nx][ny] == '1')
                        continue;
                    visited[nx][ny] = true;
                    q.add(new Node(node.count + 1, nx, ny));
                }
            }
        }
    }

    private static int calcDist(int x1, int y1) {
        boolean[][] visited = new boolean[n + 1][n + 1];
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, x, y));
        while (!q.isEmpty()) {
            Node node = q.poll();
            if (node.x == x1 && node.y == y1) {
                x = x1;
                y = y1;
                return node.count;
            }
            if (node.count == total) {
                continue;
            }
            for (int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];
                if (nx < 1 || ny < 1 || nx > n || ny > n || visited[nx][ny] || board[nx][ny] == '1')
                    continue;
                visited[nx][ny] = true;
                q.add(new Node(node.count + 1, nx, ny));
            }
        }
        return -1;
    }
}