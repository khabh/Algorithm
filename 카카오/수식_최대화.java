// https://school.programmers.co.kr/learn/courses/30/lessons/67257

import java.util.List;
import java.util.ArrayList;

class Solution {
    private List<Character> operators;
    private List<Long> numbers;
    
    private void calculate(char operator) {
        List<Character> new_operators = new ArrayList<>();
        List<Long> new_numbers = new ArrayList<>();
        int index = 1;
        new_numbers.add(numbers.get(0));
        for (char op : operators) {
            if (op == operator) {
                long number = new_numbers.get(new_numbers.size() - 1);
                if (op == '+') 
                    number += numbers.get(index);
                else if (op == '-')
                    number -= numbers.get(index);
                else
                    number *= numbers.get(index);
                new_numbers.set(new_numbers.size() - 1, number);
            } else {
                new_numbers.add(numbers.get(index));
                new_operators.add(op);
            }
            index++;
        }
        operators = new_operators;
        numbers = new_numbers;
    }
    
    
    public long solution(String expression) {
        long num = 0, result = 0;
        char[][] priority = {{'+', '-', '*'},
                             {'+', '*', '-'},
                             {'-', '+', '*'},
                             {'-', '*', '+'},
                             {'*', '-', '+'},
                             {'*', '+', '-'}};
        
        List<Character> ops = new ArrayList<>();
        List<Long> nums = new ArrayList<>();
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) < '0') {
                nums.add(num);
                num = 0;
                ops.add(expression.charAt(i));
                continue;
            }
            num = num * 10 + expression.charAt(i) - '0';
        }
        nums.add(num);
        
        for (char[] p : priority) {
            operators = new ArrayList<>(ops);
            numbers = new ArrayList<>(nums);
            for (char op : p) {
                calculate(op);
            }
            long last = Math.abs(numbers.get(0));
            if (result < last)
                result = last;
        }
        return result;
    }
}