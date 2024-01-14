package com.xjc.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

/**
 * 计算右侧小于当前元素的个数
 *
 * @date 2024-01-14 20:33:29
 */
public class CountOfSmallerNumbersAfterSelf {
    public static void main(String[] args) {
        // 题解
        Solution solution = new CountOfSmallerNumbersAfterSelf().new Solution();
        solution.countSmaller(new int[]{5, 2, 6, 1});
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int[] res;
        int[] tmp;
        int[] index;
        int[] tmpIndex;

        public List<Integer> countSmaller(int[] nums) {
            res = new int[nums.length];
            tmp = new int[nums.length];
            index = new int[nums.length];
            tmpIndex = new int[nums.length];
            for (int i = 0; i < nums.length; ++i) {
                index[i] = i;
            }
            recurse(nums, 0, nums.length - 1);
            List<Integer> result = new ArrayList<>();
            for (int i : res) {
                result.add(i);
            }
            return result;
        }

        public void recurse(int[] nums, int begin, int end) {
            if (begin >= end) {
                return;
            }
            int mid = (begin + end) / 2;
            recurse(nums, begin, mid);
            recurse(nums, mid + 1, end);
            int i = begin;
            int j = mid + 1;
            int k = begin;
            while (i <= mid && j <= end) {
                // 计算左侧每个元素的右侧小于元素时，都要考虑右侧所有值。已经遍历过的也算。
                // 根据下标更新res ，从小到大
                if (nums[i] <= nums[j]) {
                    // 左侧元素小于右侧时，右侧当前元素前的所有元素小于左侧当前元素
                    res[index[i]] += (j - mid - 1);
                    tmpIndex[k] = index[i];
                    // 正常递归的操作
                    tmp[k++] = nums[i++];
                } else {
                    tmpIndex[k] = index[j];
                    tmp[k++] = nums[j++];
                }
            }
            while (i <= mid) {
                res[index[i]] += (j - mid - 1);
                tmpIndex[k] = index[i];
                tmp[k++] = nums[i++];
            }
            while (j <= end) {
                tmpIndex[k] = index[j];
                tmp[k++] = nums[j++];
            }
            // 更新原始数组
            for (int l = begin; l <= end; l++) {
                nums[l] = tmp[l];
                index[l] = tmpIndex[l];
            }

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}