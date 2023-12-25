package com.xjc.leetcode.editor.cn;

/**
 * 二叉树展开为链表
 *
 * @date 2023-12-10 17:38:35
 */
public class FlattenBinaryTreeToLinkedList {
    public static void main(String[] args) {
        // 题解
        Solution solution = new FlattenBinaryTreeToLinkedList().new Solution();
        Integer[] para = {1, 2, 5, 3, 4, null, 6};
        TreeNode root = TreeNode.arrayToBTree(para);
        solution.flatten(root);
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
        public void flatten(TreeNode root) {
            if (root == null) {
                return;
            }

            flatten(root.left);
            flatten(root.right);
            // 后序操作
            if (root.left == null) {
                return;
            }
            TreeNode tmp = root.left.right;
            while (tmp != null) {
                // 遍历到拍平后的左子树的最后一个节点
                tmp = tmp.right;
            }
            // 左子树后接上右子树
            tmp = root.right;
            // 左子树移到右边
            root.right = root.left;
            root.left = null;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}