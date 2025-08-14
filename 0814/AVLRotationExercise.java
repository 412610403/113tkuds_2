
public class AVLRotationExercise {

    // 節點定義
    static class Node {

        int key;
        Node left, right;
        int height;

        Node(int key) {
            this.key = key;
            this.height = 1; // 新節點初始高度
        }
    }

    // 取得節點高度
    private int height(Node node) {
        return (node == null) ? 0 : node.height;
    }

    // 更新節點高度
    private void updateHeight(Node node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    // 左旋 (Right-Right 情況)
    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // 旋轉
        y.left = x;
        x.right = T2;

        // 更新高度
        updateHeight(x);
        updateHeight(y);

        return y; // 新的根節點
    }

    // 右旋 (Left-Left 情況)
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // 旋轉
        x.right = y;
        y.left = T2;

        // 更新高度
        updateHeight(y);
        updateHeight(x);

        return x; // 新的根節點
    }

    // 左右旋 (Left-Right 情況)
    private Node leftRightRotate(Node node) {
        node.left = leftRotate(node.left); // 對左子樹左旋
        return rightRotate(node); // 再右旋
    }

    // 右左旋 (Right-Left 情況)
    private Node rightLeftRotate(Node node) {
        node.right = rightRotate(node.right); // 對右子樹右旋
        return leftRotate(node); // 再左旋
    }

    // 測試旋轉
    public static void main(String[] args) {
        AVLRotationExercise avl = new AVLRotationExercise();

        // 測試 1: 右旋 (LL 情況)
        Node rootLL = new Node(30);
        rootLL.left = new Node(20);
        rootLL.left.left = new Node(10);
        avl.updateHeight(rootLL.left);
        avl.updateHeight(rootLL);
        System.out.println("右旋 (LL):");
        rootLL = avl.rightRotate(rootLL);
        avl.printTree(rootLL);
        System.out.println("---------------");

        // 測試 2: 左旋 (RR 情況)
        Node rootRR = new Node(10);
        rootRR.right = new Node(20);
        rootRR.right.right = new Node(30);
        avl.updateHeight(rootRR.right);
        avl.updateHeight(rootRR);
        System.out.println("左旋 (RR):");
        rootRR = avl.leftRotate(rootRR);
        avl.printTree(rootRR);
        System.out.println("---------------");

        // 測試 3: 左右旋 (LR 情況)
        Node rootLR = new Node(30);
        rootLR.left = new Node(10);
        rootLR.left.right = new Node(20);
        avl.updateHeight(rootLR.left);
        avl.updateHeight(rootLR);
        System.out.println("左右旋 (LR):");
        rootLR = avl.leftRightRotate(rootLR);
        avl.printTree(rootLR);
        System.out.println("---------------");

        // 測試 4: 右左旋 (RL 情況)
        Node rootRL = new Node(10);
        rootRL.right = new Node(30);
        rootRL.right.left = new Node(20);
        avl.updateHeight(rootRL.right);
        avl.updateHeight(rootRL);
        System.out.println("右左旋 (RL):");
        rootRL = avl.rightLeftRotate(rootRL);
        avl.printTree(rootRL);
    }

    // 輔助方法：印出樹狀結構 (前序)
    private void printTree(Node node) {
        if (node != null) {
            System.out.println(node.key + " (h=" + node.height + ")");
            printTree(node.left);
            printTree(node.right);
        }
    }
}
