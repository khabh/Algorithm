import java.util.*;
import java.io.*;

public class Main {

    static int n, l, result1, result2;
    static int[][] nums;

    public static void main(String[] args) throws IOException { 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringJoiner sj = new StringJoiner("\n");
        while (t-- > 0) {
            int[] input = Arrays.stream(br.readLine().split(""))
                .mapToInt(c -> c.charAt(0) - 'a')
                .toArray();
            int n = input.length;
            int k = Integer.parseInt(br.readLine());
            if (k == 1) {
                sj.add("1 1");
                continue;
            }
            result1 = n + 1;
            result2 = -1;
            
            List<List<Integer>> lists = new ArrayList<>();
            for (int i = 0; i < 26; i++) {
                lists.add(new ArrayList<>());
            }
            for (int i = 0; i < n; i++) {
                int cur = input[i];
                lists.get(cur).add(i);
            }
            for (List<Integer> list : lists) {
                if (list.size() < k) {
                    continue;
                }
                int maxIndex = list.size() - k + 1;
                for (int i = 0; i < maxIndex; i++) {
                    int length = list.get(i + k - 1) - list.get(i) + 1;
                    result1 = Math.min(result1, length);
                    result2 = Math.max(result2, length);
                }
            }
            if (result1 == n + 1) {
                sj.add("-1");
            } else {
                sj.add(result1 + " " + result2);
            }
        }
        System.out.println(sj);
    }
}
