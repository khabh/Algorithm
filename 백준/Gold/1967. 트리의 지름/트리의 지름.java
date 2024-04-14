import java.util.*;
import java.io.*;

class Main {
    static int n;
    static List<List<Path>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.valueOf(bf.readLine());
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
            int a = Integer.valueOf(st.nextToken());
            int b = Integer.valueOf(st.nextToken());
            int c = Integer.valueOf(st.nextToken());
            graph.get(a).add(new Path(b, c));
            graph.get(b).add(new Path(a, c));
        }
        
        Path first = getFarthest(1);
        Path result = getFarthest(first.target);
        System.out.println(result.dist);
    }

    private static Path getFarthest(int start) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1);
        dist[start] = 0;
        Queue<Integer> q = new ArrayDeque<>();
        q.add(start);
        int maxDist = 0;
        int index = start;
        while (!q.isEmpty()) {
            int cur = q.poll();
            for (Path path : graph.get(cur)) {
                if (dist[path.target] > -1) {
                    continue;
                }
                dist[path.target] = dist[cur] + path.dist;
                q.add(path.target);
                if (dist[path.target] > maxDist) {
                    maxDist = dist[path.target];
                    index = path.target;
                }
            }
        }
        return new Path(index, maxDist);
    }

    public static class Path {
        int target;
        int dist;

        public Path(int target, int dist) {
            this.target = target;
            this.dist = dist;
        }
    }
}
