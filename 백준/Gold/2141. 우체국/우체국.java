import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(br.readLine());
        long[][] x = new long[n][2];
        long total = 0;
        long prev = 0;

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            x[i] = new long[]{Long.valueOf(st.nextToken()), Long.valueOf(st.nextToken())};
            total += x[i][1];
        }    
        
        Arrays.sort(x, Comparator.comparingLong(a -> a[0]));

        total = (total + 1) / 2;
        int index = -1;
        while (prev < total) {
            index++;
            prev += x[index][1];
        }
        System.out.println(x[index][0]);
    }
}
