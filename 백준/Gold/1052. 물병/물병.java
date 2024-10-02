import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int result = n;
        int k = Integer.parseInt(st.nextToken());

        String bin = Integer.toBinaryString(n);
        while (count(bin) > k) {
            result++;
            bin = Integer.toBinaryString(result);
        }
        System.out.println(result - n);
    }

    private static int count(String bin) {
        return (int) bin.chars()
            .filter(c -> c == '1')
            .count();
    }
}