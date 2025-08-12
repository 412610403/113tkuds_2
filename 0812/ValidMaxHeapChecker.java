
import java.util.*;

public class ValidMaxHeapChecker {

    private final int[] heap;

    public ValidMaxHeapChecker(int[] arr) {
        // 直接使用原陣列，不進行建構
        this.heap = arr.clone();
    }

    private int leftChild(int i) {
        return 2 * i + 1;
    }

    private int rightChild(int i) {
        return 2 * i + 2;
    }

    /**
     * 檢查陣列是否符合 Max Heap 性質
     *
     * @return 如果符合則返回 -1，否則返回第一個違反規則的節點索引
     */
    public int checkMaxHeapProperty() {
        if (heap.length <= 1) {
            return -1; // 空陣列或單一元素必定符合
        }

        // 遍歷所有非葉子節點（從索引 0 到 (n-2)/2）
        int lastNonLeaf = (heap.length - 2) / 2;

        for (int i = 0; i <= lastNonLeaf; i++) {
            int left = leftChild(i);
            int right = rightChild(i);

            // 檢查左子節點
            if (left < heap.length && heap[i] < heap[left]) {
                System.out.println("違反規則: 索引 " + i + " (值: " + heap[i]
                        + ") 小於左子節點索引 " + left + " (值: " + heap[left] + ")");
                return i;
            }

            // 檢查右子節點
            if (right < heap.length && heap[i] < heap[right]) {
                System.out.println("違反規則: 索引 " + i + " (值: " + heap[i]
                        + ") 小於右子節點索引 " + right + " (值: " + heap[right] + ")");
                return i;
            }
        }

        return -1; // 符合 Max Heap 性質
    }

    /**
     * 判斷是否為有效的 Max Heap
     */
    public boolean isValidMaxHeap() {
        return checkMaxHeapProperty() == -1;
    }

    /**
     * 顯示檢查結果
     */
    public void display() {
        System.out.println("陣列: " + Arrays.toString(heap));

        int violationIndex = checkMaxHeapProperty();
        if (violationIndex == -1) {
            System.out.println("結果: true (符合 Max Heap 性質)");
        } else {
            System.out.println("結果: false (第一個違反規則的節點在索引 " + violationIndex + ")");
        }

        // 如果陣列不為空且元素數量適中，顯示樹狀結構
        if (heap.length > 0 && heap.length <= 15) {
            displayTree();
        }
    }

    /**
     * 顯示樹狀結構（適用於小型陣列）
     */
    private void displayTree() {
        if (heap.length == 0) {
            return;
        }

        System.out.println("\n樹狀結構表示:");

        if (heap.length >= 1) {
            System.out.println("        " + heap[0]);
        }
        if (heap.length >= 3) {
            System.out.println("      /   \\");
            System.out.println("     " + heap[1] + "     " + heap[2]);
        }
        if (heap.length >= 7) {
            System.out.println("    / \\   / \\");
            System.out.println("   " + heap[3] + "   " + heap[4] + " " + heap[5] + "   " + heap[6]);
        }
        if (heap.length >= 8) {
            System.out.print("  /");
            if (heap.length >= 9) {
                System.out.print("   \\");
            }
            if (heap.length >= 10) {
                System.out.print("       /");
            }
            if (heap.length >= 11) {
                System.out.print("   \\");
            }
            System.out.println();

            System.out.print(" " + heap[7]);
            if (heap.length >= 9) {
                System.out.print("     " + heap[8]);
            }
            if (heap.length >= 10) {
                System.out.print("     " + heap[9]);
            }
            if (heap.length >= 11) {
                System.out.print("   " + heap[10]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Max Heap 驗證器測試 ===\n");

        // 測試案例
        int[][] testCases = {
            {100, 90, 80, 70, 60, 75, 65}, // true
            {100, 90, 80, 95, 60, 75, 65}, // false (索引3的95大於父節點90)
            {50}, // true
            {}, // true
            {10, 20, 30}, // false
            {30, 20, 10, 15, 5} // true
        };

        for (int i = 0; i < testCases.length; i++) {
            System.out.println("測試案例 " + (i + 1) + ":");
            ValidMaxHeapChecker checker = new ValidMaxHeapChecker(testCases[i]);
            checker.display();
            System.out.println();
        }

    }

}
