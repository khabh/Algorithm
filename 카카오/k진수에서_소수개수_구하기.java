import java.util.*;
import java.util.stream.*;

class Solution {
    public int solution(int n, int k) {
        List<Long> numbers = Arrays.stream(Integer.toString(n, k).split("0+"))
            .filter(num -> !num.isEmpty())
            .map(Long::parseLong)
            .collect(Collectors.toList());
        
        return (int)numbers.stream()
            .filter(number -> isPrime(number))
            .count();
    }
    
    private boolean isPrime(long number) {
        if (number < 2)
            return false;
        for (int i = 2; i < (long)Math.sqrt(number) + 1; i++) {
            if (number % i == 0)
                return false;
        }
      return true;
    }
}