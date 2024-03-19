import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        new Main().solution();
    }

    private final static Scanner scanner = new Scanner(System.in);

    class Path implements Comparable<Path> {
        final int firstNode;
        final int secondNode;
        final int distance;

        public Path(int firstNode, int secondNode, int distance) {
            this.firstNode = firstNode;
            this.secondNode = secondNode;
            this.distance = distance;
        }

        @Override
        public int compareTo(Path o) {
            return distance - o.distance;
        }
    }

    public void solution() {
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        int[] parents = new int[100001];
        boolean[] visited = new boolean[100001];
        int count = n;
        int result = 0;
        PriorityQueue<Path> paths = new PriorityQueue<>();
        for (int i = 1; i <= n; i++) {
            parents[i] = i;
            visited[i] = false;
        }

        while (m-- > 0) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int c = scanner.nextInt();

            paths.add(new Path(a, b, c));
        }

        while (!paths.isEmpty()) {
            if (count == 2)
                break;
            Path path = paths.poll();
            int a = path.firstNode;
            int b = path.secondNode;

            if (areInSameSet(a, b, parents)) {
                continue;
            }
            unionParent(parents, a, b);
            result += path.distance;
            count--;
        }

        System.out.println(result);
    }

    private static int findParent(int[] parents, int node) {
        int child = node;
        while (child != parents[child]) {
            child = parents[child];
        }
        parents[node] = child;

        return parents[node];
    }

    private static void unionParent(int[] parents, int a, int b) {
        a = findParent(parents, a);
        b = findParent(parents, b);

        if (a < b) {
            parents[b] = a;
            return;
        }
        parents[a] = b;
    }

    private static boolean areInSameSet(int a, int b, int[] parents) {
        return findParent(parents, a) == findParent(parents, b);
    }
}
