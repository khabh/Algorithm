// 크레인 인형뽑기 게임
import java.util.*;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int n = board.length;
        int answer = 0;
        Map<Integer, Stack<Integer>> machine = new HashMap<>();
        for (int i = 0; i < n; i++) {
            Stack<Integer> stack = new Stack<>();
            for (int j = n - 1; j >= 0; j--) {
                if (board[j][i] == 0)
                    break;
                stack.push(board[j][i]);
            }
            machine.put(i + 1, stack);
        }
        Stack<Integer> result = new Stack<>();
        for (int move : moves) {
            Stack<Integer> stack = machine.get(move);
            if (stack.isEmpty())
                continue;
            int current = stack.pop();
            if (result.isEmpty() || result.peek() != current) {
                result.push(current);
                continue;
            }
            answer += 2;
            result.pop();
        }
        return answer;
    }
}

// 튜플
import java.util.*;
import java.util.stream.*;

class Solution {
    public int[] solution(String s) {
        String[] inputs = s.substring(2, s.length() - 2).split("\\},\\{");
        List<List<Integer>> tuples = new ArrayList<>();
        Set<Integer> numbers = new HashSet<>();
        int[] answer = new int[inputs.length];
        
        for (String input : inputs) {
            tuples.add(Arrays.stream(input.split(","))
                .map(Integer::valueOf)
                .collect(Collectors.toList()));
        }
        tuples.sort((tuple1, tuple2) -> Integer.compare(tuple1.size(), tuple2.size()));
        
        int index = 0;
        for (List<Integer> tuple : tuples) {
            for (int num : tuple) {
                if (numbers.contains(num))
                    continue;
                numbers.add(num);
                answer[index++] = num;
            }
        }
        return answer;
    }
}