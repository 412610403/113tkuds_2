
class Solution {

    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] <= nums[right]) {
                if (target < nums[mid] || target > nums[right]) {
                    right = mid - 1;
                    continue;
                }
            } else if (nums[mid] >= nums[left]) {
                if (target < nums[left] || target > nums[mid]) {
                    left = mid + 1;
                    continue;
                }
            }
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] > target) {
                right = mid - 1; 
            }else {
                left = mid + 1;
            }
        }
        return -1;
    }
}
/*
解題思路：
1. 陣列為旋轉排序陣列，因此至少有一半區間是有序的。
2. 每次二分取 mid，判斷哪一半是有序區間：
   - 如果 nums[mid] <= nums[right]，代表右半區間有序。
   - 如果 nums[mid] >= nums[left]，代表左半區間有序。
3. 判斷 target 是否落在有序區間內：
   - 如果不在，則移動指針去另一半區間。
   - 如果在，則在該區間繼續二分。
4. 如果剛好 nums[mid] == target，直接回傳。
5. 如果整個搜尋完沒找到，回傳 -1。
 */
