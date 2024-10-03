import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class Main {

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] board = new int[n][n];
        int[][] dp = new int[n][n];
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                nodes.add(new Node(i, j, board[i][j]));
            }
        }
        Queue<Node> q = nodes.stream()
                .sorted(Comparator.comparingInt(node -> node.value))
                .collect(Collectors.toCollection(LinkedList::new));

        while (!q.isEmpty()) {
            Node node = q.poll();
            int x = node.x, y = node.y;
            int next = dp[x][y] + 1;
            for (int i = 0; i < 4; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];
                if (newX < 0 || newX >= n || newY < 0 || newY >= n || board[newX][newY] <= node.value) {
                    continue;
                }
                dp[newX][newY] = Math.max(next, dp[newX][newY]);
            }
        }

        int result = Arrays.stream(dp)
                .map(values -> Arrays.stream(values).max().getAsInt())
                .max(Integer::compareTo)
                .get() + 1;
        System.out.println(result);
    }

    static class Node {
        int x;
        int y;
        int value;

        public Node(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }
}
