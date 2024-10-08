import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static int[] counts;
    static boolean[][] linked;
    static int totalCount;
    static int result = Integer.MAX_VALUE;

    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        input();
        totalCount = Arrays.stream(counts).sum();
        solve(0, new boolean[n], 0, 0);
        if (result == Integer.MAX_VALUE) {
            System.out.println("-1");
            return;
        }
        System.out.println(result);
    }

    private static void solve(int index, boolean[] visited, int count, int visitCount) {
        if (Math.abs(totalCount - count) >= result && count > (totalCount - count)) {
            return;
        }
        boolean isValid = isValid(visited, visitCount);
        if (isValid) {
            result = Math.min(result, Math.abs((totalCount - count) - count));
        }
        if (index == n) {
            return;
        }
        solve(index + 1, visited, count, visitCount);
        visited[index] = true;
        solve(index + 1, visited, count + counts[index], visitCount + 1);
        visited[index] = false;
    }

    private static boolean isValid(boolean[] first, int visitCount) {
        if (visitCount == 0 || visitCount == n) {
            return false;
        }
        Set<Integer> firstNodes = new HashSet<>();
        Set<Integer> secondNodes = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (first[i])
                firstNodes.add(i);
            else
                secondNodes.add(i);
        }

        if (firstNodes.size() <= secondNodes.size()) {
            return isValid(firstNodes) && isValid(secondNodes);
        }
        return isValid(secondNodes) && isValid(firstNodes);
    }

    private static boolean isValid(Set<Integer> nodes) {
        if (nodes.size() == 1) {
            return true;
        }
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();
        int first = nodes.stream().findAny().get();
        q.add(first);
        visited.add(first);
        while (!q.isEmpty()) {
            int node = q.poll();
            for (int i = 0; i < n; i++) {
                if (visited.contains(i) || !nodes.contains(i) || !linked[i][node]) {
                    continue;
                }
                visited.add(i);
                q.add(i);
            }
        }
        return visited.size() == nodes.size();
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        counts = Arrays.stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();
        linked = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            int[] links = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
            for (int j = 1; j <= links[0]; j++) {
                linked[i][links[j] - 1] = true;
                linked[links[j] - 1][i] = true;
            }
        }
    }
}