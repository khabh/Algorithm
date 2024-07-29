import java.util.*;
import java.io.*;

public class Main {

    static final int MAX = 50_000_000;
    static int m;
    static int c;
    static int n;
    static String codes;
    static String input;
    static int[] arr;
    static int inputIndex;
    static int codeIndex;
    static int pointer;
    static int[] jump;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken()); // 메모리(배열)의 크기
            c = Integer.parseInt(st.nextToken()); // 코드 크기
            n = Integer.parseInt(st.nextToken()); // 입력 크기
            codes = br.readLine();
            input = br.readLine();

            arr = new int[m];
            jump = new int[c];
            inputIndex = 0;
            codeIndex = 0;
            pointer = 0;
            int count = 0;
            initJump();
            while (count < MAX && codeIndex < c) {
                run();
                count++;
            }
            if (codeIndex == c) {
                System.out.println("Terminates");
            } else {
                count = 0;
                int min = c;
                int max = -1;
                while (count < MAX) {
                    run();
                    count++;
                    min = Math.min(min, codeIndex);
                    max = Math.max(max, codeIndex);
                }
                System.out.println("Loops " + (min - 1) + " " + max);
            }
        }
    }

    private static void initJump() {
        Arrays.fill(jump, -1);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < c; i++) {
            char code = codes.charAt(i);
            if (code == '[') {
                stack.push(i);
                continue;
            }
            if (code == ']') {
                int prev = stack.pop();
                jump[prev] = i + 1;
                jump[i] = prev + 1;
            }
        }
    }

    private static void run() {
        char code = codes.charAt(codeIndex++);
        if (code == '>') {
            pointer = pointer + 1 >= m ? 0 : pointer + 1;
            return;
        }
        if (code == '<') {
            pointer = pointer - 1 < 0 ? m - 1 : pointer - 1;
            return;
        }
        if (code == '+') {
            arr[pointer] = arr[pointer] + 1 > 255 ? 0 : arr[pointer] + 1;
            return;
        }
        if (code == '-') {
            arr[pointer] = arr[pointer] - 1 < 0 ? 255 : arr[pointer] - 1;
            return;
        }
        if (code == ',') {
            arr[pointer] = inputIndex == n ? 255 : input.charAt(inputIndex++);
            return;
        }
        if (code == '[') {
            if (arr[pointer] == 0) {
                codeIndex = jump[--codeIndex];
            }
            return;
        }
        if (code == ']') {
            if (arr[pointer] != 0) {
                codeIndex = jump[--codeIndex];
            }
        }
    }
}
