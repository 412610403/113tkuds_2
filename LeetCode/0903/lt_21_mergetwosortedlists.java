
class Solution {

    public ListNode

    {

        mergeTwoLists(ListNode list1
        , ListNode list2
            
        ) {
        ListNode dummy = new ListNode(-1); // 假頭節點，避免處理空鏈表的特殊情況
            ListNode tail = dummy; // 指向合併後鏈表的尾節點

            while (list1 != null && list2 != null) {
                if (list1.val < list2.val) {
                    tail.next = new ListNode(list1.val); // 新建節點存放 list1 的值
                    list1 = list1.next;
                } else {
                    tail.next = new ListNode(list2.val); // 新建節點存放 list2 的值
                    list2 = list2.next;
                }
                tail = tail.next; // 更新尾節點
            }

            // 其中一個鏈表剩餘的節點直接接到結果後面
            if (list1 != null) {
                tail.next = list1;
            }
            if (list2 != null) {
                tail.next = list2;
            }

            return dummy.next; // dummy 是假頭，返回真正的頭節點
        }
    }
    /* 解題思路：
1. 使用一個「假頭節點」（dummy）來簡化操作，避免處理結果鏈表為空的特殊情況。
2. 用指標 tail 指向結果鏈表的尾部，依次比較 list1 和 list2 的當前節點值。
   - 如果 list1.val < list2.val，則將 list1 當前值加入結果鏈表，並移動 list1 指標。
   - 否則，將 list2 當前值加入結果鏈表，並移動 list2 指標。
   - 每次新增後更新 tail 指標。
3. 當其中一個鏈表遍歷結束時，直接將另一個鏈表剩餘部分接到結果後面。
4. 返回 dummy.next 作為最終結果（因為 dummy 只是輔助節點）。
時間複雜度：O(m + n)，其中 m, n 分別是 list1 和 list2 的長度。
空間複雜度：O(1)，除了輸出結果外不需要額外空間（如果不用 new 節點，直接重用原鏈表節點）。
     */
