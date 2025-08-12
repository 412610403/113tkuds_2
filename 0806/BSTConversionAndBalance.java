

class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }

}

public class BSTConversionAndBalance {

    TreeNode prev = null;

    public TreeNode treeToDoublyList(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode dummy = new TreeNode(0);
        prev = dummy;
        inorderConvert(root);

        TreeNode head = dummy.right;
        TreeNode tail = prev;
        head.left = tail;
        tail.right = head;

        return head;
    }

    private void inorderConvert(TreeNode node) {
        if (node == null) {
            return;
        }

        inorderConvert(node.left);

        prev.right = node;
        node.left = prev;
        prev = node;

        inorderConvert(node.right);
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        return buildBST(nums, 0, nums.length - 1);
    }

    private TreeNode buildBST(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);

        root.left = buildBST(nums, left, mid - 1);
        root.right = buildBST(nums, mid + 1, right);

        return root;
    }

    public boolean isBalanced(TreeNode root) {
        return getHeight(root) != -1;
    }

    private int getHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);

        if (leftHeight == -1 || rightHeight == -1
                || Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }

        return Math.max(leftHeight, rightHeight) + 1;
    }

    int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        if (root == null) {
            return null;
        }

        convertBST(root.right);
        sum += root.val;
        root.val = sum;
        convertBST(root.left);

        return root;
    }

    public void printInorder(TreeNode root) {
        if (root == null) {
            return;
        }
        printInorder(root.left);
        System.out.print(root.val + " ");
        printInorder(root.right);
    }

    public void printDoublyList(TreeNode head) {
        if (head == null) {
            return;
        }
        TreeNode current = head;
        do {
            System.out.print(current.val + " ");
            current = current.right;
        } while (current != head);
        System.out.println();
    }

    public static void main(String[] args) {
        BSTConversionAndBalance solution = new BSTConversionAndBalance();

        TreeNode bst = new TreeNode(4);
        bst.left = new TreeNode(2);
        bst.right = new TreeNode(6);
        bst.left.left = new TreeNode(1);
        bst.left.right = new TreeNode(3);
        bst.right.left = new TreeNode(5);
        bst.right.right = new TreeNode(7);

        System.out.println("Original BST inorder:");
        solution.printInorder(bst);
        System.out.println("\nIs balanced: " + solution.isBalanced(bst));

        TreeNode doublyList = solution.treeToDoublyList(bst);
        System.out.println("Doubly linked list:");
        solution.printDoublyList(doublyList);

        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        TreeNode balancedBST = solution.sortedArrayToBST(nums);
        System.out.println("Balanced BST from array:");
        solution.printInorder(balancedBST);
        System.out.println("\nIs balanced: " + solution.isBalanced(balancedBST));

        TreeNode convertedBST = solution.convertBST(balancedBST);
        System.out.println("Converted to greater sum tree:");
        solution.printInorder(convertedBST);
    }

}
