import java.util.*;

class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();
        int n = getCho(k);
        System.out.println(n + " " + split(k, n));
    }

    private static int split(int k, int n) {
        if (k == n) {
            return 0;
        }
        int temp = n / 2;
        if (k == temp) {
            return 1;
        }
        if (k > temp) {
            k -= temp;
        }
        return 1 + split(k, temp);
    }

    private static int getCho(int k) {
        int count = 0;

        while (true) {
            int current = (int) Math.pow(2, count);
            if (current >= k) {
                return current;
            }
            count++;
        }
    }
}
