import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.valueOf(br.readLine());
        while (t-- > 0) {
            int n = Integer.valueOf(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            long[] nums = new long[n];
            PriorityQueue<Long> q = new PriorityQueue<>();
            for (int i = 0; i < n; i++) {
                q.add(Long.valueOf(st.nextToken()));
            }
            long result = 0L;
            while (q.size() > 1) {
                long temp = q.poll() + q.poll();
                result += temp;
                q.add(temp);
            }
            System.out.println(result);
        }
    }
}
