import java.util.*;
import java.io.*;

class Main {
    private static final Date START = new Date(3, 1);
    private static final Date END = new Date(11, 30);

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(br.readLine());
        List<Flower> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Flower f = new Flower(br.readLine());
            if (f.d1.compareTo(END) == 1) {
                continue;
            }
            list.add(f);
        }
        Collections.sort(list);
        Flower first = list.get(0);
        if (first.d1.compareTo(START) == 1) {
            System.out.println(0);
            return;
        }
        Stack<Flower> stack = new Stack<>();
        stack.push(first);

        for (int i = 1; i < list.size(); i++) {
            Flower current = list.get(i);
            Date d1 = current.d1;
            Date d2 = current.d2;
            Flower top = stack.peek();
            if (top.d2.compareTo(END) == 1) {
                break;
            }
            if (d2.compareTo(top.d2) != 1) {
                continue;
            }
            if (d1.compareTo(top.d2) == 1) {
                System.out.println(0);
                return;
            }
            if (d1.compareTo(top.d1) != 1) {
                stack.pop();
                current.d1 = top.d1;
                stack.push(current);
            }
            else { 
                current.d1 = top.d2;
                stack.push(current);
            }
        }

        if (stack.peek().d2.compareTo(END) != 1) {
            System.out.println(0);
            return;
        }
        System.out.println(stack.size());
    }

    static class Date {

        int m;
        int d;

        public Date(int m, int d) {
            this.m = m;
            this.d = d;
        }

        public int compareTo(Date date) {
            if (m == date.m && d == date.d) {
                return 0;
            }
            if (m < date.m || (m == date.m && d < date.d)) {
                return -1;
            }
            return 1;
        }       
    }

    static class Flower implements Comparable<Flower> {

        Date d1;
        Date d2;

        public Flower(String value) {
            StringTokenizer st = new StringTokenizer(value);
            d1 = new Date(Integer.valueOf(st.nextToken()), Integer.valueOf(st.nextToken()));
            d2 = new Date(Integer.valueOf(st.nextToken()), Integer.valueOf(st.nextToken()));
            if (d1.compareTo(START) == -1) {
                d1 = START;
            }
        }

        @Override
        public int compareTo(Flower f) {
            int startComp = d1.compareTo(f.d1);
            if (startComp != 0) {
                return startComp;
            }
            return -d2.compareTo(f.d2);
        }
    }
}
