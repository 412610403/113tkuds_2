
class Solution {

    public String countAndSay(int n) {
        String s = "1";
        for (int i = 1; i < n; i++) {
            s = countIdx(s);
        }
        return s;
    }

    public String countIdx(String s) {
        StringBuilder sb = new StringBuilder();
        char c = s.charAt(0);
        int count = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == c) {
                count++;
            } else {
                sb.append(count);
                sb.append(c);
                c = s.charAt(i);
                count = 1;
            }
        }
        sb.append(count);
        sb.append(c);
        return sb.toString();
    }
}
/*
解題思路：
1. 本題是「報數列」問題，第 n 項是由第 n-1 項轉換而來。
2. 規則：連續相同數字用「數字個數 + 數字本身」表示。
   例：1211 → 111221 （1 個 1 → "11"，1 個 2 → "12"，2 個 1 → "21"）。
3. 每次調用 countIdx 對字串進行解析：
   - 用變數 c 記錄當前字符，count 記錄次數。
   - 遍歷字串，遇到相同字符 count++，遇到不同則輸出 count + 字符，並重置。
4. 重複 n-1 次即可得到最終字串。
 */
