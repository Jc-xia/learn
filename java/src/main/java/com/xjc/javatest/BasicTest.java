package com.xjc.javatest;

    import java.util.Objects;
/**
 * 基础语法测试
 *
 * @author JC
 * @create 2023/12/31
 */
public class BasicTest {

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
        String openId = "aaaa"; // replace "your_openId" with the actual openId value
        int hashCode = getHashCode(openId) % 100;
        System.out.println(hashCode);
    }

    public static int getHashCode(String str) {
        if (Objects.isNull(str) || str.isEmpty()) return 0;
        int hash = 0;
        for (int i = 0; i < str.length(); i++) {
            char character = str.charAt(i);
            hash = ((hash << 5) - hash) + (int) character;
            hash &= hash;
        }
        hash = Math.abs(hash);
        System.out.println(hash);
        return hash;
    }


}
