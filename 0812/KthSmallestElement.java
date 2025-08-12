
import java.util.*;

public class KthSmallestElement {

    // 方法1：使用大小為 K 的 Max Heap
    public static int findKthSmallestByMaxHeap(int[] nums, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        System.out.println("處理過程:");
        for (int num : nums) {
            maxHeap.offer(num);
            System.out.print("加入 " + num + ": " + maxHeap);

            if (maxHeap.size() > k) {
                int removed = maxHeap.poll();
                System.out.print(" -> 移除最大值 " + removed + ": " + maxHeap);
            }
            System.out.println();
        }

        return maxHeap.peek();
    }

    // 方法2：使用 Min Heap 然後提取 K 次
    public static int findKthSmallestByMinHeapExtractKTimes(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : nums) {
            minHeap.offer(num);
        }

        System.out.println("Min Heap: " + minHeap);

        for (int i = 0; i < k - 1; i++) {
            int removed = minHeap.poll();
            System.out.println("取出第 " + (i + 1) + " 小: " + removed + ", 剩餘: " + minHeap);
        }

        return minHeap.peek();
    }

    // 測試和比較不同方法
    public static void testKthSmallest(int[] nums, int k) {
        System.out.println("=== 找第 " + k + " 小元素 ===");
        System.out.println("原始陣列: " + Arrays.toString(nums));

        // 驗證用的排序結果
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        System.out.println("排序後: " + Arrays.toString(sorted));
        System.out.println("正確答案應該是: " + sorted[k - 1]);

        System.out.println("\n方法1 - Max Heap (大小為 K):");
        int result1 = findKthSmallestByMaxHeap(nums.clone(), k);
        System.out.println("結果: " + result1);

        System.out.println("\n方法2 - Min Heap 並提取 K 次:");
        int result2 = findKthSmallestByMinHeapExtractKTimes(nums.clone(), k);
        System.out.println("結果: " + result2);

        System.out.println("\n時間與空間複雜度比較:");
        System.out.println("方法1 (Max Heap): 時間 O(n log k), 空間 O(k)");
        System.out.println("方法2 (Min Heap): 時間 O(n + k log n), 空間 O(n)");
        System.out.println("=".repeat(50) + "\n");
    }

    public static void main(String[] args) {
        testKthSmallest(new int[]{7, 10, 4, 3, 20, 15}, 3);  // 答案：7
        testKthSmallest(new int[]{1}, 1);                    // 答案：1
        testKthSmallest(new int[]{3, 1, 4, 1, 5, 9, 2, 6}, 4); // 答案：3
    }
}
