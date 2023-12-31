package singleton;

/**
 * 懒汉式
 * 线程不安全
 *
 * @author JC
 * @create 2023/12/31
 */
public class Mgr03 {
    private static Mgr03 INSTANCE;

    private Mgr03() {
    }

    public static Mgr03 getInstance() throws InterruptedException {
        if (INSTANCE == null) {
            Thread.sleep(1);
            INSTANCE = new Mgr03();
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    System.out.println(Mgr03.getInstance().hashCode());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

}
