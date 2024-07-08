import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

class Main {

    static int l;
    static List<Integer> nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        l = Integer.valueOf(st.nextToken());
        int k = Integer.valueOf(st.nextToken());
        int c = Integer.valueOf(st.nextToken());
        nums = getNums(k, new StringTokenizer(br.readLine()));
        c = Math.min(c, nums.size());
        int start = 0;
        int end = l;
        int first = -1;

        while (start + 1 < end) {
            int mid = (start + end) / 2;
            int result = available(mid, c);
            if (result != -1) {
                end = mid;
                first = result;
            }
            else {
                start = mid;
            }
        }
        System.out.println(end + " " + first);
    }

    private static int available(int maxLen, int c) {
        int prev = 0;
        int temp = 0;
        for (int num : nums) {
            if (num - prev <= maxLen) {
                temp = num;
                continue;
            }
            prev = temp;
            temp = num;
            c--;

            if (temp - prev > maxLen) {
                return -1;
            }
            if (c == 0) {
                break;
            }
        }
        if (c > 0) {
            if (l - prev > maxLen) {
                prev = temp;
            } else {
                prev = nums.get(nums.size() - 1);
            }
        }
        return l - prev > maxLen ? -1 : (l - prev);
    }

    private static List<Integer> getNums(int k, StringTokenizer st) {
        Set<Integer> nums = new HashSet<>();
        while (k-- > 0) {
            nums.add(Integer.valueOf(st.nextToken()));
        }
        return new ArrayList<>(nums).stream()
                .map(num -> l - num)
                .sorted()
                .collect(Collectors.toList());
    }
}
