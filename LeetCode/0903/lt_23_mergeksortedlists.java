
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
import java.util.*;

class Solution {

    public ListNode mergeKLists(ListNode[] lists) {
        //定義優先佇列的比較器
        Comparator<ListNode> cmp;
        cmp = Comparator.comparingInt(o -> o.val);

        //建立佇列
        Queue<ListNode> q = new PriorityQueue<>(cmp);
        for (ListNode l : lists) {
            if (l != null) {
                q.add(l);
            }
        }
        ListNode head = new ListNode(0);
        ListNode point = head;
        while (!q.isEmpty()) {
            //出佇列
            point.next = q.poll();
            point = point.next;
            //判斷當前鏈表是否為空，不為空就將新元素入佇列
            ListNode next = point.next;
            if (next != null) {
                q.add(next);
            }
        }
        return head.next;
    }
}
