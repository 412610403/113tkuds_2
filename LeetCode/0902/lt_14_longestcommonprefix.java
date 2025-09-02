
class Solution {

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return ""; // 如果輸入是空的，直接回傳空字串
        }

        String prefix = strs[0]; // 先假設第一個字串是共同前綴

        // 從第二個字串開始，逐一比對
        for (int i = 1; i < strs.length; i++) {
            // 如果當前字串不是以 prefix 開頭，就逐步縮短 prefix
            while (!strs[i].startsWith(prefix)) {
                prefix = prefix.substring(0, prefix.length() - 1); // 去掉最後一個字元
                if (prefix.isEmpty()) {
                    return ""; // 如果縮短到空字串，直接回傳
                }
            }
        }

        return prefix; // 最後剩下的就是最長共同前綴
    }
}
/*
解題思路：
1. 先假設第一個字串是共同前綴。
2. 拿這個前綴去跟後續字串比對：
   - 如果當前字串不是以 prefix 開頭，就把 prefix 往後縮短一個字元，再比一次。
3. 如果縮到空字串，表示沒有共同前綴，直接回傳 ""。
4. 最後比完所有字串後，剩下的 prefix 就是最長共同前綴。
 */
