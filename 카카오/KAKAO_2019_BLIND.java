// 실패율
import java.util.*;

class Solution {
    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        for (int i = 1; i <= N; i++) {
            answer[i - 1] = i;
        }
        int[] arr = new int[N + 2];
        int[] fail = new int[N + 2];
        Arrays.sort(stages);
        int current = 1;
        int total = stages.length;
        arr[1] = total;
        for (int stage : stages) {
            if (stage == current) {
                fail[stage]++;
                total--;
            }
            if (stage > current) {
                while (current < stage) {
                    current++;
                    arr[current] = total;
                }
                fail[stage] = 1;
                total--;
            }
        }
        
        List<Integer> indexList = new ArrayList<>();
        for (int i = 0; i < answer.length; i++) {
            indexList.add(i + 1);
        }

        indexList.sort(Comparator.comparingDouble(index -> - (double) fail[index] / arr[index]));
        return indexList.stream()
                          .mapToInt(Integer::intValue)
                          .toArray();
    }
}

// 후보키
import java.util.*;

class Solution {
    
    List<Set<Integer>> results = new ArrayList<>();
    
    private void dfs(String[][] relation, Set<Integer> current, int index) {
        if (!current.isEmpty() && isCandidate(relation, current)) {
            results.add(new HashSet<>(current));
            return;
        }
        for (int i = index; i < relation[0].length; i++) {
            current.add(i);
            dfs(relation, current, i + 1);
            current.remove(i);
        }
    }
    
    private boolean isCandidate(String[][] relations, Set<Integer> current) {
        Set<String> exist = new HashSet<>();
        for (String[] relation : relations) {
            StringJoiner sj = new StringJoiner("|");
            for (int index : current) {
                sj.add(relation[index]);
            }
            String result = sj.toString();
            if (exist.contains(result)) {
                return false;
            }
            exist.add(result);
        }
        return true;
    }
    
    public int solution(String[][] relation) {
        dfs(relation, new HashSet<>(), 0);
        int answer = 0;
        for (Set<Integer> result : results) {
            boolean isValid = true;
            for (Set<Integer> other : results) {
                if (!result.equals(other) && result.containsAll(other)) {
                    isValid = false;
                    break;
                }
            }
            if (isValid) {
                answer++;
            }
        }
        return answer;
    }
}

// 길 찾기 게임
import java.util.*;
import java.util.stream.*;

class Solution {
    
    class Node {
        private final int number;
        int x;
        int y;
        Node parent;
        Node left;
        Node right;
        
        public Node(int number, int x, int y) {
            this.number = number;
            this.x = x;
            this.y = y;
        }
    }
    
    private List<Integer> back(List<Integer> result, Node node) {
        if (node == null) {
            return result;
        }
        back(result, node.left);
        back(result, node.right);
        result.add(node.number);
        return result;
    }
    
    private List<Integer> prev(List<Integer> result, Node node) {
        if (node == null) {
            return result;
        }
        result.add(node.number);
        prev(result, node.left);
        prev(result, node.right);
        return result;
    }
    
    public int[][] solution(int[][] nodeinfos) {
        Map<Integer, List<Node>> map = new HashMap<>();
        for (int i = 1; i <= nodeinfos.length; i++) {
            int[] n = nodeinfos[i - 1];
            map.computeIfAbsent(n[1], ArrayList::new).add(new Node(i, n[0], n[1]));
        }
        List<Integer> keys = map.keySet().stream()
            .sorted(Comparator.reverseOrder())
            .collect(Collectors.toList());
        for (int key : keys) {
            Collections.sort(map.get(key), (first, second) -> first.x - second.x);
        }
        for (int p = 0; p < keys.size() - 1; p++) {
            List<Node> parents = map.get(keys.get(p));
            List<Node> childs = map.get(keys.get(p + 1));
            int index = 0;
            for (Node parent : parents) {
                if (index == childs.size())
                    break;
                Node child = childs.get(index);
                if (child.x < parent.x) {
                    parent.left = child;
                    child.parent = parent;
                    index++;
                    if (index == childs.size())
                        break;
                    child = childs.get(index);
                }
                if (parent.parent == null || child.x > parent.x && child.x < getMaxX(parent)) {
                    parent.right = child;
                    child.parent = parent;
                    index++;
                    continue;
                }
            }
        }
        Node root = map.get(keys.get(0)).get(0);
        int[][] answer = new int[2][nodeinfos.length];
        answer[0] = prev(new ArrayList<>(), root).stream()
            .mapToInt(Integer::valueOf)
            .toArray();
        answer[1] = back(new ArrayList<>(), root).stream()
            .mapToInt(Integer::valueOf)
            .toArray();
        return answer;
    }
    
    private int getMaxX(Node node) {
        while (node.parent != null && node.parent.right == node) {
            node = node.parent;
        }
        if (node.parent != null && node.parent.left == node) {
            return node.parent.x;
        }
        return Integer.MAX_VALUE;
    }
}

// 무지의 먹방 라이브
import java.util.*;

class Solution {
    
    class Food {
        int number;
        int time;
        
        public Food(int number, int time) {
            this.number = number;
            this.time = time;
        }
        
        public int getTime() {
            return time;
        }
        
        public int getNumber() {
            return number;
        }
    }
    
    public int solution(int[] foodTimes, long k) {
        List<Food> foods = new ArrayList<>();
        for (int i = 0; i < foodTimes.length; i++) {
            foods.add(new Food(i + 1, foodTimes[i]));
        }
        Collections.sort(foods, Comparator.comparing(Food::getTime));
        Queue<Food> q = new LinkedList<>(foods);
        int prev = 0;
        while (!q.isEmpty()) {
            Food food = q.poll();
            long time = (food.time - prev);
            int unit = q.size() + 1;
            long total = time * unit;
            prev = food.time;
            if (total <= k) {
                k -= total;
                continue;
            }
            while (total > k) {
                total -= unit;
            }
            k -= total;
            q.add(food);
            break;
        }
        List<Food> remain = new ArrayList<>(q);
        Collections.sort(remain, Comparator.comparing(Food::getNumber));
        if (remain.isEmpty()) {
            return -1;
        }
        
        return remain.get((int)k).getNumber();
    }
}