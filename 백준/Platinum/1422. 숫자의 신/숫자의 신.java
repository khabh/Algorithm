import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // n 개의 자연수
        int k = Integer.parseInt(st.nextToken()); // k개의 수 뽑기 k >= n
        List<String> nodes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            nodes.add(br.readLine());
        }
        Collections.sort(nodes, Main::compare);
        int repeat = findRepeat(nodes);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(nodes.get(i));
            if (i == repeat) {
                sb.append(nodes.get(i).repeat(k - n));
            }
        }
        System.out.println(sb.charAt(0) == '0' ? "0" : sb);
    }

    private static int compare(String s1, String s2) {
        return (s2 + s1).compareTo(s1 + s2);
    }

    private static int findRepeat(List<String> list) {
        int maxIndex = 0;
        String max = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            String cur = list.get(i);
            int result = Integer.compare(cur.length(), max.length());
            if (result < 0 || (result == 0 && cur.compareTo(max) <= 0)) {
                continue;
            }
            maxIndex = i;
            max = cur;
        }
        return maxIndex;
    }
}