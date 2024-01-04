import java.util.*;

class Solution {
    public int[] solution(String[] ids, String[] reports, int k) {
        Map<String, Set<String>> reportHistory = new HashMap<>();
        Map<String, Integer> result = new HashMap<>();
        for (String report : reports) {
            String[] splitReport = report.split(" ");
            String a = splitReport[0];
            String b = splitReport[1];
            if (!reportHistory.containsKey(b)) {
                reportHistory.put(b, new HashSet<>());
            }
            reportHistory.get(b).add(a);
        }
        for (String id : ids) {
            if (!reportHistory.containsKey(id))
                continue;
            Set<String> users = reportHistory.get(id);
            if (users.size() < k) {
                continue;
            }
            for (String user : users) {
                result.put(user, result.getOrDefault(user, 0) + 1);
            }
        }
        int[] answer = new int[ids.length];
        for (int i = 0; i < ids.length; i++) {
            answer[i] = result.getOrDefault(ids[i], 0);
        }
        return answer;
    }
}