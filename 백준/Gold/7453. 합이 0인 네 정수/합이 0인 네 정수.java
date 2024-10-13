import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static int[] abs, cds;

    public static void main(String[] args) throws IOException { 
        input();
        
        int max = n * n;
        long result = 0;
        int left = 0;
        int right = max - 1;

        while (left < max && right >= 0) {
            long sum = abs[left] + cds[right];

            if (sum == 0) {
                long leftCount = 1;
                long rightCount = 1;

                while (++left < max && abs[left] == abs[left - 1]) {
                    leftCount++;
                }
                while (--right >= 0 && cds[right] == cds[right + 1]) {
                    rightCount++;
                }
                result += (leftCount * rightCount);
                continue;
            }
            if (sum > 0) {
                right--;
            } else {
                left++;
            }
        }
        
        System.out.println(result);
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int[] a = new int[n];
        int[] b = new int[n];
        int[] c = new int[n];
        int[] d = new int[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            a[i] = Integer.parseInt(st.nextToken());
            b[i] = Integer.parseInt(st.nextToken());
            c[i] = Integer.parseInt(st.nextToken());
            d[i] = Integer.parseInt(st.nextToken());
        } 

        Arrays.sort(a);
        Arrays.sort(b);
        Arrays.sort(c);
        Arrays.sort(d);

        abs = group(a, b);
        cds = group(c, d);
    }
 
    private static int[] group(int[] a, int[] b) {
        int[] result = new int[n * n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[count++] = a[i] + b[j];
            }
        }
        Arrays.sort(result);
        return result;
    }
}
