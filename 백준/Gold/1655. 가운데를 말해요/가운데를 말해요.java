import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(br.readLine());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        while (n-- > 0) {
            int num = Integer.valueOf(br.readLine());
            if (maxHeap.size() <= minHeap.size()) {
                maxHeap.add(num);
            } else {
                minHeap.add(num);
            }
            while (!minHeap.isEmpty() && maxHeap.peek() > minHeap.peek()) {
                int a = maxHeap.poll();
                int b = minHeap.poll();
                maxHeap.add(b);
                minHeap.add(a);
            }
            System.out.println(maxHeap.peek());
        }
    }
}
