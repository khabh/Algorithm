import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] values = new int[n + 1];
        int[] result = new int[n];
        String[] valuesStr = br.readLine().split(" ");
        for (int i = 1; i <= n; i++) {
            values[i] = Integer.parseInt(valuesStr[i - 1]);
        }
        Deque<Integer> stack = new LinkedList<>();
        stack.add(1);
        for (int i = 2; i <= n; i++) {
            int current = values[i];
            while (!stack.isEmpty() && values[stack.peekLast()] < current) {
                stack.pollLast();
            }
            if (!stack.isEmpty()) {
                result[i - 1] = stack.peekLast();
            }
            stack.addLast(i);
        }
        System.out.println(Arrays.stream(result).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
    }
}
