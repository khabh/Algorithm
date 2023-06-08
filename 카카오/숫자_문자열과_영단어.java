import java.lang.StringBuilder;
import java.util.Map;
import java.util.HashMap;

class Solution {
    public int solution(String s) {
        Map<String, Integer> match = new HashMap<>();
        StringBuilder numberMaker = new StringBuilder();
        int number = 0;
        
        match.put("zero", 0);
        match.put("one", 1);
        match.put("two", 2);
        match.put("three", 3);
        match.put("four", 4);
        match.put("five", 5);
        match.put("six", 6);
        match.put("seven", 7);
        match.put("eight", 8);
        match.put("nine", 9);
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c <= '9' && c >= '0') {
                number = number * 10 + (c - '0');
                continue;
            }
            numberMaker.append(c);
            if (match.containsKey(numberMaker.toString())) {
                number = number * 10 + (match.get(numberMaker.toString()));
                numberMaker.setLength(0);
            }
        }
        
        return number;
    }
}