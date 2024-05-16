import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(br.readLine());
        int[][] values = new int[n][2];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            values[i][0] = Integer.valueOf(st.nextToken());
            values[i][1] = Integer.valueOf(st.nextToken());
        }
        Arrays.sort(values, Comparator.comparingInt(value -> value[0]));

        PriorityQueue<Integer> q = new PriorityQueue<>();
        int index = -1;
        while (++index < n) {
            int[] value = values[index];
            if (q.size() < value[0]) {
                q.add(value[1]);
                continue;
            }
            if (q.peek() < value[1]) {
                q.poll();
                q.add(value[1]);
            }
        }
        System.out.println(q.stream().mapToInt(Integer::valueOf).sum());
    }
}
