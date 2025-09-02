
class Solution {

    public String intToRoman(int num) {
        StringBuilder result = new StringBuilder();

        // 處理千位數 (1000 = M)
        int count = num / 1000;
        for (int i = 0; i < count; i++) {
            result.append("M");
        }

        // 處理百位數
        num = num % 1000;
        int numsOfHundred = num / 100;
        result.append(getResult(numsOfHundred, "C", "D", "M"));

        // 處理十位數
        num = num % 100;
        int numsOfTen = num / 10;
        result.append(getResult(numsOfTen, "X", "L", "C"));

        // 處理個位數
        int digits = num % 10;
        result.append(getResult(digits, "I", "V", "X"));

        return result.toString();
    }

    // 根據個位數生成對應的羅馬數字
    String getResult(int digits, String one, String five, String ten) {
        String result = switch (digits) {
            case 0 ->
                "";
            case 1 ->
                one;
            case 2 ->
                one + one;
            case 3 ->
                one + one + one;
            case 4 ->
                one + five;
            case 5 ->
                five;
            case 6 ->
                five + one;
            case 7 ->
                five + one + one;
            case 8 ->
                five + one + one + one;
            case 9 ->
                one + ten;
            default ->
                "";
        };
        return result;
    }
}

/* 解題思路：
   1. 羅馬數字由千位、百位、十位、個位分別組成。
   2. 先處理千位，然後依次處理百位、十位、個位。
   3. 每一位數利用 getResult() 轉換成對應的羅馬數字：
      - 1~3 → 重複符號
      - 4 → 特殊寫法 (one + five)
      - 5~8 → five 開頭 + (one 重複)
      - 9 → 特殊寫法 (one + ten)
   4. 拼接起來就是答案。
 */
