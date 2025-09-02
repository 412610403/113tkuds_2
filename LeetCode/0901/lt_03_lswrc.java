
import java.util.HashMap;
import java.util.Map;

class Solution {

    public int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0; // n = 字串長度, ans = 最長長度
        Map<Character, Integer> map = new HashMap<>(); // 存放字元及其最新出現位置

        // i = 左指針, j = 右指針
        for (int i = 0, j = 0; j < n; j++) {
            char c = s.charAt(j);

            // 若字元重複，更新左指針位置
            if (map.containsKey(c)) {
                i = Math.max(map.get(c) + 1, i);
            }

            // 更新最大長度
            ans = Math.max(ans, j - i + 1);

            // 更新字元最後出現位置
            map.put(c, j);
        }

        return ans;
    }
}

/* 
解題思路：
這題用滑動視窗（two pointers）：
1. 用 HashMap 紀錄每個字元最後出現的位置。
2. 右指針 j 往右移，遇到重複字元時，更新左指針 i 到「重複字元的下一位」。
3. 每次更新視窗長度 (j - i + 1)，取最大值。
4. 最後回傳最大值即為最長不重複子字串長度。
 */
