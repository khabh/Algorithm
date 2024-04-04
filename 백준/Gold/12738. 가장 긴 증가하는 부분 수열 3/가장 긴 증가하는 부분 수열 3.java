import java.util.*;

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
        list.add(Integer.MIN_VALUE);
        for (int number : numbers) {
            if (list.get(list.size() - 1) < number) {
                list.add(number);
                continue;
            }
            int index = getIndex(list, number);
            list.set(index, number);
        }
        System.out.println(list.size() - 1);
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