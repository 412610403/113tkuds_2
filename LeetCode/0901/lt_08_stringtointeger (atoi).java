
class Solution {

    public int myAtoi(String s) {
        int i = 0, n = s.length();
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }

        // 如果整個字串都是空格 → 回傳 0
        if (i == n) {
            return 0;
        }

        // 處理正負號
        int sign = 1;
        if (s.charAt(i) == '+' || s.charAt(i) == '-') {
            sign = (s.charAt(i) == '-') ? -1 : 1;
            i++;
        }

        // 開始讀數字
        long num = 0; // 用 long 暫存，避免中途溢位
        while (i < n && Character.isDigit(s.charAt(i))) {
            int digit = s.charAt(i) - '0';
            num = num * 10 + digit;

            // 溢位處理
            if (sign == 1 && num > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            if (sign == -1 && -num < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
            i++;
        }
        return (int) (sign * num);
    }
}
/* 
解題思路：
1. 先跳過字串開頭的空格。
2. 判斷是否有正負號，記錄 sign。
3. 逐位讀取數字，累積到 num。
4. 使用 long 暫存，避免在累積過程中溢位。
5. 若 num 超過 int 範圍，立即回傳邊界值 (Integer.MAX_VALUE 或 Integer.MIN_VALUE)。
6. 最後回傳 sign * num。
 */
