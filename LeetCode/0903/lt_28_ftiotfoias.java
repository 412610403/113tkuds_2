
class Solution {

    public int strStr(String haystack, String needle) {
        int m = needle.length();
        // 當 needle 是空字符串时我们應當返回 0
        if (m == 0) {
            return 0;
        }
        int n = haystack.length();
        if (n < m) {
            return -1;
        }
        int i = 0;
        int j = 0;
        while (i < n - m + 1) {
            // 找到首字母相等
            while (i < n && haystack.charAt(i) != needle.charAt(j)) {
                i++;
            }
            if (i == n) {// 沒有首字母相等的
                return -1;
            }
            // 遍歷後續字符，判斷是否相等
            i++;
            j++;
            while (i < n && j < m && haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            }
            if (j == m) {// 找到
                return i - j;
            } else {// 未找到
                i -= j - 1;
                j = 0;
            }
        }
        return -1;
    }
}
/*
解題思路：
基於窗口滑動的算法
<p>
時間複雜度：O(m*n)
空間複雜度：O(1)
註：n為haystack的長度，m為needle的長度
*/
