import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        Map<String, Set<String>> reportStatics = new HashMap<>();
        Map<String, Set<String>> reported = new HashMap<>();
        List<String> blocked = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        
        for (String id : id_list) {
            reported.put(id, new HashSet<String>());
            reportStatics.put(id, new HashSet<String>());
        }
        for (String r : report) {
            String[] temp = r.split(" ");
            reported.get(temp[1]).add(temp[0]);
            reportStatics.get(temp[0]).add(temp[1]);
        }
        
        for (String id : id_list) {
            if (reported.get(id).size() >= k)
                blocked.add(id);
        }
        
        for (String id : id_list) {
            int count = 0;
            for (String b : blocked) 
                if (reportStatics.get(id).contains(b))
                    count++;
            result.add(count);
        }
        
        return result.stream()
            .mapToInt(i -> i)
            .toArray();
    }
}