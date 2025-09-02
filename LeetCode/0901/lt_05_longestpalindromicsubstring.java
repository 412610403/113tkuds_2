
class Solution {

    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }

        int start = 0, end = 0;

        // 嘗試每個字元作為回文中心
        for (int i = 0; i < s.length(); i++) {
            int len1 = judge(s, i, i);     // 以單字元為中心（奇數長度回文）
            int len2 = judge(s, i, i + 1); // 以兩字元為中心（偶數長度回文）
            int len = Math.max(len1, len2);

            // 更新最長回文的起點與終點
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }

        // 回傳最長回文子字串
        return s.substring(start, end + 1);
    }

    // 向外擴展，判斷從中心 (left, right) 能形成多長的回文
    private int judge(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1; // 回文長度
    }
}

/* 
解題思路：
這題用「中心擴展法」：
1. 回文字串的中心可能是一個字元（奇數）或兩個字元（偶數）。
2. 對每個字元，都嘗試往左右擴展，計算能延伸的最大回文長度。
3. 更新最長回文的起點與終點。
4. 最後回傳對應子字串。
 */
