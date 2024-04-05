import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static final int[] dx = {1, -1, 0, 0};
    static final int[] dy = {0, 0, 1, -1};
    static int n;
    static int m;
    static char[][] board;
    static boolean[][] visitedWater;
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        board = new char[n][m];
        visitedWater = new boolean[n][m];
        
        for (int i = 0; i < n; i++) {
            String input = scanner.next();
            board[i] = input.toCharArray();
        }
        
        Queue<Pos> source = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][m];
        Pos start = getPos('S');
        source.add(start);
        visited[start.x][start.y] = true;
        
        Pos target = getPos('D');
        int time = 0;
        while (true) {
            time++;
            wave();
            Queue<Pos> next = new ArrayDeque<>();
            while (!source.isEmpty()) {
                Pos current = source.poll();
                int x = current.x;
                int y = current.y;
                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if (nx < 0 || ny < 0 || nx == n || ny == m || board[nx][ny] == '*' || visited[nx][ny] || board[nx][ny] == 'X')
                        continue;
                    if (nx == target.x && ny == target.y) {
                        System.out.println(time);
                        return;
                    }
                    visited[nx][ny] = true;
                    next.add(new Pos(nx, ny));
                }
            }
            if (next.isEmpty()) {
                break;
            }
            source = next;
        }
        
        System.out.println("KAKTUS");
    }

    private static Pos getPos(char c) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == c) {
                    return new Pos(i, j);
                }
            }
        }
        throw new RuntimeException();
    }

    private static void wave() {
        List<Pos> waters = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] != '*' || visitedWater[i][j])
                    continue;
                visitedWater[i][j] = true;
                waters.add(new Pos(i, j));
            }
        }

        for (Pos water : waters) {
            int x = water.x;
            int y = water.y;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || ny < 0 || nx == n || ny == m || board[nx][ny] == 'D' || board[nx][ny] == 'X') 
                    continue;
                board[nx][ny] = '*';
            }
        }
    }

    private static class Pos {
        final int x;
        final int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}