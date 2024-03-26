package com.xjc.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

/**
 * 无重复字符的最长子串
 *
 * @date 2024-03-26 23:31:51
 */
public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        // 题解
        Solution solution = new LongestSubstringWithoutRepeatingCharacters().new Solution();
        solution.lengthOfLongestSubstring("tmmzuxt");
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int lengthOfLongestSubstring(String s) {
            Map<Character, Integer> window = new HashMap<>();
            int max = 0;
            int left = 0, right = 0;
            while (right < s.length()) {
                char charR = s.charAt(right);
                window.put(charR, window.getOrDefault(charR, 0) + 1);
                if (window.get(charR) > 1) {
                    while (left <= right && window.get(charR) > 1) {
                        char charL = s.charAt(left);
                        left++;
                        window.put(charL, window.getOrDefault(charL, 0) - 1);
                    }
                }
                max = Math.max(max, right - left + 1);
                right++;
            }
            return max;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}