import java.util.*;
import java.io.*;

class Main {

    private static final long[] values = new long[6];
    private static final int[][] id2 = {
        {0, 1}, 
        {0, 2}, 
        {0, 3},
        {0, 4}, 

        {1, 2}, 
        {1, 3}, 
        {1, 5}, 

        {2, 4}, 
        {2, 5}, 

        {3, 4},
        {3, 5},

        {4, 5}
    };
    private static final int[][] id3 = {
        {0, 1, 2}, 
        {0, 1, 3}, 

        {0, 4, 2}, 
        {0, 4, 3},

        {1, 5, 2},
        {1, 5, 3},

        {4, 5, 2},
        {4, 5, 3}
    };
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 6; i++) {
            values[i] = Long.parseLong(st.nextToken());
        }
        if (n == 1) {
            long sum = Arrays.stream(values).sum();
            sum -= Arrays.stream(values).max().getAsLong();
            System.out.println(sum);
            return;
        }
        long result = ((n - 2) * (n - 1) * 4 + (n - 2) * (n - 2)) * findMinOne();
        result += (findMin(id3) * 4);
        result += (findMin(id2) * ((n - 1) * 4 + (n - 2) * 4));
        System.out.println(result);
    }

    private static long findMinOne() {
        return Arrays.stream(values)
            .min()
            .getAsLong();
    }

    private static long findMin(int[][] arr) {
        long result = Long.MAX_VALUE;
        for (int[] cur : arr) {
            long sum = 0L;
            for (int index : cur) {
                sum += values[index];
            }
            result = Math.min(result, sum);
        }
        return result;
    }
}
