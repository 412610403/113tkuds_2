
import java.util.LinkedList;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

class Solution {

    LinkedList<Integer> path = new LinkedList<>();
    List<List<Integer>> ans = new ArrayList<>();
    boolean[] used;
    int sum = 0;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        used = new boolean[candidates.length];
        Arrays.fill(used, false);
        Arrays.sort(candidates);
        backTracking(candidates, target, 0);
        return ans;
    }

    private void backTracking(int[] candidates, int target, int startIndex) {
        if (sum == target) {
            ans.add(new ArrayList(path));
        }
        for (int i = startIndex; i < candidates.length; i++) {
            if (sum + candidates[i] > target) {
                break;
            }
            if (i > 0 && candidates[i] == candidates[i - 1] && !used[i - 1]) {
                continue;
            }
            used[i] = true;
            sum += candidates[i];
            path.add(candidates[i]);
            backTracking(candidates, target, i + 1); // 每個數字只能用一次
            used[i] = false;
            sum -= candidates[i];
            path.removeLast();
        }
    }
}
/*
解題思路：
1. 本題與 39 類似，但「每個數字只能使用一次」，且數組可能有重複元素。
2. 採用回溯法，差異點：
   - 遞迴時傳 i+1，保證數字不重複使用。
   - 排序後，若 candidates[i] == candidates[i-1] 且上一個未使用，說明當前是同層重複，直接跳過。
3. sum 剪枝：若 sum + candidates[i] > target，直接 break。
4. 使用 used 陣列來判斷「同層是否已經選過相同數字」。
5. 回溯結束後，收集所有合法組合。
 */
