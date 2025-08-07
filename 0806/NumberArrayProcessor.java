
import java.util.*;

public class NumberArrayProcessor {

    public static int[] removeDuplicates(int[] arr) {
        Set<Integer> uniqueSet = new LinkedHashSet<>();
        for (int num : arr) {
            uniqueSet.add(num);
        }

        int[] result = new int[uniqueSet.size()];
        int index = 0;
        for (int num : uniqueSet) {
            result[index++] = num;
        }
        return result;
    }

    public static int[] mergeSortedArrays(int[] arr1, int[] arr2) {
        int[] result = new int[arr1.length + arr2.length];
        int i = 0, j = 0, k = 0;

        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] <= arr2[j]) {
                result[k++] = arr1[i++];
            } else {
                result[k++] = arr2[j++];
            }
        }

        while (i < arr1.length) {
            result[k++] = arr1[i++];
        }
        while (j < arr2.length) {
            result[k++] = arr2[j++];
        }

        return result;
    }

    public static int findMostFrequent(int[] arr) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();

        for (int num : arr) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        int mostFrequent = arr[0];
        int maxFrequency = 0;

        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() > maxFrequency) {
                maxFrequency = entry.getValue();
                mostFrequent = entry.getKey();
            }
        }

        return mostFrequent;
    }

    public static int[][] splitArray(int[] arr) {
        int midPoint = (arr.length + 1) / 2;

        int[] firstHalf = new int[midPoint];
        int[] secondHalf = new int[arr.length - midPoint];

        for (int i = 0; i < midPoint; i++) {
            firstHalf[i] = arr[i];
        }

        for (int i = 0; i < secondHalf.length; i++) {
            secondHalf[i] = arr[midPoint + i];
        }

        return new int[][]{firstHalf, secondHalf};
    }

    public static void printArray(int[] arr, String title) {
        System.out.print(title + ": [");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    public static void showFrequency(int[] arr) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : arr) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        System.out.println("頻率統計:");
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            System.out.println("  " + entry.getKey() + " 出現 " + entry.getValue() + " 次");
        }
    }

    public static void main(String[] args) {
        System.out.println("=== 數字陣列處理器測試 ===\n");

        // 測試資料
        int[] testArray1 = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5};
        int[] testArray2 = {7, 2, 8, 2, 8, 1, 8, 2, 8};
        int[] sortedArray1 = {1, 3, 5, 7, 9};
        int[] sortedArray2 = {2, 4, 6, 8, 10, 12};

        printArray(testArray1, "測試陣列1");
        printArray(testArray2, "測試陣列2");
        printArray(sortedArray1, "已排序陣列1");
        printArray(sortedArray2, "已排序陣列2");
        System.out.println();

        System.out.println("1. 移除重複元素:");
        int[] unique1 = removeDuplicates(testArray1);
        int[] unique2 = removeDuplicates(testArray2);
        printArray(unique1, "陣列1去重複後");
        printArray(unique2, "陣列2去重複後");
        System.out.println();

        System.out.println("2. 合併已排序陣列:");
        int[] merged = mergeSortedArrays(sortedArray1, sortedArray2);
        printArray(merged, "合併後陣列");
        System.out.println();

        System.out.println("3. 找出現頻率最高的元素:");
        showFrequency(testArray1);
        int mostFrequent1 = findMostFrequent(testArray1);
        System.out.println("陣列1中最頻繁的元素: " + mostFrequent1);

        showFrequency(testArray2);
        int mostFrequent2 = findMostFrequent(testArray2);
        System.out.println("陣列2中最頻繁的元素: " + mostFrequent2);
        System.out.println();

        System.out.println("4. 分割陣列:");
        int[][] split1 = splitArray(testArray1);
        printArray(split1[0], "陣列1第一半");
        printArray(split1[1], "陣列1第二半");

        int[][] split2 = splitArray(testArray2);
        printArray(split2[0], "陣列2第一半");
        printArray(split2[1], "陣列2第二半");
        System.out.println();

        int[] evenArray = {10, 20, 30, 40, 50, 60};
        System.out.println("5. 偶數長度陣列分割測試:");
        printArray(evenArray, "原始陣列");
        int[][] splitEven = splitArray(evenArray);
        printArray(splitEven[0], "第一半");
        printArray(splitEven[1], "第二半");
    }

}
