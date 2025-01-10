import java.util.*;
import java.io.*;

public class Main {

    static int[] parents;
    static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(x -> x[2]));
        while (m -- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            if (a == b) {
                continue;
            }
            int c = Integer.parseInt(st.nextToken());
            q.add(new int[]{a, b, c});
        }
        int count = 0;
        int result = 0;
        while (count < n - 1) {
            int[] node = q.poll();
            if (union(node[0], node[1])) {
                result += node[2];
                count++;
            }
        }
        System.out.println(result);
    }

    private static boolean union(int a, int b) {
        a = findParent(a);
        b = findParent(b);
        if (a == b) {
            return false;
        }
        if (a < b) {
            parents[b] = a;
            return true;
        }
        parents[a] = b;
        return true;
    }

    private static int findParent(int a) {
        if (parents[a] == a) {
            return a;
        }
        int result = findParent(parents[a]);
        parents[a] = result;
        return result;
    }
}
