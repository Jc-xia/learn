package com.xjc.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 最小覆盖子串
 *
 * @date 2024-03-25 23:44:32
 */
public class MinimumWindowSubstring {
    public static void main(String[] args) {
        // 题解
        Solution solution = new MinimumWindowSubstring().new Solution();
        solution.minWindow("aaaaaaaaaaaabbbbbcdd", "abcdd");
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String minWindow(String s, String t) {
            Map<Character, Integer> window = new HashMap<>();
            Map<Character, Integer> target = new HashMap<>();
            for (char c : t.toCharArray()) {
                Integer value = target.get(c) == null ? 0 : target.get(c);
                target.put(c, value + 1);
            }
            int left = 0, right = 0, flag = 0;
            int minLength = s.length() + 1, start = 0;
            while (right < s.length()) {
                char c = s.charAt(right);
                if (target.containsKey(c)) {
                    Integer value = window.get(c) == null ? 0 : window.get(c);
                    window.put(c, value + 1);
                    if (Objects.equals(window.get(c), target.get(c))) {
                        flag++;
                    }
                }
                while (left <= right && flag >= target.size()) {
                    if (right - left + 1 < minLength) {
                        minLength = right - left + 1;
                        start = left;
                    }
                    char c1 = s.charAt(left);
                    if (target.containsKey(c1)) {
                        window.put(c1, window.get(c1) - 1);
                        if (window.get(c1) <= target.get(c1)) {
                            flag--;
                        }
                    }
                    left++;
                }
                right++;
            }
            return minLength > s.length() ? "" : s.substring(start, start + minLength);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}