import java.util.*;
import java.io.*;

public class Main {


    public static void main(String[] args) throws IOException {
        PriorityQueue<Node> nodes = input();
        long count = 0;
        int prevMax = 0;
        while (!nodes.isEmpty()) {
            Node first = nodes.poll();
            List<Node> cur = new ArrayList<>();
            cur.add(first);
            while (!nodes.isEmpty() && nodes.peek().target == first.target) {
                cur.add(nodes.poll());
            }
            int target = Math.max(prevMax, first.target);
            for (Node node : cur) {
                int date = node.date;
                if (date < target) {
                    int tmp = (int) Math.ceil((double) (target - date) / 30);
                    count += tmp;
                    date += (tmp * 30);
                }
                node.date = date;
                prevMax = Math.max(prevMax, date);
            }
        }
        System.out.println(count);
    }

    private static PriorityQueue<Node> input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        PriorityQueue<Node> nodes = new PriorityQueue<>();
        int[] values = new int[n];
        for (int i = 0; i < n; i ++) {
            values[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nodes.add(new Node(i, values[i], Integer.parseInt(st.nextToken())));
        }
        return nodes;
    }

    private static class Node implements Comparable<Node> {
        int num;
        int date;
        int target;

        public Node(int num, int date, int target) {
            this.num = num;
            this.date = date;
            this.target = target;
        }

        @Override
        public int compareTo(Node o) {
            return target - o.target;
        }
    }
}
