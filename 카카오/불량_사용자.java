import java.util.*;

class Solution {
    
    List<List<Integer>> hist = new ArrayList<>();
    Set<Set<Integer>> result = new HashSet<>();
    
    private void dfs(Set<Integer> current) {
        if (current.size() == hist.size()) {
            result.add(new HashSet<>(current));
            return;
        }
        for (int user : hist.get(current.size())) {
            if (!current.contains(user)) {
                current.add(user);
                dfs(current);
                current.remove(user);
            }
        }
    }
    
    public int solution(String[] users, String[] bannedIds) {
        int b = bannedIds.length;
        int n = users.length;
        
        for (String banned : bannedIds) {
            String id = banned.replaceAll("\\*", ".");
            List<Integer> bannedUsers = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                String user = users[i];
                if (user.matches(id)) {
                    bannedUsers.add(i);
                }
            }
            hist.add(bannedUsers);
        }
        hist.sort((u1, u2) -> Integer.compare(u1.size(), u2.size()));
        dfs(new HashSet<>());
        return result.size();
    }
}