import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                if (st.nextToken().charAt(0) == '1') {
                    if (i < j) {
                        list.get(i).add(j);
                    } 
                }
            }
        }

        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }
        for (int i = 0; i < n; i++) {
            for (int j : list.get(i)) {
                uion(i, j);
            }
        }

        int[] nums = new int[m];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            nums[i] = Integer.parseInt(st.nextToken()) - 1;
        }
        System.out.println(hasSameParent(nums) ? "YES" : "NO");
    }

    private static void uion(int a, int b) {
        a = findParent(a);
        b = findParent(b);
        if (a == b) {
            return;
        }
        if (a > b) {
            parents[a] = b;
            return;
        }
        parents[b] = a;
    }

    private static boolean hasSameParent(int... nodes) {
        return 1 == Arrays.stream(nodes)
            .map(Main::findParent)
            .distinct()
            .count();
    }

    private static int findParent(int node) {
        if (parents[node] == node) {
            return node;
        }
        int result = findParent(parents[node]);
        parents[node] = result;
        return result;
    }
}
