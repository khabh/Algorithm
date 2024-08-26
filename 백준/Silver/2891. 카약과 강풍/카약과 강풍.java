import java.util.*;
import java.io.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int[] teams = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        while (s-- > 0) {
            teams[Integer.parseInt(st.nextToken())] -= 1;
        }
        st = new StringTokenizer(br.readLine());
        while (r-- > 0) {
            teams[Integer.parseInt(st.nextToken())] += 1;
        }
        for (int i = 0; i <= n; i++) {
            if (teams[i] != 1) {
                continue;
            }
            if (i > 0 && teams[i - 1] == -1) {
                teams[i - 1] = 0;
                teams[i] = 0;
                continue;
            }
            if (i < n && teams[i + 1] == -1) {
                teams[i + 1] = 0;
                teams[i] = 0;
            }
        }
        System.out.println(Arrays.stream(teams).filter(x -> x == -1).count());
    }
}
