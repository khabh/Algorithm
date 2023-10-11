package org.example.algorithm.basic2;

import java.util.*;


public class Problem34 {

    class Node implements Comparable<Node> {
        int x;
        int y;
        int brokenCount;

        public Node(int x, int y, int brokenCount) {
            this.x = x;
            this.y = y;
            this.brokenCount = brokenCount;
        }

        @Override
        public int compareTo(Node o) {
            return brokenCount - o.brokenCount;
        }
    }

    private int solve() {
        int[] dx = new int[] {1, -1, 0, 0};
        int[] dy = new int[] {0, 0, 1, -1};
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        char[][] board = new char[n][m];


        for (int i = 0; i < n; i++) {
            board[i] = scanner.next().toCharArray();
        }

        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(0, 0, 0));
        board[0][0] = '2';

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (current.x ==  n - 1 && current.y == m - 1)
                return current.brokenCount;
            for (int i = 0; i < 4; i++) {
                int nextX = dx[i] + current.x;
                int nextY = dy[i] + current.y;
                if (nextX < 0 || nextY < 0 || nextX == n || nextY == m || board[nextX][nextY] == '2')
                    continue;
                if (board[nextX][nextY] == '1') {
                    queue.add(new Node(nextX, nextY, current.brokenCount + 1));
                } else {
                    queue.add(new Node(nextX, nextY, current.brokenCount));
                }
                board[nextX][nextY] = '2';
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new Problem34().solve());
    }
}
