
import java.util.*;

public class LC15_3Sum_THSRStops {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        List<List<Integer>> ans = threeSum(nums);
        for (List<Integer> triplet : ans) {
            System.out.println(triplet.get(0) + " " + triplet.get(1) + " " + triplet.get(2));
        }
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue; // 跳過重複

                        }if (nums[i] > 0) {
                break; // 提前結束

                        }int l = i + 1, r = n - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    while (l < r && nums[l] == nums[l + 1]) {
                        l++;
                    }
                    while (l < r && nums[r] == nums[r - 1]) {
                        r--;
                    }
                    l++;
                    r--;
                } else if (sum < 0) {
                    l++;
                } else {
                    r--;
                }
            }
        }
        return res;
    }
}

/*解題思路：
先排序。固定 i，剩餘用雙指針 l,r 搜尋 target=-nums[i]。
若和為 0 加入結果，並移動指針同時跳過重複。
若和<0 則 l++，若和>0 則 r--。
時間 O(n^2)，空間 O(1)（不含輸出）。
 */
