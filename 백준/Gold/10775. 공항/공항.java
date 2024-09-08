import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int g = Integer.parseInt(br.readLine());
        int p = Integer.parseInt(br.readLine());
        int[] gates = new int[p];
        for (int i = 0; i < p; i++) {
            gates[i] = Integer.parseInt(br.readLine());
        }
        int[] parents = new int[g + 1];
        for (int i = 0; i <= g; i++) {
            parents[i] = i;
        }
        for (int i = 0; i < p; i++) {
            int gate = gates[i];
            int parent = findParent(gate, parents);
            if (parent == 0) {
                System.out.println(i);
                return;
            }
            uion(parent - 1, parent, parents);
        }
        System.out.println(p);
    }

    private static int findParent(int x, int[] parents) {
        if (parents[x] == x) {
            return x;
        }
        int y = findParent(parents[x], parents);
        parents[x] = y;
        return y;
    }

    private static void uion(int a, int b, int[] parents) {
        int aParent = findParent(a, parents);
        int bParent = findParent(b, parents);
        if (aParent < bParent) {
            parents[bParent] = aParent;
        } else {
            parents[aParent] = bParent;
        }
    }
}