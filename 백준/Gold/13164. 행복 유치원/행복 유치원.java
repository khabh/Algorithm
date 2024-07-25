import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.valueOf(st.nextToken());
        int k = Integer.valueOf(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = Integer.valueOf(st.nextToken());
        }

        PriorityQueue<Integer> gaps = new PriorityQueue<>();
        long total = 0L;
        for (int i = 1; i < n; i++) {
            int gap = nums[i - 1] - nums[i];
            total -= gap;
            gaps.add(gap);
        }
        while (k-- > 1) {
            total += gaps.poll();
        }

        System.out.println(total);
    }
}
