import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] result = new int[n];
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            nodes.add(new Node(i, scanner.nextInt(), scanner.nextInt()));
        }
        int[] colors = new int[n + 1];
        int total = 0;
        Collections.sort(nodes, Comparator.comparingInt(Node::getSize));
        int index = 0;
        while (index < n) {
            Node node = nodes.get(index);
            result[node.order] = total - colors[node.color];
            Map<Integer, Integer> map = new HashMap<>();
            map.put(node.color, 1);
            int count = 1;

            while (++index < n) {
                Node next = nodes.get(index);
                if (next.size > node.size) {
                    break;
                }
                map.put(next.color, map.getOrDefault(next.color, 0) + 1);
                result[next.order] = total - colors[next.color];
                count++;
            }
            total += (node.size * count);
            for (Integer color : map.keySet()) {
                colors[color] += (node.size * map.get(color));
            }
        }
        System.out.println(Arrays.stream(result)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining("\n")));
    }

    private static class Node {
        final int order;
        final int color;
        final int size;

        public Node(int order, int color, int size) {
            this.order = order;
            this.color = color;
            this.size = size;
        }

        public int getSize() {
            return size;
        }
    }
}
