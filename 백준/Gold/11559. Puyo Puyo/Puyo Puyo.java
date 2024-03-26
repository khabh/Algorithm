import java.util.*;

public class Main {
    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] board = new char[12][6];
        for (int i = 0; i < 12; i++) {
            board[i] = scanner.nextLine().toCharArray();
        }
        int count = 0;
        while (hasChange(board)) {
            count++;
            updateBoard(board);
        }
        System.out.println(count);
    }

    private static void updateBoard(char[][] board) {
        for (int i = 10; i >= 0; i--) {
            for (int j = 0; j < 6; j++) {
                if (board[i][j] == '.')
                    continue;
                int x = i;
                while (x < 11 && board[x + 1][j] == '.') {
                    x++;
                }
                if (x != i) {
                    board[x][j] = board[i][j];
                    board[i][j] = '.';
                }
            }
        }
    }

    private static boolean hasChange(char[][] board) {
        boolean[][] visited = new boolean[12][6];
        Set<Pos> popped = new HashSet<>();
        for (int j = 0; j < 6; j++) {
            for (int i = 11; i >= 0; i--) {
                if (board[i][j] == '.') {
                    break;
                }
                if (visited[i][j]) {
                    continue;
                }
                List<Pos> pops = getPos(new Pos(i, j), board, visited);
                if (pops.size() >= 4) {
                    popped.addAll(pops);
                }
            }
        }
        if (popped.isEmpty()) {
            return false;
        }
        for (Pos pos : popped) {
            board[pos.x][pos.y] = '.';
        }
        return true;
    }

    private static List<Pos> getPos(Pos start, char[][] board, boolean[][] visited) {
        char color = board[start.x][start.y];
        List<Pos> q = new ArrayList<>();
        q.add(start);
        visited[start.x][start.y] = true;
        int index = 0;
        while (index < q.size()) {
            Pos cur = q.get(index++);
            int x = cur.x;
            int y = cur.y;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || nx >= 12 || ny < 0 || ny >= 6 ||
                        board[nx][ny] != color || visited[nx][ny]) {
                    continue;
                }
                visited[nx][ny] = true;
                q.add(new Pos(nx, ny));
            }
        }
        return q;
    }

    private static class Pos {
        int x;
        int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
