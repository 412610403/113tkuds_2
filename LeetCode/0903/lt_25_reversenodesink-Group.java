
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
class Solution {

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        ListNode sub_head = head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode tail = dummy;
        ListNode toNull = head;
        while (sub_head != null) {
            int i = k;
            //找到子鏈表的尾部
            while (i - 1 > 0) {
                toNull = toNull.next;
                if (toNull == null) {
                    return dummy.next;
                }
                i--;
            }
            ListNode temp = toNull.next;
            //將子鏈表斷開
            toNull.next = null;
            ListNode new_sub_head = reverse(sub_head);
            //將倒置後的鏈表接到 tail 後面
            tail.next = new_sub_head;
            //更新 tail 
            tail = sub_head; //sub_head 由於倒置其實是新鏈表的尾部
            sub_head = temp;
            toNull = sub_head;
            //將後面斷開的鏈表接回来
            tail.next = sub_head;
        }
        return dummy.next;
    }

    public ListNode reverse(ListNode head) {
        ListNode current_head = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = current_head;
            current_head = head;
            head = next;
        }
        return current_head;
    }
}
/*
解題思路：
1. 使用「分段翻轉」的思路，每次處理一段 k 個節點。
2. dummy 節點用來簡化操作，tail 指向已處理部分的尾部。
3. 遍歷鏈表：
   - 檢查是否存在 k 個節點，不足 k 個則直接返回結果；
   - 將 k 節點斷開，翻轉後再接回；
   - 更新 tail 與 sub_head，繼續處理下一段。
4. reverse 函數是標準的單鏈表反轉。
時間複雜度 O(N)，空間複雜度 O(1)，其中 N 為鏈表長度。
*/
