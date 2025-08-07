
import java.util.*;

class MultiWayNode {

    int val;
    List<MultiWayNode> children;

    ```
MultiWayNode (int val) {
        this.val = val;
        this.children = new ArrayList<>();
    }

    void addChild(MultiWayNode child) {
        children.add(child);
    }

    ```

}

class DecisionNode {

        String question;
        String answer;
        List<DecisionNode> children;

        ```
DecisionNode (String question) {
            this.question = question;
            this.children = new ArrayList<>();
        }

        DecisionNode(String question, String answer) {
            this.question = question;
            this.answer = answer;
            this.children = new ArrayList<>();
        }


               
        ```

}

public class MultiWayTreeAndDecisionTree {

```
// 1. 多路樹的深度優先走訪
public List<Integer> dfsTraversal(MultiWayNode root) {
                List<Integer> result = new ArrayList<>();
                if (root == null) {
                    return result;
                }

                dfsHelper(root, result);
                return result;
            }

            private void dfsHelper(MultiWayNode node, List<Integer> result) {
                if (node == null) {
                    return;
                }

                result.add(node.val);
                for (MultiWayNode child : node.children) {
                    dfsHelper(child, result);
                }
            }

// 2. 多路樹的廣度優先走訪
            public List<Integer> bfsTraversal(MultiWayNode root) {
                List<Integer> result = new ArrayList<>();
                if (root == null) {
                    return result;
                }

                Queue<MultiWayNode> queue = new LinkedList<>();
                queue.offer(root);

                while (!queue.isEmpty()) {
                    MultiWayNode current = queue.poll();
                    result.add(current.val);

                    for (MultiWayNode child : current.children) {
                        queue.offer(child);
                    }
                }

                return result;
            }

// 3. 計算多路樹的高度
            public int getHeight(MultiWayNode root) {
                if (root == null) {
                    return 0;
                }

                int maxChildHeight = 0;
                for (MultiWayNode child : root.children) {
                    maxChildHeight = Math.max(maxChildHeight, getHeight(child));
                }

                return maxChildHeight + 1;
            }

// 4. 計算每個節點的度數
            public Map<Integer, Integer> getNodeDegrees(MultiWayNode root) {
                Map<Integer, Integer> degrees = new HashMap<>();
                if (root == null) {
                    return degrees;
                }

                calculateDegrees(root, degrees);
                return degrees;
            }

            private void calculateDegrees(MultiWayNode node, Map<Integer, Integer> degrees) {
                if (node == null) {
                    return;
                }

                degrees.put(node.val, node.children.size());

                for (MultiWayNode child : node.children) {
                    calculateDegrees(child, degrees);
                }
            }

// 5. 決策樹 - 猜數字遊戲
            public void playGuessingGame() {
                Scanner scanner = new Scanner(System.in);
                DecisionNode root = buildGuessingGameTree();

                System.out.println("Think of a number between 1 and 100!");
                System.out.println("I will try to guess it.");

                playGame(root, scanner);
                scanner.close();
            }

            private DecisionNode buildGuessingGameTree() {
                DecisionNode root = new DecisionNode("Is your number <= 50?");

                // 左分支 (<=50)
                DecisionNode left = new DecisionNode("Is your number <= 25?");
                DecisionNode leftLeft = new DecisionNode("Is your number <= 12?");
                leftLeft.children.add(new DecisionNode("Is it 6?", "6"));
                leftLeft.children.add(new DecisionNode("Is it 18?", "18"));

                DecisionNode leftRight = new DecisionNode("Is your number <= 37?");
                leftRight.children.add(new DecisionNode("Is it 31?", "31"));
                leftRight.children.add(new DecisionNode("Is it 43?", "43"));

                left.children.add(leftLeft);
                left.children.add(leftRight);

                // 右分支 (>50)
                DecisionNode right = new DecisionNode("Is your number <= 75?");
                DecisionNode rightLeft = new DecisionNode("Is your number <= 62?");
                rightLeft.children.add(new DecisionNode("Is it 56?", "56"));
                rightLeft.children.add(new DecisionNode("Is it 68?", "68"));

                DecisionNode rightRight = new DecisionNode("Is your number <= 87?");
                rightRight.children.add(new DecisionNode("Is it 81?", "81"));
                rightRight.children.add(new DecisionNode("Is it 93?", "93"));

                right.children.add(rightLeft);
                right.children.add(rightRight);

                root.children.add(left);
                root.children.add(right);

                return root;
            }

            private void playGame(DecisionNode node, Scanner scanner) {
                if (node.answer != null) {
                    System.out.println("Is your number " + node.answer + "?");
                    String response = scanner.nextLine().toLowerCase();
                    if (response.equals("yes") || response.equals("y")) {
                        System.out.println("Great! I guessed it!");
                    } else {
                        System.out.println("I need to improve my guessing!");
                    }
                    return;
                }

                System.out.println(node.question);
                String response = scanner.nextLine().toLowerCase();

                if (response.equals("yes") || response.equals("y")) {
                    if (!node.children.isEmpty()) {
                        playGame(node.children.get(0), scanner);
                    }
                } else {
                    if (node.children.size() > 1) {
                        playGame(node.children.get(1), scanner);
                    }
                }
            }

// 輔助方法：印出多路樹結構
            public void printMultiWayTree(MultiWayNode root) {
                printMultiWayTreeHelper(root, "", true);
            }

            private void printMultiWayTreeHelper(MultiWayNode node, String prefix, boolean isLast) {
                if (node == null) {
                    return;
                }

                System.out.println(prefix + (isLast ? "└── " : "├── ") + node.val);

                for (int i = 0; i < node.children.size(); i++) {
                    boolean isLastChild = (i == node.children.size() - 1);
                    printMultiWayTreeHelper(node.children.get(i),
                            prefix + (isLast ? "    " : "│   "), isLastChild);
                }
            }

            public static void main(String[] args) {
                MultiWayTreeAndDecisionTree solution = new MultiWayTreeAndDecisionTree();

                // 建立多路樹
                MultiWayNode root = new MultiWayNode(1);
                MultiWayNode child1 = new MultiWayNode(2);
                MultiWayNode child2 = new MultiWayNode(3);
                MultiWayNode child3 = new MultiWayNode(4);

                root.addChild(child1);
                root.addChild(child2);
                root.addChild(child3);

                child1.addChild(new MultiWayNode(5));
                child1.addChild(new MultiWayNode(6));

                child2.addChild(new MultiWayNode(7));

                child3.addChild(new MultiWayNode(8));
                child3.addChild(new MultiWayNode(9));
                child3.addChild(new MultiWayNode(10));

                System.out.println("Multi-way tree structure:");
                solution.printMultiWayTree(root);

                System.out.println("\nDFS traversal: " + solution.dfsTraversal(root));
                System.out.println("BFS traversal: " + solution.bfsTraversal(root));
                System.out.println("Tree height: " + solution.getHeight(root));
                System.out.println("Node degrees: " + solution.getNodeDegrees(root));

                System.out.println("\n--- Decision Tree Game ---");
                System.out.println("Uncomment the next line to play the guessing game:");
                // solution.playGuessingGame();
            }
            ```



            }
