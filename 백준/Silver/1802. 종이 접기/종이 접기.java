import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.valueOf(br.readLine());

        while (t-- > 0) {
            String input = br.readLine();
            boolean isValid = true;
            while (isValid && input.length() > 1) {
                int index = (input.length() - 1) / 2;
                String prev = input.substring(0, index);
                String next = input.substring(index + 1);
                for (int i = 0, j = index - 1; i < index; i++, j--) {
                    if (prev.charAt(i) == next.charAt(j)) {
                        isValid = false;
                        break;
                    }
                }
                input = prev;
            }
            if (isValid) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}
