package com.xjc.leetcode.editor.cn;

/**
 * 二叉树中的最大路径和
 *
 * @date 2023-12-05 23:25:45
 */
public class JC7MId {
    public static void main(String[] args) {
        // 题解
        Solution solution = new JC7MId().new Solution();
        Integer[] ints = {2, -1};
        TreeNode treeNode = TreeNode.arrayToBTree(ints);
        solution.maxPathSum(treeNode);
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
        int maxNum = 0;

        public int maxPathSum(TreeNode root) {
            if (root == null) {
                return maxNum;
            }
            // 出现负数的情况
            if (root.val < 0) {
                maxNum = root.val;
            }
            traverse(root);
            return maxNum;
        }

        public void traverse(TreeNode root) {
            if (root == null) {
                return;
            }
            int currentVal = root.val + Math.max(oneSideMaxSum(root.left), 0) + Math.max(oneSideMaxSum(root.right), 0);
            maxNum = Math.max(maxNum, currentVal);
            traverse(root.left);
            traverse(root.right);
        }

        public int oneSideMaxSum(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int left = 0;
            int right = 0;
            if (root.left != null) {
                left = oneSideMaxSum(root.left);
            }
            if (root.right != null) {
                right = oneSideMaxSum(root.right);
            }
            // 只能加一边 ，如果最大的一边路径和还是小于0 ，则加0
            int max = Math.max(left, right);
            return root.val + Math.max(max, 0);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}