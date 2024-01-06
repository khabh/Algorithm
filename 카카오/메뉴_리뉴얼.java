import java.util.*;
import java.util.stream.Collectors;

class Solution {
    Set<Integer> counts = new HashSet<>();
    List<String> existMenus;
    Result result = new Result();
    
    class Result {
        int[] courseOrderCount = new int[11];
        private final Map<Integer, List<String>> courses = new HashMap<>();
        
        public void add(String course, int count) {
            int menuCount = course.length();
            int currentMaxCount = courseOrderCount[menuCount];
            if (currentMaxCount > count) {
                return;
            }
            if (currentMaxCount < count) {
                courses.put(menuCount, new ArrayList<>());
            }
            courses.get(menuCount).add(course);
            courseOrderCount[menuCount] = count;
        }
        
        public String[] getAnswer() {
            return courses.values().stream()
                .flatMap(List::stream) 
                .sorted()            
                .toArray(String[]::new);
        }
    }
    
    class Order {
        Set<String> menus = new HashSet<>();
        
        public Order(String order) {
            String[] splitOrder = order.split("");
            for (String menu : splitOrder) {
                menus.add(menu);
            }
        }
        
        public boolean orderedCourse(String course) {
            if (menus.size() < course.length()) {
                return false;
            }
            for (String menu : course.split("")) {
                if (!menus.contains(menu))
                    return false;
            }
            return true;
        }
    }
    
    private void dfs(int index, String current, List<Order> orders) {
        int currentMenuCount = current.length();
        int orderedCount = 0;
        for (Order order : orders) {
            if (order.orderedCourse(current)) {
                orderedCount++;
            }
        }
        if (index != 0 && orderedCount < 2) {
            return;
        }
        if (counts.contains(currentMenuCount)) {
            result.add(current, orderedCount);
        }
        for (int i = index; i < existMenus.size(); i++) {
            String m = existMenus.get(i);
            dfs(i + 1, current + m, orders);
        }
    }
    
    public String[] solution(String[] inputOrders, int[] course) {
        List<Order> orders = new ArrayList<>();
        for (String input : inputOrders) {
            orders.add(new Order(input));
        }
        for (int c : course) {
            counts.add(c);
        }
        existMenus = Arrays.stream(String.join("", inputOrders).split(""))
            .distinct()
            .sorted()
            .collect(Collectors.toList());
        dfs(0, "", orders);
        return result.getAnswer();
    }
}