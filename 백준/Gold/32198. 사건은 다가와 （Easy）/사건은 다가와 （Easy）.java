import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<BodyBang> list = new ArrayList<>();

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        while (n-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.add(new BodyBang(t, a, b));
        }

        Collections.sort(list);
        int curT = 0;
        for (BodyBang cur : list) {
            Map<Integer, Integer> next = new HashMap<>();
            int dist = cur.t - curT;
            int a = cur.a;
            int b = cur.b;
            for (int pos : map.keySet()) {
                int min = Math.max(-1000, pos - dist);
                int max = Math.min(1000, pos + dist);
                for (int i = min; i <= max; i++) {
                    if (i > a && i < b) {
                        continue;
                    }
                    if (map.containsKey(i)) {
                        next.put(i, Math.min(next.getOrDefault(i, 2000), map.get(i)));
                    }
                    int count = map.get(pos) + Math.abs(pos - i);
                    next.put(i, Math.min(next.getOrDefault(i, 2000), count));
                }
            }
            if (next.isEmpty()) {
                System.out.println(-1);
                return;
            }
            map = next;
            curT = cur.t;
        }
        System.out.println(map.values().stream().min(Integer::compareTo).get());
    }

    static class BodyBang implements Comparable<BodyBang> {
        int t;
        int a;
        int b;

        public BodyBang(int t, int a, int b) {
            this.t = t;
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(BodyBang o) {
            return t - o.t;
        }
    }
}
