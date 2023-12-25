package com.xjc.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 寻找重复的子树
 *
 * @date 2023-12-24 11:42:18
 */
public class FindDuplicateSubtrees {
    public static void main(String[] args) {
        // 题解
        Solution solution = new FindDuplicateSubtrees().new Solution();
        solution.findDuplicateSubtrees(TreeNode.buildTreeFromArray(new Integer[]{0, 0, 0, 0, null, null, 0, 0, 0, 0, 0}));
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
        Map<String, Integer> map = new HashMap<>();
        List<TreeNode> res = new ArrayList<>();

        public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
            traverse(root);
            return res;
        }


        public String traverse(TreeNode root) {
            if (root == null) {
                return "N";
            }
            String left = traverse(root.left);
            String right = traverse(root.right);
            if (!"N".equals(left)) {
                if (map.containsKey(left)) {
                    // 官方解法这里的value值，存储的是treenode，当有重复的序列时，res中放入的不是新节点
                    // 而是放入map中存储的value，res用set类型，自动去重
                    // （类型的子树，只放入一个就行，数组表示法的情况下，他们根节点的值一定是一样的，所以答案没影响）
                    if (map.get(left) == 0) {
                        res.add(root.left);
                        map.put(left, 1);
                    }
                } else {
                    map.put(left, 0);
                }
            }
            if (!"N".equals(right)) {
                if (map.containsKey(right)) {
                    if (map.get(right) == 0) {
                        res.add(root.left);
                        map.put(right, 1);
                    }
                } else {
                    map.put(right, 0);
                }
            }
            return root.val + "," + left + "," + right;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}