package com.xjc.leetcode.editor.cn;

/**
 * 填充每个节点的下一个右侧节点指针 II
 *
 * @date 2024-03-15 22:38:47
 */
public class PopulatingNextRightPointersInEachNodeIi {
    public static void main(String[] args) {
        // 题解
        Solution solution = new PopulatingNextRightPointersInEachNodeIi().new Solution();
        Integer[] integers = {2, 1, 3, 0, 7, 9, 1, 2, null, 1, 0, null, null, 8, 8, null, null, null, null, 7};
        solution.connect(Node.buildTreeFromArray(integers));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

    class Solution {
        public Node connect(Node root) {
            if (root == null) {
                return null;
            }

            connect(root.right);

            if (root.left != null) {
                if (root.right != null) {
                    // 当前父节点的右子树不为空
                    root.left.next = root.right;
                } else {
                    // 向右边的树继续查找，直到没有next节点
                    root.left.next = findRight(root.next);
                }
            }
            if (root.right != null) {
                root.right.next = findRight(root.next);
            }
            connect(root.left);

            return root;

        }

        public Node findRight(Node root) {
            if (root == null) {
                return null;
            }
            System.out.println(root.val);
            if (root.next != null) {
                System.out.println(root.next.val);

            } else {
                System.out.println("null");
            }

            if (root.left != null) {
                return root.left;
            } else if (root.right != null) {
                return root.right;
            } else {
                // 递归向右继续查找
                return findRight(root.next);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}