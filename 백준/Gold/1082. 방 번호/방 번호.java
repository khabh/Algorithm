import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] prices = new int[n];
        for (int i = 0; i < n; i++) {
            prices[i] = Integer.valueOf(st.nextToken());
        }
        int m = Integer.valueOf(br.readLine());
        String[] dp = new String[m + 1];
        Arrays.fill(dp, "0");

        for (int i = 1; i < n; i++) {
            int price = prices[i];
            if (price > m) {
                continue;
            }
            dp[price] = String.valueOf(i);
        }
        for (int i = 1; i <= m; i++) {
            if (dp[i].equals("0")) {
                continue;
            }
            for (int num = 1; num < n; num++) {
                int price = prices[num];
                if (price + i <= m) {
                    dp[i + price] = getMax(dp[i + price], calcMax(dp[i], num));
                }
            }
        }
        String result = "0";
        for (int i = 1; i <= m; i++) {
            result = getMax(result, dp[i]);
        }
        for (int i = 1; i <= m; i++) {
            if (dp[i].equals("0")) {
                continue;
            }
            if (m - i < prices[0]) {
                break;
            }
            int zeroCount = (m - i) / prices[0];
            StringBuilder sb = new StringBuilder(dp[i]);
            while (zeroCount-- > 0) {
                sb.append('0');
            }
            result = getMax(result, sb.toString());
        }
        System.out.println(result);
    }

    private static String calcMax(String prev, int num) {
        char c = (char)('0' + num);
        int index = 0;
        while (index < prev.length() && c < prev.charAt(index)) {
            index++;
        }
        return prev.substring(0, index) + c + prev.substring(index);
    }

    private static String getMax(String num1, String num2) {
        if (num1.length() > num2.length()) {
            return num1;
        } else if (num1.length() < num2.length()) {
            return num2;
        }

        for (int i = 0; i < num1.length(); i++) {
            if (num1.charAt(i) > num2.charAt(i)) {
                return num1;
            } else if (num1.charAt(i) < num2.charAt(i)) {
                return num2;
            }
        }

        return num1;
    }
}
