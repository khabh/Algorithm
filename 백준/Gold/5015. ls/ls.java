import java.util.*;
import java.io.*;

class Main {

    private static char[] input;
    private static char[][] files;

    public static void main(String[] args) throws IOException {
        input();
        StringJoiner sj = new StringJoiner("\n");
        for (char[] file : files) {
            if (isValid(file)) {
                sj.add(String.valueOf(file));
            }
        }
        System.out.println(sj);
    }

    private static boolean isValid(char[] file) {
        int n = input.length;
        int m = file.length;
        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true;
        for (int i = 1; i <= n; i++) {
            if (input[i - 1] == '*') {
                dp[i][0] = dp[i - 1][0];
            } else {
                break;
            }
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                char p = input[i - 1];
                char f = file[j - 1];
                if (p == '*') {
                    dp[i][j] = dp[i - 1][j - 1] || dp[i][j - 1] || dp[i - 1][j];
                } else if (p == f) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }
        return dp[n][m];
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine().toCharArray();
        int n = Integer.parseInt(br.readLine());
        files = new char[n][];
        for (int i = 0; i < n; i++) {
            files[i] = br.readLine().toCharArray();
        }    
    }
}
