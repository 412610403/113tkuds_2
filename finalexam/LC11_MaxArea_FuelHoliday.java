
import java.util.*;

public class LC11_MaxArea_FuelHoliday {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] heights = new int[n];
        for (int i = 0; i < n; i++) {
            heights[i] = sc.nextInt();
        }
        System.out.println(maxArea(heights));
    }

    public static int maxArea(int[] height) {
        int l = 0, r = height.length - 1;
        int max = 0;
        while (l < r) {
            int h = Math.min(height[l], height[r]);
            max = Math.max(max, h * (r - l));
            if (height[l] < height[r]) {
                l++;
            } else {
                r--;
            }
        }
        return max;
    }
}

/*解題思路：
使用雙指針從兩端夾逼。
每次計算面積 = (r-l)*min(height[l], height[r])。
唯有移動較短邊才可能找到更大面積。
時間 O(n)，空間 O(1)。
 */
