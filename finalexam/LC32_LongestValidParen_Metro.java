
import java.util.*;

public class LC32_LongestValidParen_Metro {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(longestValidParentheses(s));
    }

    public static int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1); // 棧底基準
        int maxLen = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i); // 重新置基準
                } else {
                    maxLen = Math.max(maxLen, i - stack.peek());
                }
            }
        }
        return maxLen;
    }
}

/*解題思路：
用索引堆疊：棧底放 -1。
遇 '(' push 索引；遇 ')' pop，若棧空則 push 當前索引為新基準。
若不空，合法長度 = 當前索引 - 棧頂索引。
過程中取最大值即答案。
時間 O(n)，空間 O(n)。
 */
