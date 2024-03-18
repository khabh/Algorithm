import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int[] bags = new int[k];
        PriorityQueue<Integer> q = new PriorityQueue<>();
        Queue<Gem> gems = getGems(n, scanner);
        for (int i = 0; i < k; i++) {
            bags[i] = scanner.nextInt();
        }
        Arrays.sort(bags);
        long sum = 0;
        for (int bag : bags) {
            while (!gems.isEmpty() && gems.peek().weight <= bag) {
                q.add(-gems.poll().price);
            }
            if (!q.isEmpty()) {
                sum -= q.poll();
            }
        }
        System.out.println(sum);
    }

    private static Queue<Gem> getGems(int n, Scanner scanner) {
        List<Gem> gems = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            gems.add(new Gem(scanner.nextInt(), scanner.nextInt()));
        }
        Collections.sort(gems);
        return new LinkedList<>(gems);
    }

    private static class Gem implements Comparable<Gem> {
        int weight;
        int price;

        public Gem(int weight, int price) {
            this.weight = weight;
            this.price = price;
        }

        @Override
        public int compareTo(Gem o) {
            if (weight != o.weight)
                return weight - o.weight;
            return o.price - price;
        }
    }
}
