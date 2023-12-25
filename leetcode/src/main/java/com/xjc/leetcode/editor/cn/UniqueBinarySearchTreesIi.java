package com.xjc.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

/**
 * 不同的二叉搜索树 II
 *
 * @date 2023-12-09 17:33:32
 */
public class UniqueBinarySearchTreesIi {
    public static void main(String[] args) {
        // 题解
        Solution solution = new UniqueBinarySearchTreesIi().new Solution();
        solution.generateTrees(2);
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
        public List<TreeNode> generateTrees(int n) {
            int[] paramList = new int[n];
            for (int i = 1; i <= n; i++) {
                paramList[i - 1] = i;
            }
            return generate(paramList);
        }

        public List<TreeNode> generate(int[] paramList) {
            List<TreeNode> res = new ArrayList<>();
            if (paramList.length == 0) {
                return null;
            }

            // 每个数字作为根节点，遍历一遍，当前数字左边的作为左子树，右边的作为右子树
            for (int i = 0; i < paramList.length; i++) {
                int[] left = new int[i];
                int[] right = new int[paramList.length - i - 1];
                // 1、2、5参数代表从 paramList 的第0个位置，复制i个； 2、4参数代表复制到left数组，从0开始
                if (left.length > 0) {
                    System.arraycopy(paramList, 0, left, 0, i);
                }
                if (right.length > 0) {
                    System.arraycopy(paramList, i + 1, right, 0, paramList.length - i - 1);
                }
                List<TreeNode> leftResult = generate(left);
                List<TreeNode> rightResult = generate(right);

                // 填充左子树, 填充了左子树的List<TreeNode>
                List<TreeNode> subLeftRes = new ArrayList<>();
                if (leftResult == null) {
                    subLeftRes.add(new TreeNode(paramList[i], null, null));
                } else {
                    TreeNode root = new TreeNode(paramList[i]);
                    for (TreeNode leftNode : leftResult) {
                        root.left = leftNode;
                        subLeftRes.add(new TreeNode(root.val, root.left, null));
                    }
                }
                for (TreeNode subLeft : subLeftRes) {
                    // 填充右子树
                    if (rightResult == null) {
                        res.add(new TreeNode(subLeft.val, subLeft.left, null));
                    } else {
                        for (TreeNode rightNode : rightResult) {
                            subLeft.right = rightNode;
                            res.add(new TreeNode(subLeft.val, subLeft.left, subLeft.right));
                        }
                    }
                }
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}