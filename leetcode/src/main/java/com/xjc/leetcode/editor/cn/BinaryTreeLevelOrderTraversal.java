package com.xjc.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树的层序遍历
 *
 * @date 2023-12-08 23:10:47
 */
public class BinaryTreeLevelOrderTraversal {
    public static void main(String[] args) {
        // 题解
        Solution solution = new BinaryTreeLevelOrderTraversal().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    class Solution {
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> res = new ArrayList<>();
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (queue.size() > 0) {
                List<Integer> subList = new ArrayList<>();
                // 定义变量存储queue size，循环中size会变化
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode node = queue.poll();
                    if (node == null) {
                        break;
                    }
                    subList.add(node.val);
                    if (!(node.left == null)) {
                        queue.offer(node.left);
                    }
                    if (!(node.right == null)) {
                        queue.offer(node.right);
                    }
                }
                res.add(subList);
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}