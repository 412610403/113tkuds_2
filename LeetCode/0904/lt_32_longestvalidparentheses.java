
import java.util.*;

class Solution {

    public int longestValidParentheses(String s) {
        int n = s.length();
        int max = 0;
        Deque<Integer> sta = new LinkedList<>();// 棧用來存放字符串中最後的沒有被匹配的右括號的位置
        sta.push(-1);// 為了保持統一，放入-1表示最後一個未被匹配的右括號位置為-1
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                sta.push(i);// 碰到左括號直接入棧
            } else {
                sta.pop();// 碰到右括號時出棧棧頂元素，假設已經配對了
                if (sta.isEmpty()) {
                    sta.push(i);// 右括號和棧頂元素沒有配對成功
                } else {
                    max = Math.max(max, i - sta.peek());
                }
            }
        }
        return max;
    }
}
/*
解題思路：
1. 使用棧來追蹤「最後一個未匹配的括號位置」。
2. 初始放入 -1，方便計算合法子字串的長度。
3. 遍歷字串：
   - 遇到 '('，將索引壓入棧。
   - 遇到 ')'，先彈出一個元素：
     - 如果棧空了，代表目前右括號多餘，把當前索引壓入。
     - 否則，用當前索引減去棧頂索引，得到合法括號子字串長度，更新最大值。
4. 最終 max 就是最長合法括號子字串長度。
 */
