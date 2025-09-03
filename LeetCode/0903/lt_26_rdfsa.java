
class Solution {

    public int removeDuplicates(int[] nums) {
        int tmp = nums[0];
        int cnt = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > tmp) {
                tmp = nums[i];
                nums[cnt] = tmp;
                cnt++;
            }
        }

        return cnt;
    }
}
/*
解題思路：
要返回整數 k 以及變更 nums 陣列
*/
