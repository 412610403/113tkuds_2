
class Solution {

    public void solveSudoku(char[][] board) {
        solveSudokuHelper(board);
    }

    private boolean solveSudokuHelper(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    continue;
                }
                for (char k = '1'; k <= '9'; k++) {
                    if (isValidSudoku(i, j, k, board)) {
                        board[i][j] = k;
                        if (solveSudokuHelper(board)) {
                            return true;
                        }
                        board[i][j] = '.';
                    }
                }
                return false;
            }
        }
        return true;
    }

    private boolean isValidSudoku(int row, int col, char val, char[][] board) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == val) {
                return false;
            }
        }
        for (int j = 0; j < 9; j++) {
            if (board[j][col] == val) {
                return false;
            }
        }
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == val) {
                    return false;
                }
            }
        }
        return true;
    }
}
/*
解題思路：
1. 採用回溯法：
   - 遍歷整個 9x9 棋盤，遇到 '.' 嘗試填入數字 1~9。
   - 每填入一個數字，檢查是否符合數獨規則。
   - 若合法，繼續遞迴處理下一個空格；若不合法，回溯並嘗試下一個數字。
2. isValidSudoku 用來檢查：
   - 該行是否重複。
   - 該列是否重複。
   - 該 3x3 區域是否重複。
3. 若整個棋盤都成功填滿，回傳 true。
4. 時間複雜度較高，但對 9x9 的數獨可接受。
 */
