import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int r1 = scanner.nextInt();
        int c1 = scanner.nextInt();
        int r2 = scanner.nextInt();
        int c2 = scanner.nextInt();
        int width;
        int height = -1;
        int[][] result = new int[r2 - r1 + 1][c2 - c1 + 1];

        for (int x = r1; x <= r2; x++) {
            width = -1;
            height++;
            for (int y = c1; y <= c2; y++) {
                width++;
                int k = Math.abs(Math.max(Math.abs(x), Math.abs(y)));
                int prevLength = k * 2 - 1;
                int prev = prevLength * prevLength;
                int current = prevLength + 1;
                if (y == k) {
                    if (x == y) {
                        result[height][width] = (prevLength + 2) * (prevLength + 2);
                        continue;
                    }
                    result[height][width] = prev + k - x;
                    continue;
                }
                if (x == -k) {
                    result[height][width] = prev + current + (k - y);
                    continue;
                }
                if (y == -k) {
                    result[height][width] = prev + current * 2 + k + x;
                    continue;
                }
                result[height][width] = current * 3 + prev + k + y;
            }
        }
        int maxNumber = Arrays.stream(result)
                .mapToInt(r -> Arrays.stream(r).max().getAsInt())
                .max()
                .getAsInt();
        String format = String.format("%%%dd", String.valueOf(maxNumber).length());
        String all = Arrays.stream(result)
                .map(r -> Arrays.stream(r).mapToObj(number -> String.format(format, number)).collect(Collectors.joining(" ")))
                        .collect(Collectors.joining("\n"));
        System.out.println(all);
    }
}
