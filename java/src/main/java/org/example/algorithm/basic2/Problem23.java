package org.example.algorithm.basic2;

import java.util.*;


public class Problem23 {

    class Node {
        int order;
        boolean visited = false;
        List<Node> connections = new ArrayList<>();

        public Node(int order) {
            this.order = order;
        }

        public void addConnectedNode(Node node) {
            connections.add(node);
        }

        public void addConnectionWith(Node other) {
            this.addConnectedNode(other);
            other.addConnectedNode(this);
        }

        public boolean isNotVisited() {
            return !visited;
        }

        public void addToVisitedNodes(Queue<Node> visitedNodes) {
            this.visited = true;
            visitedNodes.add(this);
        }

        public List<Node> getConnections() {
            return connections;
        }
    }

    public static void main(String[] args) {
        new Problem23().solve();
    }

    private void bfs(Node startNode) {
        Queue<Node> nodes = new LinkedList<>();
        startNode.addToVisitedNodes(nodes);

        while (!nodes.isEmpty()) {
            Node currentNode = nodes.poll();
            currentNode.getConnections()
                    .stream()
                    .filter(Node::isNotVisited)
                    .forEach(node -> node.addToVisitedNodes(nodes));
        }
    }

    public void solve() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        List<Node> nodes = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            nodes.add(new Node(i));
        }

        while (m-- > 0) {
            Node a = nodes.get(scanner.nextInt() - 1);
            Node b = nodes.get(scanner.nextInt() - 1);
            a.addConnectionWith(b);
        }

        int result = 0;
        for (Node node : nodes) {
            if (node.isNotVisited()) {
                bfs(node);
                result++;
            }
        }

        System.out.println(result);
    }
}
