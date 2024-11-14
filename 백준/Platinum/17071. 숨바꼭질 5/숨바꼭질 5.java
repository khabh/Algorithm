import java.util.*;
import java.io.*;

class Main {

    private static final int MAX = 500_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int t = 0;
        boolean[][] visited = new boolean[2][MAX + 1];
        Queue<int[]> q = new LinkedList<>();
        visited[0][n] = true;
        q.add(new int[]{0, n});
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            if (cur[0] != t) {
                if (visited[t % 2][k]) {
                    System.out.println(t);
                    return;
                }
                t++;
                k += t;
            }
            if (k > MAX) {
                break;
            }
            int i = cur[1];
            int nextT = cur[0] + 1;
            int index = nextT % 2;
            if (i >= 1 && !visited[index][i - 1]) {
                visited[index][i - 1] = true;
                q.add(new int[]{nextT, i - 1});
            }
            if (i < MAX && !visited[index][i + 1]) {
                visited[index][i + 1] = true;
                q.add(new int[]{nextT, i + 1});
            }
            if (i * 2 <= MAX && !visited[index][i * 2]) {
                visited[index][i * 2] = true;
                q.add(new int[]{nextT, i * 2});
            }
        }

        System.out.println(-1);
    }
}
