import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] prev = new int[n + 1];
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(i + 1, new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map.get(a).add(b);
            prev[b]++;
        }

        PriorityQueue<Integer> q = new PriorityQueue<>();
        for (int i = 1; i <= n; i++) {
            if (prev[i] == 0) 
                q.add(i);
        }
        
        StringJoiner sj = new StringJoiner(" ");
        while (!q.isEmpty()) {
            int node = q.poll();
            sj.add(String.valueOf(node));
            
            for (int num : map.get(node)) {
                prev[num]--;
                if (prev[num] == 0) {
                    q.add(num);
                }
            }
        }

        System.out.println(sj);
    }
}
