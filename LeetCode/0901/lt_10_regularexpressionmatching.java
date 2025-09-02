
class Solution {

    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;// dp[i][j] 表示 s 的前 i 個是否能被 p 的前 j 個匹配
        for (int i = 1; i < p.length(); i++) {
            if (p.charAt(i) == '*' && dp[0][i - 1]) {
                dp[0][i + 1] = true;
            }
        }
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {
                if (p.charAt(j) == '.' || p.charAt(j) == s.charAt(i)) {// 如果是任意元素 或者是對於元素匹配
                    dp[i + 1][j + 1] = dp[i][j];
                } else if (p.charAt(j) == '*') {
                    if (p.charAt(j - 1) != s.charAt(i) && p.charAt(j - 1) != '.') {// 如果前一個元素不匹配 且不為任意元素 没有匹配的情況
                        dp[i + 1][j + 1] = dp[i + 1][j - 1];
                    } else {
                        // dp[i + 1][j + 1] = (dp[i + 1][j] || dp[i][j + 1] || dp[i + 1][j - 1]);
                        dp[i + 1][j + 1] = (dp[i][j + 1] || dp[i + 1][j] || dp[i + 1][j - 1]);
                        /*
                         * dp[i][j] = dp[i-1][j] // 多個字符匹配的情況 
                         * or dp[i][j] = dp[i][j-1] // 單個字符匹配的情況 
                         * or dp[i][j] = dp[i][j-2] // 没有匹配的情況（雖然p.charAt(j - 1) == s.charAt(i)但是其實不能匹配，即為零個）
                         */
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }
}
