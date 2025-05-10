import java.util.*;
import java.io.*;

public class Main {

    static char[][] board = new char[4][8];
    static int[] up = new int[]{0, 0, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 4; i++) {
            board[i] = br.readLine().toCharArray();
        }
        
        int k = Integer.parseInt(br.readLine());
        while (k-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken());
            move(start, dir);
        }
        int result = 0;
        for (int i = 0; i < 4; i++) {
            if (board[i][up[i]] == '1') {
                result += Math.pow(2, i);
            }
        }
        System.out.println(result);
    }

    private static void turn(int cur, int dir) {
        up[cur] = (up[cur] - dir + 8) % 8;
    }

    private static void move(int start, int dir) {
        int[] moves = new int[4];
        moves[start] = dir;
        for (int i = start - 1; i >= 0; i--) {
            if (getRight(i) != getLeft(i + 1)) {
                moves[i] = -moves[i + 1];
            } else {
                break;
            }
        }
        for (int i = start + 1; i < 4; i++) {
            if (getLeft(i) != getRight(i - 1)) {
                moves[i] = -moves[i - 1];
            } else {
                break;
            }
        }
        for (int i = 0; i < 4; i++) {
            turn(i, moves[i]);
        }
    }

    private static int getLeft(int i) {
        return board[i][(up[i] - 2 + 8) % 8];
    }

    private static int getRight(int i) {
        return board[i][(up[i] + 2) % 8];
    }
}
