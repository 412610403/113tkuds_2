
class Solution {

    public int searchInsert(int[] nums, int target) {
        int n = nums.length;
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] > target) {
                high = mid - 1;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return high + 1;
    }
}
/*
解題思路：
1. 使用二分搜尋來找到 target 的位置。
2. 若 target 存在於陣列中，直接回傳其索引。
3. 若不存在，最終跳出迴圈時，low 是第一個大於 target 的位置，high 是最後一個小於 target 的位置。
4. 因此答案就是 high + 1，即 target 應該插入的位置。
 */
