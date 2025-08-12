
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class BasicMinHeapPractice {

    private final List<Integer> heap;

    public BasicMinHeapPractice() {
        heap = new ArrayList<>();
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private int leftChild(int i) {
        return 2 * i + 1;
    }

    private int rightChild(int i) {
        return 2 * i + 2;
    }

    private void heapifyUp(int i) {
        while (i > 0 && heap.get(i) < heap.get(parent(i))) {
            Collections.swap(heap, i, parent(i));
            i = parent(i);
        }
    }

    private void heapifyDown(int i) {
        int smallest = i;
        int left = leftChild(i);
        int right = rightChild(i);

        // 找出父節點和子節點中最小的
        if (left < heap.size() && heap.get(left) < heap.get(smallest)) {
            smallest = left;
        }
        if (right < heap.size() && heap.get(right) < heap.get(smallest)) {
            smallest = right;
        }

        // 如果最小值不是父節點，則交換並繼續向下調整
        if (smallest != i) {
            Collections.swap(heap, i, smallest);
            heapifyDown(smallest);
        }
    }

    public void insert(int value) {
        heap.add(value);
        heapifyUp(heap.size() - 1);
    }

    public int extractMin() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }

        int min = heap.get(0);

        // 將最後一個元素移到根節點
        int last = heap.remove(heap.size() - 1);

        // 如果堆積還有元素，進行向下調整
        if (!heap.isEmpty()) {
            heap.set(0, last);
            heapifyDown(0);
        }

        return min;
    }

    public int getMin() {  // 改名為 getMin，符合需求
        if (heap.isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        return heap.get(0);
    }

    public int size() {
        return heap.size();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    @Override
    public String toString() {
        return heap.toString();
    }

    public static void main(String[] args) {
        BasicMinHeapPractice minHeap = new BasicMinHeapPractice();

        System.out.println("=== Min Heap 操作示範 ===");
        int[] values = {15, 10, 20, 8, 25, 5};
        for (int value : values) {
            minHeap.insert(value);
            System.out.println("插入 " + value + " 後: " + minHeap);
        }

        System.out.println("\n當前最小值: " + minHeap.getMin());
        System.out.println("堆積大小: " + minHeap.size());

        System.out.println("\n依序取出最小值:");
        while (!minHeap.isEmpty()) {
            System.out.println("取出: " + minHeap.extractMin() + ", 剩餘: " + minHeap);
        }

        System.out.println("\n最終堆積大小: " + minHeap.size());
        System.out.println("堆積是否為空: " + minHeap.isEmpty());
    }

}
