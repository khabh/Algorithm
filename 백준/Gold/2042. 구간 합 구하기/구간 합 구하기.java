import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken());
        
        long[] nums = new long[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Long.parseLong(br.readLine());
        }

        Tree tree = new Tree(nums);
        StringJoiner sj = new StringJoiner("\n");

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (a == 1) {
                long c = Long.parseLong(st.nextToken());
                tree.update(0, nums.length - 1, 1, b - 1, c);
            }
            else {
                int c = Integer.parseInt(st.nextToken());
                sj.add(String.valueOf(tree.get(0, nums.length - 1, 1, b - 1, c - 1)));
            }
        }
        System.out.println(sj);
    }

    static class Tree {
        long[] sum;

        public Tree(long[] values) {
            this.sum = new long[values.length * 4];
            init(0, values.length - 1, 1, values);
        }

        public long init(int start, int end, int index, long[] values) {
            if (start == end) {
                sum[index] = values[start];
                return sum[index];
            }
            int mid = (start + end) / 2;
            long left = init(start, mid, index * 2, values);
            long right = init(mid + 1, end, index * 2 + 1, values);
            sum[index] = left + right;
            return sum[index];
        }

        public void update(int start, int end, int index, int changedIndex, long changedValue) {
            if (start > changedIndex || end < changedIndex) {
                return;
            }
            if (start == end) {
                sum[index] = changedValue;
                return;
            }
            int mid = (start + end) / 2;
            update(start, mid, index * 2, changedIndex, changedValue);
            update(mid + 1, end, index * 2 + 1, changedIndex, changedValue);
            sum[index] = sum[index * 2] + sum[index * 2 + 1];
        }

        public long get(int start, int end, int index, int left, int right) {
            if (left <= start && end <= right) {
                return sum[index];
            }
            if (start > right || end < left) {
                return 0L;
            }
            int mid = (start + end) / 2;
            return get(start, mid, index * 2, left, right) + get(mid + 1, end, index * 2 + 1, left, right);
        }
    }
}
