import java.util.*;
import java.util.stream.*;

public class Main
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = sc.nextInt();
        }
        List<Integer> list = new ArrayList<>();
        int[] positions = new int[n];
        list.add(Integer.MIN_VALUE);
        for (int i = 0; i < n; i++) {
            int number = numbers[i];
            if (list.get(list.size() - 1) < number) {
                list.add(number);
                positions[i] = list.size() - 1;
                continue;
            }
            int index = getIndex(list, number);
            list.set(index, number);
            positions[i] = index;
        }
        Deque<Integer> result = new ArrayDeque<>();
        int currentIndex = list.size() - 1;
        for (int i = n - 1; i >= 0; i--) {
            if (positions[i] == currentIndex) {
                result.addFirst(numbers[i]);
                currentIndex--;
            }
        }
        System.out.println(list.size() - 1);
        System.out.println(result.stream()
                           .map(String::valueOf)
                           .collect(Collectors.joining(" ")));
    }

    private static int getIndex(List<Integer> numbers, int number) {
        int start = 0;
        int end = numbers.size() - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            int midNum = numbers.get(mid);
            if (number == midNum) {
                return mid;
            }
            if (number < midNum) {
                end = mid - 1;
            }
            if (number > midNum) {
                start = mid + 1;
            }
        }
        return start;
    }
}