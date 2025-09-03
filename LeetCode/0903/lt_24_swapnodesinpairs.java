
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

    public ListNode swapPairs(ListNode head) {
        // base case 退出提交
        if (head == null || head.next == null) {
            return head;
        }
        // 獲取當前節點的下一個節點
        ListNode next = head.next;
        // 進行遞迴
        ListNode newNode = swapPairs(next.next);
        // 進行行交換
        next.next = head;
        head.next = newNode;

        return next;
    }
}
/*
解題思路：
1. 使用遞迴解法，每次交換相鄰兩個節點。
2. 基本情況：若鏈表長度小於 2，直接返回 head。
3. 交換過程：
   - 記錄第二個節點 next；
   - 對剩餘鏈表遞迴調用；
   - 讓第二個節點指向第一個節點，第一個節點指向遞迴結果。
4. 最終返回交換後的新頭節點。
時間複雜度 O(N)，空間複雜度 O(N)（遞迴棧空間）。
*/
