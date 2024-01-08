import java.util.*;
import java.util.stream.Collectors;

class Solution {
    
    private static final String NO_FILTER = "-";
    Map<String, List<Integer>> groups = new HashMap<>();
    List<Integer> scores = new ArrayList<>();
    
    
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        for (int i = 0; i < info.length; i++) {
            String[] a = info[i].split(" ");
            addToGroup(a, i, 0);
            scores.add(Integer.parseInt(a[4]));
        }
        for (String group : groups.keySet()) {
            List<Integer> g = groups.get(group);
            g = g.stream()
                .distinct()
                .map(scores::get)
                .sorted()
                .collect(Collectors.toList());
            groups.put(group, g);
        }
        for (int i = 0; i < query.length; i++) {
            String q = query[i];
            int lastSpaceIndex = q.lastIndexOf(" ");
            String key = q.substring(0, lastSpaceIndex);
            if (!groups.containsKey(key)) {
                continue;
            }
            int score = Integer.parseInt(q.substring(lastSpaceIndex + 1));
            answer[i] = countEnough(score, groups.get(key));
        }
        return answer;
    }
    
    private void addToGroup(String[] info, int num, int index) {
        if (index == 4) {
            String key = toKey(info);
            groups.computeIfAbsent(key, k -> new ArrayList<>()).add(num);
            return;
        }
        for (int i = index; i < 4; i++) {
            String temp = info[i];
            addToGroup(info, num, i + 1);
            info[i] = NO_FILTER;
            addToGroup(info, num, i + 1);
            info[i] = temp;
        }
    }
    
    private String toKey(String[] info) {
        return Arrays.stream(info)
            .limit(4)
            .collect(Collectors.joining(" and "));
    }
    
    private int countEnough(int minScore, List<Integer> scores) {
        int start = 0;
        int end = scores.size();
        List<Integer> enough = new ArrayList<>();
        int result = end;
        while (start != end) {
            int mid = (start + end) / 2;
            int s = scores.get(mid);
            if (s < minScore) {
                start = mid + 1;
                continue;
            }
            if (s >= minScore) {
                result = mid;
                end = mid;
            }
        }
        return scores.size() - end;
    }
}

// 시도 2
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Collectors;


class Solution {
    
    private static final String NO_FILTER = "-";
    
    Map<String, Set<Integer>> langs = new HashMap<>();
    Map<String, Set<Integer>> sides = new HashMap<>();
    Map<String, Set<Integer>> careers = new HashMap<>();
    Map<String, Set<Integer>> foods = new HashMap<>();
    List<Integer> scores = new ArrayList<>();
    Set<Integer> total = new HashSet<>();
    List<Integer> sortedByScores = new ArrayList<>();
    
    
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        for (int i = 0; i < info.length; i++) {
            String[] app = info[i].split(" ");
            processData(app, i);
            total.add(i);
            scores.add(Integer.parseInt(app[4]));
        }
        sortedByScores = IntStream.range(0, info.length)
            .mapToObj(Integer::valueOf)
            .sorted(Comparator.comparing(scores::get))
            .collect(Collectors.toList());
        for (int i = 0; i < query.length; i++) {
            String[] q = query[i].split(" ");
            answer[i] = countResult(q[0], q[2], q[4], q[6], Integer.parseInt(q[7]), info);
        }
        return answer;
    }
    
    private int countResult(String lang, String side, String career, String food, int score, String[] info) {
        List<Integer> apps = getEnoughScores(score);
        return (int)apps.stream()
            .map(a -> info[a])
            .filter(a -> canApply(lang, side, career, food, a))
            .count();
    }
    
    private boolean canApply(String lang, String side, String career, String food, String info) {
        String[] app = info.split(" ");
        if (!lang.equals(NO_FILTER) && !lang.equals(app[0])) {
            return false;
        }
        if (!side.equals(NO_FILTER) && !side.equals(app[1])) {
            return false;
        }
        if (!career.equals(NO_FILTER) && !career.equals(app[2])) {
            return false;
        }
        if (!food.equals(NO_FILTER) && !food.equals(app[3])) {
            return false;
        }
        return true;
    }
    
    private List<Integer> getEnoughScores(int minScore) {
        int start = 0;
        int end = sortedByScores.size();
        List<Integer> enough = new ArrayList<>();
        int result = end;
        while (start != end) {
            int mid = (start + end) / 2;
            int s = scores.get(sortedByScores.get(mid));
            if (s < minScore) {
                start = mid + 1;
                continue;
            }
            if (s >= minScore) {
                result = mid;
                end = mid;
            }
        }
        for (int i = result; i < sortedByScores.size(); i++) {
            enough.add(sortedByScores.get(i));
        }
        return enough;
    }
    
    private Set<Integer> applyFilter(Map<String, Set<Integer>> filters, String filterKey) {
        if (filterKey.equals(NO_FILTER)) {
            return new HashSet<>(total);
        }
        return filters.getOrDefault(filterKey, new HashSet<>());
    }
    
    public void processData(String[] app, int i) {
        addData(app[0], i, langs); 
        addData(app[1], i, sides); 
        addData(app[2], i, careers); 
        addData(app[3], i, foods); 
    }

    private void addData(String key, int value, Map<String, Set<Integer>> dataMap) {
        dataMap.computeIfAbsent(key, k -> new HashSet<>()).add(value);
    }
}

// 시도1
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Collectors;


class Solution {
    
    private static final String NO_FILTER = "-";
    
    Map<String, Set<Integer>> langs = new HashMap<>();
    Map<String, Set<Integer>> sides = new HashMap<>();
    Map<String, Set<Integer>> careers = new HashMap<>();
    Map<String, Set<Integer>> foods = new HashMap<>();
    List<Integer> scores = new ArrayList<>();
    Set<Integer> total = new HashSet<>();
    List<Integer> sortedByScores = new ArrayList<>();
    
    
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        for (int i = 0; i < info.length; i++) {
            String[] app = info[i].split(" ");
            processData(app, i);
            total.add(i);
            scores.add(Integer.parseInt(app[4]));
        }
        sortedByScores = IntStream.range(0, info.length)
            .mapToObj(Integer::valueOf)
            .sorted(Comparator.comparing(scores::get))
            .collect(Collectors.toList());
        // System.out.println(sortedByScores);
        
        for (int i = 0; i < query.length; i++) {
            String[] q = query[i].split(" ");
            answer[i] = countResult(q[0], q[2], q[4], q[6], Integer.parseInt(q[7]));
        }
        return answer;
    }
    
    private int countResult(String lang, String side, String career, String food, int score) {
        List<Set<Integer>> apps = new ArrayList<>();
        apps.add(getEnoughScores(score));
        apps.add(applyFilter(sides, side));
        apps.add(applyFilter(foods, food));
        apps.add(applyFilter(langs, lang));
        apps.add(applyFilter(careers, career));
        
        apps.sort((setA, setB) -> Integer.compare(setA.size(), setB.size()));
        Set<Integer> intersection = new HashSet<>(apps.get(0));
        for (int i = 1; i < apps.size(); i++) {
            intersection.retainAll(apps.get(i));
        }
        
        return intersection.size();
    }
    
    private Set<Integer> getEnoughScores(int minScore) {
        int start = 0;
        int end = sortedByScores.size();
        Set<Integer> enough = new HashSet<>();
        int result = end;
        while (start != end) {
            int mid = (start + end) / 2;
            int s = scores.get(sortedByScores.get(mid));
            if (s < minScore) {
                start = mid + 1;
                continue;
            }
            if (s >= minScore) {
                result = mid;
                end = mid;
            }
        }
        for (int i = result; i < sortedByScores.size(); i++) {
            enough.add(sortedByScores.get(i));
        }
        return enough;
    }
    
    private Set<Integer> applyFilter(Map<String, Set<Integer>> filters, String filterKey) {
        if (filterKey.equals(NO_FILTER)) {
            return new HashSet<>(total);
        }
        return filters.getOrDefault(filterKey, new HashSet<>());
    }
    
    public void processData(String[] app, int i) {
        addData(app[0], i, langs); 
        addData(app[1], i, sides); 
        addData(app[2], i, careers); 
        addData(app[3], i, foods); 
    }

    private void addData(String key, int value, Map<String, Set<Integer>> dataMap) {
        dataMap.computeIfAbsent(key, k -> new HashSet<>()).add(value);
    }
}