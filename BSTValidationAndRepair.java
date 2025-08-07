
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

public class BSTValidationAndRepair {

    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBST(TreeNode node, long minVal, long maxVal) {
        if (node == null) {
            return true;
        }

        if (node.val <= minVal || node.val >= maxVal) {
            return false;
        }

        return isValidBST(node.left, minVal, node.val)
                && isValidBST(node.right, node.val, maxVal);
    }

    public List<Integer> findInvalidNodes(TreeNode root) {
        List<Integer> inorder = new ArrayList<>();
        inorderTraversal(root, inorder);

        List<Integer> invalidNodes = new ArrayList<>();
        for (int i = 0; i < inorder.size() - 1; i++) {
            if (inorder.get(i) >= inorder.get(i + 1)) {
                invalidNodes.add(inorder.get(i));
                invalidNodes.add(inorder.get(i + 1));
            }
        }

        return new ArrayList<>(new LinkedHashSet<>(invalidNodes));
    }

    private void inorderTraversal(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        inorderTraversal(node.left, result);
        result.add(node.val);
        inorderTraversal(node.right, result);
    }

    public void recoverTree(TreeNode root) {
        TreeNode[] swapped = new TreeNode[2];
        TreeNode[] prev = new TreeNode[1];

        inorderFindSwapped(root, swapped, prev);

        if (swapped[0] != null && swapped[1] != null) {
            int temp = swapped[0].val;
            swapped[0].val = swapped[1].val;
            swapped[1].val = temp;
        }
    }

    private void inorderFindSwapped(TreeNode node, TreeNode[] swapped, TreeNode[] prev) {
        if (node == null) {
            return;
        }

        inorderFindSwapped(node.left, swapped, prev);

        if (prev[0] != null && prev[0].val > node.val) {
            if (swapped[0] == null) {
                swapped[0] = prev[0];
                swapped[1] = node;
            } else {
                swapped[1] = node;
            }
        }
        prev[0] = node;

        inorderFindSwapped(node.right, swapped, prev);
    }

// 4. 計算需要移除多少個節點才能讓樹變成有效的BST
    public int minNodesToRemove(TreeNode root) {
        List<Integer> inorder = new ArrayList<>();
        inorderTraversal(root, inorder);

        int lis = findLongestIncreasingSubsequence(inorder);

        return inorder.size() - lis;
    }

    private int findLongestIncreasingSubsequence(List<Integer> nums) {
        if (nums.isEmpty()) {
            return 0;
        }

        List<Integer> dp = new ArrayList<>();

        for (int num : nums) {
            int pos = Collections.binarySearch(dp, num);
            if (pos < 0) {
                pos = -(pos + 1);
            }

            if (pos == dp.size()) {
                dp.add(num);
            } else {
                dp.set(pos, num);
            }
        }

        return dp.size();
    }

    public void printInorder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderTraversal(root, result);
        System.out.println("Inorder traversal: " + result);
    }

    public static void main(String[] args) {
        BSTValidationAndRepair bst = new BSTValidationAndRepair();

        TreeNode validBST = new TreeNode(5);
        validBST.left = new TreeNode(3);
        validBST.right = new TreeNode(8);
        validBST.left.left = new TreeNode(2);
        validBST.left.right = new TreeNode(4);
        validBST.right.left = new TreeNode(7);
        validBST.right.right = new TreeNode(9);

        System.out.println("Valid BST test:");
        System.out.println("Is valid BST: " + bst.isValidBST(validBST));
        bst.printInorder(validBST);
        System.out.println("Min nodes to remove: " + bst.minNodesToRemove(validBST));
        System.out.println();

        TreeNode invalidBST = new TreeNode(5);
        invalidBST.left = new TreeNode(8);  // 錯誤：左子節點應該小於根節點
        invalidBST.right = new TreeNode(3); // 錯誤：右子節點應該大於根節點
        invalidBST.left.left = new TreeNode(2);
        invalidBST.left.right = new TreeNode(4);
        invalidBST.right.left = new TreeNode(7);
        invalidBST.right.right = new TreeNode(9);

        System.out.println("Invalid BST test (before repair):");
        System.out.println("Is valid BST: " + bst.isValidBST(invalidBST));
        bst.printInorder(invalidBST);
        System.out.println("Invalid nodes: " + bst.findInvalidNodes(invalidBST));

        bst.recoverTree(invalidBST);
        System.out.println("After repair:");
        System.out.println("Is valid BST: " + bst.isValidBST(invalidBST));
        bst.printInorder(invalidBST);
        System.out.println();

        TreeNode messyBST = new TreeNode(5);
        messyBST.left = new TreeNode(3);
        messyBST.right = new TreeNode(8);
        messyBST.left.left = new TreeNode(6);
        messyBST.left.right = new TreeNode(4);
        messyBST.right.left = new TreeNode(1);
        messyBST.right.right = new TreeNode(9);

        System.out.println("Messy BST test:");
        System.out.println("Is valid BST: " + bst.isValidBST(messyBST));
        bst.printInorder(messyBST);
        System.out.println("Invalid nodes: " + bst.findInvalidNodes(messyBST));
        System.out.println("Min nodes to remove: " + bst.minNodesToRemove(messyBST));
    }

}
