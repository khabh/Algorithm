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
        int[] increase = new int[n];
        for (int i = 0; i < n; i++) {
            increase[i] = 1;
            for (int j = 0; j < i; j++) {
                if (numbers[i] > numbers[j]) {
                    increase[i] = Math.max(increase[i], increase[j] + 1);
                }
            }
        }
        int[] decrease = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            decrease[i] = 1;
            for (int j = n - 1; j > i ; j--) {
                if (numbers[j] < numbers[i]) {
                    decrease[i] = Math.max(decrease[i], decrease[j] + 1);
                }
            }
        }
        int result = 1;
        for (int i = 0; i < n; i++) {
            result = Math.max(result, decrease[i] + increase[i] - 1);
        }
        System.out.println(result);
    }
}