import java.util.*;

class Solution {
    
    private int[] result = new int[]{-1};
    private int maxDiff = 0;
    private int[] lion = new int[11];
    
    private void dfs(int remain, int[] peach, int index) {
        if (remain == 0) {
            updateResult(peach);
            return;
        }
        for (int i = 0; i <= index; i++) {
            lion[10 - i] += 1;
            dfs(remain - 1, peach, i);
            lion[10 - i] -= 1;
        }
    }
    
    private void updateResult(int[] peach) {
        int peachScore = 0;
        int lionScore = 0;
        for (int i = 0; i <= 10; i++) {
            int score = 10 - i;
            if (peach[i] == 0 && lion[i] == 0)
                continue;
            if (peach[i] >= lion[i]) {
                peachScore += score;
                continue;
            }
            lionScore += score;
        }
        
        int diff = lionScore - peachScore;
        if (diff < maxDiff || diff <= 0) {
            return;
        }
        
        if (diff > maxDiff) {
            result = lion.clone();
            maxDiff = diff;
            return;
        }
        
        for (int i = 10; i >= 0; i--) {
            if (lion[i] > result[i]) {
                result = lion.clone();
                return;
            }
            if (lion[i] < result[i])
                return;
        }
    }
    
    public int[] solution(int n, int[] info) {
        dfs(n, info, 10);
        return result;
    }
}