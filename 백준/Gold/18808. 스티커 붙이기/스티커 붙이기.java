import java.util.*;
import java.io.*;

class Main {

    static int n, m, k;
    static boolean[][] board;
    static Node[] nodes;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        nodes = new Node[k];
        board = new boolean[n][m];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            String[] cur = new String[r];
            for (int j = 0; j < r; j++) {
                cur[j] = br.readLine();
            }
            nodes[i] = new Node(r, c, cur);
        }
        
        int total = 0;
        for (Node node : nodes) {
            total += add(node); 
        }
        System.out.println(total);
    }

    private static int add(Node node) {
        for (int t = 0; t < 4; t++) {
            int maxX = n - node.r;
            int maxY = m - node.c;
            for (int i = 0; i <= maxX; i++) {
                for (int j = 0; j <= maxY; j++) {
                    int result = add(node.list, i, j);
                    if (result != 0) {
                        return result;
                    }
                }
            }
            node.turn();
        }
        return 0;
    }

    private static int add(List<int[]> points, int x, int y) {
        for (int[] point : points) {
            if (board[x + point[0]][y + point[1]]) {
                return 0;
            }
        }
        for (int[] point : points) {
            board[x + point[0]][y + point[1]] = true;
        }
        return points.size();
    }

    private static class Node {
        public int r;
        public int c;
        public List<int[]> list = new ArrayList<>();

        public Node(int r, int c, String[] board) {
            this.r = r;
            this.c = c;
            for (int i = 0; i < r; i++) {
                String[] cur = board[i].split(" ");
                for (int j = 0; j < c; j++) {
                    if (cur[j].charAt(0) == '1') {
                        list.add(new int[]{i, j});
                    }
                }
            }
        }

        public void turn() {
            for (int[] point : list) {
                int x = point[0];
                int y = point[1];
                point[0] = y;
                point[1] = r - x - 1;
            }
            int temp = r;
            r = c;
            c = temp;
        }
    }
}