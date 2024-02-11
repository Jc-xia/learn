package com.xjc.singleton;

/**
 * 懒汉式
 * 代码块加锁，线程不安全
 *
 * @author JC
 * @create 2023/12/31
 */
public class Mgr05 {
    private static Mgr05 INSTANCE;

    private Mgr05() {
    }

    public static Mgr05 getInstance() throws InterruptedException {
        if (INSTANCE == null) {
            synchronized (Mgr05.class) {
                Thread.sleep(1);
                INSTANCE = new Mgr05();
            }
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    System.out.println(Mgr05.getInstance().hashCode());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

}
