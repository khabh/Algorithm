import java.util.*;
import java.io.*;

class Main {

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.valueOf(st.nextToken());
        int k = Integer.valueOf(st.nextToken());
        Map<Integer, Deque<Node>> map = new HashMap<>();
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            for (int j = 0; j < n; j++) {
                board[i][j] = input.charAt(j * 2);
                map.put(i * n + j, new ArrayDeque<>());
            }
        }
        List<Node> nodes = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.valueOf(st.nextToken()) - 1;
            int y = Integer.valueOf(st.nextToken()) - 1;
            int dir = Integer.valueOf(st.nextToken()) - 1;
            nodes.add(new Node(i + 1, x, y, dir));
            map.get(x * n + y).addLast(nodes.get(i));
        }
        boolean change = true;
        int turn = 0;
        while (true) {
            turn++;
            if (turn >= 1000 || !change) {
                System.out.println(-1);
                return;
            }
            change = false;
            for (Node node : nodes) {
                int nx = node.x + dx[node.dir];
                int ny = node.y + dy[node.dir];
                if (nx < 0 || ny < 0 || nx == n || ny == n || board[nx][ny] == '2') {
                    node.turn();
                    nx = node.x + dx[node.dir];
                    ny = node.y + dy[node.dir];
                }
                if (nx < 0 || ny < 0 || nx == n || ny == n || board[nx][ny] == '2') {
                    continue;
                }
                change = true;
                Deque<Node> cur = map.get(node.x * n + node.y);
                boolean flipped = board[nx][ny] == '1';
                Deque<Node> moved = new ArrayDeque<>();
                while (true) {
                    Node up = cur.pollLast(); // 2 1 3
                    up.x = nx;
                    up.y = ny;
                    if (flipped) {
                        moved.addFirst(up);
                    } else {
                        moved.addLast(up);
                    }
                    if (up == node) {
                        break;
                    }
                }
                int key = nx * n + ny;
                while (!moved.isEmpty()) {
                    map.get(key).addLast(moved.pollLast());
                }
                if (map.get(key).size() >= 4) {
                    System.out.println(turn);
                    return;
                }
            }
        }
    }

    static class Node {
        static int[] opp = {1, 0, 3, 2};

        int num;
        int x;
        int y;
        int dir;

        public Node(int num, int x, int y, int dir) {
            this.num = num;
            this.x = x;
            this.y = y;
            this.dir = dir;
        }

        public void turn() {
            dir = opp[dir];
        }
    }
}
