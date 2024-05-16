import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static final List<List<Integer>> graph = new ArrayList<>();
    private static final int[][] dp = new int[1000001][2]; // 메모이제이션을 위한 배열
    private static boolean[] visited;

    public static void main(String[] args) {
        int n = sc.nextInt();
        visited = new boolean[n + 1]; // 방문 여부를 체크하기 위한 배열
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < n - 1; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        dfs(1); // 루트 노드부터 DFS 시작
        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    private static void dfs(int node) {
        visited[node] = true;
        dp[node][0] = 0; // 현재 노드가 얼리 어답터가 아닌 경우
        dp[node][1] = 1; // 현재 노드가 얼리 어답터인 경우
        for (int child : graph.get(node)) {
            if (!visited[child]) {
                dfs(child); // 자식 노드를 방문
                dp[node][0] += dp[child][1]; // 현재 노드가 얼리 어답터가 아니라면 자식은 얼리 어답터여야 함
                dp[node][1] += Math.min(dp[child][0], dp[child][1]); // 현재 노드가 얼리 어답터라면 자식은 얼리 어답터거나 아닐 수 있음
            }
        }
    }
}
