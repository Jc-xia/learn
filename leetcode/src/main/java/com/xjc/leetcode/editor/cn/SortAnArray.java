package com.xjc.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

/**
 * 排序数组
 *
 * @date 2023-12-31 16:13:10
 */
public class SortAnArray {
    public static void main(String[] args) {
        // 题解
        Solution solution = new SortAnArray().new Solution();
        solution.sortArray(new int[]{5, 2, 3, 1});
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] sortArray(int[] nums) {
            List<Integer> list = mergeSort(nums, 0, nums.length - 1);

            return list.stream().mapToInt(Integer::valueOf).toArray();
        }

        public List<Integer> mergeSort(int[] nums, int begin, int end) {
            List<Integer> res = new ArrayList<>();
            if (begin > end) {
                return res;
            } else if (begin == end) {
                res.add(nums[begin]);
                return res;
            }
            int mid = (begin + end) / 2;
            List<Integer> left = mergeSort(nums, begin, mid);
            List<Integer> right = mergeSort(nums, mid + 1, end);
            int i = 0, j = 0;

            while (i < left.size() && j < right.size()) {
                if (left.get(i) <= right.get(j)) {
                    res.add(left.get(i));
                    i++;
                } else {
                    res.add(right.get(j));
                    j++;
                }
            }
            while (i < left.size()) {
                res.add(left.get(i));
                i++;
            }
            while (j < right.size()) {
                res.add(right.get(j));
                j++;
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}