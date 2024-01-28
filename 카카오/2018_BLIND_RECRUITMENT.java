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