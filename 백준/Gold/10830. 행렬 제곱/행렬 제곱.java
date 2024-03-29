import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static int[][] board;
    private static int n;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        long b = scanner.nextLong();
        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = scanner.nextInt();
            }
        }
        int[][] result = calculate(board, b);
        StringJoiner sj = new StringJoiner("\n");
        for (int i = 0; i < n ; i++) {
            sj.add(Arrays.stream(result[i])
                    .mapToObj((number) -> String.valueOf(number % 1000))
                    .collect(Collectors.joining(" ")));
        }
        System.out.println(sj);
    }

    private static int[][] calculate(int[][] board, long b) {
        if (b == 1) {
            return board;
        }
        int[][] result = calculate(board, b / 2);
        if (b % 2 == 0) {
            return mul(result, result);
        }
        return mul(mul(result, result), board);
    }

    private static int[][] mul(int[][] first, int[][] second) {
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                long sum = 0;
                for (int k = 0; k < n; k++) {
                    sum += (first[i][k] * (long)second[k][j]);
                }
                sum %= 1000;
                result[i][j] = (int)sum;
            }
        }
        return result;
    }
}
