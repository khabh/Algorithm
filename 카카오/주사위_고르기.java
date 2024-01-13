import java.util.*;

class Solution {
    
    private int n;
    private boolean[] current;
    Result answer = new Result(0, 0, 0, new boolean[0]);
    
    class Result {
        int win;
        int draw;
        int lose;
        boolean[] selected;
        
        public Result(int win, int draw, int lose, boolean[] selected) {
            this.win = win;
            this.draw = draw;
            this.lose = lose;
            this.selected = selected;
        } 
        
        public Result getReversedOrCurrent() {
            if (win >= lose) {
                return this;
            }
            return new Result(lose, draw, win, reverseSelect());
        }
        
        private boolean[] reverseSelect() {
            boolean[] rev = new boolean[selected.length];
            for (int i = 0; i < selected.length; i++) {
                rev[i] = !selected[i];
            }
            return rev;
        }
        
        public Result getMax(Result result) {
            if (result.win > this.win)
                return result;
            return this;
        }
        
        public int[] getSelect() {
            int[] result = new int[n];
            int count = 0;
            for (int i = 0; i < selected.length; i++) {
                if (selected[i]) {
                    result[count++] = i + 1;
                }
            }
            return result;
        }
        
    }
    
    private Result getResult(int[][] dice) {
        int maxValue = n * 100 + 1;
        int[] dpA = new int[maxValue];
        int[] dpB = new int[maxValue];
        boolean isFirstA = true;
        boolean isFirstB = true;
        
        for (int i = 0; i < dice.length; i++) {
            if (!current[i]) {
                if (isFirstB) {
                    for (int d : dice[i]) {
                        dpB[d]++;
                    }
                    isFirstB = false;
                    continue;
                }
                dpB = getNextDp(dpB, dice[i]);
                continue;
            }
            if (isFirstA) {
                for (int d : dice[i]) {
                        dpA[d]++;
                    }
                    isFirstA = false;
                    continue;
            }
            dpA = getNextDp(dpA, dice[i]);
        }
        Result result = calc(dpA, dpB);
        return result.getReversedOrCurrent();
    }
    
    private Result calc(int[] first, int[] second) {
        int win = 0;
        int lose = 0;
        int draw = 0;
        
        int prev = 0;
        int next = 0;
        for (int s : second) {
            next += s;
        }
        for (int i = 1; i < first.length; i++) {
            next -= second[i];
            prev += second[i - 1];
            draw += (first[i] * second[i]);
            win += (first[i] * prev);
            lose += (first[i] * next);
        }
        
        return new Result(win, draw, lose, Arrays.copyOf(current, current.length));
    }
    
    private int[] getNextDp(int[] dp, int[] dice) {
        int[] result = new int[dp.length];
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] == 0)
                continue;
            for (int d : dice) {
                result[i + d] += dp[i];
            }
        }
        return result;
    }
    
    private void dfs(int[][] dice, int index, int count) {
        if (count == n) {
            Result currentResult = getResult(dice);
            answer = answer.getMax(currentResult);
            return;
        }
        for (int i = index; i < dice.length; i++) {
            current[i] = true;
            dfs(dice, i + 1, count + 1);
            current[i] = false;
        }
    }
    
    public int[] solution(int[][] dice) {
        n = dice.length / 2;
        current = new boolean[dice.length];
        dfs(dice, 0, 0);
        return answer.getSelect();
    }
}