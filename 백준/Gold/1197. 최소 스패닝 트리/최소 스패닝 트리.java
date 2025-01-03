import java.util.*;
import java.io.*;

class Main {

    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(x -> x[0]));
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());
            q.add(new int[]{c, a, b});
        }
        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }
        int count = 0;
        int result = 0;
        while (count != n - 1) {
            int[] node =q.poll();
            if (union(node[1], node[2])) {
                result += node[0];
                count++;
            }
        }
        System.out.println(result);
    }

    private static boolean union(int a, int b) {
        int pa = findParent(a);
        int pb = findParent(b);
        if (pa == pb) {
            return false;
        }
        if (pa < pb) {
            parents[pa] = pb;
        } else {
            parents[pb] = pa;
        }
        return true;
    }

    private static int findParent(int a) {
        if (parents[a] == a) {
            return a;
        }
        parents[a] = findParent(parents[a]);
        return parents[a];
    }
}
