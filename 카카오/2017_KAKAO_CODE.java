// 카카오 프렌즈 컬러링북
import java.util.*;

class Solution {
    
    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};
    
    private int bfs(int[][] board, int a, int b) {
        Queue<Node> q = new LinkedList<>();
        int color = board[a][b];
        board[a][b] = 0;
        int size = 1;
        q.add(new Node(a, b));
        while (!q.isEmpty()) {
            Node node = q.poll();
            int x = node.x;
            int y = node.y;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || ny < 0 || nx == board.length || ny == board[0].length || board[nx][ny] != color)
                    continue;
                board[nx][ny] = 0;
                size++;
                q.add(new Node(nx, ny));
            }
        }
        return size;
    }
    
    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (picture[i][j] != 0) {
                    maxSizeOfOneArea = Math.max(maxSizeOfOneArea, bfs(picture, i, j));
                    numberOfArea++;
                }
            }
        }
        
        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
    
    class Node {
        int x;
        int y;
        
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

// 보행자 천국
class Solution {
    int MOD = 20170805;
    public int solution(int m, int n, int[][] cityMap) {
        int[][][] dp = new int[2][m + 1][n + 1];
        dp[0][0][0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (cityMap[i][j] == 0) {
                    int temp = (dp[0][i][j] + dp[1][i][j]) % MOD;
                    dp[0][i + 1][j] += temp;
                    dp[1][i][j + 1] += temp;
                }
                if (cityMap[i][j] == 2) {
                    dp[0][i + 1][j] += dp[0][i][j] % MOD;
                    dp[1][i][j + 1] += dp[1][i][j] % MOD;
                }
            }
        }
        return (dp[0][m - 1][n - 1] + dp[1][m - 1][n - 1]) % MOD;
    }
}

// 캠핑
import java.util.*;

class Solution {
    public int solution(int n, int[][] data) {
        data = convert(data);
        int[][] counts = new int[n * 2][n * 2];
        for (int[] d : data) {
            int x = d[0];
            int y = d[1];
            counts[x][y]++;
        }
        for (int i = 0; i < n * 2; i++) {
            for (int j = 1; j < n * 2; j++) {
                if (j > 0)
                    counts[i][j] += counts[i][j - 1];
                if (i > 0)
                    counts[i][j] += counts[i - 1][j];
                if (i > 0 && j > 0)
                    counts[i][j] -= counts[i - 1][j - 1];
            }
        }
        
        int answer = 0;
        
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int[] first = data[i];
                int[] second = data[j];
                int x1 = Math.min(first[0], second[0]);
                int y1 = Math.min(first[1], second[1]);
                int x2 = Math.max(first[0], second[0]);
                int y2 = Math.max(first[1], second[1]);
                
                 if (x1 == x2 || y1 == y2) {
                    continue;
                }
                
                int count = counts[x2 - 1][y2 - 1] - counts[x1][y2 - 1] - counts[x2 - 1][y1] + counts[x1][y1];
                if (count == 0) {
                    answer++;
                }
            }
        }
        return answer;
    }
    
    private int[][] convert(int[][] data) {
        Set<Integer> set = new HashSet<>();
        for (int[] d : data) {
            for (int i : d) {
                set.add(i);
            }
        }
        List<Integer> numbers = new ArrayList<>(set);
        Collections.sort(numbers);
        Map<Integer, Integer> result = new HashMap<>();
        int value = 0;
        for (int number : numbers) {
            result.put(number, value++);
        } 
        for (int i = 0; i < data.length; i++) {
            data[i][0] = result.get(data[i][0]);
            data[i][1] = result.get(data[i][1]);
        }
        return data;
    }
}