package com.xjc.leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 基本计算器
 *
 * @date 2024-03-31 18:31:09
 */
public class BasicCalculator {
    public static void main(String[] args) {
        // 题解
        Solution solution = new BasicCalculator().new Solution();
        System.out.println(solution.calculate("(1+(4+5+2)-3)+(6+8)"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int calculate(String s) {
            // 思路：先算乘除，再去括号， 栈里只剩加减，依次做计算
            // 入栈元素是乘除时：做计算操作,下一个元素可能是括号，所以不能直接取下一个元素计算，需要入栈后计算
            // 所以需要判断栈顶元素，而不是入栈元素
            // 入栈元素是右括号时，出栈并作计算，直到碰见左括号
            Deque<String> stack = new ArrayDeque<>();
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                if (' ' == s.charAt(i)) {
                    continue;
                }
                if (Character.isDigit(s.charAt(i))) {
                    // 数字需要追加
                    stringBuilder.append(s.charAt(i));
                    continue;
                }
                if (stringBuilder.length() > 0) {
                    // 非数字时，入栈并清空Stringbuilder
                    stack.push(stringBuilder.toString());
                    stringBuilder.setLength(0);
                }
                if (stack.isEmpty()) {
                    // 为空时直接入栈
                    stack.push(String.valueOf(s.charAt(i)));
                    continue;
                }
                // 乘除先计算
                if ("*".equals(stack.peek())) {
                    int tmp = Integer.parseInt(stack.pop()) * Integer.valueOf(s.charAt(i));
                    stack.push(Integer.toString(tmp));
                } else if ("/".equals(stack.peek())) {
                    int tmp = Integer.parseInt(stack.pop()) / Integer.valueOf(s.charAt(i));
                    stack.push(Integer.toString(tmp));
                } else if (')' == s.charAt(i)) {
                    // 处理括号内加减操作
                    operate(stack);
                } else {
                    // 可以直接入栈的符号 （ + -
                    stack.push(String.valueOf(s.charAt(i)));
                }
            }
            if (stringBuilder.length() > 0) {
                stack.push(stringBuilder.toString());
            }
            operate(stack);
            return Integer.parseInt(stack.pop());
        }

        // 处理括号内操作，或者最后的加减操作
        private void operate(Deque<String> stack) {
            int sum = 0;
            while (!stack.isEmpty() && !"(".equals(stack.peek())) {
                // 取出第一个肯定是数字
                int lastValue = Integer.parseInt(stack.pop());
                // 下一个可能是，+ ，- ，（ ，或者空
                if (stack.isEmpty()) {
                    sum += lastValue;
                } else if ("+".equals(stack.peek())) {
                    stack.pop();
                    sum += lastValue;
                } else if ("-".equals(stack.peek())) {
                    stack.pop();
                    sum -= lastValue;
                } else {
                    // ( 操作
                    stack.pop();
                    sum += lastValue;
                    break;
                }
            }
            // 减号前是括号的情况，需要在弹出一次
            if ("(".equals(stack.peek())) {
                stack.pop();
            }
            stack.push(Integer.toString(sum));
        }
    }


//leetcode submit region end(Prohibit modification and deletion)

}