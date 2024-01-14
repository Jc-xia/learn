package com.xjc.leetcode.editor.cn;

/**
 * 交易逆序对的总数
 *
 * @date 2024-01-14 12:02:45
 */
public class ShuZuZhongDeNiXuDuiLcof {
    public static void main(String[] args) {
        // 题解
        Solution solution = new ShuZuZhongDeNiXuDuiLcof().new Solution();
        solution.reversePairs(new int[]{5, 4, 3, 2, 1});
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 核心思路，问题分解，当前数组逆序对的个数等于左边子数组逆序对个数+右边逆序对个数+ 左边相对于的逆序对个数
         * 也就是，左边递归 ，右边递归，递归后计算左右比较的
         */
        public int reversePairs(int[] record) {
            // 排序后的数组
            int[] tmp = record.clone();
            return recurse(record, 0, record.length - 1, tmp);

        }

        public int recurse(int[] record, int begin, int end, int[] tmp) {
            if (begin >= end) {
                return 0;
            }
            int res = 0;
            int mid = (begin + end) / 2;
            int left = recurse(record, begin, mid, tmp);
            int right = recurse(record, mid + 1, end, tmp);
            // 合并左右，从小到大
            int i = begin;
            int j = mid + 1;
            int k = begin;
            while (i <= mid && j <= end) {
                if (record[i] > record[j]) {
                    // 逆序对， i右侧的每个元素每一个都比右边大
                    res = res + (mid - i + 1);
                    tmp[k] = record[j];
                    j++;
                } else {
                    tmp[k] = record[i];
                    i++;
                }
                k++;
            }
            while (i <= mid) {
                tmp[k] = record[i];
                i++;
                k++;
            }
            while (j <= end) {
                tmp[k] = record[j];
                j++;
                k++;
            }
            // 排好序的部分 赋值到原数组，下次递归使用
            for (int l = begin; l <= end; l++) {
                record[l] = tmp[l];
            }
            return res + left + right;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}