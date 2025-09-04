
import java.util.HashMap;
import java.util.Map;

class Solution {

    public int[] twoSum(int[] nums, int target) {
        // 建立一個 HashMap，用來存「數值 -> 索引」
        Map<Integer, Integer> map = new HashMap<>();

        // 遍歷陣列
        for (int i = 0; i < nums.length; i++) {
            int temp = target - nums[i]; // 計算還需要的另一個數

            // 如果 map 中已經存在這個數，代表找到答案
            if (map.containsKey(temp)) {
                return new int[]{map.get(temp), i};
            }

            // 否則，把目前數值和索引存進 map
            map.put(nums[i], i);
        }

        // 題目保證一定有答案，所以這裡理論上不會執行到
        return null;
    }
}

/* 
解題思路：
題目要求找出兩個數字，使它們的和等於 target
1. 用 HashMap 記錄「數值 -> 索引」。
2. 遍歷陣列，每遇到一個數 nums[i]，先算出 target - nums[i]。
3. 檢查這個數是否已經在 HashMap 裡出現過：
      - 如果有，代表找到一組解，回傳兩個索引。
      - 如果沒有，就把 nums[i] 存進 HashMap，等待之後的數字來匹配。
   這樣只需要遍歷一次陣列，時間複雜度 O(n)，空間複雜度 O(n)。
 */
