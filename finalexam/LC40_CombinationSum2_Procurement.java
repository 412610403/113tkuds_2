
import java.util.*;

public class LC40_CombinationSum2_Procurement {

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(candidates, target, 0, new ArrayList<>(), res);
        return res;
    }

    private static void backtrack(int[] candidates, int remain, int start, List<Integer> path, List<List<Integer>> res) {
        if (remain == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue; // 同層去重

            }
            if (candidates[i] > remain) {
                break;
            }
            path.add(candidates[i]);
            backtrack(candidates, remain - candidates[i], i + 1, path, res); // i+1 → 每個數只能用一次
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int n = sc.nextInt(), target = sc.nextInt();
            int[] candidates = new int[n];
            for (int i = 0; i < n; i++) {
                candidates[i] = sc.nextInt();
            }
            List<List<Integer>> res = combinationSum2(candidates, target);
            for (List<Integer> list : res) {
                for (int x : list) {
                    System.out.print(x + " ");
                }
                System.out.println();
            }
            sc.close();
        }

    }
}
