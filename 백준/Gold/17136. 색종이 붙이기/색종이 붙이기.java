import java.util.*;

class Main {
    static int result = Integer.MAX_VALUE;
    static int[][] board = new int[10][10];
    static int count = 0;
    static int[] p = {0, 5, 5, 5, 5, 5};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 10; i++) {
            for (int j = 0;j < 10; j++) {
                board[i][j] = scanner.nextInt();
            }
        }
        dfs(0, 0, 0);
        if (result == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }
        System.out.println(result);
    }

    private static void dfs(int x, int y, int count) {
        if (count >= result) {
            return;
        }
        if (x > 9) {
            result = count;
            return;
        }

        if (y > 9) {
            dfs(x + 1, 0, count);
            return;
        }

        if (board[x][y] == 0) {
            dfs(x, y + 1, count);
            return;
        }
        for (int k = 5; k >= 1; k--) {
            if (p[k] == 0 || !isValid(x, y, k)) {
                continue;
            }
            update(x, y, k, 0);
            p[k]--;
            dfs(x, y + 1, count + 1);
            update(x, y, k, 1);
            p[k]++;
        }
    }

    private static boolean isValid(int x, int y, int size) {
        if (x + size > 10 || y + size > 10) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[x + i][y + j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void update(int x, int y, int size, int value) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[x + i][y + j] = value;
            }
        }
    }
}
