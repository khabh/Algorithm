import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            nodes.add(new Node(scanner.nextInt(), scanner.nextInt()));
        }
        Collections.sort(nodes);
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for (Node node : nodes) {
            if (!q.isEmpty() && q.peek() <= node.start) {
                q.poll();
            }
            q.add(node.end);
        }
        System.out.println(q.size());
    }

    private static class Node implements Comparable<Node> {
        int start;
        int end;

        public Node(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Node node) {
            if (node.start != start) {
                return start - node.start;
            }
            return end - node.end;
        }
    }
}