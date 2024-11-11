import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.valueOf(st.nextToken());
        int m = Integer.valueOf(st.nextToken());
        int[] names = new int[n];
        for (int i = 0; i < n; i++) {
            names[i] = Integer.valueOf(br.readLine());
        }
        int[] prev = new int[m + 1];
        Arrays.fill(prev, -1);
        prev[names[0]] = 0;
        
        for (int i = 1; i < n; i++) {
            int name = names[i];
            int[] next = new int[m + 1];
            Arrays.fill(next, -1);
            for (int j = 0; j <= m; j++) {
                if (prev[j] == -1) {
                    continue;
                }
                int nextPos = j + name + 1;
                if (nextPos <= m) {
                    next[nextPos] = prev[j];
                }
                int nextValue = prev[j] + pow(m - j);
                next[name] = next[name] == -1 ? nextValue : Math.min(next[name], nextValue);
            }
            prev = next;
        }

        System.out.println(Arrays.stream(prev)
            .filter(num -> num != -1)
            .min()
            .getAsInt());
    }

    private static int pow(int val) {
        return val * val;
    }
}
