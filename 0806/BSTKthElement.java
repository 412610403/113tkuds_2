
import java.util.*;

public class BSTKthElement {

    static class TreeNode {

        int val;
        TreeNode left, right;

        TreeNode(int v) {
            val = v;
        }
    }

    static void inorder(TreeNode r, List<Integer> list) {
        if (r == null) {
            return;
        }
        inorder(r.left, list);
        list.add(r.val);
        inorder(r.right, list);
    }

    static int kthSmallest(TreeNode r, int k) {
        List<Integer> list = new ArrayList<>();
        inorder(r, list);
        return list.get(k - 1);
    }

    static int kthLargest(TreeNode r, int k) {
        List<Integer> list = new ArrayList<>();
        inorder(r, list);
        return list.get(list.size() - k);
    }

    static List<Integer> rangeKtoJ(TreeNode r, int k, int j) {
        List<Integer> list = new ArrayList<>();
        inorder(r, list);
        return list.subList(k - 1, j);
    }

    public static void main(String[] args) {
        TreeNode r = new TreeNode(8);
        r.left = new TreeNode(4);
        r.right = new TreeNode(10);
        r.left.left = new TreeNode(2);
        r.left.right = new TreeNode(6);

        System.out.println("Kth Smallest: " + kthSmallest(r, 3)); // 6
        System.out.println("Kth Largest: " + kthLargest(r, 2));   // 8
        System.out.println("Range 2~4: " + rangeKtoJ(r, 2, 4));    // [4,6,8]
    }
}
