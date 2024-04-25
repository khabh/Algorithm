import java.util.*;
import java.io.*;

class Main {

    private static int n;
    private static int[] weights;

    public static void main(String[] args) throws IOException {
        boolean[][] able = new boolean[30][40_000];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.valueOf(br.readLine());
        weights = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();

        Set<Integer> prev = Set.of(0, weights[0]);

        for (int i = 1; i < n; i++) {
            int cur = weights[i];
            Set<Integer> next = new HashSet<>();
            for (int weight : prev) {
                next.add(Math.abs(cur - weight));
                next.add(cur + weight);
                next.add(weight);
            }
            prev = next;
        }

        int m = Integer.valueOf(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (m -- > 0) {
            sb.append(prev.contains(Integer.valueOf(st.nextToken())) ? "Y " : "N ");
        }
        System.out.println(sb);
    }
}
