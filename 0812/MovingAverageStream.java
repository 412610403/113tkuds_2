
import java.util.*;

/**
 * 計算資料流中滑動視窗的移動平均、中位數、最小值、最大值
 */
public class MovingAverageStream {

    private final int size;                   // 視窗大小
    private final Queue<Integer> window;      // 滑動視窗
    private long sum;                         // 視窗總和（用 long 防溢位）

    // 中位數用的 heap
    private final PriorityQueue<Integer> maxHeap; // 儲存較小一半的數（最大堆）
    private final PriorityQueue<Integer> minHeap; // 儲存較大一半的數（最小堆）

    // 儲存所有元素，用於快速取 min / max
    private final TreeMap<Integer, Integer> countMap;

    public MovingAverageStream(int size) {
        this.size = size;
        this.window = new LinkedList<>();
        this.sum = 0;
        this.maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        this.minHeap = new PriorityQueue<>();
        this.countMap = new TreeMap<>();
    }

    /**
     * 新增一個數字，返回新的移動平均
     */
    public double next(int val) {
        window.offer(val);
        sum += val;

        // 加入 TreeMap
        countMap.put(val, countMap.getOrDefault(val, 0) + 1);

        // 加入 heap
        if (maxHeap.isEmpty() || val <= maxHeap.peek()) {
            maxHeap.offer(val);
        } else {
            minHeap.offer(val);
        }

        balanceHeaps();

        // 超過視窗大小 -> 移除舊元素
        if (window.size() > size) {
            int removed = window.poll();
            sum -= removed;

            // 從 TreeMap 移除
            countMap.put(removed, countMap.get(removed) - 1);
            if (countMap.get(removed) == 0) {
                countMap.remove(removed);
            }

            // 從 heap 延遲刪除（lazy removal）
            if (removed <= maxHeap.peek()) {
                removeFromHeap(maxHeap, removed);
            } else {
                removeFromHeap(minHeap, removed);
            }
            balanceHeaps();
        }

        return (double) sum / window.size();
    }

    /**
     * 取得中位數
     */
    public double getMedian() {
        if (maxHeap.isEmpty() && minHeap.isEmpty()) {
            return 0.0;
        }
        if (maxHeap.size() == minHeap.size()) {
            return ((double) maxHeap.peek() + minHeap.peek()) / 2.0;
        }
        return maxHeap.size() > minHeap.size() ? maxHeap.peek() : minHeap.peek();
    }

    /**
     * 取得最小值
     */
    public int getMin() {
        return countMap.firstKey();
    }

    /**
     * 取得最大值
     */
    public int getMax() {
        return countMap.lastKey();
    }

    // 平衡兩個 heap 大小
    private void balanceHeaps() {
        while (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        }
        while (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    // 延遲刪除策略
    private void removeFromHeap(PriorityQueue<Integer> heap, int val) {
        heap.remove(val); // 直接 remove（Java PriorityQueue 不是最優，但簡單）
    }

    /**
     * 測試
     */
    public static void main(String[] args) {
        MovingAverageStream ma = new MovingAverageStream(3);
        System.out.println(ma.next(1));   // 1.0
        System.out.println(ma.next(10));  // 5.5
        System.out.println(ma.next(3));   // 4.666...
        System.out.println(ma.next(5));   // 6.0
        System.out.println(ma.getMedian()); // 5.0
        System.out.println(ma.getMin());    // 3
        System.out.println(ma.getMax());    // 10
    }
}
