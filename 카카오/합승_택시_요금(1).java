import java.util.*;

class Solution {
    private final static int INF = Integer.MAX_VALUE;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int[][] costs = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(costs[i], INF);
            costs[i][i] = 0;
        }
        for (int[] fare : fares) {
            costs[fare[0]][fare[1]] = fare[2];
            costs[fare[1]][fare[0]] = fare[2];
        }
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (costs[k][i] == INF || costs[k][j] == INF)
                        continue;
                    int result = Math.min(costs[i][j], costs[i][k] + costs[j][k]);
                    costs[i][j] = result;
                    costs[j][i] = result;
                }
            }
        }
        
        int result = INF;
        for (int i = 1; i <= n; i++) {
            if (costs[s][i] == INF || costs[i][a] == INF || costs[i][b] == INF)
                continue;
            int current = costs[s][i] + costs[i][a] + costs[i][b];
            result = Math.min(current, result);
        }
        return result;
    }
}