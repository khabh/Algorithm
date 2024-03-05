import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }
        if (n == 1) {
            System.out.println(nums[0]);
            return;
        }
        int[] first = new int[n];
        first[0] = nums[0];
        for (int i = 1; i < n; i++) {
            first[i] = nums[i] + Math.max(0, first[i - 1]);
        }
        int result = Arrays.stream(first).max().orElse(0);
        if (n < 3) {
            System.out.println(result);
            return;
        }
        int[] second = new int[n];
        second[0] = first[0];
        second[1] = first[1];
        second[2] = first[0] + nums[2];
        for (int i = 3; i < n; i++) {
            second[i] = Math.max(second[i - 1], first[i - 2]) + nums[i];
        }
        int nextResult = Arrays.stream(second).max().orElse(0);
        System.out.println(Math.max(result, nextResult));
    }
}
