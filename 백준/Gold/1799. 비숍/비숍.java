import java.util.*;

class Main {
    static int n;
    static boolean[][] board;
    static int[] result = new int[2];
    static boolean[] r = new boolean[20], l = new boolean[20];
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        board = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = scanner.next().equals("1");
            }
        }
        calc(0, 0, 0, 0);
        calc(0, 1, 1, 0);
        System.out.println(result[0] + result[1]);
    }

    private static void calc(int x, int y, int index, int count) {
        if (y >= n) {
            x++;
            y = y % 2 == 0 ? 1 : 0;
        }
        if (x >= n) {
            result[index] = Math.max(result[index], count);
            return;
        }
        
        calc(x, y + 2, index, count);
        if (board[x][y] && !l[y - x + n - 1] && !r[x + y]) {
            l[y - x + n - 1] = true;
            r[x + y] = true;
            calc(x, y + 2, index, count + 1);
            l[y - x + n - 1] = false;
            r[x + y] = false;
        }
    }
}
