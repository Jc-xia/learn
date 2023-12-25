package com.xjc.leetcode.editor.cn;

/**
 * 从前序与中序遍历序列构造二叉树
 *
 * @date 2023-12-12 23:12:52
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public static void main(String[] args) {
        // 题解
        Solution solution = new ConstructBinaryTreeFromPreorderAndInorderTraversal().new Solution();
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        solution.buildTree(preorder, inorder);
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
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            return build(preorder, 0, preorder.length - 1, inorder, 0, preorder.length - 1);
        }

        public TreeNode build(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
            if (preStart > preEnd) {
                return null;
            }
            int index = 0;
            TreeNode root = new TreeNode();
            for (int i = inStart; i <= inEnd; i++) {
                if (inorder[i] == preorder[preStart]) {
                    index = i;
                    root.val = inorder[i];
                    break;
                }
            }
            // ---前序数组分割
            // 左子树开始的位置，就是前序遍历中根节点后第一个
            int leftPreStart = preStart + 1;
            // 左子树结束的位置，从中序遍历中拿到左子树节点个数，(开始位置加节点个数-1) 等价于 (根节点位置 + 节点个数)
            // 节点个数就是中序遍历中，根节点到开始节点的个数
            int leftPreEnd = preStart + index - inStart;
            // 右子树顺延
            int rightPreStart = leftPreEnd + 1;
            int rightPreEnd = preEnd;

            // ---中序数组分割
            int leftInStart = inStart;
            int leftInEnd = index - 1;
            int rithtInStart = index + 1;
            int rightInEnd = inEnd;

            root.left = build(preorder, leftPreStart, leftPreEnd, inorder, leftInStart, leftInEnd);
            root.right = build(preorder, rightPreStart, rightPreEnd, inorder, rithtInStart, rightInEnd);
            return root;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}