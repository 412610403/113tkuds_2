
import java.util.*;

class Solution {

    public boolean isValid(String s) {
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');

        Deque<Character> stack = new LinkedList<>();

        for (char c : s.toCharArray()) {
            if (map.containsKey(c)) {
                Character top = stack.isEmpty() ? null : stack.pop();
                if (top == null || !top.equals(map.get(c))) {
                    return false;
                }
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }
}
