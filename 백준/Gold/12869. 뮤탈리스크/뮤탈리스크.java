import java.util.Scanner;

public class Main {
    static int result = 60;
    static int[][] delta = {{9,3,1},{9,1,3},{3,9,1},{3,1,9},{1,9,3},{1,3,9}};
    static int[][][] dp;
    static int n;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        int[] remains = new int[3];
        for (int i = 0; i < n; i++) {
            remains[i] = scanner.nextInt();
        }
        dp = new int[remains[0] + 1][remains[1] + 1][remains[2] + 1];
        getResult(remains, 0);
        System.out.println(result);
    }

    private static void getResult(int[] numbers, int count) {
        int prev = dp[numbers[0]][numbers[1]][numbers[2]];
        if (prev != 0 && prev <= count) {
            return;
        }
        dp[numbers[0]][numbers[1]][numbers[2]] = count;
        boolean isEnd = true;
        for (int num : numbers) {
            if (num > 0) {
                isEnd = false;
                break;
            }
        }
        if (isEnd) {
            result = Math.min(result, count);
            return;
        }
        for (int[] order : delta) {
            int[] next = new int[3];
            for (int i = 0; i < n; i++) {
                next[i] = Math.max(0, numbers[i] - order[i]);
            }
            getResult(next, count + 1);
            if (result <= count + 1) {
                break;
            }
        }
    }
}
