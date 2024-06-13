import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.valueOf(st.nextToken());
            if (n == 0) {
                break;
            }
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = Integer.valueOf(st.nextToken());
            }
            Tree tree = new Tree(nums);
            System.out.println(tree.getMin(0, n - 1));
        }    
    }

    static class Tree {
        int size;
        Node[] tree;

        public Tree(int[] nums) {
            this.size = nums.length;
            this.tree = new Node[size * 4];
            set(1, 0, nums.length - 1, nums);
        }

        private Node set(int index, int start, int end, int[] nums) {
            if (start == end) {
                tree[index] = new Node(nums[start], start);
                return tree[index];
            }
            int mid = (start + end) / 2;
            Node first = set(index * 2, start, mid, nums);
            Node second = set(index * 2 + 1, mid + 1, end, nums);
            tree[index] = Node.min(first, second);

            return tree[index];
        }

        public long getMin(int left, int right) {
            Node node = get(1, 0, size - 1, left, right);
            long area = (right - left + 1) * node.height;
            if (left < node.index) {
                area = Math.max(area, getMin(left, node.index - 1));
            }
            if (right > node.index) {
                area = Math.max(area, getMin(node.index + 1, right));
            }
            return area;
        }

        private Node get(int index, int start, int end, int left, int right) {
            if (start > right || end < left) {
                return new Node(Integer.MAX_VALUE, -1);
            }
            if (start >= left && end <= right) {
                return tree[index];
            }
            int mid = (start + end) / 2;
            return Node.min(get(index * 2, start, mid, left, right), get(index * 2 + 1, mid + 1, end, left, right));
        }
    }

    static class Node {
        long height;
        int index;

        public Node(long height, int index) {
            this.height = height;
            this.index = index;
        }

        public static Node min(Node n1, Node n2) {
            if (n1.height > n2.height) {
                return n2;
            }
            return n1;
        }
    }
}
