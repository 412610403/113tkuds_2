
import java.util.*;

class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

}

public class TreeReconstruction {

    public TreeNode buildTreeFromPreorderInorder(int[] preorder, int[] inorder) {
        if (preorder.length == 0 || inorder.length == 0) {
            return null;
        }

        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        return buildFromPreorderInorder(preorder, 0, preorder.length - 1,
                inorder, 0, inorder.length - 1, inorderMap);
    }

    private TreeNode buildFromPreorderInorder(int[] preorder, int preStart, int preEnd,
            int[] inorder, int inStart, int inEnd,
            Map<Integer, Integer> inorderMap) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }

        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);

        int rootIndex = inorderMap.get(rootVal);
        int leftSize = rootIndex - inStart;

        root.left = buildFromPreorderInorder(preorder, preStart + 1, preStart + leftSize,
                inorder, inStart, rootIndex - 1, inorderMap);
        root.right = buildFromPreorderInorder(preorder, preStart + leftSize + 1, preEnd,
                inorder, rootIndex + 1, inEnd, inorderMap);

        return root;
    }

    public TreeNode buildTreeFromPostorderInorder(int[] postorder, int[] inorder) {
        if (postorder.length == 0 || inorder.length == 0) {
            return null;
        }

        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        return buildFromPostorderInorder(postorder, 0, postorder.length - 1,
                inorder, 0, inorder.length - 1, inorderMap);
    }

    private TreeNode buildFromPostorderInorder(int[] postorder, int postStart, int postEnd,
            int[] inorder, int inStart, int inEnd,
            Map<Integer, Integer> inorderMap) {
        if (postStart > postEnd || inStart > inEnd) {
            return null;
        }

        int rootVal = postorder[postEnd];
        TreeNode root = new TreeNode(rootVal);

        int rootIndex = inorderMap.get(rootVal);
        int leftSize = rootIndex - inStart;

        root.left = buildFromPostorderInorder(postorder, postStart, postStart + leftSize - 1,
                inorder, inStart, rootIndex - 1, inorderMap);
        root.right = buildFromPostorderInorder(postorder, postStart + leftSize, postEnd - 1,
                inorder, rootIndex + 1, inEnd, inorderMap);

        return root;
    }

    public TreeNode buildTreeFromLevelorder(Integer[] levelorder) {
        if (levelorder == null || levelorder.length == 0 || levelorder[0] == null) {
            return null;
        }

        TreeNode root = new TreeNode(levelorder[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int i = 1;
        while (!queue.isEmpty() && i < levelorder.length) {
            TreeNode current = queue.poll();

            if (i < levelorder.length && levelorder[i] != null) {
                current.left = new TreeNode(levelorder[i]);
                queue.offer(current.left);
            }
            i++;

            if (i < levelorder.length && levelorder[i] != null) {
                current.right = new TreeNode(levelorder[i]);
                queue.offer(current.right);
            }
            i++;
        }

        return root;
    }

    public boolean validateReconstructedTree(TreeNode original, TreeNode reconstructed) {
        return areTreesEqual(original, reconstructed);
    }

    private boolean areTreesEqual(TreeNode tree1, TreeNode tree2) {
        if (tree1 == null && tree2 == null) {
            return true;
        }
        if (tree1 == null || tree2 == null) {
            return false;
        }

        return tree1.val == tree2.val
                && areTreesEqual(tree1.left, tree2.left)
                && areTreesEqual(tree1.right, tree2.right);
    }

    public List<Integer> getPreorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preorderHelper(root, result);
        return result;
    }

    private void preorderHelper(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        result.add(node.val);
        preorderHelper(node.left, result);
        preorderHelper(node.right, result);
    }

    public List<Integer> getInorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderHelper(root, result);
        return result;
    }

    private void inorderHelper(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        inorderHelper(node.left, result);
        result.add(node.val);
        inorderHelper(node.right, result);
    }

    public List<Integer> getPostorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postorderHelper(root, result);
        return result;
    }

    private void postorderHelper(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        postorderHelper(node.left, result);
        postorderHelper(node.right, result);
        result.add(node.val);
    }

    public List<Integer> getLevelorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            result.add(current.val);

            if (current.left != null) {
                queue.offer(current.left);
            }
            if (current.right != null) {
                queue.offer(current.right);
            }
        }

        return result;
    }

    public void printTree(TreeNode root) {
        printTreeHelper(root, "", true);
    }

    private void printTreeHelper(TreeNode node, String prefix, boolean isLast) {
        if (node != null) {
            System.out.println(prefix + (isLast ? "└── " : "├── ") + node.val);
            if (node.left != null || node.right != null) {
                if (node.left != null) {
                    printTreeHelper(node.left, prefix + (isLast ? "    " : "│   "),
                            node.right == null);
                }
                if (node.right != null) {
                    printTreeHelper(node.right, prefix + (isLast ? "    " : "│   "), true);
                }
            }
        }
    }

    private int[] listToArray(List<Integer> list) {
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        TreeReconstruction reconstruction = new TreeReconstruction();

        // 建立原始測試樹
        //       3
        //      / \
        //     9   20
        //        /  \
        //       15   7
        TreeNode originalTree = new TreeNode(3);
        originalTree.left = new TreeNode(9);
        originalTree.right = new TreeNode(20);
        originalTree.right.left = new TreeNode(15);
        originalTree.right.right = new TreeNode(7);

        System.out.println("Original Tree:");
        reconstruction.printTree(originalTree);
        System.out.println();

        List<Integer> preorder = reconstruction.getPreorderTraversal(originalTree);
        List<Integer> inorder = reconstruction.getInorderTraversal(originalTree);
        List<Integer> postorder = reconstruction.getPostorderTraversal(originalTree);
        List<Integer> levelorder = reconstruction.getLevelorderTraversal(originalTree);

        System.out.println("Traversal results:");
        System.out.println("Preorder:  " + preorder);
        System.out.println("Inorder:   " + inorder);
        System.out.println("Postorder: " + postorder);
        System.out.println("Levelorder:" + levelorder);
        System.out.println();

        System.out.println("1. Reconstruct from preorder and inorder:");
        int[] preorderArray = reconstruction.listToArray(preorder);
        int[] inorderArray = reconstruction.listToArray(inorder);
        TreeNode reconstructed1 = reconstruction
