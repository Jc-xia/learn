package singleton;

/**
 * 静态内部类方式
 * jvm保证线程安全
 * 加载外部类时不会加载内部类，可以实现懒加载
 *
 * @author JC
 * @create 2023/12/31
 */
public class Mgr07 {

    private Mgr07() {
    }

    private static class Mgr07Inner {
        private static final Mgr07 INSTANCE = new Mgr07();
    }

    public static Mgr07 getInstance() throws InterruptedException {

        return Mgr07Inner.INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    System.out.println(Mgr07.getInstance().hashCode());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

}
