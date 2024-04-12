import java.io.*;
import java.util.StringTokenizer;

class Main {
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        n = Integer.parseInt(br.readLine());
        int[] numbers = new int[n + 1];
        
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        
        boolean[][] dp = new boolean[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i][i] = true;
        }
        for (int i = 1; i < n; i++) {
            dp[i][i + 1] = numbers[i] == numbers[i + 1];
        }
        
        for (int i = 2; i < n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i + j > n) {
                    break;
                }
                int s = j;
                int e = j + i;
                if (dp[s + 1][e - 1] && numbers[s] == numbers[e]) {
                    dp[s][e] = true;
                }
            }
        }
        
        int m = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            sb.append(dp[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] ? "1\n" : "0\n");
        }
        System.out.print(sb);
    }
}
