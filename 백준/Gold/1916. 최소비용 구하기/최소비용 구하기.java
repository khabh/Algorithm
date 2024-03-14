import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<List<Node>> links = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            links.add(new ArrayList<>());
        }
        int m = scanner.nextInt();
        for (int i = 0; i < m; i++) {
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            int dist = scanner.nextInt();
            links.get(start).add(new Node(end, dist));
        }
        int init = scanner.nextInt();
        int target = scanner.nextInt();
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(init, 0));
        boolean[] visited = new boolean[n + 1];
        while (!q.isEmpty()) {
            Node node = q.poll();
            int cost = node.cost;
            if (visited[node.end]) {
                continue;
            }
            if (node.end == target) {
                System.out.println(cost);
                break;
            }
            visited[node.end] = true;
            for (Node next : links.get(node.end)) {
                if (visited[next.end]) {
                    continue;
                }
                q.add(new Node(next.end, cost + next.cost));
            }
        }
    }

    private static class Node implements Comparable<Node> {
        private int end;
        private int cost;

        public Node(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return cost - o.cost;
        }
    }
}
