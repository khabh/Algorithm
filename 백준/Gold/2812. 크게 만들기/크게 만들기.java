import java.util.*;

class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int length = n - k;
        String nums = scanner.next();
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack();

        for (char num : nums.toCharArray()) {
            while (!stack.isEmpty() && k > 0 && num > stack.peek()) {
                stack.pop();
                k--;
            }
            stack.push(num);
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        System.out.println(sb.reverse().substring(0, length));
    }
}
