import java.util.*;
import java.io.*;

public class Main {

    static int n, l;
    static int[][] nums;

    public static void main(String[] args) throws IOException { 
        input();
        int result = 0;
        for (int i = 0; i < n; i++) {
            if (check(i, 0, 0, 1)) {
                result++;
            }
            if (check(0, i, 1, 0)) {
                result++;
            }
        }
        System.out.println(result);
    }

    private static boolean check(int x, int y, int dx, int dy) {
        int prev = nums[x][y];
        int eqCount = 1;
        int slopeLength = 0;
        while (true) {
            x += dx;
            y += dy;
            if (x == n || y == n) {
                return slopeLength == 0;
            }
            int cur = nums[x][y];
            if (Math.abs(prev - cur) >= 2) {
                return false;
            }
            if (slopeLength != 0) {
                if (prev != cur) {
                    return false;
                }
                slopeLength -= 1;
                if (slopeLength == 0) {
                    eqCount = 0;
                }
                prev = cur;
                continue;
            }
            if (prev == cur) {
                eqCount++;
            }
            else if (prev < cur) {
                if (eqCount < l) {
                    return false;
                }
                eqCount = 1;
            }
            else {
                slopeLength = l - 1;
                if (slopeLength == 0) {
                    eqCount = 0;
                }
            }
            prev = cur;
        }
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        nums = new int[n][n];
        for (int i = 0; i < n; i++) {
            nums[i] = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        }
    }
}
