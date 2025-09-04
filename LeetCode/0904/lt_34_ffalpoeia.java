
class Solution {

    public int[] searchRange(int[] nums, int target) {
        int index = binarySearch(nums, target); // 二分查找

        if (index == -1) { // nums 中不存在 target，直接返回 {-1, -1}
            return new int[]{-1, -1}; // 匿名數組
        }
        // nums 中存在 target，則左右滑動指針，來找到符合題意的區間
        int left = index;
        int right = index;
        // 向左滑動，找左邊界
        while (left - 1 >= 0 && nums[left - 1] == nums[index]) { // 防止數組越界。邏輯短路，兩個條件順序不能换
            left--;
        }
        // 向右滑動，找右邊界
        while (right + 1 < nums.length && nums[right + 1] == nums[index]) { // 防止數组越界。
            right++;
        }
        return new int[]{left, right};
    }

    /**
     * 二分查找
     *
     * @param nums
     * @param target
     */
    public int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1; // 不變量：左閉右閉區間

        while (left <= right) { // 不變量：左閉右閉區間
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1; // 不變量：左閉右閉區間
            }
        }
        return -1; // 不存在
    }
}
/*
解題思路：
 1、首先，在 nums 數組中二分查找 target；
 2、如果二分查找失敗，則 binarySearch 返回 -1，表明 nums 中沒有 target。此時，searchRange 直接返回 {-1, -1}；
3、如果二分查找成功，則 binarySearch 返回 nums 中值為 target 的一個下標。然後，通過左右滑動指針，來找到符合題意的區間
*/
