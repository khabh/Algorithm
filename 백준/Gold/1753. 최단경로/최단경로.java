import java.util.*;
import java.io.*;

class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.valueOf(st.nextToken());
        int e = Integer.valueOf(st.nextToken());
        int k = Integer.valueOf(br.readLine());
        List<Node> nodes = new ArrayList<>();

        for (int i = 0; i <= v; i++) {
            nodes.add(new Node(i));
        }

        while (e-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            Node b = nodes.get(Integer.parseInt(st.nextToken()));
            int cost = Integer.parseInt(st.nextToken());
            nodes.get(a).addLink(b, cost);
        }

        int[] result = new int[v + 1];
        Arrays.fill(result, -1);

        PriorityQueue<Path> q = new PriorityQueue<>();
        q.add(new Path(nodes.get(k), 0));
        while (!q.isEmpty()) {
            Path path = q.poll();
            Node current = path.node;
            if (result[current.num] != -1) {
                continue;
            }
            result[current.num] = path.cost;
            for (Node next : current.links.keySet()) {
                q.add(new Path(next, path.cost + current.links.get(next)));
            }
        }

        for (int i = 1; i <= v; i++) {
            if (result[i] == -1) {
                System.out.println("INF");
            } else {
                System.out.println(result[i]);
            }   
        }
    }

    private static class Node {
        int num;
        Map<Node, Integer> links = new HashMap<>();

        public Node(int num) {
            this.num = num;
        }

        public void addLink(Node node, int cost) {
            if (links.containsKey(node)) {
                links.put(node, Math.min(links.get(node), cost));
                return;
            }
            links.put(node, cost);
        }
    }

    private static class Path implements Comparable<Path> {
        Node node;
        int cost;

        public Path(Node node, int cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(Path path) {
            return cost - path.cost;
        }
    }
}
