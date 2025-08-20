
import java.util.*;

public class M01_BuildHeap {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String type = sc.next();   // "max" 或 "min"
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // 建堆
        buildHeap(arr, type);

        // 輸出結果
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i]);
            if (i < n - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();

        sc.close();
    }

    // 自底向上
    public static void buildHeap(int[] arr, String type) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapifyDown(arr, n, i, type);
        }
    }

    // HeapifyDown
    private static void heapifyDown(int[] arr, int n, int i, String type) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int target = i;

        if (type.equals("max")) {
            // max-heap
            if (left < n && arr[left] > arr[target]) {
                target = left;
            }
            if (right < n && arr[right] > arr[target]) {
                target = right;
            }
        } else {
            // min-heap
            if (left < n && arr[left] < arr[target]) {
                target = left;
            }
            if (right < n && arr[right] < arr[target]) {
                target = right;
            }
        }

        if (target != i) {
            int tmp = arr[i];
            arr[i] = arr[target];
            arr[target] = tmp;
            heapifyDown(arr, n, target, type);

        }
    }
}

/*
Time Complexity：O(n)
說明：在大約 n/2 個節點上執行 heapifyDown
每個的時間成本與節點高度成正比，但整體攤銷下來的總成本是 O(n)
 */
