import java.util.*;
import java.io.*;

public class Main {

    static int m, n, l;
    static int[] arr;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());

        arr = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            find(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        System.out.println(result);
    }

    private static void find(int a, int b) {
        int start = -1;
        int end = m;
        int min = a + b - l;
        int max = a - b + l;
        
        while (start + 1 < end) {
            int mid = (start + end) / 2;
            int val = arr[mid];
            if (val < min) {
                start = mid;
                continue;
            }
            if (val >= min) {
                if (val <= max) {
                    result++;
                    return;
                }
                end = mid;
            }
        }
    }
}
