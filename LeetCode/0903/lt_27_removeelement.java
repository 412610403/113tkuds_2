
class Solution {

    public int removeElement(int[] nums, int val) {
        int left = 0;
        int right = nums.length - 1;
        while (right >= 0 && nums[right] == val) {
            right--; //將right移到從右數第一個值不為val的位置

        }
        while (left <= right) {
            if (nums[left] == val) { //left位置的元素需要移除
                //將right位置的元素移到left（覆蓋），right位置移除
                nums[left] = nums[right];
                right--;
            }
            left++;
            while (right >= 0 && nums[right] == val) {
                right--;
            }
        }
        return left;
    }
}
/*
解題思路：
1. 本題要求「原地刪除數組中等於 val 的元素」，返回新數組的長度。
2. 使用雙指針：
   - left 從左往右掃描；
   - right 從右往左縮小，始終指向一個不等於 val 的元素。
3. 遍歷過程：
   - 當 nums[left] == val 時，將 nums[right] 覆蓋到 nums[left]，並讓 right 左移；
   - 否則 left 直接右移。
   - 同時不斷調整 right，確保它停在一個不等於 val 的位置。
4. 當 left > right 時，處理結束，返回 left 作為新長度。
時間複雜度 O(N)，空間複雜度 O(1)。
*/
