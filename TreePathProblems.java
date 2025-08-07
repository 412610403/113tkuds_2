
import java.util.*;

class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    ```
TreeNode () {
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

public class TreePathProblems {

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        findAllPaths(root, "", result);
        return result;
    }

    private void findAllPaths(TreeNode node, String path, List<String> result) {
        if (node == null) {
            return;
        }

        String currentPath = path.isEmpty() ? String.valueOf(node.val)
                : path + "->" + node.val;

        if (node.left == null && node.right == null) {
            result.add(currentPath);
            return;
        }

        findAllPaths(node.left, currentPath, result);
        findAllPaths(node.right, currentPath, result);
    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }

        if (root.left == null && root.right == null) {
            return root.val == targetSum;
        }

        int remainingSum = targetSum - root.val;
        return hasPathSum(root.left, remainingSum)
                || hasPathSum(root.right, remainingSum);
    }

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        findPathSum(root, targetSum, currentPath, result);
        return result;
    }

    private void findPathSum(TreeNode node, int targetSum,
            List<Integer> currentPath, List<List<Integer>> result) {
        if (node == null) {
            return;
        }

        currentPath.add(node.val);

        if (node.left == null && node.right == null && node.val == targetSum) {
            result.add(new ArrayList<>(currentPath));
        } else {
            findPathSum(node.left, targetSum - node.val, currentPath, result);
            findPathSum(node.right, targetSum - node.val, currentPath, result);
        }

        currentPath.remove(currentPath.size() - 1);
    }

    public int maxPathSumRootToLeaf(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int[] maxSum = {Integer.MIN_VALUE};
        List<Integer> maxPath = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();

        findMaxPathSum(root, 0, currentPath, maxSum, maxPath);

        System.out.println("Max path sum root to leaf: " + maxSum[0]);
        System.out.println("Path: " + maxPath);

        return maxSum[0];
    }

    private void findMaxPathSum(TreeNode node, int currentSum,
            List<Integer> currentPath, int[] maxSum,
            List<Integer> maxPath) {
        if (node == null) {
            return;
        }

        currentPath.add(node.val);
        currentSum += node.val;

        if (node.left == null && node.right == null) {
            if (currentSum > maxSum[0]) {
                maxSum[0] = currentSum;
                maxPath.clear();
                maxPath.addAll(new ArrayList<>(currentPath));
            }
        } else {
            findMaxPathSum(node.left, currentSum, currentPath, maxSum, maxPath);
            findMaxPathSum(node.right, currentSum, currentPath, maxSum, maxPath);
        }

        currentPath.remove(currentPath.size() - 1);
    }

    public int maxPathSum(TreeNode root) {
        int[] maxSum = {Integer.MIN_VALUE};
        maxPathSumHelper(root, maxSum);
        return maxSum[0];
    }

    private int maxPathSumHelper(TreeNode node, int[] maxSum) {
        if (node == null) {
            return 0;
        }

        int leftMax = Math.max(0, maxPathSumHelper(node.left, maxSum));
        int rightMax = Math.max(0, maxPathSumHelper(node.right, maxSum));

        int currentMax = node.val + leftMax + rightMax;

        maxSum[0] = Math.max(maxSum[0], currentMax);

        return node.val + Math.max(leftMax, rightMax);
    }

    public int getTreeHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(getTreeHeight(root.left), getTreeHeight(root.right));
    }

    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + countNodes(root.left) + countNodes(root.right);
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

    public static void main(String[] args) {
        TreePathProblems solution = new TreePathProblems();

        // 建立測試樹
        //       5
        //      / \
        //     4   8
        //    /   / \
        //   11  13  4
        //  / \      \
        // 7   2      1
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(1);

        System.out.println("Tree structure:");
        solution.printTree(root);
        System.out.println("Tree height: " + solution.getTreeHeight(root));
        System.out.println("Node count: " + solution.countNodes(root));
        System.out.println();

        System.out.println("1. All root-to-leaf paths:");
        List<String> paths = solution.binaryTreePaths(root);
        for (String path : paths) {
            System.out.println("   " + path);
        }
        System.out.println();

        int targetSum = 22;
        System.out.println("2. Has path sum " + targetSum + ": "
                + solution.hasPathSum(root, targetSum));

        System.out.println("   All paths with sum " + targetSum + ":");
        List<List<Integer>> pathsWithSum = solution.pathSum(root, targetSum);
        for (List<Integer> path : pathsWithSum) {
            System.out.println("   " + path);
        }
        System.out.println();

        System.out.println("3. Maximum root-to-leaf path sum:");
        solution.maxPathSumRootToLeaf(root);
        System.out.println();

        System.out.println("4. Maximum path sum (any two nodes): "
                + solution.maxPathSum(root));
        System.out.println();

        TreeNode negativeRoot = new TreeNode(-10);
        negativeRoot.left = new TreeNode(9);
        negativeRoot.right = new TreeNode(20);
        negativeRoot.right.left = new TreeNode(15);
        negativeRoot.right.right = new TreeNode(7);

        System.out.println("Tree with negative values:");
        solution.printTree(negativeRoot);
        System.out.println("Maximum path sum (any two nodes): "
                + solution.maxPathSum(negativeRoot));
    }

}
