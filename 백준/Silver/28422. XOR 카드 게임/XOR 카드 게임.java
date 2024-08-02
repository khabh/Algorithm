import java.util.*;
import java.io.*;

public class Main {

    private static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.valueOf(st.nextToken());
        }
        int[] temp = new int[n + 1];
        Arrays.fill(temp, -1);
        temp[0] = 0;
        for (int i = 0; i <= n; i++) {
            if (temp[i] == -1) {
                continue;
            }
            if (i + 2 <= n) {
                temp[i + 2] = Math.max(temp[i] + getScore(i, i + 1), temp[i + 2]);
            }
            if (i + 3 <= n) {
                temp[i + 3] = Math.max(temp[i] + getScore(i, i + 2), temp[i + 3]);
            }
        }
        System.out.println(Math.max(0, temp[n]));
    }

    private static int getScore(int start, int end) {
        int result = nums[start];
        for (int i = start + 1; i <= end; i++) {
            result = result ^ nums[i];
        }

        String bin = Integer.toBinaryString(result);
        int count = 0;
        for (char b : bin.toCharArray()) {
            if (b == '1') {
                count++;
            }
        }
        return count;
    }
}
