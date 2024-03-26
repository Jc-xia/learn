package com.xjc.leetcode.editor.cn;

/**
 * @author JC
 * @create 2024/3/5
 */
public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static ListNode arrToList(int[] arr) {
        ListNode dummy = new ListNode(-1, null);
        ListNode root = dummy;
        for (int i : arr) {
            root.next = new ListNode(i);
            root = root.next;
        }
        return dummy.next;
    }
}
