
import java.util.*;

public class BinaryTreeBasicOperations {

    static class TreeNode {

        int val;
        TreeNode left, right;

        TreeNode(int v) {
            val = v;
        }
    }

    static int sum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return root.val + sum(root.left) + sum(root.right);
    }

    static int count(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + count(root.left) + count(root.right);
    }

    static int max(TreeNode r) {
        if (r == null) {
            return Integer.MIN_VALUE;
        }
        return Math.max(r.val, Math.max(max(r.left), max(r.right)));
    }

    static int min(TreeNode r) {
        if (r == null) {
            return Integer.MAX_VALUE;
        }
        return Math.min(r.val, Math.min(min(r.left), min(r.right)));
    }

    static int maxWidth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int max = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            max = Math.max(max, size);
            for (int i = 0; i < size; i++) {
                TreeNode n = q.poll();
                if (n.left != null) {
                    q.add(n.left);
                }
                if (n.right != null) {
                    q.add(n.right);
                }
            }
        }
        return max;
    }

    static boolean isComplete(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        boolean seenNull = false;
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (node == null) {
                seenNull = true; 
            }else {
                if (seenNull) {
                    return false;
                }
                q.add(node.left);
                q.add(node.right);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode r = new TreeNode(5);
        r.left = new TreeNode(3);
        r.right = new TreeNode(8);
        r.left.left = new TreeNode(1);
        r.left.right = new TreeNode(4);

        System.out.println("Sum: " + sum(r));
        System.out.println("Avg: " + (double) sum(r) / count(r));
        System.out.println("Max: " + max(r));
        System.out.println("Min: " + min(r));
        System.out.println("Width: " + maxWidth(r));
        System.out.println("Is Complete: " + isComplete(r));
    }
}
