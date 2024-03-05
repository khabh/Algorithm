import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        Set<Integer> pos = new HashSet<>();
        for (int i = 0; i < n; i++) {
            pos.add(scanner.nextInt());
        }
        List<Integer> sortedPos = pos.stream()
                .sorted()
                .collect(Collectors.toList());
        List<Integer> dist = new ArrayList<>();
        for (int i = 0; i < sortedPos.size() - 1; i++) {
            dist.add(sortedPos.get(i + 1) - sortedPos.get(i));
        }
        Collections.sort(dist);
        int distCount = dist.size() - (k - 1);
        int index = 0;
        int result = 0;
        while (index < distCount) {
            result += dist.get(index++);
        }
        System.out.println(result);
    }
}
