
import java.util.ArrayList;
import java.util.List;

class Solution {

    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s; // 特殊情況：只一行
        }
        // 建立每一行的 StringBuilder
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++) {
            rows.add(new StringBuilder());
        }

        int curRow = 0;
        boolean goingDown = false; // 控制方向

        // 將字元逐一放到對應的 row
        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            if (curRow == 0 || curRow == numRows - 1) {
                goingDown = !goingDown; // 遇到頂或底就換方向
            }
            curRow += goingDown ? 1 : -1;
        }

        // 合併所有 row
        StringBuilder ret = new StringBuilder();
        for (StringBuilder row : rows) {
            ret.append(row);
        }
        return ret.toString();
    }
}

/* 
解題思路：
這題模擬 Zigzag 排列：
1. 準備 numRows 個 StringBuilder，分別代表每一行。
2. 從上到下，再從下到上，依序把字元放入對應的行。
3. 遇到頂或底時換方向。
4. 最後把所有行合併輸出。
 */
