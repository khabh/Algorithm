import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] values = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
        }
        int result = Integer.MAX_VALUE;
        int resultLeft = values[0];
        int resutRight = values[1];
        int left = 0;
        int right = n - 1;
        while (left < right) {
            int current = values[left] + values[right];
            if (Math.abs(current) < result) {
                resultLeft = values[left];
                resutRight = values[right];
                result = Math.abs(current);
            }
            if (current == 0) {
                break;
            }
            if (current < 0) {
                left++;
                continue;
            }
            right--;
        }
        System.out.println(resultLeft + " " + resutRight);
    }
}
