import java.util.*;

class Solution {
    class Pos {
        final int x;
        final int y;
        
        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    private int[][] board;
    private int[] dx = new int[] {1, -1, 0, 0};
    private int[] dy = new int[] {0, 0, 1, -1};
    
    public int dfs(int x1, int y1, int x2, int y2, int move) {
        if (board[x1][y1] == 0) {
            return move;
        }
        List<Pos> next = new ArrayList<>();
        boolean hasNext = false;
        for (int i = 0; i < 4; i++) {
            int nx = x1 + dx[i];
            int ny = y1 + dy[i];
            if (nx < 0 || ny < 0 || nx >= board.length || ny >= board[0].length || board[nx][ny] == 0)
                continue;
            hasNext = true;
            next.add(new Pos(nx, ny));
        }
        if (!hasNext) {
            return move;
        }
        board[x1][y1] = 0;
        int maxMove = move;
        int minMove = Integer.MAX_VALUE;
        boolean canWin = false;
        for (Pos p : next) {
            
            int resultMove = dfs(x2, y2, p.x, p.y, move + 1);
            if (resultMove % 2 != move % 2) {
                canWin = true;
                minMove = Math.min(minMove, resultMove);
                continue;
            }
            maxMove = Math.max(maxMove, resultMove);
        }
        board[x1][y1] = 1;
        
        if (canWin) {
            return minMove;
        }
        return maxMove;
    }
    
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        this.board = board;
        return dfs(aloc[0], aloc[1], bloc[0], bloc[1], 0);
    }
}
// import java.util.*;

// class Solution {
//     class Pos {
//         final int x;
//         final int y;
        
//         public Pos(int x, int y) {
//             this.x = x;
//             this.y = y;
//         }
        
//         @Override
//         public boolean equals(Object o) {
//             if (this == o) return true;
//             if (o == null || getClass() != o.getClass()) return false;
//             Pos pos = (Pos) o;
//             return x == pos.x && y == pos.y;
//         }

//         @Override
//         public int hashCode() {
//             return Objects.hash(x, y);
//         }
//     }
    
//     private int[][] board;
//     private int[] dx = new int[] {1, -1, 0, 0};
//     private int[] dy = new int[] {0, 0, 1, -1};
    
//     int result = Integer.MAX_VALUE;
//     int print = 0;

    
//     public void dfs(int x1, int y1, int x2, int y2, int move) {
//         if (print < 10) {
//             print++;
//             if (move % 2 == 0)
//                 System.out.println(x1 + " " + y1 + " " + x2 + " " + y2 + " " + move);
//             else 
//                 System.out.println(x2 + " " + y2 + " " + x1 + " " + y1 + " " + move);
                
//             for (int i = 0; i < board.length; i++) {
//                 System.out.println(Arrays.toString(board[i]));
//             }
//             System.out.println();
//         }
//         if (board[x1][y1] == 0) {
//             result = Math.min(result, move);
//             return;
//         }
//         Set<Pos> otherReachable = new HashSet<>();
//         Pos other = new Pos(x2, y2);
//         for (int i = 0; i < 4; i++) {
//             int nx = x2 + dx[i];
//             int ny = y2 + dy[i];
//             if (nx < 0 || ny < 0 || nx >= board.length || ny >= board[0].length || board[nx][ny] == 0)
//                 continue;
//             otherReachable.add(new Pos(nx, ny));
//         }
//         List<List<Pos>> next = new ArrayList<>();
//         for (int i = 0; i < 3; i++) {
//             next.add(new ArrayList<>());
//         }
//         boolean hasNext = false;
//         for (int i = 0; i < 4; i++) {
//             int nx = x1 + dx[i];
//             int ny = y1 + dy[i];
//             if (nx < 0 || ny < 0 || nx >= board.length || ny >= board[0].length || board[nx][ny] == 0)
//                 continue;
//             hasNext = true;
//             Pos p = new Pos(nx, ny);
//             if (otherReachable.contains(p)) {
//                 next.get(0).add(p);
//                 continue;
//             }
//             if (p.equals(other)) {
//                 next.get(2).add(p);
//                 continue;
//             }
//             next.get(1).add(p);
//         }
//         if (!hasNext) {
//             result = Math.min(result, move);
//             return;
//         }
//         board[x1][y1] = 0;
//         for (int i = 0; i < 3; i++) {
//             for (Pos p : next.get(i)) {
//                 dfs(x2, y2, p.x, p.y, move + 1);
//             }
//             if (next.get(i).size() > 0) {
//                 break;
//             }
//         }
//         board[x1][y1] = 1;
//     }
    
//     public int solution(int[][] board, int[] aloc, int[] bloc) {
//         this.board = board;
//         dfs(aloc[0], aloc[1], bloc[0], bloc[1], 0);
//         return result; 
//     }
// }