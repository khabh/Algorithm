import java.util.*;

class Solution {
    
    private int target;
    
    public int solution(int coin, int[] cards) {
        int maxNumber = cards.length;
        target = maxNumber + 1;
        int index = maxNumber / 3;
        Set<Integer> current = new HashSet<>();
        for (int i = 0; i < index; i++) {
            current.add(cards[i]);
        }
        Set<Integer> selected = new HashSet<>();
        int round = 1;
        while (true) {
            if (index == cards.length) {
                break;
            }
            selected.add(cards[index]);
            selected.add(cards[index + 1]);
            index += 2;
            int coinCount = 3;
            int first = -1;
            for (int i = 1; i <= maxNumber / 2; i++) {
                int other = target - i;
                if (current.contains(i) && current.contains(other)) {
                    coinCount = 0;
                    first = i;
                    break;
                }
                if (coinCount > 1 && current.contains(i) && selected.contains(other) || current.contains(other) && selected.contains(i)) {
                    coinCount = 1;
                    first = i;
                    continue;
                }
                if (coinCount == 3 && selected.contains(other) && selected.contains(i)) {
                    coinCount = 2;
                    first = i;
                }
            }
            removePair(current, first);
            removePair(selected, first);
            if (coinCount == 3 || coinCount > coin) {
                break;
            }
            coin -= coinCount;
            round++;
        }
        
        return round;
    }
    
    private void removePair(Set<Integer> current, int a) {
        current.remove(a);
        current.remove(target - a);
    }
}

// import java.util.*;

// class Solution {
    
//     private int maxNumber;
//     private int[] cards;
    
//     private int dfs(Set<Integer> current, int coin, int index) {
//         int result = index;
//         List<Integer> next = new ArrayList<>();
//         if (current.size() == 0 || index == maxNumber) {
//             return index;
//         }
//         for (int i = 1; i <= maxNumber / 2; i++) {
//             if (canApply(current, i)) {
//                 next.add(i);
//             }
//         }
//         if (next.size() == 0 && coin == 0) {
//             return index;
//         }
        
//         for (int n : next) {
//             removePair(current, n);
//             result = Math.max(result, dfs(current, coin, index + 2));
//             if (coin > 0) {
//                 for (int i = index; i < index + 2; i++) {
//                     current.add(cards[i]);
//                     result = Math.max(result, dfs(current, coin - 1, index + 2));
//                     current.remove(cards[i]);
//                 }
//             }
//             if (coin > 1) {
//                 current.add(cards[index]);
//                 current.add(cards[index + 1]);
//                 result = Math.max(result, dfs(current, coin - 2, index + 2));
//                 current.remove(cards[index]);
//                 current.remove(cards[index + 1]);
//             }
//             addPair(current, n);
//         }
        
//         if (coin > 0) {
//             for (int i = index; i < index + 2; i++) {
//                 current.add(cards[i]);
//                 if (canApply(current, cards[i])) {
//                     removePair(current, cards[i]);
//                     result = Math.max(result, dfs(current, coin - 1, index + 2));
//                     addPair(current, cards[i]);
//                 }
//                 current.remove(cards[i]);
//             }
//         }
//         if (coin > 1) {
//             current.add(cards[index]);
//             current.add(cards[index + 1]);
//             if (canApply(current, cards[index])) {
//                 removePair(current, cards[index]);
//                 result = Math.max(result, dfs(current, coin - 2, index + 2));
//                 addPair(current, cards[index]);
//             }
//             if (canApply(current, cards[index + 1])) {
//                 removePair(current, cards[index + 1]);
//                 result = Math.max(result, dfs(current, coin - 2, index + 2));
//                 addPair(current, cards[index + 1]);
//             }
//             current.remove(cards[index]);
//             current.remove(cards[index + 1]);
//         }
//         return result;
//     }
    
//     private void removePair(Set<Integer> current, int a) {
//         current.remove(a);
//         current.remove(maxNumber + 1 - a);
//     }
    
//     private void addPair(Set<Integer> current, int a) {
//         current.add(a);
//         current.add(maxNumber + 1 - a);
//     }
    
//     private boolean canApply(Set<Integer> current, int a) {
//         return current.contains(a) && current.contains(maxNumber + 1 - a);
//     }
    
//     public int solution(int coin, int[] cards) {
//         this.cards = cards;
//         maxNumber = cards.length;
//         int startIndex = maxNumber / 3;
//         Set<Integer> current = new HashSet<>();
//         for (int i = 0; i < startIndex; i++) {
//             current.add(cards[i]);
//         }
//         return (dfs(current, coin, startIndex) - startIndex) / 2 + 1;
//     }
// }