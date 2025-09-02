
import java.util.*;

public class M11_HeapSortWithTie {

    static class Student {

        int score;
        int index;

        Student(int score, int index) {
            this.score = score;
            this.index = index;
        }
    }

    public static void heapSort(Student[] arr) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            Student temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i, 0);
        }
    }

    private static void heapify(Student[] arr, int heapSize, int root) {
        int largest = root;
        int left = 2 * root + 1;
        int right = 2 * root + 2;

        if (left < heapSize && compare(arr[left], arr[largest]) > 0) {
            largest = left;
        }

        if (right < heapSize && compare(arr[right], arr[largest]) > 0) {
            largest = right;
        }

        if (largest != root) {
            Student temp = arr[root];
            arr[root] = arr[largest];
            arr[largest] = temp;

            heapify(arr, heapSize, largest);
        }
    }

    // 先比 score（大者優先），若相同則index小者優先
    private static int compare(Student a, Student b) {
        if (a.score != b.score) {
            return a.score - b.score; // 大分數要優先，在 max-heap 中比大小
        } else {
            return b.index - a.index;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        Student[] arr = new Student[n];
        for (int i = 0; i < n; i++) {
            int score = sc.nextInt();
            arr[i] = new Student(score, i);
        }

        heapSort(arr);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(arr[i].score);
            if (i < n - 1) {
                sb.append(" ");
            }
        }
        System.out.println(sb.toString());
        sc.close();

        Student[] students = {
            new Student(90, 0),
            new Student(75, 1),
            new Student(90, 2),
            new Student(60, 3),
            new Student(80, 4)
        };

    }
}

/*
 Time Complexity：O(n log n)
 說明：建堆過程 O(n)
 每次取出最大值 n × O(log n) = O(n log n)
 O(n + n log n) = O(n log n)
 */
