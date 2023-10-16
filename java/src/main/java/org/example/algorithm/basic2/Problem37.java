package org.example.algorithm.basic2;

import java.util.*;

public class Problem37 {
    class Tree {
        private final List<Node> nodes;

        public Tree(int nodeCount) {
            nodes = new ArrayList<>();
            for (int i = 1; i <= nodeCount; i++) {
                nodes.add(new Node(i));
            }
        }

        class Result {
            Node node;
            int distance = 0;

            public Result(Node startNode) {
                this.node = startNode;
            }
        }

        public void addConnection(int startIndex, List<Integer> connections) {
            Node startNode = nodes.get(startIndex);
            for (int i = 0; i < connections.size(); i += 2) {
                startNode.addLine(nodes.get(connections.get(i)), connections.get(i + 1));
            }
        }

        public int getDiameter() {
            Node next = getMaxDistance(nodes.get(0)).node;
            return getMaxDistance(next).distance;
        }

        public Result getMaxDistance(Node startNode) {
            int[] distances = new int[nodes.size() + 1];
            Arrays.fill(distances, -1);
            distances[startNode.order] = 0;
            Result result = new Result(startNode);
            Queue<Node> queue = new LinkedList<>();
            queue.add(startNode);
            while (!queue.isEmpty()) {
                Node current = queue.poll();
                if (result.distance < distances[current.order]) {
                    result.distance = distances[current.order];
                    result.node = current;
                }
                current.updateDistance(distances, queue);
            }

            return result;
        }

        class Node {

            public class Line {
                Node target;
                int cost;

                public Line(Node target, int cost) {
                    this.target = target;
                    this.cost = cost;
                }

                public int getTotalCost(int prevCost) {
                    return cost + prevCost;
                }
            }

            int order;
            List<Line> connected = new ArrayList<>();

            public Node(int order) {
                this.order = order;
            }

            public void addLine(Node target, int distance) {
                connected.add(new Line(target, distance));
            }

            public void updateDistance(int[] distances, Queue<Node> queue) {
                int currentDistance = distances[order];
                for (Line line : connected) {
                    Node target = line.target;
                    if (distances[target.order] >= 0)
                        continue;
                    queue.add(target);
                    distances[target.order] = line.getTotalCost(currentDistance);
                }
            }
        }
    }

    private void solve() {
        Scanner scanner = new Scanner(System.in);
        int v = scanner.nextInt();
        Tree tree = new Tree(v);

        for (int i = 0; i < v; i++) {
            int start = scanner.nextInt() - 1;
            List<Integer> lines = new ArrayList<>();
            while (true) {
                int n = scanner.nextInt();
                if (n < 0)
                    break;
                lines.add(n - 1);
                lines.add(scanner.nextInt());
            }
            tree.addConnection(start, lines);
        }

        System.out.println(tree.getDiameter());
    }

    public static void main(String[] args) {
        new Problem37().solve();
    }
}
