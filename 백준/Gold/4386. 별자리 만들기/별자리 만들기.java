import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] parent = new int[n];
        List<Pos> pos = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int x = (int)(scanner.nextDouble() * 100);
            int y = (int)(scanner.nextDouble() * 100);
            pos.add(new Pos(x, y, i));
            parent[i] = i;
        }
        PriorityQueue<Node> q = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            Pos p1 = pos.get(i);
            for (int j = i + 1; j < n; j++) {
                Pos p2 = pos.get(j);
                q.add(new Node(p1, p2));
            }
        }
        double result = 0f;
        while (!q.isEmpty() && n > 0) {
            Node node = q.poll();
            if (!unionParent(parent, node.first, node.second)) {
                continue;
            }
            n--;
            result += node.dist;
        }
        System.out.print(result);
    }

    private static int findParent(int[] parent, int a) {
        while (parent[a] != a) {
            a = parent[a];
        }
        return a;
    }

    private static boolean unionParent(int[] parent, int a, int b) {
        int parentA = findParent(parent, a);
        int parentB = findParent(parent, b);
        if (parentA == parentB) {
            return false;
        }
        if (parentA < parentB) {
            parent[parentA] = parentB;
        } else {
            parent[parentB] = parentA;
        }
        return true;
    }

    static class Pos {
        int x;
        int y;
        int index;

        public Pos(int x, int y, int index) {
            this.x = x;
            this.y = y;
            this.index = index;
        }
    }

    static class Node implements Comparable<Node> {
        int first;
        int second;
        double dist;

        public Node(Pos p1, Pos p2) {
            first = p1.index;
            second = p2.index;
            double x1 = p1.x / (double)100;
            double y1 = p1.y / (double)100;
            double x2 = p2.x / (double)100;
            double y2 = p2.y / (double)100;
            this.dist = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
        }

        @Override
        public int compareTo(Node o) {
            if (dist > o.dist) {
                return 1;
            }
            if (dist == o.dist) {
                return 0;
            }
            return -1;
        }
    }
}
