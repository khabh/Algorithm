import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.valueOf(st.nextToken());
        int k = Integer.valueOf(st.nextToken());
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 2; i <= 20; i++) {
            map.put(i, new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            int name = br.readLine().length();
            map.get(name).add(i);
        }
        long count = 0;
        for (List<Integer> orders : map.values()) {
            int prev = 0;
            int cur = 0;
            for (Integer order : orders) {
                while (prev < cur && order - orders.get(prev) > k) {
                    prev++;
                }
                count += (cur - prev);
                cur++;
            }
        }
        System.out.println(count);
    }
}
