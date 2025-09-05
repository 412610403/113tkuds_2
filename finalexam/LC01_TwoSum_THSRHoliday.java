
import java.util.*;

public class LC01_TwoSum_THSRHoliday {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), target = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            if (map.containsKey(x)) {
                System.out.println(map.get(x) + " " + i);
                return;
            }
            map.put(target - x, i);
        }
        System.out.println("-1 -1");
    }
}

/*解題思路：
一次遍歷陣列，使用 HashMap 儲存「需要的數 → 索引」。
當掃描到的數存在於 map，即代表已找到另一個班次可湊成 target。
時間 O(n)，空間 O(n)。
 */
