
import java.util.HashSet;

class Solution {

    public boolean isValidSudoku(char[][] board) {
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < 9; i++) {              // 遍歷每一行
            for (int j = 0; j < 9; j++) {          // 遍歷每一列
                if (board[i][j] == '.') {         // 遇到空格直接跳過
                    continue;
                }
                // 將數字轉為字串形式，方便組合成唯一 key
                String num_str = "(" + board[i][j] + ")";
                // 同一行是否已經出現過該數字
                String row_str = i + num_str;
                // 同一列是否已經出現過該數字
                String col_str = num_str + j;
                // 同一個 3x3 區塊是否已經出現過該數字
                String sub_str = (i / 3) + num_str + (j / 3);

                // 如果任意一個 key 已經存在，說明數字重複，返回 false
                if (!set.add(row_str) || !set.add(col_str) || !set.add(sub_str)) {
                    return false;
                }
            }
        }
        return true;  // 沒有重複，數獨有效
    }
}
/*
解題思路：
1. 數獨規則：每一行、每一列、每個 3x3 宮格內不能有重複數字。
2. 使用 HashSet 來記錄數字是否出現過，將「行、列、區塊」的狀態編碼成字串作為唯一標記。
3. 遍歷棋盤：
   - 若格子為 '.' 則跳過。
   - 生成三個 key：row、col、subgrid。
   - 嘗試加入 set，如果有重複，立即返回 false。
4. 遍歷完成沒有衝突，返回 true。
 */
