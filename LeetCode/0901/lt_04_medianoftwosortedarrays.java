
class Solution {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        // 確保 nums1 是比較短的陣列，方便二分搜尋
        if (m > n) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
            int tmp = m;
            m = n;
            n = tmp;
        }

        int imin = 0, imax = m, halflen = (m + n + 1) / 2;

        // 二分搜尋 nums1
        while (imin <= imax) {
            int i = (imin + imax) / 2;
            int j = halflen - i;

            // i 太大，需要縮小
            if (i > imin && nums1[i - 1] > nums2[j]) {
                imax = i - 1;
            } // i 太小，需要增大
            else if (i < imax && nums2[j - 1] > nums1[i]) {
                imin = i + 1;
            } // i 剛剛好
            else {
                int maxLeft;
                if (i == 0) {
                    maxLeft = nums2[j - 1];
                } else if (j == 0) {
                    maxLeft = nums1[i - 1];
                } else {
                    maxLeft = Math.max(nums1[i - 1], nums2[j - 1]);
                }

                // 總長度為奇數，直接回傳左半邊最大值
                if ((m + n) % 2 == 1) {
                    return maxLeft;
                }

                int minRight;
                if (i == m) {
                    minRight = nums2[j];
                } else if (j == n) {
                    minRight = nums1[i];
                } else {
                    minRight = Math.min(nums1[i], nums2[j]);
                }

                // 總長度為偶數，取左右兩邊的平均值
                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }
}

/*
解題思路：
這題用「二分搜尋」來找到切割點：
1. 確保 nums1 是較短的陣列，減少搜尋範圍。
2. 在 nums1 上用二分搜尋，找到一個切點 i，對應 nums2 的切點 j。
3. 確保左半邊 <= 右半邊，否則調整 i。
4. 分兩種情況：
      - (m+n) 奇數：中位數是左半邊最大值。
      - (m+n) 偶數：中位數是 (左半邊最大 + 右半邊最小) / 2。
 */
