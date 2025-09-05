// 檔名：LC19_RemoveNth_Node_Clinic.java

public class LC19_RemoveNth_Node_Clinic {

    // 定義 ListNode，設成 public 避免 Exporting non-public type 警告
    public static class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    // 解題方法
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 建立 dummy 節點，避免刪除頭節點時麻煩
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode fast = dummy;
        ListNode slow = dummy;

        // fast 先走 n+1 步，讓 slow 停在刪除點的前一個
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        // fast 和 slow 一起移動，直到 fast 到底
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // 刪除目標節點
        slow.next = slow.next.next;

        return dummy.next;
    }

    // 測試方法
    public static void main(String[] args) {
        LC19_RemoveNth_Node_Clinic solution = new LC19_RemoveNth_Node_Clinic();

        // 建立測試鏈結串列 [1,2,3,4,5]
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        int n = 2; // 刪除倒數第 2 個節點 → 預期結果 [1,2,3,5]
        ListNode newHead = solution.removeNthFromEnd(head, n);

        // 輸出結果
        System.out.print("結果: ");
        while (newHead != null) {
            System.out.print(newHead.val + " ");
            newHead = newHead.next;
        }
    }
}
