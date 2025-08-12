
import java.util.*;

public class SlidingWindowMedian {

    private PriorityQueue<Integer> maxHeap; // 較小的一半（Max Heap）
    private PriorityQueue<Integer> minHeap; // 較大的一半（Min Heap）

    public double[] medianSlidingWindow(int[] nums, int k) {
        List<Double> result = new ArrayList<>();
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();

        for (int i = 0; i < nums.length; i++) {
            add(nums[i]);

            if (i >= k) {
                remove(nums[i - k]);
            }

            if (i >= k - 1) {
                result.add(getMedian());
            }
        }

        // 轉換成 double 陣列輸出
        double[] output = new double[result.size()];
        for (int i = 0; i < result.size(); i++) {
            output[i] = result.get(i);
        }
        return output;
    }

    private void add(int num) {
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.offer(num);
        } else {
            minHeap.offer(num);
        }
        balanceHeaps();
    }

    private void remove(int num) {
        if (num <= maxHeap.peek()) {
            maxHeap.remove(num);  // 注意：remove() 會移除第一個符合的元素，時間為 O(n)
        } else {
            minHeap.remove(num);
        }
        balanceHeaps();
    }

    private void balanceHeaps() {
        // 維持 maxHeap 大小 ≥ minHeap，且最多只多一個
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        } else if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    private double getMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return ((double) maxHeap.peek() + (double) minHeap.peek()) / 2;
        } else {
            return (double) maxHeap.peek();
        }
    }

    // 測試用主程式
    public static void main(String[] args) {
        SlidingWindowMedian swm = new SlidingWindowMedian();

        System.out.println("=== 測試案例 1 ===");
        int[] nums1 = {1, 3, -1, -3, 5, 3, 6, 7};
        int k1 = 3;
        System.out.println("輸入: " + Arrays.toString(nums1) + ", K = " + k1);
        System.out.println("輸出: " + Arrays.toString(swm.medianSlidingWindow(nums1, k1)));  // [1, -1, -1, 3, 5, 6]

        System.out.println("\n=== 測試案例 2 ===");
        int[] nums2 = {1, 2, 3, 4};
        int k2 = 2;
        System.out.println("輸入: " + Arrays.toString(nums2) + ", K = " + k2);
        System.out.println("輸出: " + Arrays.toString(swm.medianSlidingWindow(nums2, k2)));  // [1.5, 2.5, 3.5]
    }
}
