class Solution {
    private int convertTime(String time) {
        String[] newTime = time.split(":");
        int h = Integer.parseInt(newTime[0]);
        int m = Integer.parseInt(newTime[1]);
        int s = Integer.parseInt(newTime[2]);
        
        return h * 3600 + m * 60 + s;
    }
    
    public String solution(String play_time, String adv_time, String[] logs) {
        long frequency[] = new long[360003];
        int endPlayTime = convertTime(play_time);
        int advLength = convertTime(adv_time);
        long maxFrequency = 0;
        int maxTime = 0;
        
        for (String log : logs) {
            int startTime = convertTime(log.substring(0, 8));
            int endTime = convertTime(log.substring(9));
            frequency[startTime] += 1;
            frequency[endTime] -= 1;
        }
        
        for (int i = 1; i <= endPlayTime; i++) {
            frequency[i] += frequency[i - 1];
        }
        for (int i = 1; i <= endPlayTime; i++) {
            frequency[i] += frequency[i - 1];
        }
        
        maxFrequency = frequency[advLength - 1];
        
        for (int i = advLength; i < endPlayTime; i++) {
            if (frequency[i] - frequency[i - advLength] > maxFrequency) {
                maxFrequency = frequency[i] - frequency[i - advLength];
                maxTime = i - advLength + 1;
            }
        }
        
        int s = maxTime % 60;
        maxTime = (maxTime - s) / 60;
        int m = maxTime % 60;
        int h = (maxTime - m) / 60;
        
        return String.format("%02d:%02d:%02d", h, m, s);
    }
}