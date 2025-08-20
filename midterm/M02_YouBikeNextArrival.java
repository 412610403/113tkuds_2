
import java.util.*;

public class M02_YouBikeNextArrival {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();   // 時間筆數

        // 限制 1 <= n <= 200
        if (n < 1 || n > 200) {
            System.out.println("Invalid input: n must be between 1 and 200.");
            return; // 直接結束程式
        }

        int[] times = new int[n];
        for (int i = 0; i < n; i++) {
            String token = sc.next();
            times[i] = toMinutes(token);
        }

        String queryStr = sc.next();
        int query = toMinutes(queryStr);

        int idx = lowerBound(times, query);
        if (idx == -1) {
            System.out.println("No bike");
        } else {
            System.out.println(toHHMM(times[idx]));
        }
    }

    private static String toHHMM(int minutes) {
        int h = minutes / 60, m = minutes % 60;
        return String.format("%02d:%02d", h, m);
    }

    private static int toMinutes(String t) {
        String[] p = t.split(":");
        return Integer.parseInt(p[0]) * 60 + Integer.parseInt(p[1]);
    }

    private static int lowerBound(int[] a, int target) {
        int l = 0, r = a.length - 1, ans = -1;
        while (l <= r) {
            int mid = (l + r) >>> 1;
            if (a[mid] >= target) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }
}
