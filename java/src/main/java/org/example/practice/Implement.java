package org.example.practice;

import java.util.Scanner;

public class Implement {
    public static void main(String[] args) {
        // problem2();
        problem3();
    }

    // 게임 개발
    static void problem3() {
        Scanner sc = new Scanner(System.in);

        // 북 동 남 서
        int[][] moves = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {-1, 0}};
        int[][] map = new int[50][50];
        int result = 1;
        int turn = 0;

        int N = sc.nextInt();
        int M = sc.nextInt();
        int x = sc.nextInt();
        int y = sc.nextInt();
        int dir = sc.nextInt();

        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                map[i][j] = sc.nextInt();

        map[x][y] = 1;

        while (true) {
            dir--;
            turn++;
            if (dir == -1)
                dir = 3;

            int nextX, nextY;
            if (turn == 4) {
                nextX = x - moves[dir][0];
                nextY = y - moves[dir][1];
                if (map[nextX][nextY] == 1)
                    break;
            }
            else {
                nextX = x + moves[dir][0];
                nextY = y + moves[dir][1];
                if (map[nextX][nextY] == 1)
                    continue;
            }
            x = nextX;
            y = nextY;
            map[x][y] = 1;
            turn = 0;
            result++;
        }

        System.out.println(result);
    }

    // 왕실의 나이트
    static void problem2() {
        final int MAX = 8;
        final int MIN = 1;
        int result = 0;

        int[][] steps = new int[][] {
                {1, 2},
                {1, -2},
                {2, 1},
                {2, -1},
                {-1, 2},
                {-1, -2},
                {-2, 1},
                {-2, -1}
        };

        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        int column = input.charAt(0) - 'a' + 1;
        int row = Integer.parseInt(input.substring(1));

        for (int[] step : steps) {
           int nextRow = row + step[0];
           int nextColumn = column + step[1];
           if (nextColumn >= MIN && nextColumn <= MAX && nextRow >= MIN && nextRow <= MAX)
               result++;
        }

        System.out.println(result);
    }
}
