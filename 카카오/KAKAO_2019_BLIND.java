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