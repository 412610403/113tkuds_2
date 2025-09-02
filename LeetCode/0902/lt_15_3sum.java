
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int len = nums.length;

        // 長度不足 3，必定無解
        if (len < 3) {
            return result;
        }

        Arrays.sort(nums); // 排序，方便使用雙指針
        for (int i = 0; i < len - 2; i++) {
            if (nums[i] > 0) { // 若最小的數都大於 0，三數和不可能為 0
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) { // 跳過重複數字
                continue;
            }
            int L = i + 1, R = len - 1;
            while (L < R) {
                int sum = nums[i] + nums[L] + nums[R];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[L], nums[R]));
                    while (L < R && nums[L] == nums[L + 1]) {
                        L++; // 跳過重複

                    }
                    while (L < R && nums[R] == nums[R - 1]) {
                        R--; // 跳過重複

                    }
                    L++;
                    R--;
                } else if (sum > 0) {
                    R--;
                } else {
                    L++;
                }
            }
        }
        return result;
    }
}

/*
解題思路：
1. 先將陣列排序，方便使用雙指針。
2. 固定一個數 nums[i]，然後在 i 右邊用雙指針 L、R 去找另外兩個數。
3. 如果三數和 = 0，記錄答案，並跳過重複的數。
4. 如果和 > 0，右指針左移 (縮小)；如果和 < 0，左指針右移 (增大)。
5. 最後回傳所有找到的不重複解。
 */
