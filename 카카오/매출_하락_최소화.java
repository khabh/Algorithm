import java.util.*;

class Solution {    
    
    private final int[][] dp = new int[300_000][2];
    
    private void dfs(Node node) {
        int current = node.number;
        if (node.childs.size() == 0) {
            dp[current][1] = node.sale;
            dp[current][0] = 0;
            return;
        }
        int sumChild = 0;
        boolean childContained = false;
        for (Node child : node.childs) {
            dfs(child);
            int childNum = child.number;
            sumChild += Math.min(dp[childNum][1], dp[childNum][0]);
            if (dp[childNum][1] <= dp[childNum][0]) {
                childContained = true;
            }
        }
        dp[current][1] = sumChild + node.sale;
        if (childContained) {
            dp[current][0] = sumChild;
            return;
        }
        int noCurrent = Integer.MAX_VALUE;
        for (Node child : node.childs) {
            noCurrent = Math.min(noCurrent, sumChild - dp[child.number][0] + dp[child.number][1]);   
        }
        dp[current][0] = noCurrent;
    }
    
    public int solution(int[] sales, int[][] links) {
        List<Node> nodes = new ArrayList<>();   
        for (int number = 0; number < sales.length; number++) {
            nodes.add(new Node(number, sales[number]));
            Arrays.fill(dp[number], Integer.MAX_VALUE);
        }
        for (int[] link : links) {
            int first = link[0] - 1;
            int second = link[1] - 1;
            nodes.get(first).addChild(nodes.get(second));
        }
        dfs(nodes.get(0));
        return Math.min(dp[0][0], dp[0][1]);
    }
    
    class Node {
        int number;
        int sale;
        List<Node> childs = new ArrayList<>();
        
        public Node(int number, int sale) {
            this.number = number;
            this.sale = sale;
        }
        
        public void addChild(Node child) {
            childs.add(child);
        }
    }
}