package com.xjc.leetcode.editor.cn;

/**
 * 数组中的第K个最大元素
 *
 * @date 2024-02-25 17:53:51
 */
public class KthLargestElementInAnArray {
    public static void main(String[] args) {
        // 题解
        Solution solution = new KthLargestElementInAnArray().new Solution();
        System.out.println(solution.findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int findKthLargest(int[] nums, int k) {
            return quickSort(nums, 0, nums.length - 1, k);
        }

        public int quickSort(int[] nums, int begin, int end, int k) {
            if (begin > end) {
                return 0;
            }
            if (begin == end && begin + 1 == k) {
                return nums[begin];
            }
            int base = partition(nums, begin, end);
            if (base + 1 == k) {
                return nums[base];
            } else if (base + 1 > k) {
                return quickSort(nums, begin, base - 1, k);
            } else {
                return quickSort(nums, base + 1, end, k);
            }

        }

        public int partition(int[] nums, int begin, int end) {
            int baseValue = nums[end];
            int i = begin, j = end;
            while (i < j) {
                while (nums[i] >= baseValue && i < j) {
                    i++;
                }
                while (nums[j] <= baseValue && i < j) {
                    j--;
                }
                swap(nums, i, j);
            }
            swap(nums, i, j);
            return i;
        }

        public void swap(int[] nums, int i, int j) {
            if (nums[i] == nums[j]) {
                return;
            }
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)

}