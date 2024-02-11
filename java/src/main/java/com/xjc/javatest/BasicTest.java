package com.xjc.javatest;

/**
 * 基础语法测试
 *
 * @author JC
 * @create 2023/12/31
 */
public class BasicTest {
    public static void main(String[] args) {
        int[] nums = {-5, -5};
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                // 二倍超出32位整数最大值
                if (Integer.MAX_VALUE - nums[j] < nums[j]) {
                    continue;
                }

                if (nums[i] > 2 * nums[j]) {
                    res++;
                }
            }
        }
    }
}
