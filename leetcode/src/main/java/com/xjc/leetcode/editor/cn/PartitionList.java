package com.xjc.leetcode.editor.cn;

/**
 * 分隔链表
 *
 * @date 2024-03-05 21:45:50
 */
public class PartitionList {
    public static void main(String[] args) {
        // 题解
        Solution solution = new PartitionList().new Solution();
        int[] ints = {1, 4, 3, 2, 5, 2};
        ListNode listNode = ListNode.arrToList(ints);
        solution.partition(listNode, 3);
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class Solution {
        public ListNode partition(ListNode head, int x) {
            ListNode dummy = new ListNode(-201, head);
            // first节点，用来记录从后面摘除的节点，应该放在first节点后
            ListNode first = dummy, pre = dummy, cur = head;

            while (cur != null) {
                // 大于，pre,cur前进
                if (cur.val >= x) {
                    cur = cur.next;
                    pre = pre.next;
                } else {
                    // 小于x的节点
                    if (pre.val < x) {
                        // 前一节点也小于x，则同时后移即可
                        pre = pre.next;
                        cur = cur.next;
                        first = pre;
                    } else {
                        // 节点的前一节点大于x，则进行摘除cur节点操作，pre不动，
                        // 摘除并移动到first后，first,cur指针同时后移
                        pre.next = cur.next;
                        cur.next = first.next;
                        first.next = cur;
                        first = first.next;
                        cur = pre.next;
                    }
                }
            }
            return dummy.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}