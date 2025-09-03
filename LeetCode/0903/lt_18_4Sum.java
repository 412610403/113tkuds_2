
import java.util.*;

class Solution {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);  // 排序數组
        List<List<Integer>> result = new ArrayList<>();  // 结果集
        for (int k = 0; k < nums.length; k++) {
            // 剪枝處理
            if (nums[k] > target && nums[k] >= 0) {
                break;	// 此處的break可以等價於return result;
            }
            // 對nums[k]去重
            if (k > 0 && nums[k] == nums[k - 1]) {
                continue;
            }
            for (int i = k + 1; i < nums.length; i++) {
                // 第二級剪枝
                if (nums[k] + nums[i] > target && nums[k] + nums[i] >= 0) {
                    break;	// 注意是break到上一級for循環，如果直接return result;會有遺漏
                }
                // 對nums[i]去重
                if (i > k + 1 && nums[i] == nums[i - 1]) {
                    continue;
                }
                int left = i + 1;
                int right = nums.length - 1;
                while (right > left) {
                    long sum = (long) nums[k] + nums[i] + nums[left] + nums[right];
                    if (sum > target) {
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        result.add(Arrays.asList(nums[k], nums[i], nums[left], nums[right]));
                        // 對nums[left]和nums[right]去重
                        while (right > left && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        while (right > left && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        right--;
                        left++;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1, 0, -1, 0, -2, 2};
        int target = 0;
        List<List<Integer>> results = solution.fourSum(nums, target);
        for (List<Integer> result : results) {
            System.out.println(result);
        }
    }
}
/* 
解題思路：
題目要求找出陣列 nums 中所有不重複的四元組，使其總和等於 target。
1. 先排序，方便使用雙指針。
2. 外層兩層迴圈 (k, i) 固定前兩個數，內層用 left, right 雙指針找後兩個數。
3. 若總和 > target，右指針左移；若總和 < target，左指針右移；若等於 target，加入答案。
4. 為避免重複，對 nums[k], nums[i], nums[left], nums[right] 做去重處理。
5. 使用 long 型別存 sum，避免整數溢出。
6. 最後回傳所有結果。

時間複雜度：O(n^3)  
空間複雜度：O(1) (不含輸出結果)
*/
