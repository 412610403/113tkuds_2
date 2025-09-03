
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

    public ListNode removeNthFromEnd(ListNode head, int n) {
        //新建一個虚擬頭節點指向head
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;
        //快慢指針指向虚擬頭節點
        ListNode fastIndex = dummyNode;
        ListNode slowIndex = dummyNode;

        // 只要快慢指針相差n個節點即可
        for (int i = 0; i <= n; i++) {
            fastIndex = fastIndex.next;
        }
        while (fastIndex != null) {
            fastIndex = fastIndex.next;
            slowIndex = slowIndex.next;
        }

        // 此時 slowIndex 的位置就是待刪除元素的前一個位置
        // 檢查 slowIndex.next 是否為 null，以避免空指針異常
        if (slowIndex.next != null) {
            slowIndex.next = slowIndex.next.next;
        }
        return dummyNode.next;
    }
}
/*
解題思路：
1. 使用「虛擬頭節點」dummyNode 簡化刪除操作，避免刪除頭節點時需要特殊處理。
2. 設置「快慢指針」：先讓 fastIndex 前進 n+1 步，保證快慢指針相距 n。
3. 同時移動快慢指針，直到 fastIndex 到達鏈表尾端，此時 slowIndex 剛好在待刪除節點的前一個位置。
4. 修改 slowIndex.next 指向，完成刪除。
時間複雜度 O(L)，空間複雜度 O(1)，其中 L 是鏈表長度。
*/
