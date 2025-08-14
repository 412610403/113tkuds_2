
public class AVLRotations {

    // 右旋操作
    // 時間複雜度: O(1), 空間複雜度: O(1)
    public static AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        // 執行旋轉
        x.right = y;
        y.left = T2;

        // 更新高度
        y.updateHeight();
        x.updateHeight();

        return x; // 新的根節點
    }

    // 左旋操作
    // 時間複雜度: O(1), 空間複雜度: O(1)
    public static AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        // 執行旋轉
        y.left = x;
        x.right = T2;

        // 更新高度
        x.updateHeight();
        y.updateHeight();

        return y; // 新的根節點
    }

    public static void main(String[] args) {
        // 建立一個不平衡的樹 (右旋測試)
        AVLNode root = new AVLNode(30);
        root.left = new AVLNode(20);
        root.left.left = new AVLNode(10);

        // 更新高度
        root.left.updateHeight();
        root.updateHeight();

        System.out.println("旋轉前平衡因子: " + root.getBalance());
        root = rightRotate(root); // 右旋
        System.out.println("旋轉後根節點: " + root.data);
        System.out.println("旋轉後平衡因子: " + root.getBalance());

        // 再建立一個不平衡的樹 (左旋測試)
        AVLNode root2 = new AVLNode(10);
        root2.right = new AVLNode(20);
        root2.right.right = new AVLNode(30);

        root2.right.updateHeight();
        root2.updateHeight();

        System.out.println("\n旋轉前平衡因子: " + root2.getBalance());
        root2 = leftRotate(root2); // 左旋
        System.out.println("旋轉後根節點: " + root2.data);
        System.out.println("旋轉後平衡因子: " + root2.getBalance());
    }
}
