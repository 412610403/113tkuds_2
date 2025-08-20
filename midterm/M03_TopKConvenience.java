
import java.util.*;

public class M03_TopKConvenience {

    static class Item {

        String name;
        int qty;
        int index;

        Item(String name, int qty, int index) {
            this.name = name;
            this.qty = qty;
            this.index = index;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 商品數量
        int K = sc.nextInt(); // Top-K
        List<Item> items = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String name = sc.next();
            int qty = sc.nextInt();
            items.add(new Item(name, qty, i));
        }

        // Min-Heap、Top-K
        // 輸入的先後
        PriorityQueue<Item> pq = new PriorityQueue<>(K, (a, b) -> {
            if (a.qty != b.qty) {
                return a.qty - b.qty;
            }
            return a.index - b.index;
        });

        for (Item it : items) {
            pq.offer(it);
            if (pq.size() > K) {
                pq.poll(); // 移除最小的 維持大小 K
            }
        }

        List<Item> result = new ArrayList<>(pq);

        // qty 大在前 若相同則依 index
        result.sort((a, b) -> {
            if (b.qty != a.qty) {
                return b.qty - a.qty;
            }
            return a.index - b.index;
        });

        for (Item it : result) {
            System.out.println(it.name + " " + it.qty);
        }
    }
}
/*
Time Complexity：O(nlog K + K log ⁡K)≈O(n log K)
說明：每次 offer 操作 O(log K)
總共 n 筆 → O(n log K)
輸出排序：取出 K 筆再排序 O(K log K)
*/
