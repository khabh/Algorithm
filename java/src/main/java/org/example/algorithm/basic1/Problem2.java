package org.example.algorithm.basic1;

import java.util.Scanner;
import java.util.StringJoiner;

public class Problem2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = Integer.parseInt(scanner.nextLine());

        while (t-- > 0) {
            String[] input = scanner.nextLine().split(" ");
            StringJoiner resultMaker = new StringJoiner(" ");
            for (String word : input) {
                resultMaker.add(new StringBuffer(word).reverse());
            }
            System.out.println(resultMaker);
        }
    }
}
