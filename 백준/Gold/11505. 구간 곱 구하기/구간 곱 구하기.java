import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int k = scanner.nextInt();
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }
        SegmentTree segmentTree = new SegmentTree(numbers);
        while (m > 0 || k > 0) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int c = scanner.nextInt();
            if (a == 1) {
                m--;
                segmentTree.update(1, 0, numbers.length - 1, c, b - 1);
                continue;
            }
            k--;
            System.out.println(segmentTree.getResult(1, 0, numbers.length - 1, b - 1, c - 1));
        }
    }

    private static class SegmentTree {
        private static final long MOD = 1_000_000_007;
        private long[] tree;

        public SegmentTree(int[] numbers) {
            this.tree = new long[4 * numbers.length + 1];
            init(1, 0, numbers.length - 1, numbers);
        }

        private long init(int index, int start, int end, int[] numbers) {
            if (start == end) {
                tree[index] = numbers[start] % MOD;
                return tree[index];
            }
            int mid = (start + end) / 2;
            long prev = init(index * 2, start, mid, numbers);
            long next = init(index * 2 + 1, mid + 1, end, numbers);
            tree[index] = (prev * next) % MOD;
            return tree[index];
        }

        public long update(int index, int start, int end, int value, int changedIndex) {
            if (start > changedIndex || end < changedIndex) {
                return tree[index];
            }
            if (start == end) {
                tree[index] = value;
                return value;
            }
            int mid = (start + end) / 2;
            long prev = update(index * 2, start, mid, value, changedIndex);
            long next = update(index * 2 + 1, mid + 1, end, value, changedIndex);
            tree[index] = (prev * next) % MOD;
            return tree[index];
        }

        public long getResult(int index, int start, int end, int left, int right) {
            if (start > right || end < left) {
                return 1;
            }
            if (start >= left && end <= right) {
                return tree[index] % MOD;
            }
            int mid = (start + end) / 2;
            long prev = getResult(index * 2, start, mid, left, right);
            long next = getResult(index * 2 + 1, mid + 1, end, left, right);
            return (prev * next) % MOD;
        }
    }
}
