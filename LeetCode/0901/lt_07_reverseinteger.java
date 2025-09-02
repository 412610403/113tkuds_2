
class Solution {

    public int reverse(int x) {
        long rev = 0;  // 使用 long 避免中途溢位
        while (x != 0) {
            int pop = x % 10;   // 取出最後一位數字
            x /= 10;            // 去掉最後一位
            rev = rev * 10 + pop; // 將數字加到反轉後的位置
        }
        // 檢查是否超過 int 範圍
        if (rev > Integer.MAX_VALUE || rev < Integer.MIN_VALUE) {
            return 0;
        }
        return (int) rev; // 轉回 int
    }
}

/* 
解題思路：
1. 利用 % 取出 x 的個位數，然後用 / 去掉最後一位數。
2. 將取出的數字加到 rev 的末尾（rev * 10 + pop）。
3. 反覆直到 x 為 0。
4. 由於題目規定要在 int 範圍內，如果超過範圍就回傳 0。
 */
