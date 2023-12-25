package com.xjc.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author JC
 * @create 2023/11/21
 */
public class TreeNode {
    Integer val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(Integer val) {
        this.val = val;
    }

    TreeNode(Integer val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }


    public static TreeNode arrayToBTree(Integer[] arrs) {
        if (arrs == null || arrs.length == 0) {
            return new TreeNode();
        }

        List<TreeNode> nodes = new ArrayList<>(arrs.length);
        for (Integer obj : arrs) {
            TreeNode treeNode = new TreeNode();
            treeNode.val = obj;
            nodes.add(treeNode);
        }
        for (int i = 0; i < arrs.length / 2 - 1; i++) {
            TreeNode node = nodes.get(i);
            node.left = nodes.get(i * 2 + 1);
            node.right = nodes.get(i * 2 + 2);
        }
        // 只有当总节点数是奇数时，最后一个父节点才有右子节点
        int lastPNodeIndex = arrs.length / 2 - 1;
        TreeNode lastPNode = nodes.get(lastPNodeIndex);
        lastPNode.left = nodes.get(lastPNodeIndex * 2 + 1);
        if (arrs.length % 2 != 0) {
            lastPNode.right = nodes.get(lastPNodeIndex * 2 + 2);
        }
        return nodes.get(0);
    }


    public static TreeNode buildTreeFromArray(Integer[] arr) {
        if (arr == null || arr.length == 0 || arr[0] == null) {
            return null;
        }

        TreeNode[] nodes = new TreeNode[arr.length];
        for (int i = 0; i < arr.length; i++) {
            nodes[i] = (arr[i] != null) ? new TreeNode(arr[i]) : null;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = nodes[0];
        queue.offer(root);
        int i = 1;

        while (i < nodes.length) {
            TreeNode node = queue.poll();

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

