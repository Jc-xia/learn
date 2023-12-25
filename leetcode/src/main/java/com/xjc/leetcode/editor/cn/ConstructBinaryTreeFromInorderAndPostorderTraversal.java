package com.xjc.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

/**
 * 从中序与后序遍历序列构造二叉树
 *
 * @date 2023-12-14 21:42:46
 */
public class ConstructBinaryTreeFromInorderAndPostorderTraversal {
    public static void main(String[] args) {
        // 题解
        Solution solution = new ConstructBinaryTreeFromInorderAndPostorderTraversal().new Solution();

        int[] postorder = {9, 15, 7, 20, 3};
        int[] inorder = {9, 3, 15, 20, 7};
        solution.buildTree(inorder, postorder);
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
        Map<Integer, Integer> map = new HashMap<>();

        public TreeNode buildTree(int[] inorder, int[] postorder) {
            // 数组转map，key为值，val为index
            for (int i = 0; i < inorder.length; i++) {
                map.put(inorder[i], i);
            }

            return build(inorder, 0, inorder.length - 1,
                    postorder, 0, postorder.length - 1);
        }

        public TreeNode build(int[] inorder, int inorderBegin, int inorderEnd,
                              int[] postorder, int postorderBegin, int postorderEnd) {
            if (postorderBegin > postorderEnd) {
                return null;
            }
            TreeNode root = new TreeNode(postorder[postorderEnd]);
            int rootIndex = map.get(postorder[postorderEnd]);
            int inorderLeftBegin = inorderBegin;
            int inorderLeftEnd = rootIndex - 1;
            int inorderRightBegin = rootIndex + 1;
            int inorderRightEnd = inorderEnd;

            int postorderLeftBegin = postorderBegin;
            // 开始索引+ 左子树节点个数
            int postorderLeftEnd = postorderLeftBegin + inorderLeftEnd - inorderLeftBegin;
            int postorderRightBegin = postorderLeftEnd + 1;
            int postorderRightEnd = postorderEnd;

            root.left = build(inorder, inorderLeftBegin, inorderLeftBegin,
                    postorder, postorderLeftBegin, postorderLeftEnd);
            root.right = build(inorder, inorderRightBegin, inorderRightEnd,
                    postorder, postorderRightBegin, postorderRightEnd);

            return root;


        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}