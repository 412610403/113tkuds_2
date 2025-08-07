
import java.util.*;

public class LevelOrderTraversalVariations {

    static class TreeNode {

        int val;
        TreeNode left, right;

        TreeNode(int v) {
            val = v;
        }
    }

    static List<List<Integer>> levelOrder(TreeNode r) {
        List<List<Integer>> res = new ArrayList<>();
        if (r == null) {
            return res;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(r);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode n = q.poll();
                level.add(n.val);
                if (n.left != null) {
                    q.add(n.left);
                }
                if (n.right != null) {
                    q.add(n.right);
                }
            }
            res.add(level);
        }
        return res;
    }

    static List<List<Integer>> zigzagLevelOrder(TreeNode r) {
        List<List<Integer>> res = new ArrayList<>();
        if (r == null) {
            return res;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(r);
        boolean leftToRight = true;
        while (!q.isEmpty()) {
            int size = q.size();
            LinkedList<Integer> level = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode n = q.poll();
                if (leftToRight) {
                    level.addLast(n.val); 
                }else {
                    level.addFirst(n.val);
                }
                if (n.left != null) {
                    q.add(n.left);
                }
                if (n.right != null) {
                    q.add(n.right);
                }
            }
            res.add(level);
            leftToRight = !leftToRight;
        }
        return res;
    }

    static List<Integer> largestValues(TreeNode r) {
        List<Integer> res = new ArrayList<>();
        if (r == null) {
            return res;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(r);
        while (!q.isEmpty()) {
            int size = q.size();
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode n = q.poll();
                max = Math.max(max, n.val);
                if (n.left != null) {
                    q.add(n.left);
                }
                if (n.right != null) {
                    q.add(n.right);
                }
            }
            res.add(max);
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode r = new TreeNode(1);
        r.left = new TreeNode(2);
        r.right = new TreeNode(3);
        r.left.left = new TreeNode(4);
        r.right.right = new TreeNode(5);

        System.out.println("Level Order: " + levelOrder(r));
        System.out.println("Zigzag Order: " + zigzagLevelOrder(r));
        System.out.println("Largest Values: " + largestValues(r));
    }
}
