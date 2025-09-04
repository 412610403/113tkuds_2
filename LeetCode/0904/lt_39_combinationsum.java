
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

class Solution {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        backtracking(res, new ArrayList<>(), candidates, target, 0, 0);
        return res;
    }

    public void backtracking(List<List<Integer>> res, List<Integer> path, int[] candidates, int target, int sum, int idx) {
        if (sum == target) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = idx; i < candidates.length; i++) {
            if (sum + candidates[i] > target) {
                break;
            }
            path.add(candidates[i]);
            backtracking(res, path, candidates, target, sum + candidates[i], i); // 可以重複選同一元素
            path.remove(path.size() - 1);
        }
    }
}
/*
解題思路：
1. 使用回溯法求所有組合。
2. 每次從 idx 開始遍歷，避免重複組合。
3. sum 表示當前和：
   - 若 sum == target，將 path 加入結果。
   - 若 sum > target，直接剪枝。
4. 允許重複使用同一數字，因此遞迴時傳入 i（而不是 i+1）。
5. 排序後可提前停止循環，提升效率。
 */
