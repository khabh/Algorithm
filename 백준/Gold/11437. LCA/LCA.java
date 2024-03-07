import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        List<Node> nodes = new ArrayList<>();
        int n = scanner.nextInt();
        for (int i = 0; i <= n; i++) {
            nodes.add(new Node(i));
        }
        initNodes(nodes, n);
        StringJoiner result = new StringJoiner("\n");
        int m = scanner.nextInt();
        while (m-- > 0) {
            Node first = nodes.get(scanner.nextInt());
            Node second = nodes.get(scanner.nextInt());
            result.add(String.valueOf(findSameParent(first, second)));
        }
        System.out.println(result);
    }

    private static int findSameParent(Node first, Node second) {
        if (first.depth < second.depth) {
            return findSameParent(second, first);
        }
        while (first.depth > second.depth) {
            first = first.parent;
        }
        while (second != first) {
            second = second.parent;
            first = first.parent;
        }
        return first.number;
    }

    private static void initNodes(List<Node> nodes, int n) {
        List<List<Integer>> links = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            links.add(new ArrayList<>());
        }
        for (int i = 0; i < n - 1; i++) {
            int first = scanner.nextInt();
            int second = scanner.nextInt();
            links.get(first).add(second);
            links.get(second).add(first);
        }
        boolean[] visited = new boolean[n + 1];
        Queue<Node> q = new LinkedList<>();
        q.add(nodes.get(1));
        visited[1] = true;
        while (!q.isEmpty()) {
            Node node = q.poll();
            for (int i : links.get(node.number)) {
                if (visited[i])
                    continue;
                visited[i] = true;
                Node child = nodes.get(i);
                q.add(child);
                child.setParent(node);
            }
        }
    }

    private static class Node {
        int number;
        int depth = 0;
        Node parent;

        public Node(int number) {
            this.number = number;
        }

        public void setParent(Node parent) {
            this.parent = parent;
            depth = parent.depth + 1;
        }
    }
}
