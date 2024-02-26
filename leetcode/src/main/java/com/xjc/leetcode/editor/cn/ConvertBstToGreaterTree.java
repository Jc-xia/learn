package com.xjc.leetcode.editor.cn;

/**
 * 把二叉搜索树转换为累加树
 *
 * @date 2024-02-25 23:38:04
 */
public class ConvertBstToGreaterTree {
    public static void main(String[] args) {
        // 题解
        Solution solution = new ConvertBstToGreaterTree().new Solution();
        Integer[] integers = {4, 1, 6, 0, 2, 5, 7, null, null, null, 3, null, null, null, 8};
        TreeNode treeNode = TreeNode.arrayToBTree(integers);
        solution.convertBST(treeNode);
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
        int initial = 0;

        public TreeNode convertBST(TreeNode root) {
            traverse(root);
            return root;
        }

        public void traverse(TreeNode root) {
            if (root == null) {
                return;
            }
            traverse(root.right);
            root.val += initial;
            initial = root.val;
            traverse(root.left);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}