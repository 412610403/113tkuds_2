
class Solution {

    public int romanToInt(String s) {
        char[] c = s.toCharArray();
        int sum = 0;

        // 特殊情況修正：如果出現減法組合，要先減掉多加的值
        if (s.contains("IV")) {
            sum -= 2;
        }
        if (s.contains("IX")) {
            sum -= 2;
        }
        if (s.contains("XL")) {
            sum -= 20;
        }
        if (s.contains("XC")) {
            sum -= 20;
        }
        if (s.contains("CD")) {
            sum -= 200;
        }
        if (s.contains("CM")) {
            sum -= 200;
        }

        // 正常加總
        for (int i = 0; i <= c.length - 1; i++) {
            if (c[i] == 'I') {
                sum += 1;
            }
            if (c[i] == 'V') {
                sum += 5;
            }
            if (c[i] == 'X') {
                sum += 10;
            }
            if (c[i] == 'L') {
                sum += 50;
            }
            if (c[i] == 'C') {
                sum += 100;
            }
            if (c[i] == 'D') {
                sum += 500;
            }
            if (c[i] == 'M') {
                sum += 1000;
            }
        }
        return sum;
    }
}

/* 解題思路：
   1. 羅馬數字通常由大到小排列，直接相加即可。
   2. 但有些特殊情況（例如 IV = 4, IX = 9），會出現小數字在大數字前。
   3. 為處理這些情況，先掃描字串：
      - 如果有 "IV"，代表原本 I=1, V=5，加總會變 6，但實際應該是 4 → 多加了 2，要減掉。
      - 其他組合 (IX, XL, XC, CD, CM) 類似處理。
   4. 最後再逐字累加對應數字。
 */
