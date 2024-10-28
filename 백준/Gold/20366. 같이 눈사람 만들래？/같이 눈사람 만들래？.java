import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static int result = Integer.MAX_VALUE;
    static int[] heights;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        heights = new int[n];
        for (int i = 0; i < n; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(heights);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                findMin(i, j);
            }
        } 
        System.out.println(result);
    }

    private static void findMin(int i, int j) {
        int target = heights[i] + heights[j];
        int left = i + 1;
        int right = j - 1;
        while (left < right) {
            int cur = heights[left] + heights[right];
            result = Math.min(result, Math.abs(cur - target));
            if (cur >= target) {
                right--;
            } else {
                left++;    
            }
        }
    }
}
