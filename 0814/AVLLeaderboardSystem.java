
import java.util.*;

public class AVLLeaderboardSystem {

    // 節點定義
    static class Node {

        String playerId;
        int score;
        Node left, right;
        int height;
        int size; // 子樹節點數量

        Node(String playerId, int score) {
            this.playerId = playerId;
            this.score = score;
            this.height = 1;
            this.size = 1;
        }
    }

    private Node root;
    private Map<String, Integer> playerScores = new HashMap<>();

    // 工具方法：取得高度
    private int height(Node node) {
        return (node == null) ? 0 : node.height;
    }

    // 工具方法：取得子樹大小
    private int size(Node node) {
        return (node == null) ? 0 : node.size;
    }

    // 更新高度與子樹大小
    private void updateNode(Node node) {
        if (node != null) {
            node.height = 1 + Math.max(height(node.left), height(node.right));
            node.size = 1 + size(node.left) + size(node.right);
        }
    }

    // 平衡因子
    private int getBalance(Node node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    // 右旋
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        updateNode(y);
        updateNode(x);

        return x;
    }

    // 左旋
    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        updateNode(x);
        updateNode(y);

        return y;
    }

    // 比較方法：分數高的在前，若分數相同則 ID 小的在前
    private int compare(String playerId, int score, Node node) {
        if (score != node.score) {
            return (score > node.score) ? -1 : 1; // 降序
        }
        return playerId.compareTo(node.playerId); // 升序
    }

    // 插入或更新節點
    private Node insertNode(Node node, String playerId, int score) {
        if (node == null) {
            return new Node(playerId, score);
        }

        int cmp = compare(playerId, score, node);
        if (cmp < 0) {
            node.left = insertNode(node.left, playerId, score);
        } else if (cmp > 0) {
            node.right = insertNode(node.right, playerId, score);
        } else {
            node.score = score; // 更新分數
        }

        updateNode(node);
        return rebalance(node);
    }

    // 刪除節點
    private Node deleteNode(Node node, String playerId, int score) {
        if (node == null) {
            return null;
        }

        int cmp = compare(playerId, score, node);
        if (cmp < 0) {
            node.left = deleteNode(node.left, playerId, score);
        } else if (cmp > 0) {
            node.right = deleteNode(node.right, playerId, score);
        } else {
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }

            Node successor = minValueNode(node.right);
            node.playerId = successor.playerId;
            node.score = successor.score;
            node.right = deleteNode(node.right, successor.playerId, successor.score);
        }

        updateNode(node);
        return rebalance(node);
    }

    // 找最小值節點
    private Node minValueNode(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // AVL 平衡
    private Node rebalance(Node node) {
        int balance = getBalance(node);

        if (balance > 1 && getBalance(node.left) >= 0) {
            return rightRotate(node);
        }

        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && getBalance(node.right) <= 0) {
            return leftRotate(node);
        }

        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // API：添加或更新玩家分數
    public void addOrUpdatePlayer(String playerId, int score) {
        if (playerScores.containsKey(playerId)) {
            int oldScore = playerScores.get(playerId);
            root = deleteNode(root, playerId, oldScore);
        }
        root = insertNode(root, playerId, score);
        playerScores.put(playerId, score);
    }

    // API：查詢玩家排名 (1 為第一名)
    public int getPlayerRank(String playerId) {
        if (!playerScores.containsKey(playerId)) {
            return -1;
        }
        int score = playerScores.get(playerId);
        return getRank(root, playerId, score) + 1;
    }

    // 計算排名
    private int getRank(Node node, String playerId, int score) {
        if (node == null) {
            return 0;
        }

        int cmp = compare(playerId, score, node);
        if (cmp < 0) {
            return getRank(node.left, playerId, score);
        } else if (cmp > 0) {
            return size(node.left) + 1 + getRank(node.right, playerId, score);
        } else {
            return size(node.left);
        }
    }

    // API：查詢前 K 名玩家
    public List<String> getTopK(int k) {
        List<String> result = new ArrayList<>();
        getTopKHelper(root, k, result);
        return result;
    }

    private void getTopKHelper(Node node, int k, List<String> result) {
        if (node == null || result.size() >= k) {
            return;
        }

        getTopKHelper(node.left, k, result);
        if (result.size() < k) {
            result.add(node.playerId + " (" + node.score + ")");
        }
        getTopKHelper(node.right, k, result);
    }

    // 測試
    public static void main(String[] args) {
        AVLLeaderboardSystem leaderboard = new AVLLeaderboardSystem();

        leaderboard.addOrUpdatePlayer("Alice", 50);
        leaderboard.addOrUpdatePlayer("Bob", 70);
        leaderboard.addOrUpdatePlayer("Charlie", 60);
        leaderboard.addOrUpdatePlayer("David", 90);
        leaderboard.addOrUpdatePlayer("Eve", 80);

        System.out.println("前 3 名玩家: " + leaderboard.getTopK(3));
        System.out.println("Bob 排名: " + leaderboard.getPlayerRank("Bob"));

        leaderboard.addOrUpdatePlayer("Bob", 95); // 更新分數
        System.out.println("更新後前 3 名玩家: " + leaderboard.getTopK(3));
        System.out.println("Bob 新排名: " + leaderboard.getPlayerRank("Bob"));
    }
}
