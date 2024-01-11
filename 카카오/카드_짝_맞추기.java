import java.util.*;

class Solution {
    private static final int[] dx = new int[] {1, -1, 0, 0};
    private static final int[] dy = new int[] {0, 0, 1, -1};
    
    int[][] board;
    int[][] cards = new int[7][4];
    boolean[] exist = new boolean[7];
    int cardCount = 0;
    
    class Node {
        int x;
        int y;
        int count;
        
        public Node(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
    
    private int bfs(int x1, int y1, int x2, int y2) {
        boolean[][] visited = new boolean[4][4];
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(x1, y1, 0));
        visited[x1][y1] = true;
        
        while (!q.isEmpty()) {
            Node node = q.poll();
            int x = node.x;
            int y = node.y;
            int count = node.count;
            if (x == x2 && y == y2)
                return count;
            
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || nx == 4 || ny < 0 || ny == 4)
                    continue;
                if (!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    q.add(new Node(nx, ny, count + 1));
                }
                if (board[nx][ny] > 0)
                    continue;
                int cx = -1;
                int cy = -1;
                for (int t = 2; t < 4; t++) {
                    int tempX = x + dx[i] * t;
                    int tempY = y + dy[i] * t;
                    if (tempX >= 4 || tempX < 0 || tempY >= 4 || tempY < 0)
                        break;
                    if (board[tempX][tempY] > 0 || (dx[i] == -1 && tempX == 0) || (dx[i] == 1 && tempX == 3)
                       || (dy[i] == -1 && tempY == 0) || (dy[i] == 1 && tempY == 3)) {
                        cx = tempX;
                        cy = tempY;
                        break;
                    }
                }
                if (cx != -1 && !visited[cx][cy]) {
                    visited[cx][cy] = true;
                    q.add(new Node(cx, cy, count + 1));
                }
            }
        }
        
        return -1;
    }
    
    int[] is = new int[]{2,3,1};
    int index = 0;
    
    private int dfs(int x, int y, int cardCount, int moveCount) {
        if (cardCount == this.cardCount) {
            return moveCount;
        }
        int minCount = Integer.MAX_VALUE;
        for (int i = 1; i < 7; i++) {
            if (!exist[i])
                continue;
            exist[i] = false;
            int[] card = cards[i];
            int first = bfs(x, y, card[0], card[1]) + bfs(card[0], card[1], card[2], card[3]) + 2;
            board[card[0]][card[1]] = 0;
            board[card[2]][card[3]] = 0;
            minCount = Math.min(dfs(card[2], card[3], cardCount + 1, moveCount + first), minCount);
            board[card[0]][card[1]] = i;
            board[card[2]][card[3]] = i;
            int second = bfs(x, y, card[2], card[3]) + bfs(card[2], card[3], card[0], card[1]) + 2;
            board[card[0]][card[1]] = 0;
            board[card[2]][card[3]] = 0;
            minCount = Math.min(dfs(card[0], card[1], cardCount + 1, moveCount + second), minCount);
            board[card[0]][card[1]] = i;
            board[card[2]][card[3]] = i;
            exist[i] = true;
        }
        return minCount;
    }
    
    
    public int solution(int[][] board, int r, int c) {
        this.board = board;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int current = board[i][j];
                if (current == 0)
                    continue;
                if (exist[current]) {
                    cards[current][2] = i;
                    cards[current][3] = j;
                    continue;
                }
                cards[current][0] = i;
                cards[current][1] = j;
                exist[current] = true;
                cardCount++;
            }
        }
        return dfs(r, c, 0, 0);
    }
}