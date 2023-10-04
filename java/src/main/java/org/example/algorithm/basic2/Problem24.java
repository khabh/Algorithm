package org.example.algorithm.basic2;

import java.util.*;

public class Problem24 {

    enum Side {
        LEFT,
        RIGHT,
        NONE;

        public Side getOpposite() {
            if (this.equals(LEFT))
                return RIGHT;
            return LEFT;
        }
    }

    class Node {
        final int order;
        Side side = Side.NONE;
        List<Node> connectedNodes = new ArrayList<>();

        public Node(int order) {
            this.order = order;
        }

        private boolean isInSameSideWith(Node other) {
            return side.equals(other.side);
        }

        public boolean isInValidSide() {
            return connectedNodes.stream()
                    .noneMatch(this::isInSameSideWith);
        }

        public void initSide() {
            side = Side.LEFT;
        }

        public Side getOppositeSide() {
            return side.getOpposite();
        }

        public void setSide(Side side) {
            this.side = side;
        }

        public void addConnection(Node node) {
            connectedNodes.add(node);
            node.connectedNodes.add(this);
        }

        public boolean isVisited() {
            return !side.equals(Side.NONE);
        }
    }

    static private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        new Problem24().solve();
    }

    private void solve() {
        int k = scanner.nextInt();
        StringJoiner result = new StringJoiner("\n");
        while (k-- > 0) {
            result.add(getResult());
        }
        System.out.println(result);
    }

    private boolean bfs(Node node) {
        Queue<Node> nodes = new LinkedList<>();
        node.initSide();
        nodes.add(node);

        while (!nodes.isEmpty()) {
            Node currentNode = nodes.poll();
            Side nextSide = currentNode.getOppositeSide();
            for (Node nextNode : currentNode.connectedNodes) {
                if (nextNode.isVisited())
                    continue;
                nextNode.setSide(nextSide);
                if (!nextNode.isInValidSide())
                    return false;
                nodes.add(nextNode);
            }
        }

        return true;
    }

    private String getResult() {
        int v = scanner.nextInt();
        int e = scanner.nextInt();
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            nodes.add(new Node(i));
        }

        while (e-- > 0) {
            Node firstNode = nodes.get(scanner.nextInt() - 1);
            Node secondNode = nodes.get(scanner.nextInt() - 1);
            firstNode.addConnection(secondNode);
        }

        for (Node node : nodes) {
            if (node.isVisited())
                continue;
            if (!bfs(node))
                return "NO";
        }
        return "YES";
    }
}
