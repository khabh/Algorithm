import java.util.*;

class Solution {
    
    enum Type {
        SHEEP(0),
        WOLF(1);
        
        private final int type;
        
        Type(int type) {
            this.type = type;
        }
    }
    
    class Node {
        final List<Node> childs = new ArrayList<>();
        private final Type type;
        private final int number;
        
        public Node(int number, int type) {
            this.number = number;
            this.type = type == 0 ? Type.SHEEP : Type.WOLF;
        }
        
        public void addChild(Node child) {
            childs.add(child);
        }
        
        public boolean isSheep() {
            return type.equals(Type.SHEEP);
        }
    }
    
    private final List<Node> nodes = new ArrayList<>(); 
    private int maxSheep = 1;
    
    private void dfs(int remain, boolean[] nextVisit, int sheep, int wolf) {
        if (sheep <= wolf)
            sheep = 0;
        maxSheep = Math.max(sheep, maxSheep);
        if (remain + sheep <= maxSheep || remain == 0)
            return;
        for (int i = 0; i < nodes.size(); i++) {
            if (!nextVisit[i])
                continue;
            Node node = nodes.get(i);
            nextVisit[node.number] = false;
            for (Node child : node.childs) {
                nextVisit[child.number] = true;
            }
            if (node.isSheep()) {
                dfs(remain - 1, nextVisit, sheep + 1, wolf);
            } else {
                dfs(remain, nextVisit, sheep, wolf + 1);
            }             
            for (Node child : node.childs) {
                nextVisit[child.number] = false;
            }
            nextVisit[node.number] = true;
        }
    }
    
    public int solution(int[] info, int[][] edges) {
        int remain = 0;
        for (int i = 0; i < info.length; i++) {
            nodes.add(new Node(i, info[i]));
            if (info[i] == 0)
                remain++;
        }
        for (int[] edge : edges) {
            Node parent = nodes.get(edge[0]);
            Node child = nodes.get(edge[1]);
            parent.addChild(child);
        }
        boolean[] nextVisit = new boolean[nodes.size()];
        for (Node child : nodes.get(0).childs) {
            nextVisit[child.number] = true;
        } 
        dfs(remain - 1, nextVisit, 1, 0);
        return maxSheep;
    }
}