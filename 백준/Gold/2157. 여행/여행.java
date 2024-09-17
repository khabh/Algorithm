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
        int n = Integer.parseInt(st.nextToken());
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            nodes.add(new Node(i));
        }

        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            Node a = nodes.get(Integer.parseInt(st.nextToken()));
            Node b = nodes.get(Integer.parseInt(st.nextToken()));
            if (a.num > b.num) {
                continue;
            }
            int c = Integer.parseInt(st.nextToken());
            a.add(b, c);
        }
        
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], -1);
        }
        dp[1][1] = 0;
        for (int i = 1; i <= n; i++) {
            Node node = nodes.get(i);
            Set<Node> nexts = node.values.keySet();
            for (int j = 0; j < m; j++) {
                if (dp[i][j] == -1) {
                    continue;
                }
                int cur = dp[i][j];
                for (Node next : nexts) {
                    int value = node.values.get(next);
                    dp[next.num][j + 1] = Math.max(cur + value, dp[next.num][j + 1]);
                }
            }
        }

        System.out.println(Arrays.stream(dp[n]).max().getAsInt());
    }

    static class Node {
        int num;
        Map<Node, Integer> values;

        Node(int num) {
            this.num = num;
            this.values = new HashMap<>();
        }

        public void add(Node node, int value) {
            values.put(node, Math.max(values.getOrDefault(node, 0), value));
        }
    }
}