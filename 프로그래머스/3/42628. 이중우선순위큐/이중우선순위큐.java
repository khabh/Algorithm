import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        List<Integer> nums = new ArrayList<>();
        for (String op : operations) {
            int num = Integer.valueOf(op.split(" ")[1]);
            char act = op.charAt(0);
            if (act == 'I') {
                int index = getIndex(num, nums);
                nums.add(index, num);
                continue;
            }
            if (act == 'D') {
                if (nums.isEmpty()) {
                    continue;
                }
                if (num == -1) {
                    nums.remove(0);
                } else {
                    nums.remove(nums.size() - 1);
                }
            }
        }
        if (nums.isEmpty()) {
            return new int[]{0, 0};
        }
        return new int[]{nums.get(nums.size() - 1), nums.get(0)};
    }
    
    private int getIndex(Integer num, List<Integer> nums) {
        if (nums.isEmpty()) {
            return 0;
        }
        int start = 0;
        int end = nums.size();
        while (start < end) {
            int mid = (start + end) / 2;
            int cur = nums.get(mid);
            if (cur < num) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return start;
    }
}