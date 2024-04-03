package com.xjc.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 串联所有单词的子串
 *
 * @date 2024-03-30 19:54:35
 */
public class SubstringWithConcatenationOfAllWords {
    public static void main(String[] args) {
        // 题解
        Solution solution = new SubstringWithConcatenationOfAllWords().new Solution();
        solution.findSubstring("lingmindraboofooowingdingbarrwingmonkeypoundcake", new String[]{"fooo", "barr", "wing", "ding", "wing"});
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<Integer> findSubstring(String s, String[] words) {
            List<Integer> res = new ArrayList<>();
            int m = words.length, n = words[0].length();
            if (s.length() < m * n) {
                return res;
            }
            Map<Character, Integer> window = new HashMap<>();
            Map<Character, Integer> targetChar = new HashMap<>();
            List<String> wordsList = new ArrayList<>();
            for (String a : words) {
                wordsList.add(a);
                for (char b : a.toCharArray()) {
                    targetChar.put(b, targetChar.getOrDefault(b, 0) + 1);
                }
            }
            // 所有字符的个数满足要求
            int flagChar = 0;
            int l = 0, r = 0;
            while (r < s.length()) {
                char charR = s.charAt(r);
                if (targetChar.containsKey(charR)) {
                    window.put(charR, window.getOrDefault(charR, 0) + 1);
                    if (window.get(charR).equals(targetChar.get(charR))) {
                        flagChar++;
                    }
                }
                while (l <= r && flagChar == targetChar.size()) {
                    if (r - l + 1 == m * n) {
                        if (wordMatch(wordsList, n, s.substring(l, r + 1))) {
                            res.add(l);
                        }
                    }
                    char charL = s.charAt(l);
                    if (targetChar.containsKey(charL)) {
                        if (window.get(charL).equals(targetChar.get(charL))) {
                            flagChar--;
                        }
                        window.put(charL, window.getOrDefault(charL, 0) - 1);
                    }
                    l++;
                }
                r++;
            }
            return res;
        }

        // 按单词长度分割字符串，当前字符串是否和目标数组匹配
        private boolean wordMatch(List<String> wordsList, int wordLength, String s) {
            List<String> tmpList = new ArrayList<>(wordsList);
            for (int i = 0; i < s.length(); i += wordLength) {
                String tmp = s.substring(i, i + wordLength);
                if (tmpList.contains(tmp)) {
                    tmpList.remove(tmp);
                } else {
                    // 不包含单词可以直接返回
                    return false;
                }
            }
            return tmpList.size() == 0;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}