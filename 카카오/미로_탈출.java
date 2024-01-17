import java.util.*;

class Solution {
    
    class Move implements Comparable<Move> {
        Node node;
        int dist;
        int state;
        
        public Move(Node node, int dist, int state) {
            this.node = node;
            this.dist = dist;
            this.state = state;
        }
        
        @Override
        public int compareTo(Move other) {
            return  dist - other.dist;
        }
    }
    
    class Node {
        boolean isTrap = false;
        final int number;
        final Map<Node, Integer> roads = new HashMap<>(); // 내가 갈 수 있는 곳
        final Map<Node, Integer> from = new HashMap<>(); // trap일 경우에만 추가, 나에게 오는 통로
        final Map<Node, Integer> traps = new HashMap<>(); // 나와 연결된 트랩
        
        public Node(int number) {
            this.number = number;
        }
        
        public void setTrap() {
            isTrap = true;
        }
        
        public void addRoad(Node node, int dist) {
            if (node.isTrap) {
                node.from.put(this, Math.min(node.from.getOrDefault(this, Integer.MAX_VALUE), dist));
                traps.put(node, Math.min(traps.getOrDefault(node, Integer.MAX_VALUE), dist));
            } else {
                roads.put(node, Math.min(roads.getOrDefault(node, Integer.MAX_VALUE), dist));                
            }
            if (isTrap) {
                node.traps.put(this,  Math.min(node.traps.getOrDefault(this, Integer.MAX_VALUE), dist));
            }
        }
        
        public Map<Node, Integer> getNextNodes(Set<Node> flipped) {
            Map<Node, Integer> nextNodes;
            boolean thisFlipped = flipped.contains(this);
            if (!thisFlipped) {
                nextNodes = new HashMap<>(roads);
            } else {
                nextNodes = new HashMap<>(from);
            }
            for (Node trap : traps.keySet()) {
                // 내가 트랩이고 뒤집힌 경우
                if (thisFlipped) {
                    if (flipped.contains(trap) && trap.from.containsKey(this)) {
                        nextNodes.put(trap, trap.from.get(this));
                        continue;
                    }
                    if (!flipped.contains(trap) && from.containsKey(trap)) {
                        nextNodes.put(trap, from.get(trap));
                    }
                    continue;
                }
                // 내가 트랩이고 뒤집히지 않은 경우
                if (isTrap) {
                    if (flipped.contains(trap) && from.containsKey(trap)) {
                        nextNodes.put(trap, from.get(trap));
                        continue;
                    }
                    if (!flipped.contains(trap) && trap.from.containsKey(this)) {
                        nextNodes.put(trap, trap.from.get(this));
                    }
                    continue;
                }
                // 내가 트랩이 아닌 경우
                if (flipped.contains(trap) && trap.roads.containsKey(this)) {
                    nextNodes.put(trap, trap.roads.get(this));
                    continue;
                }
                if (!flipped.contains(trap) && trap.from.containsKey(this)) {
                    nextNodes.put(trap, trap.from.get(this));
                }
            }
            return nextNodes;
        }
        
           @Override
        public String toString() {
            return String.valueOf(number);
        }

    }

    public int solution(int n, int start, int end, int[][] roads, int[] traps) {
        int maxNum = (int)Math.pow(2, traps.length);
        List<Node> nodes = new ArrayList<>();
        
        PriorityQueue<Move> q = new PriorityQueue<>();
        List<Node> t = new ArrayList<>();
        int[][] min = new int[n + 1][maxNum];
        
        for (int i = 0; i <= n; i++) {
            nodes.add(new Node(i));
            Arrays.fill(min[i], Integer.MAX_VALUE);
        }
        
        Node startNode = nodes.get(start);
        Node target = nodes.get(end);
        
        for (int trap : traps) {
            Node node = nodes.get(trap);
            node.setTrap();
            t.add(node);
        }
        for (int[] road : roads) {
            Node a = nodes.get(road[0]);
            Node b = nodes.get(road[1]);
            a.addRoad(b, road[2]);
        }

        q.add(new Move(startNode, 0, 0));
        
        while (!q.isEmpty()) {
            Move move = q.poll();
            Node node = move.node;
            int state = move.state;
            int dist = move.dist;
            if (target.equals(node)) {
                return dist;
            }
            if (min[node.number][state] < dist) {
                continue;
            }
            
            Map<Node, Integer> nexts = node.getNextNodes(getFlipped(state, t));
            for (Node next : nexts.keySet()) {
                int nextDist = dist + nexts.get(next);
                int nextState = state;
                if (next.isTrap) {
                    nextState = flipState(next, t, state);
                }
                if (min[next.number][nextState] < nextDist) {
                    continue;
                }
                q.add(new Move(next, nextDist, nextState));
                min[next.number][nextState] = nextDist;
            }
            System.out.println();
        }
        return 0;
    }
    
    private int flipState(Node node, List<Node> traps, int state) {
        for (int i = 0; i < traps.size(); i++) {
            if (node.equals(traps.get(i))) {
                return state ^ (1 << i);
            }
        }
        throw new RuntimeException();
    }
    
    private Set<Node> getFlipped(int state, List<Node> traps) {
        Set<Node> flipped = new HashSet<>();
        for (int i = 0; i < traps.size(); i++) {
            if ((state & 1 << i) != 0) {
                flipped.add(traps.get(i));
            }
        }
        return flipped;
    }
}