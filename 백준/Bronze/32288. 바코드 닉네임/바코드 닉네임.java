import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException { 
        Scanner sc = new Scanner(System.in);
        sc.next();
        String input = sc.next();
        for (char i : input.toCharArray()) {
            if (i == 'I') {
                System.out.print('i');
            } else {
                System.out.print('L');
            }
        }
    }
}
