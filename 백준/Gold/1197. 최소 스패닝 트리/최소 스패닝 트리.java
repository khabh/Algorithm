import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int v = scanner.nextInt();
        int[] parents = new int[v + 1];
        for (int i = 1; i <= v; i++) {
            parents[i] = i;
        }
        Queue<Path> paths = getSortedPath(scanner);
        int count = 0;
        int result = 0;
        while (count < v - 1 && !paths.isEmpty()) {
            Path path = paths.poll();
            if (!unionParent(parents, path.a, path.b)) {
                continue;
            }
            count++;
            result += path.cost;
        }
        System.out.println(result);
    }

    private static int findParent(int[] parents, int a) {
        while (a != parents[a]) {
            a = parents[a];
        }
        return a;
    }

    private static boolean unionParent(int[] parents, int a, int b) {
        a = findParent(parents, a);
        b = findParent(parents, b);
        if (a == b) {
            return false;
        }
        if (a < b) {
            parents[b] = a;
        } else {
            parents[a] = b;
        }
        return true;
    }

    private static Queue<Path> getSortedPath(Scanner scanner) {
        List<Path> paths = new ArrayList<>();
        int e = scanner.nextInt();

        for (int i = 0; i < e; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int c = scanner.nextInt();
            paths.add(new Path(a, b, c));
        }
        Collections.sort(paths);

        return new ArrayDeque<>(paths);
    }

    private static class Path implements Comparable<Path> {
        int a;
        int b;
        int cost;

        public Path(int a, int b, int cost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
        }

        @Override
        public int compareTo(Path path) {
            return cost - path.cost;
        }
    }
}
