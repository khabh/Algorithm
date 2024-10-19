import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class Main {

    static int n, l, result1, result2;
    static int[][] nums;

    public static void main(String[] args) throws IOException { 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Deque<Integer> nums = new ArrayDeque<>(Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .sorted()
                    .boxed()
                    .collect(Collectors.toList()));
        int result = 0;
        while (!nums.isEmpty()) {
            Deque<Integer> next = new ArrayDeque<>();
            result++;
            int count = 1;
            nums.poll();
            while (!nums.isEmpty()) {
                int num = nums.poll();
                if (num < count) {
                    next.add(num);
                } else {
                    count++;
                }
            }
            nums = next;
        }
        System.out.println(result);
    }
}
