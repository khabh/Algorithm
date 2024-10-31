import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        long result = 0;
        List<Long> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            long value = Long.parseLong(st.nextToken());
            result += value;
            list.add(Long.parseLong(st.nextToken()) - value);
        }
        Collections.sort(list);
        
        for (int i = 0; i < b; i++) {
            result += list.get(i);
        }
        System.out.println(result);
    }
}
