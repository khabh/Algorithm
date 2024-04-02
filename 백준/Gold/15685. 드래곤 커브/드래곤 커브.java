import java.util.*;

public class Main {
    static final boolean[][] board = new boolean[101][101];
    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int d = scanner.nextInt();
            int g = scanner.nextInt();
            setBoard(x, y, d, g);
        }

        int count = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (board[i][j] && board[i + 1][j] && board[i + 1][j + 1] && board[i][j + 1]) {
                    count++;
                }
            }
        }

        System.out.println(count);
    }

    private static void setBoard(int x, int y, int d, int g) {
        List<Integer> directions = new ArrayList<>();
        directions.add(d);
        int count = 1;
        for (int i = 0; i < g; i++) {
            for (int j = count - 1; j >= 0; j--) {
                directions.add(turn(directions.get(j)));
            }
            count *= 2;
        }
        board[x][y] = true;
        for (int direction : directions) {
            x += dx[direction];
            y += dy[direction];
            board[x][y] = true;

        }
    }

    private static int turn(int d) {
        if (d <= 2) {
            return d + 1;
        }
        return 0;
    }
}
