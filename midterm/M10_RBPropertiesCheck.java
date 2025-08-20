
import java.util.*;

public class M10_RBPropertiesCheck {

    static class Node {

        int val;
        char color; // 'B' or 'R'
        int index;
        Node left, right;

        Node(int val, char color, int index) {
            this.val = val;
            this.color = color;
            this.index = index;
        }
    }

    static Node buildTree(int n, int[] vals, char[] colors) {
        if (n == 0) {
            return null;
        }
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            if (vals[i] == -1) {
                nodes[i] = null;
            } else {
                nodes[i] = new Node(vals[i], colors[i], i);
            }
        }
        for (int i = 0; i < n; i++) {
            if (nodes[i] != null) {
                int l = 2 * i + 1, r = 2 * i + 2;
                if (l < n) {
                    nodes[i].left = nodes[l];
                }
                if (r < n) {
                    nodes[i].right = nodes[r];
                }
            }
        }
        return nodes[0];
    }

    static boolean rootIsBlack(Node root) {
        if (root == null) {
            return true;
        }
        return root.color == 'B';
    }

    static String redRedViolation(Node root) {
        if (root == null) {
            return null;
        }
        if (root.color == 'R') {
            if (root.left != null && root.left.color == 'R') {
                return "RedRedViolation at index " + root.left.index;
            }
            if (root.right != null && root.right.color == 'R') {
                return "RedRedViolation at index " + root.right.index;
            }
        }
        String leftCheck = redRedViolation(root.left);
        if (leftCheck != null) {
            return leftCheck;
        }
        return redRedViolation(root.right);
    }

    static class BHResult {

        boolean valid;
        int blackHeight;

        BHResult(boolean valid, int blackHeight) {
            this.valid = valid;
            this.blackHeight = blackHeight;
        }
    }

    static BHResult checkBlackHeight(Node root) {
        if (root == null) {
            return new BHResult(true, 1);
        }
        BHResult left = checkBlackHeight(root.left);
        BHResult right = checkBlackHeight(root.right);

        if (!left.valid || !right.valid) {
            return new BHResult(false, -1);
        }
        if (left.blackHeight != right.blackHeight) {
            return new BHResult(false, -1);
        }

        int add = (root.color == 'B') ? 1 : 0;
        return new BHResult(true, left.blackHeight + add);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        int[] vals = new int[n];
        char[] colors = new char[n];
        for (int i = 0; i < n; i++) {
            vals[i] = sc.nextInt();
            colors[i] = sc.next().charAt(0);
            if (vals[i] == -1) {
                vals[i] = -1;
                colors[i] = 'B';
            }
        }

        Node root = buildTree(n, vals, colors);

        if (!rootIsBlack(root)) {
            System.out.println("RootNotBlack");
            return;
        }

        String redRed = redRedViolation(root);
        if (redRed != null) {
            System.out.println(redRed);
            return;
        }

        BHResult bh = checkBlackHeight(root);
        if (!bh.valid) {
            System.out.println("BlackHeightMismatch");
            return;
        }

        System.out.println("RB Valid");
    }
}
