import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.valueOf(br.readLine());
        StringJoiner sj = new StringJoiner("\n");
        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.valueOf(st.nextToken());
            int k = Integer.valueOf(st.nextToken());
            List<Node> nodes = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                nodes.add(new Node(i, Integer.valueOf(st.nextToken())));
            }
            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                Node first = nodes.get(Integer.valueOf(st.nextToken()) - 1);
                Node second = nodes.get(Integer.valueOf(st.nextToken()) - 1);
                first.addChild(second);
            }
            int target = Integer.valueOf(br.readLine()) - 1;
            Queue<Node> q = new LinkedList<>();
            int[] costs = new int[n];
            for (Node node : nodes) {
                if (node.prevCount > 0)
                    continue;
                q.add(node);
            }
            
            
            while (!q.isEmpty()) {
                Node current = q.poll();
                costs[current.num] += current.cost;
                int cost = costs[current.num];
                if (current.num == target) {
                    break;
                }
                for (Node node : current.next) {
                    node.prevCount--;
                    costs[node.num] = Math.max(costs[node.num], cost);
                    if (node.prevCount == 0) {
                        q.add(node);
                    }
                }
            }
            sj.add(String.valueOf(costs[target]));
        }
        System.out.println(sj);
    }

    private static class Node {
        int num;
        int prevCount = 0;
        int cost;
        List<Node> next = new ArrayList<>();

        public Node(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }

        public void addChild(Node node) {
            next.add(node);
            node.prevCount++;
        }
    }
}
