import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] numbers = new int[n];
        findResult(numbers, 0);
        System.out.println(Arrays.stream(numbers)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining()));
    }

    private static boolean findResult(int[] numbers, int current) {
        if (current == numbers.length) {
            return true;
        }
        for (int i = 1; i <= 3; i++) {
            numbers[current] = i;
            if (!isValid(numbers, current)) {
                continue;
            }
            if (findResult(numbers, current + 1)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isValid(int[] numbers, int last) {
        int length = 1;
        while (length * 2 <= last + 1) {
            int prev = last - length * 2 + 1;
            int current = last - length + 1;
            boolean isSame = true;
            for (int i = 0; i < length; i++) {
                if (numbers[prev + i] != numbers[current + i]) {
                    isSame = false;
                    break;
                }
            }
            if (isSame) {
                return false;
            }
            length++;
        }
        return true;
    }
}
