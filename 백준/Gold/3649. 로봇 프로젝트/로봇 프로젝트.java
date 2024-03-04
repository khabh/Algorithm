import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = null;
        while ((input = br.readLine()) != null) {
            int x = Integer.parseInt(input) * 10000000;
            int n = Integer.parseInt(br.readLine());
            int[] lengths = new int[n];
            for (int i = 0; i < n; i++) {
                lengths[i] =  Integer.parseInt(br.readLine());
            }
            Arrays.sort(lengths);
            int left = 0;
            int right = n - 1;
            boolean result = false;
            while (left < right) {
                int sum = lengths[left] + lengths[right];
                if (sum == x) {
                    result = true;
                    break;
                }
                if (sum < x) {
                    left++;
                    continue;
                }
                right--;
            }
            if (!result) {
                System.out.println("danger");
                continue;
            }
            System.out.printf("yes %d %d\n", lengths[left], lengths[right]);
        }
    }
}
