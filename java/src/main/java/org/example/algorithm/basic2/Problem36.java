package org.example.algorithm.basic2;

import java.util.*;

public class Problem36 {

    class Tree {
        private int widthCount = 1;

        List<Node> nodes = new ArrayList<>();
        Map<Integer, LevelStatus> status = new HashMap<>();

        public Tree(int nodeCount) {
            for (int order = 1; order <= nodeCount; order++) {
                nodes.add(new Node(order));
            }
        }

        public Node getTopNode() {
            return nodes.stream()
                    .filter(node -> node.parent.isEmpty())
                    .findFirst()
                    .get();
        }

        public String getResult() {
            return status.values().stream()
                    .max(LevelStatus::compareTo)
                    .get()
                    .getResult();
        }

        public void setConnection(int parent, int left, int right) {
            Node current = nodes.get(parent);
            if (left >= 0)
                current.setLeft(nodes.get(left));
            if (right >= 0)
                current.setRight(nodes.get(right));
        }

        public void calcLevel(Node node) {
            Integer height = node.getHeight();
            node.left.ifPresent(this::calcLevel);
            node.setWidth(widthCount++);
            if (status.containsKey(height)) {
                status.get(height).setSecond(node);
            } else {
                status.put(height, new LevelStatus(node));
            }
            node.right.ifPresent(this::calcLevel);
        }

        class LevelStatus implements Comparable<LevelStatus> {
            int height;
            Node first;
            Node second;

            public LevelStatus(Node node) {
                first = node;
                second = node;
                height = node.height;
            }

            public void setSecond(Node node) {
                second = node;
            }

            public int getDistance() {
                return second.width - first.width;
            }

            @Override
            public int compareTo(LevelStatus o) {
                int dist1 = o.getDistance();
                int dist2 = getDistance();
                if (dist1 != dist2) {
                    return dist2 - dist1;
                }
                return o.height - height;
            }

            public String getResult() {
                return String.format("%d %d", height, second.width - first.width + 1);
            }
        }

        class Node {
            int number;
            Optional<Node> left = Optional.empty();
            Optional<Node> right = Optional.empty();
            Optional<Node> parent = Optional.empty();
            int height;
            int width;

            public Node(int number) {
                this.number = number;
            }

            public void setLeft(Node left) {
                this.left = Optional.of(left);
                left.setParent(this);
            }

            public void setRight(Node right) {
                this.right = Optional.of(right);
                right.setParent(this);
            }

            public void setParent(Node parent) {
                this.parent = Optional.of(parent);
            }

            public int getHeight() {
                this.height = parent.map(parent -> parent.height + 1).orElse(1);
                return this.height;
            }

            public void setWidth(int width) {
                this.width = width;
            }
        }
    }

    public static void main(String[] args) {
        new Problem36().solve();
    }

    public void solve() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Tree tree = new Tree(n);
        while (n-- > 0) {
            tree.setConnection(scanner.nextInt() - 1, scanner.nextInt() - 1, scanner.nextInt() - 1);
        }
        tree.calcLevel(tree.getTopNode());
        System.out.println(tree.getResult());
    }
}
