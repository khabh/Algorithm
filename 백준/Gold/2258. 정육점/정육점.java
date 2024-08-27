import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        PriorityQueue<Node> q = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            q.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        int result = Integer.MAX_VALUE;
        int prevW = 0;
        boolean hasResult = false;
        
        while (!q.isEmpty()) {
            Node first = q.poll();
            Queue<Node> nodes = new LinkedList<>();
            int totalCost = 0;
            nodes.add(first);
            while (!q.isEmpty() && q.peek().c == first.c) {
                nodes.add(q.poll());
            }
            while (!nodes.isEmpty()) {
                Node node = nodes.poll();
                prevW += node.w;
                totalCost += node.c;
                if (prevW >= m) {
                    result = Math.min(totalCost, result);
                    hasResult = true;
                    break;
                }
            }
            while (!nodes.isEmpty()) {
                prevW += nodes.poll().w;
            }
        }
        if (!hasResult) {
            System.out.println(-1);
        } else {
            System.out.println(result);
        }
    }

    static class Node implements Comparable<Node> {
        int w;
        int c;

        public Node(int w, int c) {
            this.w = w;
            this.c = c;
        }

        public int getC() {
            return c;
        }

        @Override
        public int compareTo(Node o) {
            if (c != o.c) {
                return c - o.c;
            }
            return o.w - w;
        }
    }
}