package org.example.solve;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


class Planet {
    int number;
    int x;
    int y;
    int z;

    public Planet(int number, int x, int y, int z) {
        this.number = number;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int compareX(Planet p) {
        return x - p.x;
    }

    public int compareY(Planet p) {
        return y - p.y;
    }

    public int compareZ(Planet p) {
        return z - p.z;
    }
}

class Tunnel {
    private static int[] parents = IntStream.range(0, 100000).toArray();
    long cost;
    int firstPlanet;
    int secondPlanet;

    public Tunnel(int p1, int p2, int cost) {
        this.firstPlanet = p1;
        this.secondPlanet = p2;
        this.cost = cost;
    }

    private int getParent(int number) {
        int result = number;
        while (result != parents[result])
            result = parents[result];
        return result;
    }

    public boolean establishNewLink() {
        firstPlanet = getParent(firstPlanet);
        secondPlanet = getParent(secondPlanet);

        if (firstPlanet == secondPlanet)
            return false;

        if (firstPlanet < secondPlanet)
            parents[secondPlanet] = firstPlanet;
        else
            parents[firstPlanet] = secondPlanet;
        return true;
    }

    public int compareTo(Tunnel o) {
        if (cost >= o.cost)
            return 1;
        return -1;
    }
}

public class Graph {
    public static void main(String[] args) {
        new Graph().problem4();
    }

//    private void solve(Scanner scanner) {
//        int n = scanner.nextInt();
//        int[] preCount = new int[n + 1];
//        for (int i = 0; i < n; i++) {
//            preCount[scanner.nextInt()] = i;
//        }
//        int m = scanner.nextInt();
//
//        for (int i = 0; i < m; i++) {
//            int up = scanner.nextInt();
//            int down = scanner.nextInt();
//            if (preCount[up] < preCount[down]) {
//                System.out.println("IMPOSSIBLE");
//                return;
//            }
//            for (int j = 1; j <= n; j++) {
//                if (preCount[i] < preCount[down] || preCount[i] > preCount[up])
//                    continue;
//                preCount[i]++;
//            }
//            preCount[down]++;
//            preCount[up]--;
//        }
//
//    }
//
//    public void problem5() {
//        Scanner scanner = new Scanner(System.in);
//        int t = scanner.nextInt();
//        while (t-- > 0) {
//
//        }
//    }

    // 행성 터널
    public void problem4() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int count = 0;
        long totalCost = 0;
        List<Planet> planets = IntStream.range(0, n)
                .mapToObj(index -> new Planet(index, scanner.nextInt(), scanner.nextInt(), scanner.nextInt()))
                .collect(Collectors.toList());

        List<Tunnel> tunnels = new ArrayList<>();

        planets.sort(Planet::compareX);
        for (int i = 0; i < n - 1; i ++) {
            Planet a = planets.get(i);
            Planet b = planets.get(i + 1);
            tunnels.add(new Tunnel(a.number, b.number, Math.abs(a.x - b.x)));
        }
        planets.sort(Planet::compareY);
        for (int i = 0; i < n - 1; i ++) {
            Planet a = planets.get(i);
            Planet b = planets.get(i + 1);
            tunnels.add(new Tunnel(a.number, b.number, Math.abs(a.y - b.y)));
        }
        planets.sort(Planet::compareZ);
        for (int i = 0; i < n - 1; i ++) {
            Planet a = planets.get(i);
            Planet b = planets.get(i + 1);
            tunnels.add(new Tunnel(a.number, b.number, Math.abs(a.z - b.z)));
        }
        tunnels.sort(Tunnel::compareTo);

        for (Tunnel tunnel : tunnels) {
            if (!tunnel.establishNewLink())
                continue;
            count++;
            totalCost += tunnel.cost;
            if (count == n - 1)
                break;
        }

        System.out.println(totalCost);
    }

    // 어두운 길
    class Path implements Comparable<Path> {
        final int firstNode;
        final int secondNode;
        final int cost;

        public Path(int firstNode, int secondNode, int cost) {
            this.firstNode = firstNode;
            this.secondNode = secondNode;
            this.cost = cost;
        }

        @Override
        public int compareTo(Path o) {
            return cost - o.cost;
        }
    }

    public void problem3() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int count = 0;
        int result = 0;
        int[] parents = IntStream.range(0, n).toArray();
        Queue<Path> paths = new PriorityQueue<>();

        while (m-- > 0) {
            Path path = new Path(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
            result += path.cost;
            paths.add(path);
        }

        while (count < n - 1) {
            Path path = paths.poll();
            int a = path.firstNode;
            int b = path.secondNode;
            if (getParent(parents, a) == getParent(parents, b))
                continue;
            result -= path.cost;
            unionParent(a, b, parents);
            count++;
        }
        System.out.println(result);
    }

    // 탑승구
    public int getParent(int[] parents, int node) {
        while (parents[node] != node) {
            node = parents[node];
        }
        return node;
    }

    public void unionParent(int a, int b, int[] parents) {
        a = getParent(parents, a);
        b = getParent(parents, b);

        if (b > a)
            parents[b] = a;
        else
            parents[a] = b;
    }

    public void problem2() {
        Scanner scanner = new Scanner(System.in);
        int g = scanner.nextInt();
        int p = scanner.nextInt();
        int result = 0;
        int[] count = IntStream.range(0, g + 1)
                .toArray();
        int[] planes = IntStream.range(0, p)
                .map(index -> scanner.nextInt())
                .toArray();

        for (int plane : planes) {
            int availableGate = getParent(count, plane);
            if (availableGate == 0)
                break;
            result++;
            unionParent(availableGate, availableGate - 1, count);
        }
        System.out.println(result);
    }

    // 여행 계획
    public void problem1() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] board = new int[n][n];
        int m = scanner.nextInt();
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = scanner.nextInt();
            }
        }

        int[] plan = IntStream.range(0, m)
                .map(index -> scanner.nextInt() - 1)
                .toArray();

        Queue<Integer> nodes = new LinkedList<>();
        nodes.add(plan[0]);
        visited[plan[0]] = true;
        while (!nodes.isEmpty()) {
            int node = nodes.poll();

            for (int i = 0; i < n; i++) {
                if (board[node][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    nodes.add(i);
                }
            }
        }

        if (Arrays.stream(plan).allMatch(p -> visited[p]))
            System.out.println("YES");
        else
            System.out.println("NO");
    }
}
