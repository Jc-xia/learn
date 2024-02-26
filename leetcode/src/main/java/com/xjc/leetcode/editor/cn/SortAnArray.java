package com.xjc.leetcode.editor.cn;

/**
 * 排序数组
 *
 * @date 2023-12-31 16:13:10
 */
public class SortAnArray {
    public static void main(String[] args) {
        // 题解
        Solution solution = new SortAnArray().new Solution();
        solution.sortArray(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6});
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] sortArray(int[] nums) {
            quickSort(nums, 0, nums.length - 1);
            return nums;
        }

        public void quickSort(int[] nums, int begin, int end) {
            if (begin >= end) {
                return;
            }
            int base = partition(nums, begin, end);
            quickSort(nums, begin, base - 1);
            quickSort(nums, base + 1, end);

        }

        public int partition(int[] nums, int begin, int end) {
            // 取末尾元素为基准值
            // 改为取随机值后，随机取下标，交换到末尾，其他不变
            int base = (int) (Math.random() * (end - begin) + begin);
            swap(nums, end, base);
            int baseValue = nums[end];
            int i = begin, j = end;
            while (i < j) {
                while (nums[i] <= baseValue && i < j) {
                    i++;
                }
                while (nums[j] >= baseValue && i < j) {
                    j--;
                }
                if (nums[i] > nums[j]) {
                    swap(nums, i, j);
                }
            }
            swap(nums, i, end);
            return i;
        }

        public void swap(int[] a, int i, int j) {
            int tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}