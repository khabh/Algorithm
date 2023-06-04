// https://school.programmers.co.kr/learn/courses/30/lessons/92335?language=java

import java.util.Stack;

class Solution92335 {
    private boolean isPrime(long number) {
        if (number < 2)
            return false;
        for (int i = 2; i < (long)Math.sqrt(number) + 1; i++) {
            if (number % i == 0)
                return false;
        }
        return true;
    }
    
    public int solution(int n, int k) {
        int answer = 0;
        Stack<Integer> numbers = new Stack();
        while (n > 0) {
            numbers.push(n % k);
            n /= k;
        }
        
        while (!numbers.empty()) {
            while (!numbers.empty() && numbers.peek() == 0)
                numbers.pop();
            long number = 0;
            while (!numbers.empty() && numbers.peek() != 0) {
                number = number * 10 + numbers.peek();
                numbers.pop();
            }
            if (isPrime(number))
                answer++;
        }
        return answer;
    }
}