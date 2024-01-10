import java.util.*;

class Solution {
    public String solution(String playTime, String advTime, String[] logs) {
        int maxTime = timeToNumber(playTime);
        long[] playCount = new long[maxTime + 1];
        int advNumber = timeToNumber(advTime);
        for (String log : logs) {
            String[] logTimes = log.split("-");
            int start = timeToNumber(logTimes[0]);
            int end = timeToNumber(logTimes[1]);
            playCount[start]++;
            playCount[end]--;
        }
        for (int i = 1; i <= maxTime; i++) {
            playCount[i] += playCount[i - 1];
        }
        
        int startTime = 0;
        long maxCount = 0L;
        long currentCount = 0L;
        for (int i = 0; i < advNumber; i++) {
            currentCount += playCount[i];
        }
        maxCount = currentCount;
        
        for (int i = advNumber; i < maxTime; i++) {
            currentCount -= playCount[i - advNumber];
            currentCount += playCount[i];
            
            if (currentCount > maxCount) {
                startTime = i - advNumber + 1;
                maxCount = currentCount;
            }
        }
            
        return numberToTime(startTime);
    }
    
    private String numberToTime(int number) {
        int s = number % 60;
        number = (number - s) / 60;
        int m = number % 60;
        int h = (number - m) / 60;
        
        return String.format("%02d:%02d:%02d", h, m, s);
    }
    
    private int timeToNumber(String time) {
        int[] splitTime = Arrays.stream(time.split(":"))
            .mapToInt(Integer::parseInt)
            .toArray();
        return splitTime[0] * 3600 + splitTime[1] * 60 + splitTime[2];
    }
}