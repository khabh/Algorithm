import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Integer.parseInt(br.readLine());
        String result = Arrays.stream(br.readLine().split(" "))
                .map(Node::new)
                .sorted()
                .map(Node::getVal)
                .collect(Collectors.joining(""))
                .replaceFirst("^0+", "");
        System.out.println(result.length() == 0 ? "0" : result);
    }

    private static class Node implements Comparable<Node> {
        String value;

        public Node(String value) {
            this.value = value;
        }

        public String getVal() {
            return value;
        }

        @Override
        public int compareTo(Node o) {
            String a = value + o.value;
            String b = o.value + value;
            return b.compareTo(a);
        }
    }
}