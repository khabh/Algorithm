import java.util.*;
import java.io.*;

public class Main {

    static final int[] dx = {1, -1, 0, 0};
    static final int[] dy = {0, 0, 1, -1};

    static int n;
    static int m;
    static char[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new char[n][m];
        for (int i = 0; i < n; i++) {
            board[i] = br.readLine().toCharArray();
        }
        Node start = findStart();
        Set<Node> visited = new HashSet<>();
        Queue<Node> q = new LinkedList<>();
        visited.add(start);
        q.add(start);
        while (!q.isEmpty()) {
            Node node = q.poll();
            int x = node.x;
            int y = node.y;
            int count = node.count;
            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + x;
                int ny = dy[i] + y;
                if (nx < 0 || ny < 0 || nx == n || ny == m || board[nx][ny] == '#') {
                    continue;
                }
                char next = board[nx][ny];
                if (next >= 'A' && next <= 'F' && !node.hasKey(next)) {
                    continue;
                }
                if (next == '1') {
                    System.out.println(count + 1);
                    return;
                }
                Node target = node.to(nx, ny, next);
                if (!visited.contains(target)) {
                    visited.add(target);
                    q.add(target);
                }
            }
        }
        System.out.println(-1);
    }

    private static Node findStart() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m ; j++) {
                if (board[i][j] == '0') {
                    board[i][j] = '.';
                    return new Node(i, j, 0, 0);
                }
            }
        }
        throw new IllegalArgumentException();
    }

    static class Node {
        int keys;
        int count;
        int x;
        int y;

        public Node(int x, int y, int count, int keys) {
            this.x = x;
            this.y = y;
            this.count = count;
            this.keys = keys;
        }

        public Node to(int nx, int ny, char next) {
            if (next == '.') {
                return new Node(nx, ny, count + 1, keys);
            }
            return new Node(nx, ny, count + 1, addKey(next));
        }

        private int addKey(char key) {
            int index = key - 'a';
            return keys | (1 << index);
        }

        public boolean hasKey(char door) {
            int index = door - 'A';
            return (keys & (1 << index)) != 0;
        }

        @Override
        public boolean equals(Object o) {
            Node node = (Node) o;
            return x == node.x && y == node.y && keys == node.keys;
        }

        @Override
        public int hashCode() {
            return Objects.hash(keys, x, y);
        }
    }
}