import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] dp = new int[11];
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            int temp = i / 2;
            dp[i] = (temp * (i - temp)) + dp[temp] + dp[i - temp];
        }
        System.out.println(dp[n]);
    }
}
