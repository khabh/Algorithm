package org.example.algorithm.basic1;

import java.util.Scanner;
import java.util.StringJoiner;

public class Problem7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        StringJoiner resultMaker = new StringJoiner(" ");

        StringBuilder result = new StringBuilder();
        int start = 0;
        boolean isTag = false;
        for (int end = 0; end < input.length(); end++ ) {
            if (input.charAt(end) == '<') {
                result.append(new StringBuilder(input.substring(start, end)).reverse());
                start = end;
                isTag = true;
            }
            if (input.charAt(end) == '>') {
                result.append(input, start, end + 1);
                start = end + 1;
                isTag = false;
                if (start == input.length())
                    break;
            }
            if (input.charAt(end) == ' ' && !isTag) {
                if (input.charAt(end - 1) != '>') {
                    result.append(new StringBuilder(input.substring(start, end)).reverse());
                }
                resultMaker.add(result);
                result.setLength(0);
                start = end + 1;
            }
        }
        if (start < input.length()) {
            result.append(new StringBuilder(input.substring(start)).reverse());
        }
        resultMaker.add(result);
        System.out.println(resultMaker);
    }
}
