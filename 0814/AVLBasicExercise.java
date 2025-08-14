
public class AVLBasicExercise {

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
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    // 計算平衡因子
    private int getBalance(Node node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    // 右旋轉
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // 執行旋轉
        x.right = y;
        y.left = T2;

        // 更新高度
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    // 左旋轉
    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // 執行旋轉
        y.left = x;
        x.right = T2;

        // 更新高度
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    // 插入節點
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
        // 更新節點高度
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // 計算平衡因子
        int balance = getBalance(node);

        // LL
        if (balance > 1 && key < node.left.key) {
            return rightRotate(node);
        }

        // RR
        if (balance < -1 && key > node.right.key) {
            return leftRotate(node);
        }

        // LR
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RL
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node; // 無需旋轉
    }

    // 搜尋節點
    public boolean search(int key) {
        return searchNode(root, key);
    }

    private boolean searchNode(Node node, int key) {
        if (node == null) {
            return false;
        }
        if (node.key == key) {
            return true;
        }
        return key < node.key ? searchNode(node.left, key) : searchNode(node.right, key);
    }

    // 計算整棵樹高度
    public int getHeight() {
        return height(root);
    }

    // 檢查是否為有效的 AVL 樹
    public boolean isValidAVL() {
        return checkAVL(root);
    }

    private boolean checkAVL(Node node) {
        if (node == null) {
            return true;
        }

        int balance = getBalance(node);
        if (balance < -1 || balance > 1) {
            return false;
        }

        return checkAVL(node.left) && checkAVL(node.right);
    }

    // 測試用 main
    public static void main(String[] args) {
        AVLBasicExercise avl = new AVLBasicExercise();

        avl.insert(10);
        avl.insert(20);
        avl.insert(30);
        avl.insert(40);
        avl.insert(50);
        avl.insert(25);

        System.out.println("搜尋 25: " + avl.search(25)); // true
        System.out.println("樹的高度: " + avl.getHeight());
        System.out.println("是否為有效 AVL 樹: " + avl.isValidAVL());
    }
}
