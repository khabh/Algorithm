package org.example.algorithm.basic2;

import java.util.HashMap;
import java.util.Optional;
import java.util.Scanner;

public class Problem35 {
    class Tree {
        HashMap<Character, Node> nodes = new HashMap<>();

        public void setNodes(int count) {
            char start = 'A';
            for (int i = 0; i < count; i++) {
                char next = (char)(start + i);
                nodes.put(next, new Node(next));
            }
        }

        public void prev(Node node, StringBuilder result) {
            result.append(node.current);
            node.left.ifPresent(next -> prev(next, result));
            node.right.ifPresent(next -> prev(next, result));
        }

        public void mid(Node node, StringBuilder result) {
            node.left.ifPresent(next -> mid(next, result));
            result.append(node.current);
            node.right.ifPresent(next -> mid(next, result));
        }

        public void back(Node node, StringBuilder result) {
            node.left.ifPresent(next -> back(next, result));
            node.right.ifPresent(next -> back(next, result));
            result.append(node.current);
        }

        public void setConnection(Character current, Character left, Character right) {
            Node parent = nodes.get(current);
            if (nodes.containsKey(left))
                parent.setLeft(nodes.get(left));
            if (nodes.containsKey(right))
                parent.setRight(nodes.get(right));
        }

        public Node getTopNode() {
            return nodes.values()
                    .stream()
                    .filter(Node::noParentNode)
                    .findFirst().get();
        }

        class Node {
            char current;
            Optional<Node> left = Optional.empty();
            Optional<Node> right = Optional.empty();
            Optional<Node> parent = Optional.empty();

            public Node(char current) {
                this.current = current;
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

            public boolean noParentNode() {
                return parent.isEmpty();
            }
        }

    }

    public static void main(String[] args) {
        new Problem35().solve();
    }

    public void solve() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Tree tree = new Tree();
        tree.setNodes(n);
        for (int i = 0; i < n; i++) {
            tree.setConnection(scanner.next().charAt(0), scanner.next().charAt(0), scanner.next().charAt(0));
        }
        Tree.Node topNode = tree.getTopNode();
        StringBuilder sb = new StringBuilder();
        tree.prev(topNode, sb);
        sb.append("\n");
        tree.mid(topNode, sb);
        sb.append("\n");
        tree.back(topNode, sb);
        System.out.println(sb);
    }
}
