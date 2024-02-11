package com.xjc.javatest;

import java.util.ArrayList;
import java.util.List;

/**
 * 引用传递 or 值传递
 * java只有值传递 <a href="https://zhuanlan.zhihu.com/p/388486387">Java 中到底有没有引用传递</a>
 * 引用类型的变量，作为方法参数传递时，会复制一个变量，指向相同的地址
 * 如果在方法内对这个变量整个重新赋值，不会改变原来变量。
 * 如果在方法内调用变量的成员方法进行成员变量值的修改，相当于修改该引用地址的内的数值，会改变原来的变量
 * 这个时候如果成员变量是final的，则修改不会生效（参考String、Integer等）
 *
 * @author JC
 * @create 2023/12/24
 */
public class PassByRefer {
    static List<Integer> a = new ArrayList<>();
    static Integer a1 = 0;

    static String a2 = "a2";

    public static void main(String[] args) {
        modifyList(a);
        System.out.println(a);

        modifyInteger(a1);
        System.out.println(a1);

        modifyString(a2);
        System.out.println(a2);

        System.out.println("Hello world!");
    }

    public static void modifyList(List<Integer> b) {

        ArrayList<Integer> objects = new ArrayList<>();
        objects.add(1);
        b = objects;
        System.out.println(b);
    }

    public static void modifyInteger(Integer b1) {
        b1 = 1;
        System.out.println(b1);
    }

    public static void modifyString(String b2) {
        b2 = "b2";
        System.out.println(b2);
    }
}
