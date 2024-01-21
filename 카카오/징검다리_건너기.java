class Solution {
    
    public int solution(int[] stones, int k) {
        int s = stones.length; 
        int start = 0;
        int end = Integer.MAX_VALUE;
        
        while (start <= end) {
            int mid = (start + end) / 2;
            if (canCross(stones, k, mid)) {
                start = mid + 1;
            }
            else {
                end = mid - 1;
            }
        }
        return start;
    }
    
    private boolean canCross(int[] stones, int k, int m) {
        int prev = -1;
        int prevCount = 0;
        for (int i = 0; i < stones.length; i++) {
            if (i - prev > k) {
                return false;
            }
            if (stones[i] <= m) {
                continue;
            }
            prev = i;
        }
        if (stones.length - prev > k) {
            return false;
        }
        return true;
    }
}