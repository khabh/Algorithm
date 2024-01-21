import java.util.*;

class Solution {
    private static HashMap<Long, Long> room = new HashMap<>();

    public long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];

        for(int i = 0; i < room_number.length; i++) {
            long assignedRoom = findEmptyRoom(room_number[i]);
            answer[i] = assignedRoom;
        }

        return answer;
    }

    private static long findEmptyRoom(long n) {
        if (!room.containsKey(n)) {
            room.put(n, n + 1);
            return n;
        }

        long next = room.get(n);
        long emptyRoom = findEmptyRoom(next);
        room.put(n, emptyRoom + 1);
        return emptyRoom;
    }
}