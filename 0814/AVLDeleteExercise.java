
public class AVLDeleteExercise {

    // 節點定義
    static class Node {

        int key;
        Node left, right;
        int height;

        Node(int key) {
            this.key = key;
            this.height = 1; // 新節點高度為 1
        }
    }

    private Node root;

    // 取得節點高度
    private int height(Node node) {
        return (node == null) ? 0 : node.height;
    }

    // 計算平衡因子
    private int getBalance(Node node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    // 更新節點高度
    private void updateHeight(Node node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    // 右旋
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // 旋轉
        x.right = y;
        y.left = T2;

        // 更新高度
        updateHeight(y);
        updateHeight(x);

        return x;
    }

    // 左旋
    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // 旋轉
        y.left = x;
        x.right = T2;

        // 更新高度
        updateHeight(x);
        updateHeight(y);

        return y;
    }

    // 插入節點 (為了測試刪除)
    public void insert(int key) {
        root = insertNode(root, key);
    }

    private Node insertNode(Node node, int key) {
        if (node == null) {
            return new Node(key);
        }

        if (key < node.key) {
            node.left = insertNode(node.left, key);
        } else if (key > node.key) {
            node.right = insertNode(node.right, key);
        } else {
            return node; // 不允許重複值
        }
        updateHeight(node);
        return rebalance(node);
    }

    // 刪除節點
    public void delete(int key) {
        root = deleteNode(root, key);
    }

    private Node deleteNode(Node node, int key) {
        if (node == null) {
            return null;
        }

        // 標準 BST 刪除
        if (key < node.key) {
            node.left = deleteNode(node.left, key);
        } else if (key > node.key) {
            node.right = deleteNode(node.right, key);
        } else {
            // 找到要刪除的節點
            if (node.left == null && node.right == null) {
                // 1. 無子節點 (葉子)
                return null;
            } else if (node.left == null) {
                // 2. 只有右子節點
                return node.right;
            } else if (node.right == null) {
                // 2. 只有左子節點
                return node.left;
            } else {
                // 3. 兩個子節點 → 找後繼
                Node successor = minValueNode(node.right);
                node.key = successor.key;
                node.right = deleteNode(node.right, successor.key);
            }
        }

        // 更新高度
        updateHeight(node);

        // 刪除後重新平衡
        return rebalance(node);
    }

    // 找最小值節點（後繼）
    private Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    // AVL 平衡調整
    private Node rebalance(Node node) {
        int balance = getBalance(node);

        // LL
        if (balance > 1 && getBalance(node.left) >= 0) {
            return rightRotate(node);
        }

        // LR
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RR
        if (balance < -1 && getBalance(node.right) <= 0) {
            return leftRotate(node);
        }

        // RL
        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // 印出樹 (前序)
    public void printTree() {
        printPreOrder(root);
        System.out.println();
    }

    private void printPreOrder(Node node) {
        if (node != null) {
            System.out.print(node.key + " ");
            printPreOrder(node.left);
            printPreOrder(node.right);
        }
    }

    // 測試
    public static void main(String[] args) {
        AVLDeleteExercise avl = new AVLDeleteExercise();

        // 插入節點
        int[] nums = {20, 10, 30, 5, 15, 25, 40, 35};
        for (int n : nums) {
            avl.insert(n);
        }

        System.out.println("原始樹:");
        avl.printTree();

        // 刪除葉子節點
        System.out.println("刪除葉子節點 5:");
        avl.delete(5);
        avl.printTree();

        // 刪除只有一個子節點的節點
        System.out.println("刪除只有一個子節點的節點 40:");
        avl.delete(40);
        avl.printTree();

        // 刪除有兩個子節點的節點
        System.out.println("刪除有兩個子節點的節點 20:");
        avl.delete(20);
        avl.printTree();
    }
}
