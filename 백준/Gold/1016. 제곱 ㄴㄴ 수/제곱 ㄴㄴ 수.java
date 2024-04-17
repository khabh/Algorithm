import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long start = scanner.nextLong();
        long end = scanner.nextLong();
        Set<Long> numbers = new HashSet<>();
        for (long i = 2; i * i <= end; i++) {
            long num = i * i;
            long j = (start + num - 1) / num;
            while (j * num <= end) {
                numbers.add(j * num);
                j++;
            }
        }
        System.out.println(end - start - numbers.size() + 1);
    }
}
