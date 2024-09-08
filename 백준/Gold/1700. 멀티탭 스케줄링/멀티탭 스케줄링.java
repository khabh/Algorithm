import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        List<Node> list = new ArrayList<>();
        for (int i = 0; i <= k; i++) {
            list.add(new Node(i));
        }
        int[] nodes = new int[k];
        for (int i = 0; i < k; i++) {
            int node = Integer.parseInt(st.nextToken());
            nodes[i] = node;
            list.get(node).add(i);
        }
        for (Node node : list) {
            node.add(k + 1);
        }
        Set<Node> plugs = new HashSet<>();
        int count = 0;
        for (int i : nodes) {
            Node node = list.get(i);
            int first = node.poll();

            if (plugs.contains(node)) {
                continue;
            }
            if (plugs.size() < n) {
                plugs.add(node);
                continue;
            }

            count++;
            Node removed = node;
            for (Node plugged : plugs) {
                if (plugged.peek() > first) {
                    first = plugged.peek();
                    removed = plugged;
                }
            }
            plugs.remove(removed);
            plugs.add(node);
        }
        System.out.println(count);
    }

    static class Node {
        int num;
        Queue<Integer> q = new LinkedList<>();

        public Node(int num) {
            this.num = num;
        }

        public void add(int order) {
            q.add(order);
        }

        public int poll() {
            return q.poll();
        }

        public int peek() {
            return q.peek();
        }
    }
}
