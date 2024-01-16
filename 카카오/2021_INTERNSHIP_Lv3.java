// 숫자 문자열과 영단어
import java.util.*;

class Solution {
    public int solution(String s) {
        Map<String, String> numbers = initNumbers();
        for (String number : numbers.keySet()) {
            s = s.replace(number, numbers.get(number));
        }
        return Integer.parseInt(s);
    }
    
    private Map<String, String> initNumbers() {
        Map<String, String> numbers = new HashMap<>();
        numbers.put("zero", "0");
        numbers.put("one", "1");
        numbers.put("two", "2");
        numbers.put("three", "3");
        numbers.put("four", "4");
        numbers.put("five", "5");
        numbers.put("six", "6");
        numbers.put("seven", "7");
        numbers.put("eight", "8");
        numbers.put("nine", "9");
        
        return numbers;
    }
}

// 거리두기 확인하기
import java.util.*;

class Solution {

    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};
    
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        for (int i = 0; i < 5; i++) {
            char[][] place = new char[5][5];
            for (int j = 0; j < 5; j++) {
                place[j] = places[i][j].toCharArray();
            }
            answer[i] = isValid(place);
        }
        return answer;
    }
    
    private int isValid(char[][] place) {
        List<Node> nodes = new ArrayList<>();
        
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (place[i][j] == 'P') {
                    nodes.add(new Node(i, j));
                }
            }
        }
        
        for (Node node : nodes) {
            Queue<Node> q = new LinkedList<>();
            q.add(node);
            place[node.x][node.y] = '-';
            for (int k = 0; k < 2; k++) {
                Queue<Node> next = new LinkedList<>();
                while (!q.isEmpty()) {
                    Node n = q.poll();
                    for (int i = 0; i < 4; i++) {
                        int nx = n.x + dx[i];
                        int ny = n.y + dy[i];
                        if (nx < 0 || ny < 0 || nx == 5 || ny == 5 || place[nx][ny] == '-' || place[nx][ny] == 'X')
                            continue;
                        if (place[nx][ny] == 'P')
                            return 0;
                        place[nx][ny] = '-';
                        next.add(new Node(nx, ny));
                    }
                }
                q = next;
            }
            
        }
        return 1;
    }
    
    class Node {
        int x;
        int y;
        
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

// 표 편집 2
import java.util.*;

class Solution {
    public String solution(int n, int k, String[] cmds) {
        int[][] numbers = new int[n][2];
        for (int i = 0; i < n; i++) {
            numbers[i][0] = i - 1;
            numbers[i][1] = i + 1;
        }
        Stack<Integer> z = new Stack<>();
        boolean[] deleted = new boolean[n];
        for (String cmd : cmds) {
            if (cmd.equals("C")) {
                int prev = numbers[k][0];
                int next = numbers[k][1];
                z.push(k);
                deleted[k] = true;
                if (next < n) {
                    numbers[next][0] = prev;  
                    k = next;
                } else {
                    k = prev;
                }
                if (prev > -1)
                    numbers[prev][1] = next;
                continue;
            }
            if (cmd.equals("Z")) {
                int deletedNum = z.pop();
                int prev = numbers[deletedNum][0];
                int next = numbers[deletedNum][1];
                deleted[deletedNum] = false;
                if (next < n) {
                    numbers[next][0] = deletedNum;  
                }
                if (prev > -1)
                    numbers[prev][1] = deletedNum;
                continue;
            }
            String[] splitCmd = cmd.split(" ");
            int move = Integer.parseInt(splitCmd[1]);
            int findIndex = 1;
            if (splitCmd[0].equals("U")) {
                findIndex = 0;
            }
            for (int i = 0; i < move; i++) {
                k = numbers[k][findIndex];
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
             if (deleted[i]) {
                sb.append("X");
            } else {
                sb.append("O");
            }
        }
        return sb.toString();
    }
}

// 표 편집 1 (효율성X)
import java.util.*;

class Solution {
    public String solution(int n, int k, String[] cmds) {
        boolean[] exist = new boolean[n];
        Arrays.fill(exist, true);
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            numbers.add(i);
        }
        Stack<Integer> deleted = new Stack<>();
        for (String cmd : cmds) {
            if (cmd.equals("C")) {
                int current = numbers.get(k);
                deleted.push(current);
                numbers.remove(k);
                exist[current] = false;
                if (k == numbers.size()) {
                    k--;
                }
                continue;
            }
            if (cmd.equals("Z")) {
                int deletedNumber = deleted.pop();
                int index = getIndex(numbers, deletedNumber);
                exist[deletedNumber] = true;
                numbers.add(index, deletedNumber);
                if (k >= index) {
                    k++;
                }
                continue;
            }
            String[] splitCmd = cmd.split(" ");
            int move = Integer.parseInt(splitCmd[1]);
            if (splitCmd[0].equals("U")) {
                k -= move;
                continue;
            }
            k += move;            
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
             if (!exist[i]) {
                sb.append("X");
            } else {
                sb.append("O");
            }
        }
        return sb.toString();
    }
    
    private int getIndex(List<Integer> numbers, int value) {
        int start = 0;
        int end = numbers.size() - 1;
        int result = numbers.size();
        while (start <= end) {
            int mid = (start + end) / 2;
            if (numbers.get(mid) > value) {
                result = mid;
                end = mid - 1;
            }
            if (numbers.get(mid) < value) {
                start = mid + 1;
            }
        }
        return result;
    }
}