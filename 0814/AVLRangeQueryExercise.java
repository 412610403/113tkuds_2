
import java.util.*;

public class AVLRangeQueryExercise {

    // 節點定義
    static class Node {

        int key;
        Node left, right;
        int height;

        Node(int key) {
            this.key = key;
            this.height = 1;
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

    // 更新高度
    private void updateHeight(Node node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    // 右旋
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    // 左旋
    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        updateHeight(x);
        updateHeight(y);

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
            return node; // 不允許重複
        }
        updateHeight(node);
        return rebalance(node);
    }

    // AVL 平衡
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

    // 範圍查詢
    public List<Integer> rangeQuery(int min, int max) {
        List<Integer> result = new ArrayList<>();
        rangeQueryHelper(root, min, max, result);
        return result;
    }

    private void rangeQueryHelper(Node node, int min, int max, List<Integer> result) {
        if (node == null) {
            return;
        }

        // 剪枝：如果節點值大於 min，才需要去左子樹找
        if (node.key > min) {
            rangeQueryHelper(node.left, min, max, result);
        }

        // 範圍內就加入結果
        if (node.key >= min && node.key <= max) {
            result.add(node.key);
        }

        // 剪枝：如果節點值小於 max，才需要去右子樹找
        if (node.key < max) {
            rangeQueryHelper(node.right, min, max, result);
        }
    }

    // 測試
    public static void main(String[] args) {
        AVLRangeQueryExercise avl = new AVLRangeQueryExercise();

        int[] nums = {20, 10, 30, 5, 15, 25, 35, 2, 7, 13, 17};
        for (int n : nums) {
            avl.insert(n);
        }

        System.out.println("範圍查詢 [10, 25]: " + avl.rangeQuery(10, 25));
        System.out.println("範圍查詢 [5, 15]: " + avl.rangeQuery(5, 15));
        System.out.println("範圍查詢 [0, 40]: " + avl.rangeQuery(0, 40));
    }
}
