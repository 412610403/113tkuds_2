
import java.util.Arrays;

public class SelectionSortImplementation {

    private static int comparisons = 0;
    private static int swaps = 0;

    public static void selectionSort(int[] arr) {
        int n = arr.length;
        comparisons = 0;
        swaps = 0;

        System.out.println("開始選擇排序:");
        System.out.println("初始陣列: " + Arrays.toString(arr));

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                comparisons++;
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                swap(arr, i, minIndex);
                swaps++;
            }

            System.out.printf("第 %d 輪: %s (找到最小值 %d)\n",
                    i + 1, Arrays.toString(arr), arr[i]);
        }

        System.out.println("排序完成!");
        System.out.println("最終結果: " + Arrays.toString(arr));
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        int bubbleComparisons = 0;
        int bubbleSwaps = 0;

        System.out.println("\n開始氣泡排序:");
        System.out.println("初始陣列: " + Arrays.toString(arr));

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                bubbleComparisons++;
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    bubbleSwaps++;
                    swapped = true;
                }
            }

            System.out.printf("第 %d 輪: %s\n", i + 1, Arrays.toString(arr));

            if (!swapped) {
                break;
            }
        }

        System.out.println("氣泡排序完成!");
        System.out.println("比較次數: " + bubbleComparisons);
        System.out.println("交換次數: " + bubbleSwaps);
    }

    public static void showStatistics() {
        System.out.println("\n=== 選擇排序統計 ===");
        System.out.println("比較次數: " + comparisons);
        System.out.println("交換次數: " + swaps);
    }

    public static void performanceComparison(int[] originalArray) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("           效能比較測試");
        System.out.println("=".repeat(50));

        int[] arr1 = originalArray.clone();
        long startTime = System.nanoTime();
        selectionSort(arr1);
        long selectionTime = System.nanoTime() - startTime;
        int selectionComparisons = comparisons;
        int selectionSwaps = swaps;

        int[] arr2 = originalArray.clone();
        startTime = System.nanoTime();
        bubbleSort(arr2);
        long bubbleTime = System.nanoTime() - startTime;

        System.out.println("\n=== 效能比較結果 ===");
        System.out.printf("%-15s %-10s %-10s %-15s\n", "排序方法", "比較次數", "交換次數", "執行時間(ns)");
        System.out.println("-".repeat(55));
        System.out.printf("%-15s %-10d %-10d %-15d\n", "選擇排序", selectionComparisons, selectionSwaps, selectionTime);
        System.out.printf("%-15s %-10s %-10s %-15d\n", "氣泡排序", "較多", "較多", bubbleTime);

        System.out.println("\n分析:");
        System.out.println("• 選擇排序比較次數固定: O(n²)");
        System.out.println("• 選擇排序交換次數較少: O(n)");
        System.out.println("• 氣泡排序比較和交換都較多");
    }

    public static void main(String[] args) {
        System.out.println("=== 選擇排序演算法實作 ===\n");

        int[] testArray1 = {64, 34, 25, 12, 22, 11, 90};
        int[] testArray2 = {5, 2, 8, 1, 9, 3, 7, 4, 6};

        System.out.println("測試 1: 基本選擇排序");
        selectionSort(testArray1.clone());
        showStatistics();

        System.out.println("\n" + "=".repeat(40));

        System.out.println("測試 2: 另一組資料");
        selectionSort(testArray2.clone());
        showStatistics();

        performanceComparison(testArray2);

        System.out.println("\n=== 邊界情況測試 ===");

        int[] singleElement = {42};
        System.out.println("單一元素陣列:");
        selectionSort(singleElement.clone());

        int[] sortedArray = {1, 2, 3, 4, 5};
        System.out.println("\n已排序陣列:");
        selectionSort(sortedArray.clone());
        showStatistics();

        int[] reverseArray = {5, 4, 3, 2, 1};
        System.out.println("\n反向排序陣列:");
        selectionSort(reverseArray.clone());
        showStatistics();
    }

}
