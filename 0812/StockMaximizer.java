
import java.util.*;

public class StockMaximizer {

    public int maxProfit(int[] prices, int k) {
        // Max Heap 儲存所有可獲利的交易利潤
        PriorityQueue<Integer> profitHeap = new PriorityQueue<>(Collections.reverseOrder());

        int n = prices.length;
        int i = 0;

        while (i < n - 1) {
            // 找到當前的低點（買入點）
            while (i < n - 1 && prices[i] >= prices[i + 1]) {
                i++;
            }
            int buy = i;

            // 找到當前的高點（賣出點）
            while (i < n - 1 && prices[i] <= prices[i + 1]) {
                i++;
            }
            int sell = i;

            // 計算利潤
            if (buy < sell) {
                int profit = prices[sell] - prices[buy];
                profitHeap.offer(profit);
            }

            i++;
        }

        // 取前 K 大的利潤總和
        int totalProfit = 0;
        for (int j = 0; j < k && !profitHeap.isEmpty(); j++) {
            totalProfit += profitHeap.poll();
        }

        return totalProfit;
    }

    // 測試主程式
    public static void main(String[] args) {
        StockMaximizer sm = new StockMaximizer();

        System.out.println("=== 測試案例 1 ===");
        int[] prices1 = {2, 4, 1};
        int k1 = 2;
        System.out.println("輸入: " + Arrays.toString(prices1) + ", K = " + k1);
        System.out.println("最大利潤: " + sm.maxProfit(prices1, k1));  // ➜ 2

        System.out.println("\n=== 測試案例 2 ===");
        int[] prices2 = {3, 2, 6, 5, 0, 3};
        int k2 = 2;
        System.out.println("輸入: " + Arrays.toString(prices2) + ", K = " + k2);
        System.out.println("最大利潤: " + sm.maxProfit(prices2, k2));  // ➜ 7

        System.out.println("\n=== 測試案例 3 ===");
        int[] prices3 = {1, 2, 3, 4, 5};
        int k3 = 2;
        System.out.println("輸入: " + Arrays.toString(prices3) + ", K = " + k3);
        System.out.println("最大利潤: " + sm.maxProfit(prices3, k3));  // ➜ 4
    }
}
