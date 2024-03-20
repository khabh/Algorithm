import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        int k = scanner.nextInt();
        int[][] coins = new int[k][2];
        for (int i = 0; i < k; i++) {
            coins[i][0] = scanner.nextInt();
            coins[i][1] = scanner.nextInt();
        }
        int[] prevDp = new int[t + 1];
        int[] nextDp = new int[t + 1];
        int maxValue = Math.min(t, coins[0][0] * coins[0][1]);
        for (int i = coins[0][0]; i <= maxValue; i += coins[0][0]) {
            prevDp[i] = 1;
        }
        for (int i = 1; i < k; i++) {
            int coin = coins[i][0];
            int maxCoin = Math.min(t, coin * coins[i][1]);
            for (int j = 0; j <= t; j++) {
                nextDp[j] = prevDp[j];
            }
            for (int value = coin; value <= maxCoin; value += coin) {
                nextDp[value]++;
                for (int prev = 1; prev <= t - value; prev++) {
                    nextDp[prev + value] += prevDp[prev];
                }
            }
            prevDp = Arrays.copyOf(nextDp, nextDp.length);
        }
        System.out.println(nextDp[t]);
    }
}
