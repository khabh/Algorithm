import java.util.*;
import java.io.*;

public class Main {
    
    static Map<Integer, Integer> lefts = new HashMap<>();
    static Map<Integer, Integer> rights = new HashMap<>();
    static Set<Section> list = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            list.add(new Section(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        for (Section section : list) {
            int l = section.l;
            int r = section.r;
            lefts.put(l, Math.max(lefts.getOrDefault(l, -1), r));
            rights.put(r, Math.min(rights.getOrDefault(r, Integer.MAX_VALUE), l));
        }
        int q = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (q-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            sb.append(resolve(l, r) + "\n");
        }
        System.out.print(sb);
    }

    private static int resolve(int l, int r) {
        if (list.contains(new Section(l, r))) {
            return 1;
        }
        if (!lefts.containsKey(l) || !rights.containsKey(r) || lefts.get(l) < r || rights.get(r) > l) {
            return -1;
        }
        return 2;
    }

    static class Section {
        int l;
        int r;

        public Section(int l, int r) {
            this.l = l;
            this.r = r;
        }

        public Optional<Section> inter(Section o) {
            if (l > o.r || r < o.l) {
                return Optional.empty();
            }
            int left = Math.max(l, o.l);
            int right = Math.min(r, o.r);
            return Optional.of(new Section(left, right));
        }

        @Override
        public boolean equals(Object o) {
            Section s = (Section) o;
            return s.l == l && s.r == r;
        }

        @Override
        public int hashCode() {
            return Objects.hash(l, r);
        }
    }
}
