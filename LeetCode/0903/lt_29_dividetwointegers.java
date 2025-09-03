
class Solution {

    public int divide(int dividend, int divisor) {
        if (dividend == 0) {
            return 0;
        }
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        long t = Math.abs((long) dividend);
        long d = Math.abs((long) divisor);
        int result = 0;

        for (int i = 31; i >= 0; i--) {
            //找出足夠大的數2^n*divisor
            if ((t >> i) >= d) {
                //將结果加上2^n
                result += 1 << i;
                //將被除數減去2^n*divisor
                t -= d << i;
            }
        }

        //符號相異取反
        return (dividend ^ divisor) < 0 ? -result : result;
    }
}
/*
解題思路：
1. 不能使用乘法、除法和 mod，因此使用「位運算」來模擬除法。
2. 將被除數 dividend 和除數 divisor 轉為 long 並取絕對值，避免溢出。
3. 從高位到低位（31 → 0）遍歷：
   - 判斷 d << i 是否 <= t（等價於 t >> i >= d）；
   - 如果成立，則說明結果至少包含 2^i，累加到 result；
   - 並從 t 中減去對應的數值。
4. 最後根據 dividend 與 divisor 的符號決定正負。
5. 特殊處理：當 dividend = Integer.MIN_VALUE 且 divisor = -1 時，結果超出 int 範圍，直接返回 Integer.MAX_VALUE。
時間複雜度 O(logN)，空間複雜度 O(1)。
*/
