package org.example.boj;

import java.util.*;

public class Main1707 {

    static class Node {
        int number;
        List<Node> links = new ArrayList<>();

        public Node(int number) {
            this.number = number;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();
        while (k-- > 0) {
            int v = scanner.nextInt();
            int e = scanner.nextInt();
            List<Node> nodes = new ArrayList<>();
            for (int i = 1; i <= v; i++) {
                nodes.add(new Node(i));
            }
            for (int i = 0; i < e; i++) {
                Node first = nodes.get(scanner.nextInt() - 1);
                Node second = nodes.get(scanner.nextInt() - 1);
                first.links.add(second);
                second.links.add(first);
            }
            System.out.println(check(nodes));
        }
    }

    private static String check(List<Node> nodes) {
        boolean[] visited = new boolean[nodes.size() + 1];
        for (Node node : nodes) {
            if (!visited[node.number]) {
                boolean result = check(node, visited);
                if (!result) {
                    return "NO";
                }
            }
        }
        return "YES";
    }

    private static boolean check(Node node, boolean[] visited) {
        int[] g = new int[visited.length];
        g[node.number] = 1;
        visited[node.number] = true;
        Queue<Node> q = new LinkedList<>();
        q.add(node);
        while (!q.isEmpty()) {
            Node cur = q.poll();
            int group = g[cur.number];
            for (Node next : cur.links) {
                if (g[next.number] == group) {
                    return false;
                }
                if (g[next.number] != 0) {
                    continue;
                }
                visited[next.number] = true;
                g[next.number] = group * -1;
                q.add(next);
            }
        }
        return true;
    }
}
