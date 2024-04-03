import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] numbers = new int[n];
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for (int i = 0; i < n; i++) {
            int number = scanner.nextInt();
            for (int j = 0; j < i; j++) {
                if (numbers[j] < number)
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            numbers[i] = number;
        }
        System.out.println(Arrays.stream(dp).max().orElse(1));
    }
}