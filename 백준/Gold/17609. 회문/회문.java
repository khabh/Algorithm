import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.valueOf(br.readLine());
        while (t-- > 0) {
            String input = br.readLine();
            System.out.println(check(input, 0, input.length() - 1, false));
        }
    }

    private static int check(String input, int left, int right, boolean removed) {
        while (left < right) {
            if (input.charAt(left) == input.charAt(right)) {
                left++;
                right--;
                continue;
            }
            if (removed) {
                return 2;
            }
            if (check(input, left + 1, right, true) == 0 || check(input, left, right - 1, true) == 0) {
                return 1;
            } else {
                return 2;
            }
        }
        return 0;
    }
}
