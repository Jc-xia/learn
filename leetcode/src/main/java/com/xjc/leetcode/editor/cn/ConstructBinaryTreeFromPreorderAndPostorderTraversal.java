package com.xjc.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

/**
 * 根据前序和后序遍历构造二叉树
 *
 * @date 2023-12-14 22:50:43
 */
public class ConstructBinaryTreeFromPreorderAndPostorderTraversal {
    public static void main(String[] args) {
        // 题解
        Solution solution = new ConstructBinaryTreeFromPreorderAndPostorderTraversal().new Solution();
        int[] postorder = {4, 5, 2, 6, 7, 3, 1};
        int[] preorder = {1, 2, 4, 5, 3, 6, 7};
        solution.constructFromPrePost(preorder, postorder);
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
        Map<Integer, Integer> preorderMap = new HashMap<>();
        Map<Integer, Integer> postorderMap = new HashMap<>();

        public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
            // 数组转map，key为值，val为index
            for (int i = 0; i < preorder.length; i++) {
                preorderMap.put(preorder[i], i);
            }

            // 数组转map，key为值，val为index
            for (int i = 0; i < postorder.length; i++) {
                postorderMap.put(postorder[i], i);
            }

            return build(preorder, 0, postorder, postorder.length - 1);
        }

        public TreeNode build(int[] preorder, int preorderRootIndex, int[] postorder, int postorderRootIndex) {

            if (preorderRootIndex >= preorder.length) {
                return null;
            }
            if (postorderRootIndex < 0) {
                return null;
            }


            TreeNode root = new TreeNode(preorder[preorderRootIndex]);
            if (preorderRootIndex + 1 < preorder.length && postorderRootIndex - 1 >= 0) {
                // 从前序中拿到左子树根节点（当前根节点下一个）， 其在后序遍历的索引通过map获取
                int index1 = postorderMap.get(preorder[preorderRootIndex + 1]);
                root.left = build(preorder, preorderRootIndex + 1, postorder, index1);

                // 从后序中拿到右子树根节点（当前根节点前一个），其在前序遍历的索引通过map获取
                int index2 = preorderMap.get(postorder[postorderRootIndex - 1]);
                root.right = build(preorder, index2, postorder, postorderRootIndex - 1);

            }
            return root;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}