import java.util.Scanner;
import java.util.StringJoiner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] numbers = new int[n];
        SegmentTree tree = getTree(scanner, n, numbers);
        int m = scanner.nextInt();
        StringJoiner stringJoiner = new StringJoiner("\n");
        while (m-- > 0) {
            int op = scanner.nextInt();
            int first = scanner.nextInt();
            int second = scanner.nextInt();
            if (op == 1) {
                tree.update(0, n - 1, 1, second, first - 1);
                continue;
            }
            stringJoiner.add(Integer.toString(tree.getMin(0, n - 1, 1, first - 1, second - 1)));
        }
        System.out.println(stringJoiner);
    }

    private static SegmentTree getTree(Scanner scanner, int n, int[] numbers) {
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }
        return new SegmentTree(numbers);
    }

    private static class SegmentTree {
        int[] tree;

        public SegmentTree(int[] numbers) {
            int n = numbers.length;
            int x = (int) Math.ceil(Math.log(n) / Math.log(2));
            int size = 2 * (int) Math.pow(2, x);
            tree = new int[size];
            initTree(0, n - 1, 1, numbers);
        }

        private int initTree(int start, int end, int index, int[] numbers) {
            if (start == end) {
                tree[index] = numbers[start];
                return tree[index];
            }
            int mid = (start + end) / 2;
            tree[index] = Math.min(initTree(start, mid, index * 2, numbers), initTree(mid + 1, end, index * 2 + 1, numbers));
            return tree[index];
        }

        public void update(int start, int end, int index, int value, int order) {
            if (order < start || end < order) {
                return;
            }
            if (start == end) {
                tree[index] = value;
                return;
            }
            int mid = (start + end) / 2;
            update(start, mid, index * 2, value, order);
            update(mid + 1, end, index * 2 + 1, value, order);
            tree[index] = Math.min(tree[index * 2 + 1], tree[index * 2]);
        }

        public int getMin(int start, int end, int index, int left, int right) {
            if (right < start || end < left) {
                return Integer.MAX_VALUE;
            }
            if (start >= left && end <= right) {
                return tree[index];
            }
            int mid = (start + end) / 2;
            return Math.min(getMin(start, mid, index * 2, left, right), getMin(mid + 1, end, index * 2 + 1, left, right));
        }
    }
}
