import java.util.List;
import java.util.ArrayList;

class Solution {
    public int[] solution(int n, long left, long right) {
        List<Integer> result = new ArrayList<>();
        for (long number = left; number <= right; number++) {
            result.add((int)Math.max(number / n, number % n) + 1);
        }
        
        return result.stream()
                .mapToInt(i -> i)
                .toArray();
    }
}