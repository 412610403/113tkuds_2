
public class TreeMirrorAndSymmetry {

    static class TreeNode {

        int val;
        TreeNode left, right;

        TreeNode(int v) {
            val = v;
        }
    }

    static boolean isSymmetric(TreeNode root) {
        return root == null || isMirror(root.left, root.right);
    }

    static boolean isMirror(TreeNode a, TreeNode b) {
        if (a == null || b == null) {
            return a == b;
        }
        return a.val == b.val && isMirror(a.left, b.right) && isMirror(a.right, b.left);
    }

    static TreeNode mirror(TreeNode r) {
        if (r == null) {
            return null;
        }
        TreeNode left = mirror(r.left);
        TreeNode right = mirror(r.right);
        r.left = right;
        r.right = left;
        return r;
    }

    static boolean isSubtree(TreeNode root, TreeNode sub) {
        if (root == null) {
            return false;
        }
        if (isSame(root, sub)) {
            return true;
        }
        return isSubtree(root.left, sub) || isSubtree(root.right, sub);
    }

    static boolean isSame(TreeNode a, TreeNode b) {
        if (a == null || b == null) {
            return a == b;
        }
        return a.val == b.val && isSame(a.left, b.left) && isSame(a.right, b.right);
    }

    public static void main(String[] args) {
        TreeNode r = new TreeNode(1);
        r.left = new TreeNode(2);
        r.right = new TreeNode(2);
        r.left.left = new TreeNode(3);
        r.right.right = new TreeNode(3);

        System.out.println("Is Symmetric: " + isSymmetric(r));
        mirror(r);
        System.out.println("After mirror, root left: " + (r.left != null ? r.left.val : null));
    }
}
