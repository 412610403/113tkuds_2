
class Solution {

    public boolean isPalindrome(int x) {
        String k = String.valueOf(x);

        for (int i = 0; i < k.length() / 2; i++) {
            if (k.charAt(i) != k.charAt(k.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }
}
/*
解題思路：
1.轉字串：先把整數 x 轉成字串，方便比較。
2.雙指針比較：一個指針從字串頭（左邊）開始，另一個指針從字串尾（右邊）開始，逐一比較字元，如果有不同就回傳false。
3.檢查：如果全部都一樣 → 回傳 true。如果中途發現不同 → 回傳 false。
*/
