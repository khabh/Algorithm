import java.util.*;

class Solution {
    
    class Node {
        final int number;
        final int count;
        List<Node> childs = new ArrayList<>();
        
        public Node(int number, int count) {
            this.number = number;
            this.count = count;
        }
        
        public void addChild(Node node) {
            childs.add(node);
        }
        
        @Override 
        public String toString() {
            return String.valueOf(number);
        }
    }
    
    public int solution(int k, int[] num, int[][] links) {
        List<Node> nodes = new ArrayList<>();
        int maxValue = 0;
        int sum = 0;
        for (int i = 0; i < num.length; i++) {
            nodes.add(new Node(i, num[i]));
            sum += num[i];
            maxValue = Math.max(maxValue, num[i]);
        }
        boolean[] isRoot = new boolean[num.length];
        Arrays.fill(isRoot, true);
        for (int i = 0; i < num.length; i++) {
            int first = links[i][0];
            int second = links[i][1];
            Node current = nodes.get(i);
            if (first > -1) {
                current.addChild(nodes.get(first));
                isRoot[first] = false;
            }
            if (second > -1) {
                current.addChild(nodes.get(second));
                isRoot[second] = false;
            }
        }
        Node root = getRoot(isRoot, nodes);
        Stack<Node> s = getSortedNodes(root);
        int start = maxValue;
        int end = sum;
        while (start <= end) {
            int mid = (start + end) / 2;
            Stack<Node> stack = new Stack<>();
            stack.addAll(s);
            boolean isValid = dp(stack, mid, k);
            if (isValid) {
                end = mid - 1;
                continue;
            }
            start = mid + 1;
        }
        return start;
    }
    
    private boolean dp(Stack<Node> nodes, int l, int k) {
        int[][] dp = new int[nodes.size()][2];
        while (!nodes.isEmpty()) {
            Node node = nodes.pop();
            int number = node.number;
            int count = node.count;
            if (count > l) {
                return false;
            }
            if (node.childs.size() == 0) {
                dp[number][0] = 1;
                dp[number][1] = count;
                continue;
            }
            if (node.childs.size() == 1) {
                Node child = node.childs.get(0);
                int childGroup = dp[child.number][0];
                int childCount = dp[child.number][1];
                if (childCount + count <= l) {
                    dp[number][1] = count + childCount;
                    dp[number][0] = childGroup;
                } else {
                    dp[number][1] = count;
                    dp[number][0] = 1 + childGroup;
                }
                if (dp[number][0] > k) {
                    return false;
                }
                continue;
            }
            int first = node.childs.get(0).number;
            int second = node.childs.get(1).number;
            if (dp[first][1] + dp[second][1] + count <= l) {
                dp[number][0] = dp[first][0] + dp[second][0] - 1;
                dp[number][1] = dp[first][1] + dp[second][1] + count;
            }
            else if (dp[first][1] + count <= l || dp[second][1] + count <= l) {
                dp[number][0] = dp[first][0] + dp[second][0];
                dp[number][1] = count + Math.min(dp[first][1], dp[second][1]);
            }
            else {
                dp[number][0] = 1 + dp[first][0] + dp[second][0];
                dp[number][1] = count;
            }
            if (dp[number][0] > k) {
                return false;
            }
        }
        return true;
    }
    
    private Stack<Node> getSortedNodes(Node root) {
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        Stack<Node> stack = new Stack<>();
        stack.add(root);
        while (!q.isEmpty()) {
            Node n = q.poll();
            for (Node child : n.childs) {
                q.add(child);
                stack.push(child);
            }
        }
        return stack;
    }
    
    private Node getRoot(boolean[] isRoot, List<Node> nodes) {
        for (int i = 0; i < isRoot.length; i++) {
            if (isRoot[i]) {
                return nodes.get(i);
            }
        }
        return null;
    }
}