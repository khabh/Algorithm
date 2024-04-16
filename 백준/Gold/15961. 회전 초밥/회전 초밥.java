import java.util.*;

class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int d = scanner.nextInt();
        int k = scanner.nextInt();
        int c = scanner.nextInt();

        int last = n + k - 1;
        int[] type = new int[last];
        for (int i = 0; i < n; i++) {
            type[i] = scanner.nextInt();
        }
        for (int i = n; i < last; i++) {
            type[i] = type[i - n];
        }

        Map<Integer, Integer> counts = new HashMap<>();
        counts.put(c, 1);
        for (int i = 0; i < k; i++) {
            int cur = type[i];
            counts.put(cur, counts.getOrDefault(cur, 0) + 1);
        }
        int result = counts.size();
        for (int i = k; i < last; i++) {
            if (result == d) {
                break;
            }
            int cur = type[i];
            counts.put(cur, counts.getOrDefault(cur, 0) + 1);
            int prev = type[i - k];
            if (counts.get(prev) == 1) {
                counts.remove(prev);
            } else {
                counts.put(prev, counts.get(prev) - 1);
            }
            result = Math.max(result, counts.size());
        }
        System.out.println(result);
    }
}
