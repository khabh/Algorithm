// 문자열 압축
import java.util.*;

class Solution {
    public int solution(String s) {
        int n = s.length();
        int answer = n;
        for (int i = 1; i <= n / 2; i++) {
            int start = 0;
            String prev = "";
            int count = 0;
            int total = 0;
            while (start + i <= n) {
                if (total >= answer) {
                    break;
                }
                String current = s.substring(start, start + i);
                start += i;
                if (start == i) {
                    prev = current;
                    count = 1;
                    continue;
                }
                if (current.equals(prev)) {
                    count++;
                    continue;
                }
                total += i;
                if (count > 1) {
                    total += String.valueOf(count).length();
                }
                count = 1;
                prev = current;
            }
            total += i;
            if (count > 1) {
                total += String.valueOf(count).length();
            }
            total += (n - start);
            answer = Math.min(answer, total);
        }
        return answer;
    }
}

// 괄호 변환
import java.util.*;

class Solution {
    
    private static final String LEFT = "(";
    private static final String RIGHT = ")";
    
    private String isValid(String s) {
        if (s.length() == 0) {
            return s;
        }
        int l = 0;
        int r = 0;
        boolean isCorrect = true;
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (cur == '(') {
                l++;   
            } else {
                r++;
                if (r > l) {
                    isCorrect = false;
                }
            }
            if (l == r) {
                break;
            }
        }
        String u = s.substring(0, l * 2);
        String v = s.substring(l * 2);
        if (isCorrect) {
            return u + isValid(v);
        }
        return LEFT + isValid(v) + RIGHT + flip(u);
    }
    
    private String flip(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < s.length() - 1; i++) {
            if (s.charAt(i) == '(') {
                sb.append(RIGHT);
                continue;
            }
            sb.append(LEFT);
        }
        return sb.toString();
    }
    
    public String solution(String p) {
        return isValid(p);
    }
}

// 자물쇠와 열쇠
import java.util.*;

class Solution {
    int m;
    int n;
    int[][] board;
    int lockCount = 0;
    
    public boolean solution(int[][] key, int[][] lock) {
        m = key.length;
        n = lock.length;
        int size = n + 2 * (m - 1);
        board = new int[size][size];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int l = lock[i][j];
                if (l == 0) {
                    l = 2;
                    lockCount++;
                }
                board[i + m - 1][j + m - 1] = l;
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int x = 0; x < n + m - 1; x++) {
                for (int y = 0; y < n + m - 1; y++) {
                    if (unlock(key, x, y)) {
                        return true;
                    }
                }
            }
            key = turn(key);
        }
        return false;
    }
    
    private int[][] turn(int[][] key) {
        int[][] result = new int[m][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                result[j][m - 1 - i] = key[i][j];
            }
        }
        return result;
    }
    
    private boolean unlock(int[][] key, int x, int y) {
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                int l = board[i + x][j + y];
                if (l == 0) {
                    continue;
                }
                int k = key[i][j];
                if (k == 1 && l == 1 || l == 2 && k == 0) {
                    return false;
                }
                if (k == 1 && l == 2) {
                    count++;
                }
            }
        }
        return count == lockCount;
    }
}

// 가사 검색
import java.util.*;

class Solution {
    
    class Trie {
        int count = 0;
        Map<Character, Trie> childs = new HashMap<>();
        
        public void add(String s) {
            Trie current = this;
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                Trie next = current.childs.getOrDefault(ch, new Trie());
                current.putChild(ch, next);
                current = next;
            }
        }
        
        public int count(String s) {
            Trie current = this;
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                Trie next = current.childs.get(ch);
                if (next == null) {
                    return 0;
                }
                current = next;
            }
            return current.count;
        }
        
        private void putChild(char ch, Trie trie) {
            count++;
            childs.put(ch, trie);
        }
    }
    
    public int[] solution(String[] words, String[] queries) {
        Map<Integer, Trie> tries = new HashMap<>();
        Map<Integer, Trie> flip = new HashMap<>();
        int[] answer = new int[queries.length];
        
        for (String word : words) {
            Trie first = tries.getOrDefault(word.length(), new Trie());
            first.add(word);
            tries.put(word.length(), first);
            String f = new StringBuilder(word).reverse().toString();
            Trie second = flip.getOrDefault(word.length(), new Trie());
            second.add(f);
            flip.put(word.length(), second);
        }
        int index = 0;
        for (String query : queries) {
            String search = query;
            Trie current;
            if (query.endsWith("?")) {
                current = tries.get(query.length());
            } else {
                current = flip.get(query.length());
                search = new StringBuilder(query).reverse().toString();
            }
            if (current == null) {
                answer[index++] = 0;
                continue;
            }
            search = search.replaceAll("\\?", "");
            answer[index++] = current.count(search);
        }
        return answer;
    }
}

// 기둥과 보 설치
import java.util.*;

class Solution {
    int N;
    boolean[][] p = new boolean[101][101];
    boolean[][] f = new boolean[101][101];
    int totalCount = 0;
    
    public int[][] solution(int n, int[][] buildFrame) {
        this.N = n;
        for (int[] build : buildFrame) {
            int x = build[0];
            int y = build[1];
            int type = build[2];
            if (build[3] == 1) {
                if (type == 0) {
                    p[x][y] = isValidPillar(x, y);
                    if (p[x][y])
                        totalCount++;
                } else {
                    f[x][y] = isValidFloor(x, y);
                    if (f[x][y])
                        totalCount++;
                }
                continue;
            }
            if (type == 0) {
                removePillar(x, y);
            } else {
                removeFloor(x, y);
            }
        }
        int[][] answer = new int[totalCount][3];
        int index = 0;
        for (int x = 0; x <= N; x++) {
            for (int y = 0; y <= N; y++) {
                if (p[x][y]) {
                    answer[index++] = new int[]{x, y, 0};
                } 
                if (f[x][y]) {
                    answer[index++] = new int[]{x, y, 1};
                }
            }
        }
        return answer;
    }
    
    private void removePillar(int x, int y) {
        p[x][y] = false;
        if (isValid()) {
            totalCount--;
            return;
        }
        p[x][y] = true;
    }
    
    private void removeFloor(int x, int y) {
        f[x][y] = false;
        if (isValid()) {
            totalCount--;
            return;
        }
        f[x][y] = true;
    }
    
    private boolean isValid() {
        for (int x = 0; x <= N; x++) {
            for (int y = 0; y <= N; y++) {
                if (p[x][y] && !isValidPillar(x, y)) {
                    return false;
                }
                if (f[x][y] && !isValidFloor(x, y)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean isValidFloor(int x, int y) {
         if (p[x][y - 1] || p[x + 1][y - 1]) {
            return true;
        }
        return x > 0 && x < N && f[x - 1][y] && f[x + 1][y];
    }
    
    private boolean isValidPillar(int x, int y) {
        if (y == 0) {
            return true;
        }
        return f[x][y] || p[x][y - 1] || (x > 0 && f[x - 1][y]);
    }
}

// 외벽 점검
import java.util.*;

class Solution {
    
    private int[] board;
    private int[] current;
    private int result;
    
    private void calcResult(int index) {
        int total = board.length / 2;
        for (int start = 0; start < total; start++) {
            int pos = start;
            for (int i = 0; i < index; i++) {
                int nextDist = board[pos] + current[i];
                while (board[pos] <= nextDist && pos + 1 < board.length) {
                    pos++;
                }
                if (pos - start >= total) {
                    result = Math.min(result, i + 1);
                    break;
                }
            } 
        }
    }
    
    private void dfs(int index, int[] dist, boolean[] visited) {
        if (index > result) {
            return;
        }
        if (index == dist.length || index < result) {
            calcResult(index);
        }
        if (index == dist.length) {
            return;
        }
        for (int i = 0; i < dist.length; i++) {
            if (visited[i])
                continue;
            visited[i] = true;
            current[index] = dist[i];
            dfs(index + 1, dist, visited);
            visited[i] = false;
            current[index] = 0;
        }
    }
    
    public int solution(int n, int[] weak, int[] dist) {
        int w = weak.length;
        current = new int[dist.length];
        board = new int[w * 2];
        for (int i = 0; i < w; i++) {
            board[i] = weak[i];
            board[i + w] = weak[i] + n; 
        }
        result = dist.length + 1;
        dfs(0, dist, new boolean[dist.length]);
        if (result > dist.length) {
            return -1;
        }
        return result;
    }
}

// 블록 이동하기
import java.util.*;

class Solution {
    
    private final int[] dx = {0, 1, 0, -1};
    private final int[] dy = {1, 0, -1, 0};
    private int n;
    private int[][] board;
    private int[][][] counts;
    
    class Pos {
        int x;
        int y;
        int dir;
        
        public Pos(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
    
    public int solution(int[][] board) {
        n = board.length;
        this.board = board;
        counts = new int[n][n][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(counts[i][j], -1);
            }
        }
        counts[0][0][0] = 0;
        Queue<Pos> q = new LinkedList<>();
        q.add(new Pos(0, 0, 0));
        while (!q.isEmpty()) {
            Pos p = q.poll();
            int x1 = p.x;
            int y1 = p.y;
            int dir = p.dir;
            int x2 = x1 + dx[dir];
            int y2 = y1 + dy[dir];
            if ((x1 == n -1 && y1 == n - 1) || (x2 == n - 1 && y2 == n - 1))
                return counts[x1][y1][dir];
            int count = counts[x1][y1][dir];
            for (int i = 0; i < 4; i++) {
                int nx1 = x1 + dx[i];
                int ny1 = y1 + dy[i];
                int nx2 = x2 + dx[i];
                int ny2 = y2 + dy[i];
                if (!valid(nx1, ny1, nx2, ny2))
                    continue;
                if (i < 2) {
                    addPos(x1, y1, i, count + 1, q);
                    addPos(x2, y2, i, count + 1, q);
                }
                else {
                    int opDir = i - 2;
                    addPos(nx1, ny1, opDir, count + 1, q);
                    addPos(nx2, ny2, opDir, count + 1, q);
                }
                addPos(nx1, ny1, dir, count + 1, q);
            }
        }
        int answer = 0;
        return answer;
    }    
    
    private boolean valid(int x1, int y1, int x2, int y2) {
        return valid(x1, y1) && valid(x2, y2);
    }
    
    private boolean valid(int x, int y) {
        return x >= 0 && y >= 0 && x < n && y < n && board[x][y] == 0;
    }
    
    private void addPos(int x, int y, int dir, int count, Queue<Pos> q) {
        if (counts[x][y][dir] == -1) {
            counts[x][y][dir] = count;
            q.add(new Pos(x, y, dir));
        }
    }
}