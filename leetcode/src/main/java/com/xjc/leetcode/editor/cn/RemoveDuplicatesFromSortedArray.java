package com.xjc.leetcode.editor.cn;

/**
 * 删除有序数组中的重复项
 *
 * @date 2024-03-15 23:47:11
 */
public class RemoveDuplicatesFromSortedArray {
    public static void main(String[] args) {
        // 题解
        Solution solution = new RemoveDuplicatesFromSortedArray().new Solution();
        int[] ints = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        solution.removeDuplicates(ints);
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {
        public int removeDuplicates(int[] nums) {
            int i = 0, j = 1;
            while (j + 1 < nums.length && i < nums.length) {
                if (nums[i] >= nums[i + 1]) {
                    nums[i + 1] = nums[++j];
                } else {
                    i++;
                }
            }

            return i + 1;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}