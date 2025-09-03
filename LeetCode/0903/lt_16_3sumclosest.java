
import java.util.Arrays;

class Solution {

    public int threeSumClosest(int[] nums, int target) {
        int minans = Integer.MAX_VALUE;
        int ans = 0;
        int len = nums.length;
        Arrays.sort(nums);// 對數组進行排序
        for (int a = 0; a < len; a++) {
            int c = len - 1;
            int leave = target - nums[a];
            for (int b = a + 1; b < len; b++) {
                while (b < c && nums[b] + nums[c] > leave) {
                    if (Math.abs(target - nums[a] - nums[b] - nums[c]) < minans) {
                        minans = Math.abs(target - nums[a] - nums[b] - nums[c]);
                        ans = nums[a] + nums[b] + nums[c];
                    }
                    c--;
                }

                if (b == c) {
                    break;
                }

                if (Math.abs(target - nums[a] - nums[b] - nums[c]) < minans) {
                    minans = Math.abs(target - nums[a] - nums[b] - nums[c]);
                    ans = nums[a] + nums[b] + nums[c];
                }
            }
        }
        return ans;
    }
}
/* 
解題思路：
題目要求找出 nums 中三個數，使其總和最接近 target。
1. 先對陣列排序，方便使用雙指針。
2. 固定第一個數 nums[a]，剩下用雙指針 b, c 找另外兩個數。
3. 若 nums[b] + nums[c] 偏大，就右指針左移；反之則左指針右移。
4. 每次比較當前三數和與 target 的距離，更新最小差值。
5. 最後回傳最接近的總和。

時間複雜度：O(n^2)  
空間複雜度：O(1)
 */
