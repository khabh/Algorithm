import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws IOException {
        // 왼쪽 발을 움직이거나 오른쪽 발을 움직이기

        // 중심에서 움직일 때 2
        // 인접한 칸으로 움직일 때 3
        // 반대편으로 움직일 때 4
        // 같은 지점을 누를 때 1

        // 직전에서 가장 작은 움직임, or 전전 움직임에서 반대 움직임
        // 왼발 움직임, 오른발 움직임?
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        List<Integer> steps = new ArrayList<>();
        while (true) {
            int step = Integer.valueOf(st.nextToken());
            if (step == 0) {
                break;
            }
            steps.add(step);
        }
        int n = steps.size();
        if (n == 0) {
            System.out.println(0);
            return;
        }
        if (n == 1) {
            System.out.println(2);
            return;
        }
        int[][] dp = new int[n][5];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][0] = 2;
        for (int i = 1; i < n; i++) {
            int prev = steps.get(i - 1);
            int prevCost = getCost(prev, steps.get(i));
            if (prevCost == 1) {
                for (int k = 0; k <= 4; k++) {
                    if (dp[i - 1][k] == Integer.MAX_VALUE) {
                        continue;
                    }
                    dp[i][k] = dp[i - 1][k] + 1;
                }
                continue;
            }
            for (int k = 0; k <= 4; k++) {
                if (dp[i - 1][k] == Integer.MAX_VALUE) {
                    continue;
                }
                dp[i][k] = Math.min(dp[i][k], dp[i - 1][k] + prevCost);
                dp[i][prev] = Math.min(dp[i][prev], dp[i - 1][k] + getCost(k, steps.get(i)));
            }
        }
        System.out.println(Arrays.stream(dp[n - 1]).min().getAsInt());
    }

    private static int getCost(int a, int b) {
        if (a == 0) {
            return 2;
        }
        if (a == b) {
            return 1;
        }
        if (Math.abs(a - b) == 2) {
            return 4;
        }
        return 3;
    }
}
