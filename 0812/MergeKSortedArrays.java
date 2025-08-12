
import java.util.*;

class Element implements Comparable<Element> {

    int value;
    int arrayIndex;
    int elementIndex;

    public Element(int value, int arrayIndex, int elementIndex) {
        this.value = value;
        this.arrayIndex = arrayIndex;
        this.elementIndex = elementIndex;
    }

    @Override
    public int compareTo(Element other) {
        return Integer.compare(this.value, other.value);
    }
}

public class MergeKSortedArrays {

    public static int[] mergeKSortedArrays(int[][] arrays) {
        PriorityQueue<Element> minHeap = new PriorityQueue<>();

        // 將每個陣列的第一個元素加入 minHeap
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i].length > 0) {
                minHeap.offer(new Element(arrays[i][0], i, 0));
                System.out.println("加入元素: " + arrays[i][0] + " (來自陣列 " + i + ")");
            }
        }

        List<Integer> result = new ArrayList<>();

        while (!minHeap.isEmpty()) {
            Element current = minHeap.poll();
            result.add(current.value);
            System.out.println("取出最小值: " + current.value);

            // 加入同陣列的下一個元素（如果存在）
            if (current.elementIndex + 1 < arrays[current.arrayIndex].length) {
                int nextIndex = current.elementIndex + 1;
                int nextValue = arrays[current.arrayIndex][nextIndex];
                minHeap.offer(new Element(nextValue, current.arrayIndex, nextIndex));
                System.out.println("  加入下一元素: " + nextValue + " (來自陣列 " + current.arrayIndex + ")");
            }
        }

        // 轉換成陣列輸出
        return result.stream().mapToInt(i -> i).toArray();
    }

    // 測試
    public static void main(String[] args) {
        testCase(new int[][]{{1, 4, 5}, {1, 3, 4}, {2, 6}}, "[1, 1, 2, 3, 4, 4, 5, 6]");
        testCase(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}, "[1, 2, 3, 4, 5, 6, 7, 8, 9]");
        testCase(new int[][]{{1}, {0}}, "[0, 1]");
    }

    private static void testCase(int[][] arrays, String expected) {
        System.out.println("=== 測試輸入 ===");
        for (int i = 0; i < arrays.length; i++) {
            System.out.println("陣列 " + i + ": " + Arrays.toString(arrays[i]));
        }

        int[] result = mergeKSortedArrays(arrays);
        System.out.println("合併結果: " + Arrays.toString(result));
        System.out.println("期望輸出: " + expected);
        System.out.println(Arrays.toString(result).equals(expected) ? "✅ 測試通過" : "⚠️ 結果請確認");
        System.out.println();
    }
}
