import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int l = scanner.nextInt();
        List<Pos> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            list.add(new Pos(start, end));
        }
        Collections.sort(list);
        int count = 0;
        int index = 0;
        for (Pos pos : list) {
            int start = pos.start;
            int end = pos.end;
            if (index > end) {
                continue;
            }
            index = Math.max(index, start);
            int delta = (int)Math.ceil((end - index) / (double)l);
            count += delta;
            index += (delta * l);
        }
        System.out.println(count);
    }

    private static class Pos implements Comparable<Pos> {
        int start;
        int end;

        public Pos(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Pos o) {
            return start - o.start;
        }
    }
}
