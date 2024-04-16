import java.util.*;
import java.io.*;
import java.util.stream.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.valueOf(br.readLine());
        while (t-- > 0) {
            solve(br);
        }
    }

    private static void solve(BufferedReader br) throws IOException {
        int n = Integer.valueOf(br.readLine());
        int[] numbers = Arrays.stream(br.readLine().split(" "))
            .mapToInt(Integer::valueOf)
            .toArray();
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            nodes.add(new Node(i));
        }
        for (int i = 0; i < n; i++) {
            Node prev = nodes.get(numbers[i]);
            prev.start = i;
            for (int j = i + 1; j < n; j++) {
                prev.addNext(nodes.get(numbers[j]));
            }
        }

        int m = Integer.valueOf(br.readLine());
        while (m-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            Node first = nodes.get(Integer.valueOf(st.nextToken()));
            Node second = nodes.get(Integer.valueOf(st.nextToken()));
            if (first.start > second.start) {
                Node temp = first;
                first = second;
                second = temp;
            }
            first.moveToNextOf(second);
        }
        List<Node> result = new ArrayList<>();
        Queue<Node> q = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            if (nodes.get(i).prevCount == 0) {
                q.add(nodes.get(i));
                nodes.get(i).visited = true;
                break;
            }
        }
        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (!q.isEmpty()) {
                System.out.println("IMPOSSIBLE");
                return;
            }
            result.add(cur);
            for (Node node : cur.next) {
                node.prevCount--;
                if (node.visited) {
                    System.out.println("IMPOSSIBLE");
                    return;
                }
                if (node.prevCount == 0) {
                    node.visited = true;
                    q.add(node);
                }
            }
        }
        if (result.size() != n) {
            System.out.println("IMPOSSIBLE");
            return;
        }
        System.out.println(result.stream()
            .map(node -> String.valueOf(node.number))
            .collect(Collectors.joining(" ")));
    }

    private static class Node {
        boolean visited = false;
        int number;
        int start;
        int prevCount = 0;
        Set<Node> next = new HashSet<>();

        public Node(int number) {
            this.number = number;
        }

        public void addNext(Node node) {
            node.prevCount++;
            next.add(node);
        }

        public void moveToNextOf(Node o) {
            next.remove(o);
            prevCount++;
            o.prevCount--;
            o.next.add(this);
        }
    }
}
