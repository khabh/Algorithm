import java.util.*;

class Main {
    static int n;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int c = scanner.nextInt();
        int m = scanner.nextInt();
        List<List<Node>> nodes = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            nodes.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int cost = scanner.nextInt();
            nodes.get(a).add(new Node(b, cost));
        }
        int[] car = new int[n + 1];
        int result = 0;
        int w = 0;
        for (int index = 1; index <= n; index++) {
            List<Node> cur = nodes.get(index);
            Collections.sort(cur, Comparator.comparingInt(Node::getTarget));
            result += car[index];
            w -= car[index];
            car[index] = 0;
            for (Node node : cur) {
                int target = node.target;
                int cost = node.weight;
                if (w < c) {
                    int temp = Math.min(c - w, cost);
                    cost -= temp;
                    car[target] += temp;
                    w += temp;
                }
                for (int i = n; i >= target + 1; i--) {
                    if (cost == 0) {
                        break;
                    }
                    if (car[i] == 0) {
                        continue;
                    }
                    int change = Math.min(cost, car[i]);
                    car[i] -= change;
                    car[target] += change;
                    cost -= change;
                }
            }
        }

        System.out.println(result);
    }

    private static class Node {
        int target;
        int weight;

        Node(int target, int weight) {
            this.target = target;
            this.weight = weight;
        }

        public int getTarget() {
            return target;
        }
    }
}
