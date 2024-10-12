import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();
        Set<Integer> results = new HashSet<>();
        Map<Integer, Set<Integer>> set = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            if (!set.containsKey(num)) {
                set.put(num, new HashSet<>());
            }
            set.get(num).add(i);
        }
        for (int i = 0; i < n; i++) {
            int a = nums[i];
            for (int j = i + 1; j < n; j++) {
                int cur = a + nums[j];
                if (!set.containsKey(cur)) {
                    continue;
                }
                Set<Integer> curSet = set.get(cur);
                boolean containsI = curSet.remove(i);
                boolean containsJ = curSet.remove(j);
                results.addAll(curSet);
                if (!containsI && !containsJ) {
                    set.remove(cur);
                } else {
                    Set<Integer> next = new HashSet<>();
                    if (containsI) {
                        next.add(i);
                    }
                    if (containsJ) {
                        next.add(j);
                    }
                    set.put(cur, next);
                }
            }
        }
        System.out.println(results.size());
    }
}