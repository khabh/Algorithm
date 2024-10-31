import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<int[]> board = new ArrayList<>();
        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            board.add(new int[]{Integer.parseInt(st.nextToken()), i});
            board.add(new int[]{Integer.parseInt(st.nextToken()), i});
        }
        Collections.sort(board, (first, second) -> first[0] - second[0]);
        Queue<Integer> q = new LinkedList<>();
        for (int[] node : board) {
            q.add(node[1]);
        }
        int count = 0;
        int result = 0;
        while (count != n) {
            count++;
            result = q.poll();
            q.add(result);
        }
        System.out.println(result);
    }
}
