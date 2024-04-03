package com.xjc.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 字符串的排列
 *
 * @date 2024-03-30 15:36:50
 */
public class MPnaiL {
    public static void main(String[] args) {
        // 题解
        Solution solution = new MPnaiL().new Solution();
        solution.checkInclusion("ab", "eidbaooo");
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean checkInclusion(String s1, String s2) {
            Map<Character, Integer> window = new HashMap<>();

            Map<Character, Integer> target = new HashMap<>();
            for (char c : s1.toCharArray()) {
                target.put(c, target.getOrDefault(c, 0) + 1);
            }

            int l = 0, r = 0;
            int flag = 0;
            while (r < s2.length()) {
                char charR = s2.charAt(r);
                // 这里可以不用判断，为了提升效率，所以判断
                if (target.containsKey(charR)) {
                    window.put(charR, target.getOrDefault(charR, 0) + 1);
                    if (Objects.equals(window.get(charR), target.get(charR))) {
                        flag++;
                    }
                }
                while (l <= r && flag >= target.size()) {
                    if (r - l + 1 == s1.length()) {
                        return true;
                    }
                    char charL = s2.charAt(l);
                    if (target.containsKey(charL)) {
                        // 相等的时候，再-1就不满足条件了，所以把入窗口的操作完全反过来就行，包括代码顺序和加减操作
                        if (Objects.equals(window.get(charL), target.get(charL))) {
                            flag--;
                        }
                        window.put(charL, target.getOrDefault(charL, 0) - 1);
                    }
                    l++;
                }
                r++;
            }
            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}