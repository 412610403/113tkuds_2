
import java.util.*;

public class LC25_ReverseKGroup_Shifts {

    public static class ListNode {

        int val;
        ListNode next;

        ListNode(int v) {
            val = v;
        }
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        while (true) {
            ListNode node = prev;
            for (int i = 0; i < k && node != null; i++) {
                node = node.next;
            }
            if (node == null) {
                break;
            }

            ListNode start = prev.next;
            ListNode next = node.next;
            node.next = null;

            prev.next = reverse(start);
            start.next = next;
            prev = start;
        }
        return dummy.next;
    }

    private static ListNode reverse(ListNode head) {
        ListNode prev = null, cur = head;
        while (cur != null) {
            ListNode nxt = cur.next;
            cur.next = prev;
            prev = cur;
            cur = nxt;
        }
        return prev;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        sc.nextLine();
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
        ListNode head = reverseKGroup(dummy.next, k);
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
    }
}
