import java.util.Arrays;

class Solution {
    private static final int INF = Integer.MAX_VALUE;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int result = Integer.MAX_VALUE;
        int[][] dist = new int[201][201];
        
        for(int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }
            
        for (int[] fare : fares) {
            dist[fare[0]][fare[1]] = Math.min(dist[fare[0]][fare[1]], fare[2]);
            dist[fare[1]][fare[0]] = Math.min(dist[fare[1]][fare[0]], fare[2]);
        }
        for(int k = 1; k<= n; k++) {
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j<=n; j++) {
                    if (dist[i][k] == INF || dist[k][j] == INF)
                        continue;
                    dist[i][j] = Math.min(dist[i][j], dist[i][k]+dist[k][j]);
                }
            }
        }
        
        for (int i = 1; i <= n; i++) {
            if (dist[s][i] < INF && dist[i][a] < INF && dist[i][b] < INF)
                result = Math.min(dist[s][i] + dist[i][a] + dist[i][b], result);
        }
            
        return result;
    }
}