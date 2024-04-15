import java.util.*;
import java.io.*;

class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.valueOf(bf.readLine());
        while (t-- > 0) {
            int[][] graph = getGraph(bf);
            StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
            int a = Integer.valueOf(st.nextToken());
            int b = Integer.valueOf(st.nextToken());
            if (graph[a][1] > graph[b][1]) {
                int temp = b;
                b = a;
                a = temp;
            }
            while (graph[b][1] > graph[a][1]) {
                b = graph[b][0];
            }
            while (a != b) {
                b = graph[b][0];
                a = graph[a][0];
            }
            System.out.println(a);
        }
    }

    private static int[][] getGraph(BufferedReader bf) throws IOException {
        int n = Integer.valueOf(bf.readLine());
        List<List<Integer>> links = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            links.add(new ArrayList<>());
        }
        boolean[] isChild = new boolean[n + 1];
        int[][] graph = new int[n + 1][2];

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
            int p = Integer.valueOf(st.nextToken());
            int c = Integer.valueOf(st.nextToken());
            links.get(p).add(c);
            graph[c][0] = p;
            isChild[c] = true;
        }
        int root = getRoot(isChild);
        Queue<Integer> q = new ArrayDeque<>();
        q.add(root);
        graph[root][0] = root;
        graph[root][1] = 1;
        while (!q.isEmpty()) {
            int cur = q.poll();
            int childDepth = graph[cur][1] + 1;
            for (int link : links.get(cur)) {
                graph[link][1] = childDepth;
                q.add(link);
            }
        }
        return graph;
    }

    private static int getRoot(boolean[] isChild) {
        int i = 1;
        while (i < isChild.length) {
            if (!isChild[i]) {
                return i;
            }
            i++;
        }
        throw new RuntimeException();
    }

    private static class Node {
        Node parent;
        List<Node> childs = new ArrayList<>();

        public void addChild(Node node) {
            childs.add(node);
            node.parent = this;
        }
    }
}
