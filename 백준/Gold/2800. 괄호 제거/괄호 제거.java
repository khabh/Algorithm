import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Deque<Integer> open = new LinkedList<>();
        List<List<Integer>> map = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(') {
                open.addLast(i);
                continue;
            }
            if (input.charAt(i) == ')') {
                map.add(List.of(open.pollLast(), i));
            }
        }
        int max = 1 << map.size();
        max -= 2;
        Set<String> set = new HashSet<>();
        while (max >= 0) {
            StringBuilder sb = new StringBuilder();
            Set<Integer> removed = new HashSet<>();
            for (int i = 0; i < map.size(); i++) {
                if ((max & 1 << i) == 0) {
                    removed.addAll(map.get(i));
                }
            }
            for (int i = 0; i < input.length(); i++) {
                if (removed.contains(i)) {
                    continue;
                }
                sb.append(input.charAt(i));
            }
            max--;
            if (set.contains(sb.toString())) {
                continue;
            }
            set.add(sb.toString());
        }
        System.out.println(set.stream().sorted()
                .collect(Collectors.joining("\n")));
    }
}
