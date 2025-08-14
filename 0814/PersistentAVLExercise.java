
import java.util.*;

public class PersistentAVLExercise {

    // 不可變節點類別
    private static class Node {

        final int key;
        final int height;
        final Node left, right;

        Node(int key, Node left, Node right) {
            this.key = key;
            this.left = left;
            this.right = right;
            this.height = 1 + Math.max(height(left), height(right));
        }

        private static int height(Node n) {
            return n == null ? 0 : n.height;
        }

        private int balanceFactor() {
            return height(left) - height(right);
        }
    }

    // 儲存各版本的根節點
    private final List<Node> versions = new ArrayList<>();

    public PersistentAVLExercise() {
        versions.add(null); // 版本0：空樹
    }

    // 取得節點高度
    private static int height(Node n) {
        return n == null ? 0 : n.height;
    }

    // 右旋轉
    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        return new Node(x.key, x.left, new Node(y.key, T2, y.right));
    }

    // 左旋轉
    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        return new Node(y.key, new Node(x.key, x.left, T2), y.right);
    }

    // 插入操作 (路徑複製)
    private Node insert(Node node, int key) {
        if (node == null) {
            return new Node(key, null, null);
        }

        if (key < node.key) {
            Node newLeft = insert(node.left, key);
            node = new Node(node.key, newLeft, node.right);
        } else if (key > node.key) {
            Node newRight = insert(node.right, key);
            node = new Node(node.key, node.left, newRight);
        } else {
            return node; // 不允許重複鍵
        }

        // 平衡調整
        int balance = node.balanceFactor();

        // LL
        if (balance > 1 && key < node.left.key) {
            return rotateRight(node);
        }
        // RR
        if (balance < -1 && key > node.right.key) {
            return rotateLeft(node);
        }
        // LR
        if (balance > 1 && key > node.left.key) {
            Node newLeft = rotateLeft(node.left);
            node = new Node(node.key, newLeft, node.right);
            return rotateRight(node);
        }
        // RL
        if (balance < -1 && key < node.right.key) {
            Node newRight = rotateRight(node.right);
            node = new Node(node.key, node.left, newRight);
            return rotateLeft(node);
        }

        return node;
    }

    // 插入並建立新版本
    public void insert(int version, int key) {
        if (version < 0 || version >= versions.size()) {
            throw new IllegalArgumentException("版本不存在: " + version);
        }
        Node root = versions.get(version);
        Node newRoot = insert(root, key);
        versions.add(newRoot);
    }

    // 查詢指定版本是否存在某值
    public boolean contains(int version, int key) {
        if (version < 0 || version >= versions.size()) {
            throw new IllegalArgumentException("版本不存在: " + version);
        }
        Node node = versions.get(version);
        while (node != null) {
            if (key < node.key) {
                node = node.left; 
            }else if (key > node.key) {
                node = node.right; 
            }else {
                return true;
            }
        }
        return false;
    }

    // 列印版本的中序遍歷
    public void printVersion(int version) {
        if (version < 0 || version >= versions.size()) {
            throw new IllegalArgumentException("版本不存在: " + version);
        }
        System.out.print("版本 " + version + ": ");
        inOrder(versions.get(version));
        System.out.println();
    }

    private void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.print(node.key + " ");
        inOrder(node.right);
    }

    // 測試
    public static void main(String[] args) {
        PersistentAVLExercise tree = new PersistentAVLExercise();

        tree.insert(0, 10); // 建立版本1
        tree.insert(1, 20); // 建立版本2
        tree.insert(2, 5);  // 建立版本3
        tree.insert(3, 4);  // 建立版本4
        tree.insert(4, 15); // 建立版本5

        for (int v = 0; v < 6; v++) {
            tree.printVersion(v);
        }

        System.out.println("版本3 是否包含 20? " + tree.contains(3, 20));
        System.out.println("版本5 是否包含 20? " + tree.contains(5, 20));
    }
}
