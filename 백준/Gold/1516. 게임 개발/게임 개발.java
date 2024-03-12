import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] costs = new int[n + 1];
        int[] prevCounts = new int[n + 1];
        List<List<Integer>> childs = new ArrayList<>();

        int[] results = new int[n + 1];
        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i <= n; i++) {
            childs.add(new ArrayList<>());
        }
        for (int i = 1; i <= n; i++) {
            costs[i] = scanner.nextInt();
            int count = 0;
            while (true) {
                int parent = scanner.nextInt();
                if (parent == -1)
                    break;
                count++;
                childs.get(parent).add(i);
            }
            if (count == 0) {
                q.add(i);
                results[i] = costs[i];
            }
            prevCounts[i] = count;
        }

        while (!q.isEmpty()) {
            int current = q.poll();
            int currentCost = results[current];
            for (int child : childs.get(current)) {
                prevCounts[child]--;
                results[child] = Math.max(costs[child] + currentCost, results[child]);
                if (prevCounts[child] == 0) {
                    q.add(child);
                }
            }
        }
        for (int i = 1; i <= n; i++) {
            System.out.println(results[i]);
        }
    }
}
