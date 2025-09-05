
import java.util.*;

public class LC04_Median_QuakeFeeds {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        double[] A = new double[n], B = new double[m];
        for (int i = 0; i < n; i++) {
            A[i] = sc.nextDouble();
        }
        for (int j = 0; j < m; j++) {
            B[j] = sc.nextDouble();
        }

        if (n > m) { // 保證 A 是短的
            double[] tmp = A;
            A = B;
            B = tmp;
            int t = n;
            n = m;
            m = t;
        }

        int imin = 0, imax = n, half = (n + m + 1) / 2;
        while (imin <= imax) {
            int i = (imin + imax) / 2;
            int j = half - i;
            if (i < n && B[j - 1] > A[i]) {
                imin = i + 1; // i 太小
            } else if (i > 0 && A[i - 1] > B[j]) {
                imax = i - 1; // i 太大
            } else {
                double maxLeft;
                if (i == 0) {
                    maxLeft = B[j - 1]; 
                }else if (j == 0) {
                    maxLeft = A[i - 1]; 
                }else {
                    maxLeft = Math.max(A[i - 1], B[j - 1]);
                }

                if ((n + m) % 2 == 1) {
                    System.out.printf("%.1f\n", maxLeft);
                    return;
                }

                double minRight;
                if (i == n) {
                    minRight = B[j]; 
                }else if (j == m) {
                    minRight = A[i]; 
                }else {
                    minRight = Math.min(A[i], B[j]);
                }

                System.out.printf("%.1f\n", (maxLeft + minRight) / 2.0);
                return;
            }
        }
    }
}

/*解題思路：
使用二分搜尋切割法 (Median of Two Sorted Arrays)。
保證在短陣列上做二分，嘗試找到切割點使左半部與右半部平衡，並檢查邊界條件。
若總長度為奇數，回傳左半最大；若偶數，回傳左右中間兩數平均。
時間 O(log(min(n,m)))，空間 O(1)。
 */
