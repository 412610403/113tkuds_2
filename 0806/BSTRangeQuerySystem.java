
import java.util.*;

public class BSTRangeQuerySystem {

    static class TreeNode {

        int val;
        TreeNode left, right;

        TreeNode(int v) {
            val = v;
        }
    }

    static void rangeQuery(TreeNode r, int min, int max, List<Integer> res) {
        if (r == null) {
            return;
        }
        if (r.val > min) {
            rangeQuery(r.left, min, max, res);
        }
        if (r.val >= min && r.val <= max) {
            res.add(r.val);
        }
        if (r.val < max) {
            rangeQuery(r.right, min, max, res);
        }
    }

    static int rangeSum(TreeNode r, int min, int max) {
        if (r == null) {
            return 0;
        }
        int sum = 0;
        if (r.val >= min && r.val <= max) {
            sum += r.val;
        }
        if (r.val > min) {
            sum += rangeSum(r.left, min, max);
        }
        if (r.val < max) {
            sum += rangeSum(r.right, min, max);
        }
        return sum;
    }

    static int closest(TreeNode r, int target) {
        int closest = r.val;
        while (r != null) {
            if (Math.abs(r.val - target) < Math.abs(closest - target)) {
                closest = r.val;
            }
            r = (target < r.val) ? r.left : r.right;
        }
        return closest;
    }

    public static void main(String[] args) {
        TreeNode r = new TreeNode(10);
        r.left = new TreeNode(5);
        r.right = new TreeNode(15);
        r.left.left = new TreeNode(3);
        r.left.right = new TreeNode(7);

        List<Integer> result = new ArrayList<>();
        rangeQuery(r, 4, 12, result);
        System.out.println("Range Query: " + result);
        System.out.println("Range Sum: " + rangeSum(r, 4, 12));
        System.out.println("Closest to 6: " + closest(r, 6));
    }
}
