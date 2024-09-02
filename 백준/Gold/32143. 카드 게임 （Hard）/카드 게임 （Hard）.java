import java.util.*;
import java.io.*;

public class Main {

    private static final String NO_RESULT = "-1\n";
    private static long h;
    private static long total = 0;
    private static PriorityQueue<Long> q = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        h = Long.parseLong(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (n-- > 0) {
            updateQueue(Long.parseLong(st.nextToken()));
        }
        sb.append(total < h ? NO_RESULT : (q.size() + "\n"));
        while (m-- > 0) {
            updateQueue(Long.parseLong(br.readLine()));
            sb.append(total < h ? NO_RESULT : (q.size() + "\n"));
        }
        System.out.println(sb);
    }

    private static void updateQueue(long num) {
        total += num;
        q.add(num);
        while (total - q.peek() >= h) {
            total -= q.poll();
        }
    }
}