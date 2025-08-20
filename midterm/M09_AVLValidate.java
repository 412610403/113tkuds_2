
import java.util.*;

public class M09_AVLValidate {

    static class TreeNode {

        int val;
        TreeNode left, right;

        TreeNode(int v) {
            this.val = v;
        }
    }

    static TreeNode buildTree(int[] arr) {
        if (arr.length == 0 || arr[0] == -1) {
            return null;
        }

        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        int i = 1;
        while (i < arr.length) {
            TreeNode cur = q.poll();
            if (cur == null) {
                continue;
            }

            if (i < arr.length && arr[i] != -1) {
                cur.left = new TreeNode(arr[i]);
                q.offer(cur.left);
            }
            i++;

            if (i < arr.length && arr[i] != -1) {
                cur.right = new TreeNode(arr[i]);
                q.offer(cur.right);
            }
            i++;
        }
        return root;
    }

    static boolean isBST(TreeNode root, long min, long max) {
        if (root == null) {
            return true;
        }
        if (root.val <= min || root.val >= max) {
            return false;
        }
        return isBST(root.left, min, root.val) && isBST(root.right, root.val, max);
    }

    static int checkAVL(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftH = checkAVL(root.left);
        if (leftH == -1) {
            return -1;
        }

        int rightH = checkAVL(root.right);
        if (rightH == -1) {
            return -1;
        }

        if (Math.abs(leftH - rightH) > 1) {
            return -1;
        }

        return Math.max(leftH, rightH) + 1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        TreeNode root = buildTree(arr);

        if (!isBST(root, Long.MIN_VALUE, Long.MAX_VALUE)) {
            System.out.println("Invalid BST");
            return;
        }

        if (checkAVL(root) == -1) {
            System.out.println("Invalid AVL");
            return;
        }

        System.out.println("Valid");
    }
}

/*
 Time Complexity：O(n)
 說明：建樹 O(n)，檢查 BST O(n)，檢查 AVL O(n)，
 整體仍為 O(n)，每個節點最多訪問常數次。
 */
