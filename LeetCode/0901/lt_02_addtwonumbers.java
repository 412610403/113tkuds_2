// Definition for singly-linked list.

class ListNode {

    int val;          // 節點存的數字
    ListNode next;    // 指向下一個節點

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class Solution {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 建立一個虛擬頭節點(dummy head)，方便操作鏈結串列
        ListNode head = new ListNode(0);
        ListNode p = l1, q = l2, curr = head;
        int carry = 0;  // 進位數

        // 當兩個鏈結串列其中一個還沒走完時，繼續迴圈
        while (p != null || q != null) {
            // 如果節點為空，當作 0
            int x = (p == null ? 0 : p.val);
            int y = (q == null ? 0 : q.val);

            // 計算當前位數的總和
            int sum = x + y + carry;

            // 更新進位
            carry = sum / 10;

            // 建立新節點，數字為 sum 的個位數
            curr.next = new ListNode(sum % 10);

            // curr 往後移動
            curr = curr.next;

            // p、q 分別往後移動
            if (p != null) {
                p = p.next;
            }
            if (q != null) {
                q = q.next;
            }
        }

        // 如果最後還有進位，要補上一個節點
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }

        // 因為 head 是 dummy head，所以真正的結果從 head.next 開始
        return head.next;
    }
}

/* 解題思路：
題目要求將兩個由鏈結串列表示的「反向整數」相加，並回傳一個新的鏈結串列。
1. 使用 dummy head 節點，方便串接結果。
2. 同時遍歷 l1 和 l2，逐位相加，若節點不存在則視為 0。
3. 每次相加需考慮進位 (carry)，並更新下一輪使用。
4. 將結果的個位數建立新節點加入鏈結串列。
5. 最後如果還有進位，額外補上一個節點。
6. 回傳 dummy head.next 即為最終結果。
 */
