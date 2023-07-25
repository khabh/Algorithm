package org.example.practice;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BFSDFS {
}

class Pair {
    private final int x;
    private final int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

// 음료수 얼려먹기
class Problem3 {
    static int[][] iceTray = new int[1000][1000];
    static int n;
    static int m;

    public static void main(String[] args) {
        solve();
    }

    static void bfs(int x, int y) {
        int[][] moves = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        iceTray[x][y] = 1;
        Queue<Pair> queue = new LinkedList<>();

        queue.add(new Pair(x, y));

        while (!queue.isEmpty()) {
            Pair ice = queue.poll();
            x = ice.getX();
            y = ice.getY();
            for (int[] move : moves) {
                int nextX = x + move[0];
                int nextY = y + move[1];
                if (nextX >= n || nextY >= m || nextX < 0 || nextY < 0 || iceTray[nextX][nextY] == 1)
                    continue;
                iceTray[nextX][nextY] = 1;
                queue.add(new Pair(nextX, nextY));
            }
        }
    }

    static void solve() {
        int result = 0;

        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        for (int i = 0; i < n; i++) {
            String ice = sc.next();
            for (int j = 0; j < m; j++) {
                iceTray[i][j] = ice.charAt(j) - '0';
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (iceTray[i][j] == 0) {
                    bfs(i, j);

                    result++;
                }
            }
        }

        System.out.println(result);
    }

}

class Triple {
    private final int x;
    private final int y;
    private final int count;

    public Triple(int x, int y, int count) {
        this.x = x;
        this.y = y;
        this.count = count;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getCount() {
        return count;
    }
}


// 미로 탈출
class Problem4 {

    public static void main(String[] args) {
        solve();
    }

    static void solve() {
        char[][] map = new char[200][200];
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        for (int i = 0; i < n; i++) {
            String line = sc.next();
            for (int j = 0; j < m; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        int[][] moves = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        Queue<Triple> queue = new LinkedList<>();
        queue.add(new Triple(0, 0, 1));
        while (!queue.isEmpty()) {
            Triple current = queue.poll();
            int x = current.getX();
            int y = current.getY();
            int nextCount = current.getCount() + 1;

            for (int[] move : moves) {
                int nextX = x + move[0];
                int nextY = y + move[1];
                if (nextX >= n || nextY >= m || nextX < 0 || nextY < 0 || map[nextX][nextY] == '0')
                    continue;
                if (nextX == n - 1 && nextY == m - 1) {
                    System.out.println(nextCount);
                    return;
                }
                map[nextX][nextY] = '0';
                queue.add(new Triple(nextX, nextY, nextCount));
            }
        }
    }
}