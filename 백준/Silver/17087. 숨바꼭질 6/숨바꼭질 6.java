import java.util.Scanner;

public class Main {

    private static int getGCD(int a, int b) {
        if (a < b) {
            return getGCD(b, a);
        }
        int n = a % b;
        while (n != 0) {
            a = b;
            b = n;
            n = a % b;
        }

        return b;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int s = scanner.nextInt();
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }
        int gcd = Math.abs(s - numbers[0]);
        for (int i = 1; i < n; i++) {
            gcd = getGCD(gcd, Math.abs(s - numbers[i]));
        }
        System.out.println(gcd);
    }
}
