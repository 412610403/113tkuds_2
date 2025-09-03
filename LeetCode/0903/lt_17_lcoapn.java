
import java.util.ArrayList;
import java.util.List;

class Solution {

    //設置全局列表儲存最後的结果
    List<String> list = new ArrayList<>();

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return list;
        }
        //初始對應所有的數字，為了直接對應2-9，新增了兩個無效的字符串""
        String[] numString = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        //迭代處理
        backTracking(digits, numString, 0);
        return list;

    }

    //每次迭代獲取一個字符串，所以會涉及大量的字符串拼接，所以這裡選擇更為高效的 StringBuilder
    StringBuilder temp = new StringBuilder();

    //比如digits如果為"23",num 為0，則str表示2對應的 abc
    public void backTracking(String digits, String[] numString, int num) {
        //遍歷全部一次記錄一次得到的字符串
        if (num == digits.length()) {
            list.add(temp.toString());
            return;
        }
        //str 表示當前num對應的字符串
        String str = numString[digits.charAt(num) - '0'];
        for (int i = 0; i < str.length(); i++) {
            temp.append(str.charAt(i));
            //遞迴，處理下一層
            backTracking(digits, numString, num + 1);
            //剔除末尾，繼續嘗試
            temp.deleteCharAt(temp.length() - 1);
        }
    }
}
/* 
解題思路：
題目要求找出數字字串對應到電話鍵盤的所有字母組合。
1. 建立一個數字到字母的映射表 (2->abc, 3->def ... )。
2. 使用回溯 (Backtracking)：
   - 每次選取當前數字對應的字母之一。
   - 遞迴處理下一個數字。
   - 當遍歷到最後一位數字，將目前的字串加入結果集。
3. 使用 StringBuilder 減少字串拼接的開銷。
4. 最後回傳所有組合。

時間複雜度：O(3^n ~ 4^n)，n = digits 長度  
空間複雜度：O(n) (遞迴深度)
*/
