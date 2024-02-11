package com.xjc.leetcode.editor.cn;

/**
 * 189. 轮转数组
 */
public class RotateArray {
    public static void main(String[] args) {
        // 题解
        Solution solution = new RotateArray().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public void rotate(int[] nums, int k) {
        int n = nums.length;
        // k 等于数组长度时，相当于没有移动，取余的结果是最后移动的次数
        if(k>= n){
            k = k % n;
        }
        if(k==0){
            return;
        }

        // 每次移动一组元素，这一组元素间隔k个位置,从第一个位置开始，放置正确的元素
        // 这一组元素个数由n和k决定，有可能一组元素就是整个数组，也有可能只是三个元素循环
        // 所以当遇到一组元素不是整个数组的情况时，要进行多次循环
        // 循环的次数可以由n、k决定，可以用一个计数变量，每移动一个元素+1，遍历结束时应该是除了每组第一个元素每个元素只移动一次,
        for(int i=0,conut =0;i<n && conut<n;i++){
            // 暂存第一个位置的元素
            int tmp = nums[i];
            // 位置i上的元素应该是 i-k,i-k为负数时，代表倒数第几个，可以直接给i+n再-k，然后对n进行取余
            int pre = (i+n-k)%n;
            int cur =i;
            // 遍历该组元素
            while(pre!=i){
                nums[cur]=nums[pre];
                cur =pre;
                pre=(pre+n-k)%n;
                conut++;
            }

            // 将暂存的元素放置到正确位置，题目中：把1放到4的位置,同时算作一次移动次数 +1
            nums[cur] = tmp;
            conut++;

        }
    }
    }
//leetcode submit region end(Prohibit modification and deletion)

}