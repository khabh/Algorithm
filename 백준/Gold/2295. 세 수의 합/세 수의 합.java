import java.util.*;

class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] nums = new int[n];
        Set<Integer> sum = new HashSet<>();
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        Arrays.sort(nums);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sum.add(nums[i] + nums[j]);
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                int gap = nums[i] - nums[j];
                if (sum.contains(gap)) {
                    System.out.println(nums[i]);
                    return;
                }
            }
        }
        
    }
}
