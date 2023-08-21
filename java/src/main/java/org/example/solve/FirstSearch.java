package org.example.solve;

import java.util.*;

public class FirstSearch {
    public static void main(String[] args) {
        new FirstSearch().problem2();
    }

    class Virus implements Comparable<Virus> {
        private final int x;
        private final int y;
        private final int type;

        public Virus(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }

        public int getType() {
            return type;
        }

        @Override
        public int compareTo(Virus o) {
            return type - o.getType();
        }
    }

    // 경쟁적 전염
    public void problem2() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();

        int[][] board = new int[n + 1][n + 1];

        List<Virus> viruses = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                int type = scanner.nextInt();
                if (type == 0)
                    continue;
                board[i][j] = type;
                viruses.add(new Virus(i, j, type));
            }
        }

        int[] dx = new int[]{1, -1, 0, 0};
        int[] dy = new int[]{0, 0, 1, -1};
        int s = scanner.nextInt();
        int resultX = scanner.nextInt();
        int resultY = scanner.nextInt();
        int time = 0;

        while (time++ < s) {
            viruses.sort(Virus::compareTo);
            List<Virus> nextViruses = new ArrayList<>();

            for (Virus virus : viruses) {
                int type = virus.getType();
                for (int i = 0; i < 4; i++) {
                    int nextX = dx[i] + virus.x;
                    int nextY = dy[i] + virus.y;
                    if (nextX < 1 || nextY < 1 || nextY > n || nextX > n || board[nextX][nextY] != 0)
                        continue;
                    board[nextX][nextY] = type;
                    nextViruses.add(new Virus(nextX, nextY, type));
                }
            }
            viruses = nextViruses;
        }

        System.out.println(board[resultX][resultY]);
    }

    // 연구소
    public void problem1() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int k = scanner.nextInt();
        int x = scanner.nextInt();

        boolean[] visited = new boolean[n + 1];
        int distance = 0;

        Map<Integer, List<Integer>> graph = new HashMap<>();

        while (m-- > 0) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();

            if (!graph.containsKey(a))
                graph.put(a, new ArrayList<>());
            graph.get(a).add(b);
        }

        Queue<Integer> queue = new LinkedList<>();
        visited[x] = true;
        queue.add(x);
        while (distance < k) {
            Queue<Integer> nextQueue = new LinkedList<>();
            while (!queue.isEmpty()) {
                int current = queue.poll();
                if (!graph.containsKey(current))
                    continue;
                for (int node : graph.get(current)) {
                    if (visited[node])
                        continue;
                    visited[node] = true;
                    nextQueue.add(node);
                }
            }
            queue = nextQueue;
            if (queue.isEmpty()) {
                System.out.println("-1");
                return;
            }
            distance++;
        }

        queue.stream().mapToInt(Integer::intValue)
                .sorted()
                .forEach(System.out::println);
    }
}
