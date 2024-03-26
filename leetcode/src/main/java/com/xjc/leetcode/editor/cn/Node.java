package com.xjc.leetcode.editor.cn;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author JC
 * @create 2024/3/15
 */
public class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }

    public static Node buildTreeFromArray(Integer[] arr) {
        if (arr == null || arr.length == 0 || arr[0] == null) {
            return null;
        }

        Node[] nodes = new Node[arr.length];
        for (int i = 0; i < arr.length; i++) {
            nodes[i] = (arr[i] != null) ? new Node(arr[i]) : null;
        }

        Queue<Node> queue = new LinkedList<>();
        Node root = nodes[0];
        queue.offer(root);
        int i = 1;

        while (i < nodes.length) {
            Node node = queue.poll();

            if (i < nodes.length && nodes[i] != null) {
                node.left = nodes[i];
                queue.offer(node.left);
            }
            i++;

            if (i < nodes.length && nodes[i] != null) {
                node.right = nodes[i];
                queue.offer(node.right);
            }
            i++;
        }

        return root;
    }
}