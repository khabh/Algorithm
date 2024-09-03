import java.util.*;
import java.io.*;

public class Main {

    private static int n, m, k;
    private static char[][] board;
    private static String result = "0".repeat(51);
    private static boolean[] visited;
    private static char[] rsp = {'R', 'S', 'P'};
    private static Map<Character, Character> map = Map.of('R', 'P', 'S', 'R', 'P', 'S');

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        board = new char[n][m];
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            board[i] = input.toCharArray();
        }
        solve("", 0, 0);
        if (result.length() > m) {
            System.out.println("-1");
            return;
        }
        System.out.println(result.length());
        System.out.println(result);
    }

    private static void solve(String prev, int visitCount, int roundCount) {
        if (prev.length() >= result.length()) {
            return;
        }
        if (n - visitCount <= k) {
            if (n == visitCount) {
                return;
            }
            result = prev;
            return;
        }
        if (roundCount >= m) {
            return;
        }
        for (int i = 0; i < 3; i++) {
            char cur = rsp[i];
            char expected = map.get(cur);
            List<Integer> changes = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if (visited[j]) {
                    continue;
                }
                if (board[j][roundCount] != expected) {
                    visited[j] = true;
                    changes.add(j);
                }
            }
            solve(prev + cur, visitCount + changes.size(), roundCount + 1);
            for (int change : changes) {
                visited[change] = false;
            }
        }
    }
}