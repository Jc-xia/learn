package com.xjc.leetcode.editor.cn;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树的序列化与反序列化
 *
 * @date 2023-12-19 23:32:16
 */
public class SerializeAndDeserializeBinaryTree {
    public static void main(String[] args) {
        // 题解
        Codec solution = new SerializeAndDeserializeBinaryTree().new Codec();
        Integer[] ints = {1, 2, 3, null, null, 4, 5};
        TreeNode treeNode = TreeNode.arrayToBTree(ints);
//        System.out.println(solution.serialize(treeNode));
        solution.deserialize(solution.serialize(treeNode));
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */
    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) {
                return "N";
            }
            String leftStr = serialize(root.left);
            String rightStr = serialize(root.right);
            // 后序操作
            return root.val + "," + leftStr + "," + rightStr;
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            System.out.println(data);
            List<String> list = new LinkedList<>(Arrays.asList(data.split(",")));
            return recurse(list);
        }

        public TreeNode recurse(List<String> list) {
            if (list.size() == 0 || "N".equals(list.get(0))) {
                list.remove(0);
                return null;
            }
            TreeNode root = new TreeNode(Integer.parseInt(list.get(0)));
            list.remove(0);
            root.left = recurse(list);
            root.right = recurse(list);
            return root;
        }


    }

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
//leetcode submit region end(Prohibit modification and deletion)

}