package com.xjc.singleton;

/**
 * 静态内部类方式
 * jvm保证线程安全
 * 加载外部类时不会加载内部类，可以实现懒加载
 *
 * @author JC
 * @create 2023/12/31
 */
public enum Mgr08 {

    INSTANCE;

    public void method() {
        System.out.println("Mgr08 method sout");
    }

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(Mgr08.INSTANCE.hashCode());
            }).start();
        }
        System.out.println(Mgr08.INSTANCE instanceof Mgr08);
        Mgr08.INSTANCE.method();
    }

}
