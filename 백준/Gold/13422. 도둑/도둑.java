import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            int[] values = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                values[i] = Integer.parseInt(st.nextToken());
            }
            int cur = 0;
            int result = 0;
            for (int i = 0; i < m; i++) {
                cur += values[i];
            }
            if (m == n) {
                sb.append(cur < k ? "1\n" : "0\n");
                continue;
            }
            for (int i = 0; i < n; i++) {
                cur -= values[i];
                int added = i + m;
                if (added >= n) added -= n;
                cur += values[added];
                if (cur < k) result++;
                if (added == m - 1) break;
            }
            sb.append(result + "\n");
        }
        System.out.println(sb);
    }
}
