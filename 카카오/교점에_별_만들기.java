import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.lang.StringBuilder;

class Solution {
    public String[] solution(int[][] line) {
        Map<Long, Set<Long>> points = new HashMap<>();
        List<String> result = new ArrayList<String>();
        Long maxX = Long.MIN_VALUE, maxY = Long.MIN_VALUE, minX = Long.MAX_VALUE, minY = Long.MAX_VALUE;
        
        for (int i = 0; i < line.length - 1; i++) {
            long a = line[i][0], b = line[i][1], e = line[i][2];
            for (int j = i + 1; j < line.length; j++) {
                long c = line[j][0], d = line[j][1], f = line[j][2];
                double y = (double)(b * f - e * d) / (a * d - b * c);
                double x = (double)(e * c - a * f) / (a * d - b * c);
                if (x % 1 == 0 && y % 1 == 0) {
                    long x1 = (long)x, y1 = (long)y;
                    if (!points.containsKey(x1)) 
                        points.put(x1, new HashSet<Long>());
                    points.get(x1).add(y1);
                    maxX = Math.max(maxX, x1);
                    minX = Math.min(minX, x1);
                    maxY= Math.max(maxY, y1);
                    minY= Math.min(minY, y1);
                }
            }
        }
        
        for (long i = maxX; i >= minX; i--) {
            if (!points.containsKey(i)) {
                result.add(".".repeat((int)(maxY - minY + 1)));
                continue;
            }
            StringBuilder starMaker = new StringBuilder();
            for (long j = minY; j <= maxY; j++) {
                if (points.get(i).contains(j)) 
                    starMaker.append("*");
                else
                    starMaker.append(".");
            }
            result.add(starMaker.toString());
        }
        
        return result.toArray(new String[0]);
    }
}