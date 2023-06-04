import java.util.List;
import java.util.ArrayList;

class Solution {
    private static final int MAX = 5;
    
    private int isDistancing(String[] place) {
        for (int i = 0; i < MAX; i++) {
            for (int j = 0; j < MAX; j++) {
                if (place[i].charAt(j) != 'P')
                    continue;
                if (i < MAX - 1) {
                    if (place[i + 1].charAt(j) == 'P')
                        return 0;
                    if (place[i + 1].charAt(j) == 'O') {
                        if ((i < MAX - 2 && place[i + 2].charAt(j) == 'P') || (j < MAX - 1 && place[i + 1].charAt(j + 1) == 'P'))
                            return 0;
                    }
                    if (i < MAX - 1 && j > 0 && place[i + 1].charAt(j - 1) == 'P' && (place[i + 1].charAt(j) == 'O' || place[i].charAt(j - 1) == 'O'))
                        return 0;
                }
                if (j < MAX - 1) {
                    if (place[i].charAt(j + 1) == 'P')
                        return 0;
                    if (place[i].charAt(j + 1) == 'O') {
                        if ((j < MAX - 2 && place[i].charAt(j + 2) == 'P') || (i < MAX - 1 && place[i + 1].charAt(j + 1) == 'P'))
                            return 0;
                    }
                }
            }
        }
        return 1;
    }
    
    public int[] solution(String[][] places) {
        List<Integer> answer = new ArrayList<>();
        
        for (String[] place : places) 
            answer.add(isDistancing(place));
        
        return answer.stream()
            .mapToInt(Integer::intValue)
            .toArray();
    }
}