import java.util.*;
import java.io.*;

class Main {

    static int[] parents;
    static int[] counts;
    static int index;
    static Map<String, Integer> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringJoiner sj = new StringJoiner("\n");

        while (t-- > 0) {
            int f = Integer.parseInt(br.readLine());
            parents = new int[f * 2];
            counts = new int[f * 2];
            index = 0;
            map = new HashMap<>();

            while (f-- > 0) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                sj.add(String.valueOf(count(st.nextToken(), st.nextToken())));
            }
        }
        System.out.print(sj);
    }

    private static int count(String a, String b) {
        int i = getParent(getIndex(a));
        int j = getParent(getIndex(b));
        
        if (i == j) {
            return counts[i];
        }
        union(i, j);
        return counts[Math.min(i, j)];
    }

    private static int getIndex(String name) {
        if (map.containsKey(name)) {
            return map.get(name);
        }
        parents[index] = index;
        counts[index] = 1; 
        map.put(name, index);
        return index++;
    }

    private static int getParent(int x) {
        if (parents[x] == x) {
            return x;
        }
        parents[x] = getParent(parents[x]);
        return parents[x];
    }

    private static void union(int a, int b) {
        if (a > b) {
            union(b, a);
            return;
        }
        parents[b] = a;
        counts[a] += counts[b];
    }
}
