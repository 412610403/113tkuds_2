
import java.util.*;

class Solution {

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return result;
        }

        // 每個 word 的長度
        int wordLen = words[0].length();
        // words 總共的字串數
        int wordCount = words.length;
        // 建立詞頻表，記錄 words 中每個字的出現次數
        Map<String, Integer> wordMap = new HashMap<>();
        for (String w : words) {
            wordMap.put(w, wordMap.getOrDefault(w, 0) + 1);
        }

        // 因為可能不是從 index=0 對齊，所以要從 [0, wordLen-1] 都試一遍
        for (int i = 0; i < wordLen; i++) {
            int left = i;  // 窗口的左邊界
            int count = 0; // 窗口中已經匹配的單詞數
            Map<String, Integer> seen = new HashMap<>(); // 當前窗口中的詞頻

            // 每次右移一個單詞長度
            for (int right = i; right + wordLen <= s.length(); right += wordLen) {
                String sub = s.substring(right, right + wordLen);

                // 如果 sub 是 words 裡的單詞
                if (wordMap.containsKey(sub)) {
                    seen.put(sub, seen.getOrDefault(sub, 0) + 1);
                    count++;

                    // 如果某個單詞數量超過需要，縮小左邊界
                    while (seen.get(sub) > wordMap.get(sub)) {
                        String leftWord = s.substring(left, left + wordLen);
                        seen.put(leftWord, seen.get(leftWord) - 1);
                        left += wordLen;
                        count--;
                    }

                    // 如果剛好匹配 wordCount 個，表示找到一個合法解
                    if (count == wordCount) {
                        result.add(left);
                        // 繼續縮小左邊界，準備找下一個
                        String leftWord = s.substring(left, left + wordLen);
                        seen.put(leftWord, seen.get(leftWord) - 1);
                        left += wordLen;
                        count--;
                    }
                } else {
                    // sub 不在 words 中，重置窗口
                    seen.clear();
                    count = 0;
                    left = right + wordLen;
                }
            }
        }

        return result;
    }
}

/* 
解題思路：
1. words 中所有字的長度相同，假設每個字長度為 wordLen，總字數為 wordCount。
   那麼每個合法子字串長度 = wordLen * wordCount。
2. 建立一個 HashMap 紀錄 words 中每個單詞出現次數。
3. 因為 s 可能不是從 index=0 開始對齊單詞長度，因此要從 [0, wordLen-1] 開始嘗試。
4. 使用sliding window：
   - 每次向右移動 wordLen 長度，取出子字串 sub。
   - 如果 sub 在 wordMap 中，加入當前窗口 seen。
   - 若 seen 中某個單詞數量超過 wordMap，縮小左邊界直到合法。
   - 當窗口中剛好有 wordCount 個單詞時，找到一個合法解，把左邊界記錄下來。
   - 如果 sub 不在 wordMap 中，清空窗口，從下一個位置繼續。
5. 重複直到遍歷整個 s。

時間複雜度：O(n * wordLen)，n = s 的長度  
空間複雜度：O(m)，m = words 中不同單詞數量
 */
