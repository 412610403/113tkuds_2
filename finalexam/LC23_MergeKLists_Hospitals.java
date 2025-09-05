
import java.util.*;

public class LC23_MergeKLists_Hospitals {

    public static class ListNode {

        int val;
        ListNode next;

        ListNode(int v) {
            val = v;
        }
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
        for (ListNode node : lists) {
            if (node != null) {
                pq.add(node);
            }
        }
        ListNode dummy = new ListNode(0), tail = dummy;
        while (!pq.isEmpty()) {
            ListNode cur = pq.poll();
            tail.next = cur;
            tail = tail.next;
            if (cur.next != null) {
                pq.add(cur.next);
            }
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        sc.nextLine();
        ListNode[] lists = new ListNode[k];
        for (int i = 0; i < k; i++) {
            String line = sc.nextLine().trim();
            if (line.equals("-1")) {
                continue;
            }
            String[] parts = line.split("\\s+");
            ListNode dummy = new ListNode(0), cur = dummy;
            for (String s : parts) {
                int v = Integer.parseInt(s);
                if (v == -1) {
                    break;
                }
                cur.next = new ListNode(v);
                cur = cur.next;
            }
            lists[i] = dummy.next;
        }
        ListNode merged = mergeKLists(lists);
        while (merged != null) {
            System.out.print(merged.val + " ");
            merged = merged.next;
        }
    }
}
