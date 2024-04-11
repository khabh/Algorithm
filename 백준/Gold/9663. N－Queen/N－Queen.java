import java.util.*;

class Main {
    static int n;
    static int[] queen = new int[15];
    static boolean[] visited = new boolean[15];
    static int result = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        getResult(0);
        System.out.println(result);
    }

    private static void getResult(int index) {
        if (index == n) {
            result++;
            return;
        }
        for (int y = 0; y < n; y++) {
            if (visited[y]) {
                continue;
            }
            boolean isValid = true;
            for (int qx = 0; qx < index; qx++) {
                if (qx - queen[qx] == index - y || qx + queen[qx] == index + y) {
                    isValid = false;
                    break;
                }
            }
            if (isValid) {
                queen[index] = y;
                visited[y] = true;
                getResult(index + 1);
                visited[y] = false;
            }
        }
    }
}
