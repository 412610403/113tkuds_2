
class Solution {

    public int maxArea(int[] height) {
        int N = height.length;
        int left = 0, right = N - 1;  // 兩個指標，分別從左右開始
        int maxArea = 0;              // 記錄最大面積

        while (left < right) {
            // 計算當前容器面積 = 寬度 * 較小的高度
            maxArea = Math.max(maxArea, (right - left) * Math.min(height[left], height[right]));

            // 移動指標 → 矮的那一邊，因為寬度減少，想要更大面積必須換更高的高度
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }
}

/* 解題思路：
   1. 使用「雙指標」分別從左右兩端開始。
   2. 計算面積 = (right - left) * min(height[left], height[right])。
   3. 為了找更大面積，移動矮的那根柱子（因為高度是瓶頸）。
   4. 重複直到左右指標相遇。
   時間複雜度 O(n)，空間複雜度 O(1)。
 */
