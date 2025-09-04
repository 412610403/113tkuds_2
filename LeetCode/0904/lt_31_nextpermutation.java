
import java.util.Arrays;

class Solution {

    public void nextPermutation(int[] nums) {
        for (int i = nums.length - 1; i >= 0; i--) {
            for (int j = nums.length - 1; j > i; j--) {
                if (nums[j] > nums[i]) {
                    // 交換
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                    // [i + 1, nums.length) 内元素升序排序
                    Arrays.sort(nums, i + 1, nums.length);
                    return;
                }
            }
        }
        Arrays.sort(nums); // 不存在下一個更大的排列，則將數字重新排列成最小的排列（即升序排列）。
    }
}
/*
解題思路：
1. 從後往前找，找到第一個「nums[i] < nums[j]」的位置，這代表 nums[i] 需要換成更大的數字，才能生成更大的排列。
2. 將 nums[i] 與右邊比它大的最小數字交換。
3. 為了確保得到「下一個」排列，交換後要把 i 右邊的數字升序排序。
4. 如果整個數組都是降序排列，表示已經是最大排列，直接排序成升序即為最小排列。
 */
