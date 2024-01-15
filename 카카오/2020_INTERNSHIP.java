// 키패드 누르기
class Solution1 {
    
    public String solution(int[] numbers, String hand) {
        int r = 11;
        int l = 9;
        
        String answer = "";
        
        for (int number : numbers) {
            if (number == 0) {
                number = 11;
            }
            number -= 1;
            if (number % 3 == 0) {
                answer += "L";
                l = number;
                continue;
            }
            if (number % 3 == 2) {
                answer += "R";
                r = number;
                continue;
            }
            int distLeft = calcDist(number, l);
            int distRight = calcDist(number, r);
            if (distLeft == distRight) {
                if (hand.equals("right")) {
                    answer += "R";
                    r = number;
                    continue;
                }
                answer += "L";
                l = number;
                continue;
            }
            if (distLeft > distRight) {
                answer += "R";
                r = number;
                continue;
            }
            answer += "L";
            l = number;
        }
        return answer;
    }
    
    private int calcDist(int first, int second) {
        int x1 = first / 3;
        int y1 = first % 3;
        int x2 = second / 3;
        int y2 = second % 3;
        
        return Math.abs(x1 - x2) + Math.abs(y2 - y1);
    }
}

// 수식 최대화
import java.util.*;

class Solution2 {
    String[] op = {"+", "-", "*"};
    String[] current = new String[3];
    List<Long> numbers = new ArrayList<>();
    List<String> operators = new ArrayList<>();
    
    private long dfs(int count, boolean[] visited) {
        if (count == 3) {
            return calcResult();
        }
        long result = 0;
        for (int i = 0; i < 3; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            current[count] = op[i];
            result = Math.max(result, dfs(count + 1, visited));
            visited[i] = false;
        }
        return result;
    }
    
    private long calcResult() {
        List<Long> numbers = new ArrayList<>(this.numbers);
        List<String> operators = new ArrayList<>(this.operators); 
        
        for (String op : current) {
            List<Long> nn = new ArrayList<>();
            List<String> no = new ArrayList<>();
            for (int i = 0; i < operators.size(); i++) {
                String currentOp = operators.get(i);
                if (!op.equals(currentOp)) {
                    nn.add(numbers.get(i));
                    no.add(currentOp);
                    continue;
                }
                if (op.equals("+")) {
                    numbers.set(i + 1, numbers.get(i) + numbers.get(i + 1));
                    continue;
                }
                if (op.equals("-")) {
                    numbers.set(i + 1, numbers.get(i) - numbers.get(i + 1));
                    continue;
                }
                numbers.set(i + 1, numbers.get(i) * numbers.get(i + 1));
            }
            nn.add(numbers.get(numbers.size() - 1));
            numbers = nn;
            operators = no;
        }
        return Math.abs(numbers.get(0));
    }
    
    public long solution(String expression) {
        String[] splitExpression = expression.split("[+-/*]");
        int index = 0;
        for (String number : splitExpression) {
            numbers.add(Long.parseLong(number));
            index += number.length();
            if (index < expression.length()) {
                operators.add(expression.substring(index, index + 1));
                index++;
            }
        }
        return dfs(0, new boolean[3]);
    }
}

// 보석 쇼핑
import java.util.*;

class Solution3 {
    public int[] solution(String[] gems) {
        int[] answer = {1, gems.length};
        Map<String, Integer> counts = new HashMap<>();
        int start = 0;
        int end = 0;
        counts.put(gems[0], 1);
        int currentCount = 1;
        int maxIndex = gems.length;
        int typeCount = getTypeCount(gems);
        
        while (start <= end) {
            if (currentCount < typeCount) {
                end++;
                if (end == maxIndex) {
                    break;
                }
                if (!counts.containsKey(gems[end])) {
                    counts.put(gems[end], 1);
                    currentCount++;
                    continue;
                }
                counts.put(gems[end], counts.get(gems[end]) + 1);
                continue;
            }
            if (currentCount == typeCount) {
                if (end - start < answer[1] - answer[0]) {
                    answer = new int[] {start + 1, end + 1};
                }
                if (counts.get(gems[start]) == 1) {
                    counts.remove(gems[start++]);
                    currentCount--;
                    continue;
                }
                counts.put(gems[start], counts.get(gems[start]) - 1);
                start++;
            }
            
        }
        
        return answer;
    }
    
    private int getTypeCount(String[] gems) {
        Set<String> gemTypes = new HashSet<>();
        for (String gem : gems) {
           gemTypes.add(gem);
        }
        return gemTypes.size();
    }
}

// 경주로 건설
import java.util.*;

class Solution4 {
    
    private final int[] dx = {1, -1, 0, 0};
    private final int[] dy = {0, 0, 1, -1};
    
    class Pos implements Comparable<Pos> {
        int x;
        int y;
        int dir;
        int cost;
        
        public Pos(int x, int y, int dir, int cost) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Pos other) {
            return cost - other.cost;
        }
    }
    
    public int solution(int[][] board) {
        int n = board.length;
        int[][][] min = new int[n][n][4];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                Arrays.fill(min[i][j], Integer.MAX_VALUE);
        }
        Arrays.fill(min[0][0], 0);
        PriorityQueue<Pos> q = new PriorityQueue<Pos>();
        if (board[0][1] == 0) {
            min[0][1][2] = 100;
            q.add(new Pos(0, 1, 2, 100));
        }
        if (board[1][0] == 0) {
            min[1][0][0] = 100;
            q.add(new Pos(1, 0, 0, 100));
        }
        while (!q.isEmpty()) {
            Pos pos = q.poll();
            int x = pos.x;
            int y = pos.y;
            int dir = pos.dir;
            int cost = pos.cost;
            if (x == n - 1 && y == n - 1) {
                return cost;
            }
            if (min[x][y][dir] < cost) {
                continue;
            }
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || ny < 0 || nx >= n || ny >= n || board[nx][ny] == 1)
                    continue;
                int nextCost = cost + 100;
                if (i != dir) {
                    nextCost += 500;
                }
                if (min[nx][ny][i] < nextCost) {
                    continue;
                }
                min[nx][ny][i] = nextCost;
                q.add(new Pos(nx, ny, i, nextCost));
            }
        }
        return 0;
    }
}

// 동굴 탐험
import java.util.*;

class Solution5 {
    
    class Node {
        int number;
        List<Node> near = new ArrayList<>();
        boolean canVisit = true;
        boolean visited = false;
        
        public Node(int number) {
            this.number = number;
        }
        
        public void addNear(Node node) {
            near.add(node);
        }
    }
    
    public boolean solution(int n, int[][] paths, int[][] orders) {
        Node[] nodes = new Node[n];
        Map<Node, Node> prev = new HashMap<>();
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i);
        }
        for (int[] path : paths) {
            nodes[path[0]].addNear(nodes[path[1]]);
            nodes[path[1]].addNear(nodes[path[0]]);
        }
        for (int[] order : orders) {
            Node second = nodes[order[1]];
            prev.put(nodes[order[0]], second);
            second.canVisit = false;
        }
        Queue<Node> q = new LinkedList<>();
        Set<Node> set = new HashSet<>();
        q.add(nodes[0]);
        nodes[0].visited = true;
        if (!nodes[0].canVisit) {
            return false;
        }
        while (!q.isEmpty()) {
            Node node = q.poll();
            if (prev.containsKey(node)) {
                Node next = prev.get(node);
                next.canVisit = true;
                if (set.contains(next)) {
                    set.remove(next);
                    q.add(next);
                }
            }
            int number = node.number;
            for (Node near : node.near) {
                if (near.visited)
                    continue;
                near.visited = true;
                if (near.canVisit) {
                    q.add(near);
                } else {
                    set.add(near);
                }
            }
        }
        return set.isEmpty();
    }
}