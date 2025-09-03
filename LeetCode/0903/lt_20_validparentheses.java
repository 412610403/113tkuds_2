
import java.util.*;

class Solution {

    public boolean isValid(String s) {
        Deque<Character> deque = new LinkedList<>();
        char ch;
        for (int i = 0; i < s.length(); i++) {
            ch = s.charAt(i);
            //碰到左括號，就把相應的右括號入棧
            if (ch == '(') {
                deque.push(')');
            } else if (ch == '{') {
                deque.push('}');
            } else if (ch == '[') {
                deque.push(']');
            } else if (deque.isEmpty() || deque.peek() != ch) {
                return false;
            } else {//如果是右括號判斷是否和棧頂元素匹配
                deque.pop();
            }
        }
        //遍歷结束，如果棧為空，則括號全部匹配
        return deque.isEmpty();
    }
}
/*
解題思路：
1. 使用棧結構來匹配括號。
2. 每遇到一個左括號，將對應的右括號壓入棧中。
3. 每遇到一個右括號，檢查是否與棧頂元素匹配：
   - 如果匹配，彈出棧頂；
   - 如果不匹配或棧為空，返回 false。
4. 遍歷結束後，如果棧為空，表示所有括號正確配對。
時間複雜度 O(N)，空間複雜度 O(N)，其中 N 為字串長度。
*/
