
import java.util.*;

public class AdvancedArrayRecursion {

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivot = partition(arr, low, high);
            quickSort(arr, low, pivot - 1);
            quickSort(arr, pivot + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int[] mergeSortedArrays(int[] arr1, int[] arr2) {
        int[] result = new int[arr1.length + arr2.length];
        mergeHelper(arr1, 0, arr2, 0, result, 0);
        return result;
    }

    private static void mergeHelper(int[] arr1, int i1, int[] arr2, int i2, int[] result, int k) {
        // 基礎情況：其中一個陣列已完全處理
        if (i1 >= arr1.length) {
            // 複製 arr2 的剩餘元素
            while (i2 < arr2.length) {
                result[k++] = arr2[i2++];
            }
            return;
        }
        if (i2 >= arr2.length) {
            // 複製 arr1 的剩餘元素
            while (i1 < arr1.length) {
                result[k++] = arr1[i1++];
            }
            return;
        }

        if (arr1[i1] <= arr2[i2]) {
            result[k] = arr1[i1];
            mergeHelper(arr1, i1 + 1, arr2, i2, result, k + 1);
        } else {
            result[k] = arr2[i2];
            mergeHelper(arr1, i1, arr2, i2 + 1, result, k + 1);
        }
    }

    public static int findKthSmallest(int[] arr, int k) {
        return quickSelect(arr.clone(), 0, arr.length - 1, k - 1);
    }

    private static int quickSelect(int[] arr, int low, int high, int k) {
        if (low == high) {
            return arr[low];
        }

        int pivot = partition(arr, low, high);

        if (k == pivot) {
            return arr[k];
        } else if (k < pivot) {
            return quickSelect(arr, low, pivot - 1, k);
        } else {
            return quickSelect(arr, pivot + 1, high, k);
        }
    }

    public static boolean hasSubsetSum(int[] arr, int target) {
        return hasSubsetSumHelper(arr, 0, target);
    }

    private static boolean hasSubsetSumHelper(int[] arr, int index, int target) {
        if (target == 0) {
            return true;
        }
        if (index >= arr.length || target < 0) {
            return false;
        }

        return hasSubsetSumHelper(arr, index + 1, target - arr[index])
                || hasSubsetSumHelper(arr, index + 1, target);
    }

    public static void printArray(int[] arr, String title) {
        System.out.println(title + ": " + Arrays.toString(arr));
    }

    public static List<Integer> findSubsetWithSum(int[] arr, int target) {
        List<Integer> result = new ArrayList<>();
        if (findSubsetHelper(arr, 0, target, result)) {
            return result;
        }
        return null;
    }

    private static boolean findSubsetHelper(int[] arr, int index, int target, List<Integer> current) {
        if (target == 0) {
            return true;
        }
        if (index >= arr.length || target < 0) {
            return false;
        }

        current.add(arr[index]);
        if (findSubsetHelper(arr, index + 1, target - arr[index], current)) {
            return true;
        }
        current.remove(current.size() - 1);

        return findSubsetHelper(arr, index + 1, target, current);
    }

    public static void main(String[] args) {
        System.out.println("=== 陣列遞迴操作進階 ===\n");

        int[] testArray = {64, 34, 25, 12, 22, 11, 90, 5};
        int[] sortedArray1 = {1, 5, 9, 13, 17};
        int[] sortedArray2 = {3, 7, 11, 15, 19, 21};
        int[] subsetArray = {3, 34, 4, 12, 5, 2};

        System.out.println("1. 遞迴快速排序:");
        printArray(testArray, "原始陣列");
        int[] quickSortArray = testArray.clone();
        quickSort(quickSortArray, 0, quickSortArray.length - 1);
        printArray(quickSortArray, "快速排序後");
        System.out.println();

        System.out.println("2. 遞迴合併已排序陣列:");
        printArray(sortedArray1, "已排序陣列1");
        printArray(sortedArray2, "已排序陣列2");
        int[] merged = mergeSortedArrays(sortedArray1, sortedArray2);
        printArray(merged, "合併結果");
        System.out.println();

        System.out.println("3. 尋找第 k 小元素:");
        printArray(testArray, "測試陣列");
        for (int k = 1; k <= 5; k++) {
            int kthSmallest = findKthSmallest(testArray, k);
            System.out.printf("第 %d 小的元素: %d\n", k, kthSmallest);
        }
        System.out.println();

        System.out.println("4. 子序列總和檢查:");
        printArray(subsetArray, "測試陣列");
        int[] targets = {9, 11, 15, 30, 100};

        for (int target : targets) {
            boolean exists = hasSubsetSum(subsetArray, target);
            System.out.printf("目標和 %d: %s", target, exists ? "存在" : "不存在");

            if (exists) {
                List<Integer> subset = findSubsetWithSum(subsetArray, target);
                if (subset != null) {
                    System.out.printf(" -> %s", subset);
                }
            }
            System.out.println();
        }

        System.out.println("\n=== 效能測試 ===");
        int[] largeArray = new int[1000];
        Random rand = new Random();
        for (int i = 0; i < largeArray.length; i++) {
            largeArray[i] = rand.nextInt(1000);
        }

        long startTime = System.nanoTime();
        quickSort(largeArray.clone(), 0, largeArray.length - 1);
        long quickSortTime = System.nanoTime() - startTime;

        System.out.printf("快速排序 1000 個元素耗時: %.2f ms\n", quickSortTime / 1_000_000.0);

        startTime = System.nanoTime();
        int result = findKthSmallest(largeArray, 500);
        long selectTime = System.nanoTime() - startTime;

        System.out.printf("尋找第 500 小元素耗時: %.2f ms\n", selectTime / 1_000_000.0);
        System.out.printf("第 500 小的元素: %d\n", result);
    }

}
