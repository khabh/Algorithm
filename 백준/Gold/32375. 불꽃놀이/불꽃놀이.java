import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int result = 0;
        List<Integer> values = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int value = Integer.parseInt(st.nextToken());
            if (value >= k) {
                result++;
                continue;
            }
            values.add(value);
        }
        Collections.sort(values);
        
        int left = 0;
        int right = values.size() - 1;
        while (left < right) {
            int sum = values.get(left) + values.get(right);
            if (sum >= k) {
                result++;
                left++;
                right--;
            }
            else {
                left++;
            }
        }
        if (result == 0) {
            System.out.println(-1);
            return;
        }
        System.out.println(result);
    }
}
