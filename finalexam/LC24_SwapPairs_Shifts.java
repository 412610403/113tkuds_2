
import java.util.*;

public class LC24_SwapPairs_Shifts {

    public static class ListNode {

        int val;
        ListNode next;

        ListNode(int v) {
            val = v;
        }
    }

    public static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        while (prev.next != null && prev.next.next != null) {
            ListNode a = prev.next;
            ListNode b = a.next;
            a.next = b.next;
            b.next = a;
            prev.next = b;
            prev = a;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine().trim();
        if (line.isEmpty()) {
            return;
        }
        String[] parts = line.split("\\s+");
        ListNode dummy = new ListNode(0), cur = dummy;
        for (String s : parts) {
            cur.next = new ListNode(Integer.parseInt(s));
            cur = cur.next;
        }
        ListNode head = swapPairs(dummy.next);
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
    }
}
