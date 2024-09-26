import java.util.*;
import java.io.*;

public class Main {

    private static int n, m;
    private static int[] c, w;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        c = new int[n];
        for (int i = 0; i < n; i++) {
            c[i] = Integer.parseInt(st.nextToken());
        }
        m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        w = new int[m];
        for (int i = 0; i < m; i++) {
            w[i] = Integer.parseInt(st.nextToken());
        }
        c = sortReverse(c);
        w = sortReverse(w);
        if (w[0] > c[0]) {
            System.out.println(-1);
            return;
        }
        int count = 0;
        int result = 0;
        while (count < m) {
            result++;
            int prevIndex = 0;
            for (int crane : c) {
                while (prevIndex < m && (w[prevIndex] == 0 || w[prevIndex] > crane)) {
                    prevIndex++;
                }
                if (prevIndex == m) {
                    break;
                }
                w[prevIndex] = 0;
                count++;
            }
        }
        System.out.println(result);
    }

    private static int[] sortReverse(int[] arr) {
        return Arrays.stream(arr)
                    .boxed()
                    .sorted(Comparator.reverseOrder())
                    .mapToInt(Integer::intValue)
                    .toArray();
    }
}