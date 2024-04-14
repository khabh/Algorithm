import java.util.*;

class Main {
    static int[] result = new int[4];
    static int[][] board = new int[4][18];
    static int[] score = new int[18];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 18; j++) {
                board[i][j] = scanner.nextInt();
            }
        }
        dfs(0, 1);
        for (int i = 0; i < 4; i++) {
            System.out.print(result[i] + " ");
        }
    }

    static int print = 0;

    private static void dfs(int x, int y) {
        if (x > 4) {
            for (int i = 0; i < 4; i++) {
                if (result[i] == 1) {
                    continue;
                }
                if (Arrays.equals(score, board[i])) {
                    result[i] = 1;
                }
            }
            return;
        }

        if (y > 5) {
            dfs(x + 1, x + 2);
            return;
        } 

        for (int i = 0; i <= 2; i++) {
            score[x * 3 + i]++;
            score[y * 3 + getOpposite(i)]++;
            dfs(x, y + 1);
            score[x * 3 + i]--;
            score[y * 3 + getOpposite(i)]--;
        }
    }

    private static int getOpposite(int i) {
        if (i == 1) {
            return 1;
        }
        if (i == 0) {
            return 2;
        }
        return 0;
    }
}
