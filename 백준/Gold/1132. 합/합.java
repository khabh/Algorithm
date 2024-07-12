import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(br.readLine());
        List<Alphabet> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new Alphabet(i));
        }

        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            long value = (long) Math.pow(10, input.length());
            list.get(input.charAt(0) - 'A').canBeZero = false;
            for (char c : input.toCharArray()) {
                value /= 10;
                list.get(c - 'A').sum += value;
            }
        }
        list.sort(Comparator.comparingLong(a -> -a.sum));
        if (!list.get(9).canBeZero) {
            int index = 8;
            while (!list.get(index).canBeZero) {
                index--;
            }
            Alphabet temp = list.get(index);
            list.remove(index);
            list.add(temp);
        }
        
        long result = 0;
        for (int i = 0; i < 10; i++) {
            result += (list.get(i).sum * (9 - i));
        }
        System.out.println(result);
    }

    static class Alphabet {
        boolean canBeZero = true;
        long sum = 0;
        int order;

        public Alphabet(int order) {
            this.order = order;
        }
    }
}
