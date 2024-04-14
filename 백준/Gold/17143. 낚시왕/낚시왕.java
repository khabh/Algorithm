import java.util.*;

class Main {
    static int n;
    static int m;
    static int[][] board;
    static List<Shark> sharks = new ArrayList<>();
    static int result = 0;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] input = Arrays.stream(scanner.nextLine().split(" "))
            .mapToInt(Integer::valueOf)
            .toArray();
        n = input[0];
        m = input[1];
        int k = input[2];
        board = new int[n][m];
        sharks.add(new Shark(-1, -1, -1));
        for (int i = 1; i <= k; i++) {
            int[] numbers = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::valueOf)
                .toArray();
            int x = numbers[0] - 1;
            int y = numbers[1] - 1;
            board[x][y] = i;
            sharks.add(new Shark(numbers[2], numbers[3], numbers[4]));
        }

        for (int i = 0; i < m; i++) {
            removeTop(i);
            int[][] next = new int[n][m];
            for (int x = 0; x < n; x++) {
                for (int y = 0; y < m; y++) {
                    move(next, x, y);
                }
            }
            board = next;
        }
        System.out.println(result);
    }

    private static void removeTop(int y) {
        for (int i = 0; i < n; i++) {
            if (board[i][y] > 0) {
                result += sharks.get(board[i][y]).z;
                board[i][y] = 0;
                return;
            }
        }
    }

    private static void move(int[][] next, int x, int y) {
        int order = board[x][y];
        if (order == 0) {
            return;
        }
        Shark shark = sharks.get(order);
        int remain = shark.s;
        // System.out.println(shark.dir + " " + shark.s + " " + x + " " + y);
        while (remain > 0) {
            int nx = x + dx[shark.dir];
            int ny = y + dy[shark.dir];
            if (nx < 0 || ny < 0 || nx == n || ny == m) {
                shark.turn();
                continue;
            }
            x = nx;
            y = ny;
            remain--;
        }
        // System.out.println(x + " " + y);
        if (sharks.get(next[x][y]).z > shark.z) {
            return;
        }
        next[x][y] = order;
    }

    private static class Shark {
        static int[] turns = {1, 0, 3, 2};

        int s;
        int dir;
        int z;

        public Shark(int s, int dir, int z) {
            this.s = s;
            this.dir = dir - 1;
            this.z = z;
        }

        private void turn() {
            dir = turns[dir];
        }
    }
}
