import java.util.*;

class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String first = scanner.nextLine();
        String second = scanner.nextLine();

        while (second.length() != first.length()) {
            boolean flip = second.charAt(second.length() - 1) == 'B';
            second = second.substring(0, second.length() - 1);
            if (flip) {
                second = new StringBuffer(second).reverse().toString();
            }
        }
        if (second.equals(first)) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }
}
