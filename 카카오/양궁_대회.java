class Solution {
    private int[] temp = new int[11];
    private int[] opposite = new int[11];
    private int maxPoint = 0;
    private int[] result = new int[11];
    
    private int calcGap() {
        int a = 0, l = 0;
        for (int i = 0; i < 11; i++) {
            if (opposite[i] < temp[i])
                l += (10 - i);
            else if (opposite[i] > 0)
                a += (10 - i);
        }
        
        return l - a;
    }
    
    private void dfs(int index, int count, int n) {
        if (index < 0) {
            int point = calcGap();
            
            if (point > maxPoint) {
                maxPoint = point;
                result = temp.clone();
            }
            return;
        }
        
        for (int i = n - count; i >= 0; i--) {
            temp[index] = i;
            dfs(index - 1, count + i, n);
        }
        temp[index] = 0;
    }
    
    
    public int[] solution(int n, int[] info) {
        opposite = info;
        dfs(10, 0, n);
        if (maxPoint == 0)
            return new int[]{-1};
        return result;
    }
}