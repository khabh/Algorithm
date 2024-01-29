// n진수 게임
import java.util.*;

class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder maker = new StringBuilder();
        int needed = t * m;
        int number = 0;
        while (maker.length() < needed) {
            maker.append(Integer.toString(number++, n));
        }
        String all = maker.toString();
        StringBuilder result = new StringBuilder();
        for (int i = p - 1; i < needed; i += m) {
            result.append(all.charAt(i));
        }
        
        return result.toString().toUpperCase();
    }
}

// 파일명 정렬
import java.util.regex.*;
import java.util.*;

class Solution {
    
    static class File implements Comparable<File> {
        private static final Pattern pattern = Pattern.compile("([^0-9]*)([0-9]{1,5})(.*)");
        
        String fileName;
        String head;
        int number;
        int order;
        
        public File(int order, String fileName) {
            this.fileName = fileName;
            Matcher matcher = pattern.matcher(fileName);
            matcher.find();
            this.order = order;
            this.head = matcher.group(1).toLowerCase();
            this.number = Integer.parseInt(matcher.group(2));
        }
        
        @Override
        public int compareTo(File other) {
            if (!head.equals(other.head)) {
                return head.compareTo(other.head);
            }
            if (number != other.number) {
                return number - other.number;
            }
            return order - other.order;
        }
    }
    
    public String[] solution(String[] files) {
        List<File> answer = new ArrayList<>();
        int index = 0;
        for (String file : files) {
            answer.add(new File(index++, file));
        }
        return answer.stream()
            .sorted()
            .map(file -> file.fileName)
            .toArray(String[]::new);
    }
}

// 압축
import java.util.*;

class Solution {
    public int[] solution(String msg) {
        Map<String, Integer> dic = new HashMap<>();
        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            dic.put(String.valueOf((char)('A' + i)), i + 1);
        }
        int index = 0;
        while (index < msg.length()) {
            int end = search(msg, index, dic);
            String current = msg.substring(index, end);
            answer.add(dic.get(current));
            if (end < msg.length()) {
                dic.put(msg.substring(index, end + 1), dic.size() + 1);
            }
            index = end;
        }
        
        return answer.stream().mapToInt(Integer::valueOf).toArray();
    }
    
    private int search(String msg, int index, Map<String, Integer> dic) {
        int start = index + 1;
        int end = msg.length();
        while (start <= end) {
            int mid = (start + end) / 2;
            if (dic.containsKey(msg.substring(index, mid))) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return end;
    }
}

// 방금그곡
import java.util.regex.*;

class Solution {
    
    class Music {
        int time;
        boolean match = false;
        String name;
        String music;
        
        public Music(String input) {
            String[] m = input.split(",");
            name = m[2];
            time = gap(m[0], m[1]);
            this.music = getMusic(m[3], time);
        }
        
        private String getMusic(String music, int playTime) {
            StringBuilder played = new StringBuilder();
            String[] musicNotes = music.replaceAll("([A-G]#?)", "$1 ").split(" ");
            int idx = 0;
            
            for (int i = 0; i < playTime; i++) {
                played.append(musicNotes[idx]);
                idx = (idx + 1) % musicNotes.length;
            }
            
            return played.toString();
        }
        
        private int gap(String start, String end) {
            String[] first = start.split(":");
            String[] second = end.split(":");
            int time1 = Integer.parseInt(first[0]) * 60 + Integer.parseInt(first[1]);
            int time2 = Integer.parseInt(second[0]) * 60 + Integer.parseInt(second[1]);
            
            return time2 - time1;
        }
    }
    
    public String solution(String m, String[] musicinfos) {
        Pattern p = Pattern.compile(".*"+ m +"([^#]+|$)");
        String answer = "(None)";
        int length = 0;
        for (String music : musicinfos) {
            Music cur = new Music(music);
            Matcher matcher = p.matcher(cur.music);
            if (matcher.find() && cur.time > length) {
                answer = cur.name;
                length = cur.time;
            }
        }
        
        return answer;
    }
}

// 비밀지도
class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String format = "%" + n + "s";
        String[] answer = new String[n];
        for (int i = 0; i < n; i++) {
            answer[i] = String.format(format, Integer.toBinaryString(arr1[i]|arr2[i]))
                .replace("0", " ")
                .replace("1", "#");
        }
        return answer;
    }
}

// 다트게임
import java.util.regex.*;
import java.util.*;

class Solution {
    public int solution(String dartResult) {
        Map<String, Integer> b = new HashMap<String, Integer>();
        Pattern p = Pattern.compile("([0-9]+)([SDT])([\\*#]?)");
        Matcher m = p.matcher(dartResult);
        int[] scores = new int[3];
        int index = 0;
        b.put("S", 1);
        b.put("D", 2);
        b.put("T", 3);
        
        while (m.find()) {
            int score = Integer.valueOf(m.group(1));
            String bonus = m.group(2);
            score = (int)Math.pow(score, b.get(bonus));
            if (m.group(3).equals("*")) {
                score *= 2;
                scores[Math.max(0, index - 1)] *= 2;
            } else if (m.group(3).equals("#")) {
                score *= -1;
            }
            scores[index++] = score;
        }
        return Arrays.stream(scores).sum();
    }
}

// 캐시
import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        if (cacheSize == 0) {
            return cities.length * 5;
        }
        LinkedList<String> cache = new LinkedList<>();
        Set<String> exists = new HashSet<>();
        int total = 0;
        for (String c : cities) {
            String city = c.toLowerCase();
            if (exists.contains(city)) {
                total += 1;
                cache.remove(city);
            } else {
                total += 5;
                if (cache.size() == cacheSize) {
                    String removed = cache.removeFirst();
                    exists.remove(removed);
                }
            }
            cache.add(city);
            exists.add(city);
        }
        return total;
    }
}


// 뉴스 클러스터링
import java.util.*;

class Solution {
    
    private static final int PREFIX = 65536;
    
    public int solution(String str1, String str2) {
        Map<String, Integer> first = convert(str1);
        Map<String, Integer> second = convert(str2);
        Set<String> inter = new HashSet<>(first.keySet());
        inter.retainAll(second.keySet());
        int interCount = 0;
        for (String s : inter) {
            interCount += (Math.min(first.get(s), second.get(s)));
        }
        int totalCount = countAll(first) + countAll(second) - interCount;
        if (totalCount == 0) {
            return PREFIX;
        }
        return (int)(((double)interCount / totalCount) * PREFIX);
    }
    
    private int countAll(Map<String, Integer> map) {
        int result = 0;
        for (String s : map.keySet()) {
            result += map.get(s);
        }
        return result;
    }

    private Map<String, Integer> convert(String str) {
        Map<String, Integer> map = new HashMap<>();
        str = str.toLowerCase();
        for (int i = 0; i < str.length() - 1; i++) {
            String cur = str.substring(i, i + 2);
            if (!cur.matches("[a-z]{2}")) {
                continue;
            }
            map.put(cur, map.getOrDefault(cur, 0) + 1);
        }
        return map;
    }
}

// 프렌즈4블록
import java.util.*;

class Solution {
    
    private static final int[] dx = {1, 0, 1, 0};
    private static final int[] dy = {0, 1, 1, 0};
    
    private char[][] graph;

    public int solution(int m, int n, String[] board) {
        int answer = 0;
        graph = new char[m][n];
        for (int i = 0; i < m; i++) {
            graph[i] = board[i].toCharArray();
        }
        while (true) {
            List<Node> nodes = new ArrayList<>();
            for (int i = 0; i < m - 1; i++) {
                for (int j = 0; j < n - 1; j++) {
                    if (graph[i][j] != ' ' && canRemove(i, j)) {
                        nodes.add(new Node(i, j));
                    }
                }
            }
            if (nodes.isEmpty()) {
                break;
            }
            for (Node node : nodes) {
                answer += remove(node.x, node.y);
            }
            for (int j = 0; j < n; j++) {
                for (int i = m - 1; i >= 0; i--) {
                    if (graph[i][j] == ' ')
                        continue;
                    int index = i;
                    while (index + 1 < m && graph[index + 1][j] == ' ') {
                        index++;
                    }
                    if (index == i)
                        continue;
                    graph[index][j] = graph[i][j];
                    graph[i][j] = ' ';
                }
            }
        }
        return answer;
    }
    
    private int remove(int x, int y) {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (graph[nx][ny] == ' ') {
                continue;
            }
            count++;
            graph[nx][ny] = ' ';
        }
        return count;
    }
    
    private boolean canRemove(int x, int y) {
        char cur = graph[x][y];
        for (int i = 0; i < 3; i++) {
            if (graph[x + dx[i]][y + dy[i]] != cur) {
                return false;
            }
        }
        return true;
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

// 추석 트래픽
class Solution {
    int[] starts = new int[24 * 60 * 60 * 1000];
    int[] ends = new int[24 * 60 * 60 * 1000];
    
    public int solution(String[] lines) {
        for (String line : lines) {
            String[] split = line.split(" ");
            int endTime = getTime(split[1]);
            addTime(endTime, split[2]);
        }
        int count = 0;
        for (int i = 0; i < 1000; i++) {
            count += starts[i];
        }
        int answer = count;
        for (int i = 1000; i < starts.length; i++) {
            count += starts[i];
            count -= ends[i - 1000];
            answer = Math.max(answer, count);
        }
        return answer;
    }
    
    private void addTime(int endTime, String s) {
        int time = (int)(1000 * Double.parseDouble(s.split("s")[0]));
        int start = Math.max(0, endTime - time + 1);
        int end = Math.min(endTime, starts.length - 1);
        starts[start]++;
        ends[end]++;
    }
    
    private int getTime(String input) {
        String[] split = input.split(":");
        int h = Integer.parseInt(split[0]) * 60 * 60 * 1000;
        int m = Integer.parseInt(split[1]) * 60 * 1000;
        int s = (int)(Double.parseDouble(split[2]) * 1000);

        return h + m + s;
    }
}