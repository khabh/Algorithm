package org.example.algorithm.basic2;

import java.util.*;

class Screen {
    private static Set<Screen> visited = new HashSet<>();
    private static int target;
    private static int MAX_INDEX;
    final int clipboard;
    final int inputtedCount;
    final int operateCount;

    public Screen(int clipboard, int inputtedCount, int operateCount) {
        this.clipboard = clipboard;
        this.inputtedCount = inputtedCount;
        this.operateCount = operateCount;
    }

    public int addNextScreens(Queue<Screen> queue) {
        if (addClipboardPasted(queue) || addRemoved(queue))
            return operateCount + 1;
        addClipboardUpdated(queue);
        return -1;
    }

    public boolean addClipboardPasted(Queue<Screen> queue) {
        if (clipboard == 0 || inputtedCount + clipboard >= MAX_INDEX)
            return false;
        if (inputtedCount + clipboard == target)
            return true;
        Screen screen = new Screen(clipboard, inputtedCount + clipboard, operateCount + 1);
        if (!visited.contains(screen)) {
            queue.add(screen);
            visited.add(screen);
        }
        return false;
    }

    public void addClipboardUpdated(Queue<Screen> queue) {
        if (inputtedCount * 2 >= MAX_INDEX)
            return;
        Screen screen = new Screen(inputtedCount, inputtedCount, operateCount + 1);
        if (!visited.contains(screen)) {
            queue.add(screen);
            visited.add(screen);
        }
    }

    public boolean addRemoved(Queue<Screen> queue) {
        if (inputtedCount == 0)
            return false;
        if (inputtedCount - 1 == target)
            return true;
        Screen screen = new Screen(clipboard, inputtedCount - 1, operateCount + 1);
        if (!visited.contains(screen)) {
            queue.add(screen);
            visited.add(screen);
        }
        return false;
    }

    public boolean isSameAs(int target) {
        return target == inputtedCount;
    }

    public static void setTarget(int target) {
        Screen.target = target;
        MAX_INDEX = target * 2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Screen screen = (Screen) o;
        return clipboard == screen.clipboard && inputtedCount == screen.inputtedCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(clipboard, inputtedCount);
    }
}

public class Problem32 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int s = scanner.nextInt();
        Screen.setTarget(s);
        Queue<Screen> screens = new LinkedList<>();
        screens.add(new Screen(0, 1, 0));
        while (!screens.isEmpty()) {
            Screen screen = screens.poll();
            int result = screen.addNextScreens(screens);
            if (result != -1) {
                System.out.println(result);
                return;
            }
        }
    }
}
