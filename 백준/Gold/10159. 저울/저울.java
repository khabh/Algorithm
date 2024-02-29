import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] comp = new int[n + 1][n + 1];
        for (int i = 0; i < m; i++) {
            int first = scanner.nextInt();
            int second = scanner.nextInt();
            comp[first][second] = 1;
            comp[second][first] = -1;
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (i == j || comp[i][j] != 0 || comp[i][k] == 0 || comp[k][j] == 0 || comp[k][j] != comp[i][k])
                        continue;
                    comp[i][j] = comp[k][j];
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            int count = -1;
            for (int j = 1; j <= n; j++) {
                if (comp[i][j] == 0)
                    count++;
            }
            sb.append(count).append("\n");
        }
        System.out.println(sb);
    }
}
