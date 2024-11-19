import java.util.*;
import java.io.*;

class Main {

    private static int n;
    private static List<List<Integer>> nodes = new ArrayList<>();
    private static int[] values;

    public static void main(String[] args) throws IOException {
        input();
        int[] result = dfs(0, new boolean[n]);
        System.out.println(Math.max(result[1], result[3]));
    }

    private static int[] dfs(int index, boolean[] visited) {
        visited[index] = true;
        int cur = values[index];
        List<int[]> results = new ArrayList<>();
        for (int node : nodes.get(index)) {
            if (visited[node]) {
                continue;
            }
            results.add(dfs(node, visited));
        }
        if (results.size() == 0) {
            return new int[]{0, cur, 0, 0};
        }
       
        int first = cur;
        int second = 0;
        boolean hasLinked = false;
        for (int[] result : results) {
            if (result[0] == 0) {
                hasLinked = true;
                second += result[1];
            } else {
                second += result[3];
            }
            first += Math.max(result[2], result[3]);
        }
        int third = second;
        if (!hasLinked) {
            int[] maxResult = results.stream()
                .sorted((r1, r2) -> {
                    int v1 = r1[1] - r1[3];
                    int v2 = r2[1] - r2[3];

                    return v2 - v1;
                })
                .findFirst()
                .get();
            third = third - maxResult[3] + maxResult[1];
        }
        
        return new int[]{Math.max(first, second) == first ? 0 : 1, first, second, third};
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        values = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = Integer.parseInt(st.nextToken());
            nodes.add(new ArrayList<>());
        }
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            nodes.get(a).add(b);
            nodes.get(b).add(a);
        }
    }
}
