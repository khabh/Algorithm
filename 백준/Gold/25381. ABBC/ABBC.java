import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] input = br.readLine().toCharArray();
        int n = input.length;
        
        System.out.println(countBC(input, n) + countAB(input, n));
    }

    private static int countBC(char[] input, int n) {
        int bIndex = 0;
        int cIndex = 0;
        int count = 0;

        while (true) {
            while (bIndex < n && input[bIndex] != 'B') {
                bIndex++;
            }
            while (cIndex < n && input[cIndex] != 'C') {
                cIndex++;
            }
            if (bIndex == n || cIndex == n) {
                break;
            }
            if (bIndex < cIndex) {
                input[bIndex] = '0'; 
                count++;
                bIndex++;
            }
            cIndex++;
        }
        return count;
    }

    private static int countAB(char[] input, int n) {
        int bIndex = n - 1;
        int aIndex = n - 1;
        int count = 0;

        while (true) {
            while (bIndex >= 0 && input[bIndex] != 'B') {
                bIndex--;
            }
            while (aIndex >= 0 && input[aIndex] != 'A') {
                aIndex--;
            }
            if (aIndex < 0 || bIndex < 0) {
                break;
            }
            if (bIndex > aIndex) {
                input[bIndex] = '0'; 
                count++;
                bIndex--;
            }
            aIndex--;
        }
        return count;
    }
}