import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            weights[i] = scanner.nextInt();
        }
        Arrays.sort(weights);
        int target = 1;
        for (int weight : weights) {
            if (weight > target) {
                break;
            }
            target += weight;
        }
        System.out.println(target);
    }
}
